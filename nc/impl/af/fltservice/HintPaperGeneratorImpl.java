package nc.impl.af.fltservice;

import java.io.Serializable;
import java.util.*;
import java.util.Map.Entry;

import nc.bs.dao.BaseDAO;
import nc.impl.af.assembly.TrolleyProviderImpl;
import nc.itf.af.fltservice.IHintPaperGenerator;
import nc.pub.af.kit.AFLogger;
import nc.pub.af.utils.AFUtil;
import nc.pub.af.utils.BaseDocUtil;
import nc.pub.af.utils.MaterialVO;
import nc.pub.af.utils.R;
import nc.vo.af.assembly_details.DeviceItem;
import nc.vo.af.assembly_details.FoodItem;
import nc.vo.af.assembly_details.TrolleyItem;
import nc.vo.af.fltservice.AggFltserviceVO;
import nc.vo.af.fltservice.Fltservice_defoodVO;
import nc.vo.af.trolleyhintpaper.AggTrolleyHintPaperHVO;
import nc.vo.af.trolleyhintpaper.TrolleyHintPaperBVO;
import nc.vo.af.trolleyhintpaper.TrolleyHintPaperHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.mutable.MutableInt;


/**
 * 餐牌打印
 * @author 蓝剑樟
 * @version Create at 2017/3
 */
public class HintPaperGeneratorImpl implements IHintPaperGenerator {

    private BaseDAO baseDAO;

    public BaseDAO getBaseDAO() {
        if (null == baseDAO) {
            baseDAO = new BaseDAO();
        }
        return baseDAO;
    }

    private List<FoodItem> installedResults = null;
    private List<TrolleyItem> origRepos = new ArrayList<TrolleyItem>();
    private List<TrolleyItem> extraReposList;
    private Set<String> spaces;
    private Map<String, AggTrolleyHintPaperHVO> deviceIDPaperMap;
    private List<FoodItem> extraItems;
    private Map<String, String> legMap;
    private String airplaneNbr;
    private Map<String, String> modelMap;
    private String connRepos;
    private Map<String, Map<String, Map<String, DeviceItem>>> devices;

    Map<String, String> getLegMap(AggFltserviceVO aggVO) {
        if (null == legMap || legMap.isEmpty()) {
            legMap = getLegName(aggVO.getParentVO().getPk_legofavoyage());
        }
        return legMap;
    }

    public AggTrolleyHintPaperHVO[] generate(AggFltserviceVO[] aggVOs) throws BusinessException {
        installedResults = new ArrayList<FoodItem>();
        extraReposList = new ArrayList<TrolleyItem>();
        extraItems = new ArrayList<FoodItem>();
        legMap = null;
        airplaneNbr = null;
        modelMap = null;
        connRepos = null;
        spaces = new LinkedHashSet<String>();
        deviceIDPaperMap = new HashMap<String, AggTrolleyHintPaperHVO>();
        devices = null;
//        IRecipeAnalysis analyser = NCLocator.getInstance().lookup(IRecipeAnalysis.class);
        RecipeAnalysisImpl analyser = new RecipeAnalysisImpl();
//        ITrolleyProvider provider = NCLocator.getInstance().lookup(ITrolleyProvider.class);
        TrolleyProviderImpl provider = new TrolleyProviderImpl();

        for (AggFltserviceVO aggVO : aggVOs) {
            Map<String, List<FoodItem>> map = analyser.analyze(new AggFltserviceVO[]{aggVO});
            if (null == map || map.isEmpty()) {
                throw new BusinessException("未能解释出餐食，请检查该单据的配餐信息。");
            }
            if (null != map && !map.isEmpty()) {
                boolean isEmpty = true;
                for (List<FoodItem> itemList : map.values()) {
                    if (null != itemList && !itemList.isEmpty()) {
                        isEmpty = false;
                        break;
                    }
                }
                if (isEmpty) {
                    throw new BusinessException("未能解释出餐食，请检查该单据的配餐信息。");
                }
            }
            String pkAirplane = aggVO.getParentVO().getPk_airplane();
            String pkModel = aggVO.getParentVO().getPk_airplanemodel();
            Fltservice_defoodVO[] defoodVOs = aggVO.getFltservice_defoodVO();
            UFDateTime validTime = aggVO.getParentVO().getDt_plandeparturetime();
            String pkFltno = aggVO.getParentVO().getPk_fltno();
            List<TrolleyItem> repos = provider.getRepos(pkAirplane, pkModel, pkFltno, validTime);
            if (repos.isEmpty()) {
                throw new BusinessException("未能获取餐车信息，请检查该单据对应的飞机号、餐谱对应的装配布局图及其对应的装车明细图。");
            }
            origRepos = (List<TrolleyItem>) AFUtil.clone((ArrayList<TrolleyItem>)repos);
            devices = provider.analyseRepos(repos);
            Set<String> spaceList = devices.keySet();
            for (TrolleyItem reposItem : origRepos) {
                spaces.add(reposItem.getSpace());
            }
            Map<String, List<FoodItem>> extraMap = new HashMap<String, List<FoodItem>>();
            Map<String, List<FoodItem>> outExtraMap = new HashMap<String, List<FoodItem>>();
            for (String space : spaceList) {
                for (String deviceType : devices.get(space).keySet()) {
                    repos = assembly(repos, map, extraMap, space, deviceType);
                    extraMerge(outExtraMap, extraMap);
                }
            }
            assemblyFreeForSpecial(repos, map, devices);
            extraMerge(outExtraMap, map);
            assemblyEmpty(repos, outExtraMap, false);
//            assemblyNormalEmpty(extraMap);
            assemblyEmptyForSpecial(repos, map);
            return generatePapers(repos, installedResults, aggVO);
        }
        return null;
    }

    private void assemblyNormalEmpty(Map<String, List<FoodItem>> map) {
        Collection<List<FoodItem>> itemValues = map.values();
        for (List<FoodItem> items  : itemValues) {
            for (FoodItem foodItem : items) {
                if (!foodItem.getBisspecial().booleanValue() && !foodItem.isInstalledPortion()) {
                    extraItems.add(foodItem);
                }
            }
        }
    }

    private void assemblyFreeForSpecial(List<TrolleyItem> repos, Map<String, List<FoodItem>> map,
                                        Map<String, Map<String, Map<String, DeviceItem>>> devices) {
        for (TrolleyItem reposItem : repos) {
            if (reposItem.isFree() && !reposItem.isSpecicalInstalled() && reposItem.getInstalledIDs().isEmpty()
                    && !reposItem.isVirtual()) {
                reposItem.setSpecial(true);
            }
        }
        Set<String> spaceList = devices.keySet();
        for (String space : spaceList) {
            for (String deviceType : devices.get(space).keySet()) {
                assemblyForSpecialLast(repos, map, space, deviceType);
            }
        }
    }

    private void updatecRowNo(List<AggTrolleyHintPaperHVO> destVOsList) {
        for (AggTrolleyHintPaperHVO aggVO : destVOsList) {
            TrolleyHintPaperBVO[] bVOs = (TrolleyHintPaperBVO[]) aggVO.getChildrenVO();
            Integer crowno = 10;
            for (TrolleyHintPaperBVO bVO : bVOs) {
                bVO.setCrowno(crowno.toString());
                crowno += 10;
            }
        }
    }

    private AggTrolleyHintPaperHVO[] generatePapers(List<TrolleyItem> repos, List<FoodItem> installResults,
                                                    AggFltserviceVO aggVO) throws BusinessException {
        Map<String, AggTrolleyHintPaperHVO> papers = new HashMap<String, AggTrolleyHintPaperHVO>();
        List<AggTrolleyHintPaperHVO> destVOsList = new ArrayList<AggTrolleyHintPaperHVO>();
        repos.addAll(extraReposList);
        for (TrolleyItem reposItem : repos) {
            String deviceID = reposItem.getDeviceID();
            AggTrolleyHintPaperHVO aggPVO = null;
            if (papers.containsKey(deviceID)) {
                aggPVO = queryPaperVO(deviceID);
            } else {
                aggPVO = new AggTrolleyHintPaperHVO();
                papers.put(deviceID, aggPVO);
            }
            boolean isNewHVO = false;
            TrolleyHintPaperHVO hPVO = aggPVO.getParentVO();
            if (null == hPVO) {
                hPVO = new TrolleyHintPaperHVO();
                isNewHVO = true;
//                hPVO.setStatus(VOStatus.NEW);
                hPVO.setVnote(reposItem.getNote());
//                hPVO.setCreationtime(new UFDateTime());
                hPVO.setDbilldate(new UFDate());
//                hPVO.setDdate(new UFDate(aggVO.getParentVO().getDdate().toDate()));
                hPVO.setFstatusflag(-1);
                if (reposItem.isSpecicalInstalled()) {
                    hPVO.setBisspecialinstalled(UFBoolean.TRUE);
                }
                hPVO.setPapertype("1");//TODO 有特殊餐则为2
//                hPVO.setPk_group(aggVO.getParentVO().getPk_group());
//                hPVO.setPk_org(aggVO.getParentVO().getPk_org());
//                hPVO.setPk_org_v(aggVO.getParentVO().getPk_org_v());
                hPVO.setTdeparturestm(aggVO.getParentVO().getDt_plandeparturetime());
                hPVO.setVairplanenbr(getAirplaneNbr(aggVO.getParentVO().getPk_airplane()));
                hPVO.setVfltno(getFltnoCode(aggVO.getParentVO().getPk_fltno()));
                //				hPVO.setVfood(vfood) //TODO 因为存在一配餐放在表体
                hPVO.setVkitchen(getKitchenName(reposItem.getKitchen()));
                legMap = getLegName(aggVO.getParentVO().getPk_legofavoyage());
                hPVO.setVleg(legMap==null?"":legMap.get("name"));
                //TODO 英文名
                //hPVO.setVlegEnname(legMap==null?"":legMap.get("enname"));
//                hPVO.setVmodel(getModel(aggVO).get("name"));
                //TODO 用code还是用name 是个问题
                hPVO.setVplanserver(reposItem.getPlanServer());
                hPVO.setVplanseat(reposItem.getPlanSeat());
                hPVO.setVtrolleymodel(BaseDocUtil.getMaterialVO(reposItem.getDeviceType()).getName());
                hPVO.setVtrolleynbr(reposItem.getDeviceNbr());
//                hPVO.setVrepos(getConnRepos(aggVO));
                //			hPVO.setPk_trolleyhintpaper_h(getDBTool().getOIDs(1)[0]);
//			} else {
            }
            if (reposItem.isSpecicalInstalled()) {
                hPVO.setBisspecialinstalled(UFBoolean.TRUE);
            }
            if (isNewHVO) {
                aggPVO.setParentVO(hPVO);
            }
            deviceIDPaperMap.put(deviceID, aggPVO);

            //这里新建表体
            //TODO 要合并表体
            Set<String> installedIDs = reposItem.getInstalledIDs();
            TrolleyHintPaperBVO[] vos = new TrolleyHintPaperBVO[installedIDs.size()];
            FoodItem result = null;
            int index = -1;
            for (String installedID : installedIDs) {
                result = findResults(installResults, installedID);
                TrolleyHintPaperBVO vo = new TrolleyHintPaperBVO();
//                vo.setStatus(VOStatus.NEW);
                vo.setPk_defood(result.getShortDefoodKey());
                vo.setVdefood(getDefoodName(result.getShortDefoodKey()));
                vo.setPk_material(result.getPkMaterial());
                vo.setVitem(BaseDocUtil.getMaterialVO(result.getPkMaterial()).getName());
                vo.setVquantity(result.getInstalled().toString());
//                vo.setCsrcid(aggVO.getParentVO().getPk_fltservice_h());
                vos[++index] = vo;
            }
            TrolleyHintPaperBVO[] oVOs = (TrolleyHintPaperBVO[]) aggPVO.getChildrenVO();
            TrolleyHintPaperBVO[] mergeVOs = mergeBVOs(oVOs, vos);
            aggPVO.setChildrenVO(mergeVOs);
            if (((null != mergeVOs && mergeVOs.length > 0)||!StringUtils.isBlank(aggPVO.getParentVO().getVnote()))
                    && !destVOsList.contains(aggPVO))
                destVOsList.add(aggPVO);
        }
        if (null != extraItems && extraItems.size() > 0) {
            AggTrolleyHintPaperHVO aggPVO = new AggTrolleyHintPaperHVO();
            TrolleyHintPaperHVO hPVO = new TrolleyHintPaperHVO();
            hPVO.setVnote("此打印单餐食为超售而未能安装餐食");
//            hPVO.setStatus(VOStatus.NEW);
//            hPVO.setCreationtime(new UFDateTime());
//            hPVO.setDbilldate(new UFDate());
//            hPVO.setDdate(new UFDate(aggVO.getParentVO().getDdate().toDate()));
            hPVO.setFstatusflag(-1);
//            hPVO.setPk_group(aggVO.getParentVO().getPk_group());
//            hPVO.setPk_org(aggVO.getParentVO().getPk_org());
//            hPVO.setPk_org_v(aggVO.getParentVO().getPk_org_v());
            hPVO.setTdeparturestm(aggVO.getParentVO().getDt_plandeparturetime());
            hPVO.setVairplanenbr(getAirplaneNbr(aggVO.getParentVO().getPk_airplane()));
            hPVO.setVfltno(getFltnoCode(aggVO.getParentVO().getPk_fltno()));
            legMap = getLegName(aggVO.getParentVO().getPk_legofavoyage());
            hPVO.setVleg(legMap==null?"":legMap.get("name"));
//            hPVO.setVmodel(getModel(aggVO).get("name"));
//            hPVO.setVrepos(getConnRepos(aggVO));
            aggPVO.setParentVO(hPVO);
            for (FoodItem extraItem : extraItems) {
                TrolleyHintPaperBVO vo = new TrolleyHintPaperBVO();
                vo.setPk_defood(extraItem.getShortDefoodKey());
                vo.setVdefood(getDefoodName(extraItem.getShortDefoodKey()));
                vo.setPk_material(extraItem.getPkMaterial());
                MaterialVO materialVO = BaseDocUtil.getMaterialVO(extraItem.getPkMaterial());
                String materialName = null != materialVO ? materialVO.getName() : extraItem.getPkMaterial();
                vo.setVitem(materialName);
                vo.setVquantity(extraItem.getRemain().toString());
                TrolleyHintPaperBVO[] oVOs = (TrolleyHintPaperBVO[]) aggPVO.getChildrenVO();
                TrolleyHintPaperBVO[] mergeBVOs = mergeBVOs(oVOs, new TrolleyHintPaperBVO[] { vo });
                aggPVO.setChildrenVO(mergeBVOs);
            }
            destVOsList.add(aggPVO);
        }
        if (null != destVOsList && destVOsList.size() > 0) {
            updatecRowNo(destVOsList);
//            removePrevItems(aggVO.getParentVO().getPk_fltservice_h(), new String[]{"2", "1"});
            AFLogger.info("save");
//            operator.processBatch("SAVEBASE", "HD37", destVOsList.toArray(new AggTrolleyHintPaperHVO[destVOsList.size()]), null, null, null);
        }
        return destVOsList.toArray(new AggTrolleyHintPaperHVO[destVOsList.size()]);
    }

    private TrolleyHintPaperBVO[] mergeBVOs(TrolleyHintPaperBVO[] oVOs, TrolleyHintPaperBVO[] vos) {
        if (null == oVOs || oVOs.length == 0) {
            return vos;
        }
        for (TrolleyHintPaperBVO vo : vos) {
            String pkDefood = vo.getPk_defood();
            String pkMaterial = vo.getPk_material();
            boolean isExists = false;
            if (oVOs.length > 0)
                for (TrolleyHintPaperBVO oVO : oVOs) {
                    if (oVO.getPk_defood().equals(pkDefood) && oVO.getPk_material().equals(pkMaterial)) {
                        isExists = true;
                        UFDouble oQuantity = new UFDouble(oVO.getVquantity());
                        UFDouble quantity = new UFDouble(vo.getVquantity());
                        oVO.setVquantity(oQuantity.add(quantity).toString());
                    }
                }
            if (!isExists) {
                List<TrolleyHintPaperBVO> oVOsList = new ArrayList<TrolleyHintPaperBVO>(Arrays.asList(oVOs));
                oVOsList.add(vo);
                oVOs = oVOsList.toArray(new TrolleyHintPaperBVO[oVOsList.size()]);
            }
        }
        return oVOs;
    }

    private AggTrolleyHintPaperHVO queryPaperVO(String deviceID) {
        return deviceIDPaperMap.get(deviceID);
    }

    private String getDefoodName(String shortDefoodKey) {
        return shortDefoodKey;
    }

    private FoodItem findResults(List<FoodItem> installResults, String installedID) throws BusinessException {
        for (FoodItem result : installResults) {
            if (result.getUniqueID().equals(installedID)) {
                return result;
            }
        }
        throw new BusinessException("unique ID error");
    }
    private Map<String, String> getModel(AggFltserviceVO aggVO) {
        return modelMap;
    }
    private Map<String, String> getLegName(String leg) {
        return null;
    }

    private String getKitchenName(String kitchen) {
        return null;
    }
    private String getFltnoCode(String pk_fltno) {
        return null;
    }
    private String getAirplaneNbr(String pk_airplane) {
        return airplaneNbr;
    }

    private List<TrolleyItem> assemblyForSpecialFirst(List<TrolleyItem> repos, Map<String, List<FoodItem>> foodItems,
                                                      String space, String deviceType) {
        int indexOfRepos = -1;
        List<TrolleyItem> copyRepos = (List<TrolleyItem>) AFUtil
                .clone((ArrayList<TrolleyItem>) repos);
        int specialItemsNeed = calculateSpecItemNeed(foodItems, space, deviceType); /** 區分 热/餐 **/
        for (TrolleyItem reposItem : copyRepos) {
            indexOfRepos++;
            if (reposItem.isSpecialPriority()
                    && space.equals(reposItem.getSpace()) && getDeviceType(reposItem).equals(deviceType)) {
                if (specialItemsNeed-- > 0) {
                    repos = shiftForSpecial(repos, indexOfRepos, space, deviceType);
                    AFLogger.info("shift end");
                } else {
                    break;
                }
            }
        }
        for (TrolleyItem reposItem : repos) {
            Collection<List<FoodItem>> itemValues = foodItems.values();
            for (List<FoodItem> items : itemValues) {
                for (FoodItem foodItem : items) {
                    if (foodItem.getBisspecial().booleanValue()
                            && reposItem.isSpecial()
                            && foodItem.getPkSpace().equals(
                            reposItem.getSpace())
                            && !reposItem.isSpecicalInstalled()
                            && foodItem.getPkSpace().equals(space)
                            && foodItem.getRemain()
                            .compareTo(UFDouble.ZERO_DBL) > 0
                            && reposItem.isForHot() && foodItem.getBishot().booleanValue()
                            && getDeviceType(reposItem).equals(deviceType)) {
                        AFLogger.info("trolley "
                                + reposItem.getDeviceNbr()
                                + " "
                                + reposItem.getStartIndex()
                                + (reposItem.gethPart().equals("1") ? " A "
                                : " B ") + " installed "
                                + reposItem.isFree()
                                + foodItem.getCategory()
                                + foodItem.getPkMaterial() + " "
                                + foodItem.getRemain());
                        reposItem.installSpecial(foodItem);
                        installedResults.add(foodItem);
                    }
                    if (foodItem.getBisspecial().booleanValue()
                            && reposItem.isSpecial()
                            && foodItem.getPkSpace().equals(
                            reposItem.getSpace())
                            && !reposItem.isSpecicalInstalled()
                            && foodItem.getPkSpace().equals(space)
                            && foodItem.getRemain()
                            .compareTo(UFDouble.ZERO_DBL) > 0
                            && !reposItem.isForHot() && !foodItem.getBishot().booleanValue()
                            && getDeviceType(reposItem).equals(deviceType)) {
                        AFLogger.info("trolley "
                                + reposItem.getDeviceNbr()
                                + " "
                                + reposItem.getStartIndex()
                                + (reposItem.gethPart().equals("1") ? " A "
                                : " B ") + " installed "
                                + reposItem.isFree()
                                + foodItem.getCategory()
                                + foodItem.getPkMaterial() + " "
                                + foodItem.getRemain());
                        reposItem.installSpecial(foodItem);
                        installedResults.add(foodItem);
                    }
                }
            }
        }
        if (hasSpecialFoodItems(foodItems, space, deviceType)) {
            return assemblyEmpty(repos, foodItems, true, space, deviceType);
        }
        return repos;
    }

    private String getDeviceType(TrolleyItem item) {
        return item.isForHot() ? "true" : "false";
    }

    private int calculateSpecItemNeed(Map<String, List<FoodItem>> foodItems,
                                      String space, String deviceType) {
        boolean isForHot = deviceType.equals("true");
        int ret = 0;
        Collection<List<FoodItem>> itemValues = foodItems.values();
        for (List<FoodItem> items : itemValues) {
            for (FoodItem foodItem : items) {
                if (foodItem.getBisspecial().booleanValue()
                        && foodItem.getPkSpace().equals(space)
                        && foodItem.getBishot().booleanValue() == isForHot) {
                    ret++;
                }
            }
        }
        return ret;
    }

    private List<TrolleyItem> assemblyForSpecialLast(List<TrolleyItem> repos, Map<String, List<FoodItem>> foodItems,
                                                     String space, String deviceType) {
        for (TrolleyItem reposItem : repos) {
            if (deviceType.equals(getDeviceType(reposItem)) && space.equals(reposItem.getSpace())) {
                Collection<List<FoodItem>> itemValues = foodItems.values();
                for (List<FoodItem> items : itemValues) {
                    for (FoodItem foodItem : items) {
                        if (foodItem.getBisspecial().booleanValue()
                                && reposItem.isSpecial()
                                && foodItem.getPkSpace().equals(
                                reposItem.getSpace())
                                && !reposItem.isSpecicalInstalled()
                                && foodItem.getPkSpace().equals(space)
                                && foodItem.getRemain()
                                .compareTo(UFDouble.ZERO_DBL) > 0
                                && reposItem.isForHot() && foodItem.getBishot().booleanValue()) {
                            AFLogger.info("trolley "
                                    + reposItem.getDeviceNbr()
                                    + " "
                                    + reposItem.getStartIndex()
                                    + (reposItem.gethPart().equals("1") ? " A "
                                    : " B ") + " installed "
                                    + reposItem.isFree()
                                    + foodItem.getCategory()
                                    + foodItem.getPkMaterial() + " "
                                    + foodItem.getRemain());
                            reposItem.installSpecial(foodItem);
                            installedResults.add(foodItem);
                        }
                        if (foodItem.getBisspecial().booleanValue()
                                && reposItem.isSpecial()
                                && foodItem.getPkSpace().equals(
                                reposItem.getSpace())
                                && !reposItem.isSpecicalInstalled()
                                && foodItem.getPkSpace().equals(space)
                                && foodItem.getRemain()
                                .compareTo(UFDouble.ZERO_DBL) > 0
                                && !reposItem.isForHot() && !foodItem.getBishot().booleanValue()) {
                            AFLogger.info("trolley "
                                    + reposItem.getDeviceNbr()
                                    + " "
                                    + reposItem.getStartIndex()
                                    + (reposItem.gethPart().equals("1") ? " A "
                                    : " B ") + " installed "
                                    + reposItem.isFree()
                                    + foodItem.getCategory()
                                    + foodItem.getPkMaterial() + " "
                                    + foodItem.getRemain());
                            reposItem.installSpecial(foodItem);
                            installedResults.add(foodItem);
                        }
                    }
                }
            }
        }
        return repos;
    }

    private List<TrolleyItem> assembly(List<TrolleyItem> repos, Map<String, List<FoodItem>> foodItems, String space,
                                       String deviceType) throws BusinessException {
        Map<String, List<FoodItem>> extraMap = new HashMap<String, List<FoodItem>>();
        return assembly(repos, foodItems, extraMap, space, deviceType);
    }

    private List<TrolleyItem> assembly(List<TrolleyItem> repos, Map<String, List<FoodItem>> foodItems,
                                       Map<String, List<FoodItem>> extraMap, String space, String deviceType)
            throws BusinessException {
        boolean hasSpecSpace = hasSpecSpace(devices, space, deviceType);
        int order = getSpecSpaceOrder(devices, space, deviceType);
        if (order == 1) {
            repos = assemblyForSpecialFirst(repos, foodItems, space, deviceType);
            repos = assemblyNormal(repos, foodItems, extraMap, space, deviceType);
        } else if (order == -1) {
            repos = preserveSpecialNeed(repos, foodItems, space, deviceType);
            repos = assemblyNormal(repos, foodItems, extraMap, space, deviceType);
            repos = assemblyForSpecialLast(repos, foodItems, space, deviceType);
        } else if (hasSpecSpace) {
            repos = preserveSpecialNeed(repos, foodItems, space, deviceType);
            repos = assemblyNormal(repos, foodItems, extraMap, space, deviceType);
            repos = assemblyForSpecialLast(repos, foodItems, space, deviceType);
        } else {
            repos = preserveSpecialNeed(repos, foodItems, space, deviceType);
            repos = assemblyNormal(repos, foodItems, extraMap, space, deviceType);
            repos = assemblyForSpecialLast(repos, foodItems, space, deviceType);
        }
        return repos;
    }

    private List<TrolleyItem> preserveSpecialNeed(List<TrolleyItem> repos, Map<String, List<FoodItem>> foodItems,
                                                  String space, String deviceType) {
        int specialItemsNeed = calculateSpecItemNeed(foodItems, space, deviceType);
        int specialItemsHas = calculateSpecItemHas(repos, space, deviceType);
        if (specialItemsHas - specialItemsNeed >= 0) {
            markSpecial(repos, specialItemsNeed, space, deviceType);
        } else {
            for (TrolleyItem reposItem : repos) {
                if (reposItem.isSpecialPriority() && getDeviceType(reposItem).equals(deviceType)
                        && reposItem.getSpace().equals(space) && specialItemsNeed-- > 0) {
                    reposItem.setSpecial(true);
                } else if (specialItemsNeed <= 0){
                    break; /*performance*/
                }
            }
        }
        return repos;
    }

    private void markSpecial(List<TrolleyItem> repos, int need, String space, String deviceType) {
        for (TrolleyItem reposItem : repos) {
            if (reposItem.isSpecialPriority() && need-- > 0 && getDeviceType(reposItem).equals(deviceType)
                    && reposItem.getSpace().equals(space)) {
                reposItem.setSpecial(true);
            } else if (need <= 0) {
                break; /*performance*/
            }
        }
    }

    private int calculateSpecItemHas(List<TrolleyItem> repos, String space, String deviceType) {
        int ret = 0;
        for (TrolleyItem reposItem : repos) {
            if (reposItem.getSpace().equals(space) && getDeviceType(reposItem).equals(deviceType)
                    && reposItem.isSpecialPriority()) {
                ret ++;
            }
        }
        return ret;
    }

    private List<TrolleyItem> assemblyNormal(List<TrolleyItem> repos, Map<String, List<FoodItem>> foodItems, String space,
                                             String deviceType) throws BusinessException {
        Map<String, List<FoodItem>> extraMap = new HashMap<String, List<FoodItem>>();
        return assemblyNormal(repos, foodItems, extraMap, space, deviceType);
    }

    private List<TrolleyItem> assemblyNormal(List<TrolleyItem> repos, Map<String, List<FoodItem>> foodItems,
                                             Map<String, List<FoodItem>> extraMap, String space, String deviceType)
            throws BusinessException {
        List<TrolleyItem> extraRepos = new ArrayList<TrolleyItem>();
        int indexOfRepos = -1;
        boolean hasBreak = false;
        for (TrolleyItem reposItem : repos) {
            boolean match = false;
            indexOfRepos ++;
            if (reposItem.getSpace().equals(space) && getDeviceType(reposItem).equals(deviceType)
                    && !reposItem.isVirtual() && !reposItem.isSpecicalInstalled() && !reposItem.isSpecial()) {
                Collection<List<FoodItem>> itemValues = foodItems.values();
                boolean installed = false;
                for (int step = 1; step < 7; step ++) { // 规则优先级
                    for (List<FoodItem> items  : itemValues) {
                        for (FoodItem foodItem : items) {
                            if (!foodItem.isInstalledPortion() /*未装过,装过剩余的会存到extraMap*/
                                    && !foodItem.getBisspecial().booleanValue()) {
                                String longDefood = foodItem.getLongDefoodKey();
                                boolean isMatchMulti = isMatchMulti(reposItem, foodItem);
                                boolean isMatch = (reposItem.isFree() && reposItem.getInstalledIDs().size() == 0
                                        && reposItem.getSpace().equals(foodItem.getPkSpace())
                                        && foodItem.getRemain().compareTo(UFDouble.ZERO_DBL) > 0)
                                        || isMatch(reposItem, foodItem);
                                boolean hasSameDefood = hasSameDefood(repos, indexOfRepos, longDefood);
                                boolean isNewTrolley = isNewTrolley(repos, indexOfRepos);
                                if (step == 1 && isMatchMulti && hasSameDefood) {
                                    installed = installed || install(repos, indexOfRepos, foodItem, extraMap, extraRepos);
                                } else if (step == 2 && isMatch && hasSameDefood) {
                                    installed = installed || install(repos, indexOfRepos, foodItem, extraMap, extraRepos);
                                } else if (step == 3 && isMatchMulti && isNewTrolley) {
                                    installed = installed || install(repos, indexOfRepos, foodItem, extraMap, extraRepos);
                                } else if (step == 4 && isMatch && isNewTrolley) {
                                    installed = installed || install(repos, indexOfRepos, foodItem, extraMap, extraRepos);
                                } else if (step == 5 && isMatchMulti) {
                                    installed = installed || install(repos, indexOfRepos, foodItem, extraMap, extraRepos);
                                } else if (step == 6 && isMatch) {
                                    installed = installed || install(repos, indexOfRepos, foodItem, extraMap, extraRepos);
                                }
                            }
                        }
                    }
                }
                if (!installed /*performance*/) {
                    for (List<FoodItem> items  : itemValues) {
                        if (!match /*performance*/) {
                            for (FoodItem foodItem : items) {
                                if (isMatch(reposItem, foodItem) && !reposItem.isFree()) {
                                    match = true;
                                    break; /*performance*/
                                }
                            }
                        }
                    }
                    //注意 installPortion
                    //所有餐食不滿足此餐车单元
                    //标记此单元为自由 可安装挪餐餐食
                    if (reposItem.getInstalledIDs().isEmpty() && !match && !reposItem.isFree()) {
                        //马上挪餐
                        reposItem.setFree(true);
                        hasBreak = true;
                        break;
                    }
                }
            }
        }
        Map<String, List<FoodItem>> newExtraMap = (Map<String, List<FoodItem>>) AFUtil.clone((Serializable) extraMap);
        extraMerge(newExtraMap, foodItems);
        //TODO
        if (/**直到行完一次，像沒有break出來一樣**/hasBreak) {
            repos = shift(repos, space, deviceType, indexOfRepos);
//            return assemblyNormal(repos, foodItems, extraMap, space, deviceType);
        }/**然後未裝的餐食都在extraMap裡**/

        //合併后 先检查有没有空闲餐车单元 有则递归
        if (hasFreeSpaceForNormal(repos, newExtraMap, space, deviceType)) {
            extraMap.clear();
            return assemblyNormal(repos, newExtraMap, extraMap, space, deviceType);
        } else {
            return repos;
        }
    }

    private int getSpecSpaceOrder(
            Map<String, Map<String, Map<String, DeviceItem>>> devices2,
            String space, String deviceType) {
        Map<String, Map<String, DeviceItem>> map = devices2.get(space);
        for (Entry<String, Map<String, DeviceItem>> entry : map.entrySet()) {
            if (entry.getKey().equals(deviceType)) {
                for (Entry<String, DeviceItem> ientry : entry.getValue().entrySet()) {
                    DeviceItem item = ientry.getValue();
                    if (item.isPrior4Spec()) {
                        if (item.getOrder() == 1) {
                            return 1;
                        } else if (item.getOrder() > 1) {
                            if (item.getOrder() != entry.getValue().size()) {
                                //error return 0
                                return 0;
                            } else {
                                return -1;
                            }
                        }
                    }
                }
            }
        }
        return 0;
    }

    private boolean hasSpecSpace(
            Map<String, Map<String, Map<String, DeviceItem>>> devices2,
            String space, String deviceType) {
        Map<String, Map<String, DeviceItem>> map = devices2.get(space);
        for (Entry<String, Map<String, DeviceItem>> entry : map.entrySet()) {
            if (entry.getKey().equals(deviceType)) {
                for (Entry<String, DeviceItem> ientry : entry.getValue().entrySet()) {
                    DeviceItem item = ientry.getValue();
                    if (item.isPrior4Spec()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private List<TrolleyItem> assemblyEmpty(List<TrolleyItem> repos,
                                            Map<String, List<FoodItem>> extraMap, boolean isSpecial, String space,
                                            String deviceType) {
        AFLogger.info("直接打印一张单");
        boolean isForHot = deviceType.equals("true");
        for (Entry<String, List<FoodItem>> entry : extraMap.entrySet()) {
            for (FoodItem item : entry.getValue()) {
                if (item.getBisspecial().booleanValue() == isSpecial
                        && item.getBishot().booleanValue() == isForHot
                        && item.getPkSpace().equals(space)
                        && !item.isInstalledPortion() && !item.getBisspecial().booleanValue()) {
                    extraItems.add(item);
                }
            }
        }
        return repos;
    }

    private List<TrolleyItem> assemblyEmpty(List<TrolleyItem> repos,
                                            Map<String, List<FoodItem>> extraMap, boolean isSpecial) {
        AFLogger.info("直接打印一张单");
        for (Entry<String, List<FoodItem>> entry : extraMap.entrySet()) {
            for (FoodItem item : entry.getValue()) {
                if (item.getBisspecial().booleanValue() == isSpecial
                        && !item.isInstalledPortion() && !item.getBisspecial().booleanValue()) {
                    extraItems.add(item);
                }
            }
        }
        return repos;
    }

    private List<TrolleyItem> assemblyEmptyForSpecial(List<TrolleyItem> repos, Map<String, List<FoodItem>> extraMap) {
        AFLogger.info("直接打印一张单");
        for (Entry<String, List<FoodItem>> entry : extraMap.entrySet()) {
            for (FoodItem item : entry.getValue()) {
                if (!item.isInstalledPortion() && item.getBisspecial().booleanValue()) {
                    extraItems.add(item);
                }
            }
        }
        return repos;
    }

    private boolean hasSpecialFoodItems(Map<String, List<FoodItem>> extraMap, String space, String deviceType) {
        boolean isForHot = deviceType.equals("true");
        for (Entry<String, List<FoodItem>> entry : extraMap.entrySet()) {
            for (FoodItem foodItem : entry.getValue()) {
                if (foodItem.getBisspecial().booleanValue()
                        && !foodItem.isInstalledPortion()
                        && foodItem.getPkSpace().equals(space)
                        && foodItem.getBishot().booleanValue() == isForHot) {
                    return true;
                }
            }
        }
        return false;
    }

    private int calculateCellNeed(Map<String, List<FoodItem>> extraMap) throws BusinessException {
        UFDouble ret = UFDouble.ZERO_DBL;
        for (Entry<String, List<FoodItem>> entry : extraMap.entrySet()) {
            for (FoodItem item : entry.getValue()) {
                ret = ret.add(calculateOccupy(origRepos, item));
            }
        }
        return ret.intValue();
    }

    private List<TrolleyItem> shiftForSpecial(List<TrolleyItem> repos, int startIndex, String space, String deviceType) {
        String currDeviceType = getDeviceType(repos.get(startIndex));
        String currSpace = repos.get(startIndex).getSpace();
        TrolleyItem newItem = (TrolleyItem) AFUtil.clone(repos.get(startIndex));
        newItem.settCellNumber(1);
        newItem.setSpecial(true);
        repos.add(startIndex, newItem);
        int currLast = getCurrTrolleyLast(repos, startIndex + 1 /*加了新项 所以+1*/);
        TrolleyItem lastItem = repos.get(currLast);
        if (currLast == startIndex + 1) {
            lastItem.setVirtual(true);
            return repos;
        }
        TrolleyItem nextItem = repos.get(currLast + 1);
        if (!getDeviceType(nextItem).equals(deviceType) || !nextItem.getSpace().equals(space)) {
            lastItem.setVirtual(true);
            return repos;
        }
        if (nextItem.isVirtual()) {
            lastItem.setVirtual(true);
            return repos;
        }
        if (getDeviceType(lastItem).equals(currDeviceType)
                && lastItem.getSpace().equals(currSpace)) {
            if (lastItem.gettCellNumber() == 1 || true) {
                lastItem.setDeviceID(nextItem.getDeviceID());
                return shiftForSpecial(repos, currLast + 1, null, space, deviceType);
            } else {
                lastItem.settCellNumber(lastItem.gettCellNumber() - 1);
                Map<String, UFDouble> cateCapMap = lastItem.getCateCapMap();
                Map<String, UFDouble> newCateCapMap = new HashMap<String, UFDouble>();
                Map<String, UFDouble> nextCateCapMap = new HashMap<String, UFDouble>();
                for (Entry<String, UFDouble> entry : cateCapMap.entrySet()) {
                    UFDouble cap = entry.getValue();
                    UFDouble newCap = cap
                            .div(repos.get(startIndex).gettCellNumber() + 1)
                            .multiply(repos.get(startIndex).gettCellNumber())
                            .setScale(0, UFDouble.ROUND_CEILING);
                    UFDouble nextCap = cap.sub(newCap);
                    newCateCapMap.put(entry.getKey(), newCap);
                    nextCateCapMap.put(entry.getKey(), nextCap);
                }
                lastItem.setCateCapMap(newCateCapMap);
                lastItem.setOrigCateCapMap(newCateCapMap);
                TrolleyItem splitItem = (TrolleyItem) AFUtil.clone(lastItem);
                splitItem.setDeviceID(nextItem.getDeviceID());
                splitItem.settCellNumber(1);
                return shiftForSpecial(repos, currLast, splitItem, space, deviceType);
            }
        } else {
            if (lastItem.gettCellNumber() == 1 || true) {
                lastItem.setVirtual(true);
            } else {
                lastItem.settCellNumber(lastItem.gettCellNumber() - 1);
                Map<String, UFDouble> cateCapMap = lastItem.getCateCapMap();
                Map<String, UFDouble> newCateCapMap = new HashMap<String, UFDouble>();
                Map<String, UFDouble> nextCateCapMap = new HashMap<String, UFDouble>();
                for (Entry<String, UFDouble> entry : cateCapMap.entrySet()) {
                    UFDouble cap = entry.getValue();
                    UFDouble newCap = cap
                            .div(repos.get(startIndex).gettCellNumber() + 1)
                            .multiply(repos.get(startIndex).gettCellNumber())
                            .setScale(0, UFDouble.ROUND_CEILING);
                    UFDouble nextCap = cap.sub(newCap);
                    newCateCapMap.put(entry.getKey(), newCap);
                    nextCateCapMap.put(entry.getKey(), nextCap);
                }
                lastItem.setCateCapMap(newCateCapMap);
                lastItem.setOrigCateCapMap(newCateCapMap);
                TrolleyItem splitItem = (TrolleyItem) AFUtil.clone(lastItem);
                splitItem.setDeviceID(nextItem.getDeviceID());
                splitItem.settCellNumber(1);
                splitItem.setVirtual(true);
                repos.add(currLast + 1, splitItem);
            }
            return repos;
        }
    }

    private List<TrolleyItem> shiftForSpecial(List<TrolleyItem> repos,
                                              int startIndex, TrolleyItem newItem, String space, String deviceType) {
        String currDeviceType = getDeviceType(repos.get(startIndex));
        String currSpace = repos.get(startIndex).getSpace();
        if (null != newItem)
            repos.add(startIndex, newItem);
        int currLast = 0;
        if (null != newItem)
            currLast = getCurrTrolleyLast(repos, startIndex + 1);
        else
            currLast = getCurrTrolleyLast(repos, startIndex);
        TrolleyItem lastItem = repos.get(currLast);
        if (null != newItem) {
            if (currLast == startIndex + 1) {
                lastItem.setVirtual(true);
                return repos;
            }
        } else {
            if (currLast == startIndex) {
                lastItem.setVirtual(true);
                return repos;
            }
        }
        TrolleyItem nextItem = repos.get(currLast + 1);
        if (!getDeviceType(nextItem).equals(deviceType) || !nextItem.getSpace().equals(space)) {
            lastItem.setVirtual(true);
            return repos;
        }
        if (nextItem.isVirtual()) {
            lastItem.setVirtual(true);
            return repos;
        }
        if (getDeviceType(lastItem).equals(currDeviceType)
                && lastItem.getSpace().equals(currSpace)) {
            if (lastItem.gettCellNumber() == 1 || true) {
                lastItem.setDeviceID(nextItem.getDeviceID());
                if (null != newItem)
                    return shiftForSpecial(repos, currLast + 1, null, space, deviceType);
                else
                    return shiftForSpecial(repos, currLast, null, space, deviceType);
            } else {
                lastItem.settCellNumber(lastItem.gettCellNumber() - 1);
                Map<String, UFDouble> cateCapMap = lastItem.getCateCapMap();
                Map<String, UFDouble> newCateCapMap = new HashMap<String, UFDouble>();
                Map<String, UFDouble> nextCateCapMap = new HashMap<String, UFDouble>();
                for (Entry<String, UFDouble> entry : cateCapMap.entrySet()) {
                    UFDouble cap = entry.getValue();
                    UFDouble newCap = cap
                            .div(repos.get(startIndex).gettCellNumber() + 1)
                            .multiply(repos.get(startIndex).gettCellNumber())
                            .setScale(0, UFDouble.ROUND_CEILING);
                    UFDouble nextCap = cap.sub(newCap);
                    newCateCapMap.put(entry.getKey(), newCap);
                    nextCateCapMap.put(entry.getKey(), nextCap);
                }
                lastItem.setCateCapMap(newCateCapMap);
                lastItem.setOrigCateCapMap(newCateCapMap);
                TrolleyItem splitItem = (TrolleyItem) AFUtil.clone(lastItem);
                splitItem.setDeviceID(nextItem.getDeviceID());
                splitItem.settCellNumber(1);
                return shiftForSpecial(repos, currLast, splitItem, space, deviceType);
            }
        } else {
            lastItem.setVirtual(true);
            return repos;
        }
    }

    private int getCurrTrolleyLast(List<TrolleyItem> repos, int startIndex) {
        for (int i = startIndex; i < repos.size() - 1; i++) {
            if (!repos.get(i + 1).getSpace().equals(repos.get(i).getSpace())) {
                return i;
            }
            if (!getDeviceType(repos.get(i + 1)).equals(getDeviceType(repos.get(i)))) {
                return i;
            }
            if (!repos.get(i + 1).getDeviceID().equals(repos.get(i).getDeviceID())) {
                return i;
            }
            if (repos.get(i + 1).isVirtual()) {
                return i;
            }
        }
        return repos.size() - 1;
    }
    private List<TrolleyItem> shift(List<TrolleyItem> repos, String space, String deviceType, int startIndex) {
        List<TrolleyItem> ret = (List<TrolleyItem>)AFUtil.clone((ArrayList<TrolleyItem>)repos);
        for (int i = startIndex; i < repos.size() - 1; i++) {
            if (repos.get(i).isFree() && repos.get(i).getSpace().equals(space)
                    && getDeviceType(repos.get(i)).equals(deviceType)) {
                MutableInt j = new MutableInt(i);
                TrolleyItem currItem = ret.get(i);
                TrolleyItem nextItem = getNextNotFreeItem(ret, j, space, deviceType);
                if (null == nextItem) {
                    return ret;
                }
                int tCellNbr = currItem.gettCellNumber();
                int nexttCellNbr = nextItem.gettCellNumber();
                if (currItem.getDeviceID().equals(nextItem.getDeviceID())) {
                    ret = swapReposItem(ret, i, j.intValue());
                    return shift(ret, space, deviceType, j.intValue());
                } else if (currItem.getSpace().equals(nextItem.getSpace())
                        && getDeviceType(currItem).equals(nextItem.getDeviceType())) {
                    if (tCellNbr == nexttCellNbr || true) {
                        currItem.setFree(false);
                        Map<String, UFDouble> cateCapMap = nextItem.getCateCapMap();
                        Set<String> categories = nextItem.getCategories();
                        String note = nextItem.getNote();
                        String srcAssemblyB = nextItem.getSrcAssemblyB();
                        currItem.setCateCapMap(cateCapMap);
                        currItem.setCategories(categories);
                        currItem.setNote(note);
                        currItem.setSrcAssemblyB(srcAssemblyB);
                        nextItem.setFree(true);
                        return shift(ret, space, deviceType, j.intValue());
                    } else if (tCellNbr < nexttCellNbr /* 统一使用一单元格 条件暂时作废 */) {
                        currItem.setFree(false);
                        Map<String, UFDouble> cateCapMap = nextItem.getCateCapMap();
                        Map<String, UFDouble> adCateCapMap = calculateCateCap(cateCapMap, tCellNbr, nexttCellNbr);
                        Set<String> categories = nextItem.getCategories();
                        String note = nextItem.getNote();
                        String srcAssemblyB = nextItem.getSrcAssemblyB();
                        currItem.setCateCapMap(adCateCapMap);
                        currItem.setCategories(categories);
                        currItem.setNote(note);
                        currItem.setSrcAssemblyB(srcAssemblyB);

                        Map<String, UFDouble> reduceMap = calculateCateCap(cateCapMap, adCateCapMap);
                        TrolleyItem extraReposItem = (TrolleyItem) AFUtil.clone(nextItem);
                        nextItem.setCateCapMap(reduceMap);
                        nextItem.settCellNumber(nexttCellNbr - tCellNbr);
                        extraReposItem.setFree(true);
                        extraReposItem.settCellNumber(tCellNbr);
                        ret.add(j.intValue() + 1, extraReposItem);
                        return shift(ret, space, deviceType, j.intValue());
                    } else {
                        currItem.setFree(false);
                        Map<String, UFDouble> cateCapMap = nextItem.getCateCapMap();
                        Set<String> categories = nextItem.getCategories();
                        String note = nextItem.getNote();
                        String srcAssemblyB = nextItem.getSrcAssemblyB();
                        TrolleyItem extraReposItem = (TrolleyItem) AFUtil.clone(currItem);
                        currItem.setCateCapMap(cateCapMap);
                        currItem.setCategories(categories);
                        currItem.settCellNumber(nexttCellNbr);
                        currItem.setNote(note);
                        currItem.setSrcAssemblyB(srcAssemblyB);
                        nextItem.setFree(true);
                        extraReposItem.settCellNumber(tCellNbr - nexttCellNbr);
                        extraReposItem.setFree(true);
                        ret.add(j.intValue() + 1, extraReposItem);
                        return shift(ret, space, deviceType, j.intValue());
                    }
                }
            }
        }
        return ret;
    }

    private TrolleyItem getNextNotFreeItem(List<TrolleyItem> repos, MutableInt i, String space, String deviceType) {
        if (i.intValue() < repos.size() - 1) {
            i.add(1);
            if (!repos.get(i.intValue()).isFree() && !repos.get(i.intValue()).isSpecial() /** 特殊餐不挪 **/
                && getDeviceType(repos.get(i.intValue())).equals(deviceType)
                    && repos.get(i.intValue()).getSpace().equals(space)) {
                return repos.get(i.intValue());
            } else {
                return getNextNotFreeItem(repos, i, space, deviceType);
            }
        }
        return null;
    }

    private List<TrolleyItem> swapReposItem(List<TrolleyItem> repos, int i, int j) {
        List<TrolleyItem> ret = (List<TrolleyItem>)AFUtil.clone((ArrayList<TrolleyItem>)repos);
        if (repos.get(j).isVirtual() && !repos.get(i).isVirtual()) {
            repos.get(j).setVirtual(false);
            repos.get(i).setVirtual(true);
        }
        ret.set(i, repos.get(j));
        ret.set(j, repos.get(i));
        return ret;
    }


    private Map<String, UFDouble> calculateCateCap(Map<String, UFDouble> cateCapMap,
                                                   Map<String, UFDouble> adCateCapMap) {
        Map<String, UFDouble> ret = new HashMap<String, UFDouble>();
        for (Entry<String, UFDouble> entry : cateCapMap.entrySet()) {
            UFDouble capacity = entry.getValue();
            if (capacity.compareTo(UFDouble.ZERO_DBL) > 0) {
                capacity = capacity.sub(adCateCapMap.get(entry.getKey()));
            }
            ret.put(entry.getKey(), capacity);
        }
        return ret;
    }

    private Map<String, UFDouble> calculateCateCap(Map<String, UFDouble> cateCapMap,
                                                   int tCellNbr, int nexttCellNbr) {
        Map<String, UFDouble> ret = new HashMap<String, UFDouble>();
        for (Entry<String, UFDouble> entry : cateCapMap.entrySet()) {
            UFDouble capacity = entry.getValue();
            if (capacity.compareTo(UFDouble.ZERO_DBL) > 0) {
                capacity = capacity.div(nexttCellNbr).multiply(tCellNbr).setScale(0, UFDouble.ROUND_FLOOR);
            }
            ret.put(entry.getKey(), capacity);
        }
        return ret;
    }

    private boolean isMatchMulti(TrolleyItem reposItem, FoodItem foodItem) {
        if (!isMatch(reposItem, foodItem)) {
            return false;
        }
        if (reposItem.getCategories().size() > 1) {
            return true;
        }
        return false;
    }

    private void extraMerge(Map<String, List<FoodItem>> extraMap,
                            Map<String, List<FoodItem>> foodItems) {
        Collection<List<FoodItem>> itemValus = foodItems.values();
        for (List<FoodItem> items : itemValus) {
            for (FoodItem foodItem : items) {
                if (!foodItem.isInstalledPortion()) {
                    String longDefoodKey = foodItem.getLongDefoodKey();
                    List<FoodItem> extraList = extraMap.get(longDefoodKey) != null ?
                            extraMap.get(longDefoodKey) : new ArrayList<FoodItem>();
                    FoodItem extraItem = (FoodItem) AFUtil.clone(foodItem);
                    extraItem.setInstalled(UFDouble.ZERO_DBL);
                    extraItem.setUniqueID(UUID.randomUUID().toString());
                    extraList.add(extraItem);
                    extraMap.put(longDefoodKey, extraList);
                }
            }
        }
    }

    private boolean hasFreeSpaceForNormal(List<TrolleyItem> repos, Map<String, List<FoodItem>> foodItems, String space,
                                          String deviceType) {
        for (TrolleyItem reposItem : repos) {
            if (reposItem.getInstalledIDs().isEmpty() && getDeviceType(reposItem).equals(deviceType)
                    && reposItem.getSpace().equals(space) && !reposItem.isVirtual() && !reposItem.isSpecial()) {
                for (List<FoodItem> list : foodItems.values()) {
                    for (FoodItem foodItem : list) {
                        boolean unFinish = !foodItem.isInstalledPortion();
                        boolean isMatch = isMatch(reposItem, foodItem);
                        boolean isSpecial = foodItem.getBisspecial().booleanValue();
                        if (unFinish && isMatch && !isSpecial) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean hasFreeSpaceForSpecial(List<TrolleyItem> repos, Map<String, List<FoodItem>> extraMap) {
        for (TrolleyItem reposItem : repos) {
            if (reposItem.getInstalledIDs().isEmpty()) {
                for (List<FoodItem> list : extraMap.values()) {
                    for (FoodItem foodItem : list) {
                        boolean isMatch = isMatch(reposItem, foodItem);
                        boolean isSpecial = foodItem.getBisspecial().booleanValue();
                        boolean isSpecialPriority = reposItem.isSpecialPriority();
                        if (isMatch && isSpecial && isSpecialPriority) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }


    private UFDouble queryRealOccupytCellNumber(List<TrolleyItem> repos, String deviceType,
                                                FoodItem foodItem) {
        //当容纳能力填0（代表任意时），查找对应的单元格数，确保安全起见 取最大数
        UFDouble maxCellNumber = UFDouble.ZERO_DBL;
        for (TrolleyItem reposItem : repos) {
            if (isMatch(reposItem, foodItem) && reposItem.getDeviceType().equals(deviceType)) {
                if (maxCellNumber.compareTo(new UFDouble(reposItem.gettCellNumber())) < 0) {
                    maxCellNumber = new UFDouble(reposItem.gettCellNumber());
                }
            }
        }
        return UFDouble.ZERO_DBL;
    }

    private UFDouble calculateOccupy(List<TrolleyItem> repos, FoodItem foodItem) throws BusinessException {
        for (TrolleyItem reposItem : repos) {
            if (reposItem.getOrigCateCapMap().keySet().contains(foodItem.getCategory())) {
                int tCellNumber = reposItem.gettCellNumber();
                UFDouble capacity = reposItem.getOrigCapacity(foodItem.getCategory());
                if (capacity.compareTo(UFDouble.ZERO_DBL) == 0) {
                    return UFDouble.ONE_DBL;
                }
                UFDouble cellNeed = new UFDouble(tCellNumber).multiply(foodItem.getQuantity().div(capacity))
                        .setScale(0, UFDouble.ROUND_CEILING);
                if (cellNeed.compareTo(UFDouble.ZERO_DBL) == 0) {
                    return UFDouble.ONE_DBL;
                }
                return cellNeed;
            }
        }
        throw new BusinessException("没有餐食["+BaseDocUtil.getMaterialVO(foodItem.getPkMaterial()).getName()+"]对应的分配规则，现有装配规则不满足装配所有餐食。");
//		return null;
    }

    private UFDouble calculateOccupy(List<TrolleyItem> repos, FoodItem foodItem, String deviceType) throws BusinessException {
        for (TrolleyItem reposItem : repos) {
            if (reposItem.getOrigCateCapMap().keySet() != null && reposItem.getOrigCateCapMap().keySet().contains(foodItem.getCategory())) {
                int tCellNumber = reposItem.gettCellNumber();
                UFDouble capacity = reposItem.getOrigCapacity(foodItem.getCategory());
                if (capacity.compareTo(UFDouble.ZERO_DBL) == 0) {
                    return UFDouble.ONE_DBL;
                }
                UFDouble cellNeed = new UFDouble(tCellNumber).multiply(foodItem.getQuantity().div(capacity)).setScale(0, UFDouble.ROUND_CEILING);
                if (cellNeed.compareTo(UFDouble.ZERO_DBL) == 0) {
                    return UFDouble.ONE_DBL;
                }
                return cellNeed;
            }
        }
        throw new BusinessException("没有餐食["+BaseDocUtil.getMaterialVO(foodItem.getPkMaterial()).getName()+"]和装配设备["+BaseDocUtil.getMaterialVO(deviceType).getName()+"]对应的分配规则，现有装配规则不满足装配所有餐食。");
//		return null;
    }

    private boolean isNewTrolley(List<TrolleyItem> repos, int index) {
        TrolleyItem currentReposItem = repos.get(index);
        String deviceID = currentReposItem.getDeviceID();
        for (TrolleyItem reposItem : repos) {
            if (reposItem.getDeviceID().equals(deviceID) && !reposItem.getInstalledMap().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    private boolean isMatch(TrolleyItem reposItem, FoodItem foodItem) {
        //TODO
        // 位置问題
        // 设备种类问题
        String pkSpace = foodItem.getPkSpace();
        String reposSpace = reposItem.getSpace();
        String category = foodItem.getCategory();
        /*if (!reposSpace.equals(pkSpace)) {
            return false;
        }*/
        /*if (!getDeviceType(reposItem).equals(foodItem.getBishot().booleanValue() ? "true" : "false")) {
            return false;
        }*/
        if (foodItem.getRemain().compareTo(UFDouble.ZERO_DBL) <= 0) {
            return false;
        }
        if (!reposItem.getCategories().contains(category)) {
            return false;
        } else if (reposItem.getRemainCapacity(category).compareTo(UFDouble.ZERO_DBL) <= 0) {
            return false;
        }
        return true;
    }

    private boolean install(List<TrolleyItem> repos, int indexOfRepos, FoodItem foodItem,
                            Map<String, List<FoodItem>> extraMap, List<TrolleyItem> extraRepos) throws BusinessException {
        TrolleyItem reposItem = repos.get(indexOfRepos);
        UFDouble quantity = foodItem.getRemain();
        String category = foodItem.getCategory();
        UFDouble capacity = reposItem.getRemainCapacity(category);
        if (capacity.compareTo(UFDouble.ZERO_DBL) <= 0) {
            capacity = queryCapacity(category);
        }

        if (capacity.compareTo(quantity) >= 0) {
            AFLogger.info("trolley " + reposItem.getDeviceNbr() + " " + reposItem.getStartIndex() +
                    (reposItem.gethPart().equals("1")?" A ":" B ") +
                    reposItem.isFree() +
                    " installed " + BaseDocUtil.getMaterialVO(foodItem.getPkMaterial()).getName() +
                    " " + quantity);
            installedResults.add(foodItem);
            return reposItem.install(category, foodItem);
        } else {
            if (capacity.compareTo(UFDouble.ZERO_DBL) <= 0)
                capacity = queryCapacity(category);
            UFDouble installQuantity = capacity.compareTo(quantity) >= 0 ? quantity : capacity;
            String longDefoodKey = foodItem.getLongDefoodKey();
            List<FoodItem> extraList = extraMap.get(longDefoodKey) == null ?
                    new ArrayList<FoodItem>() : extraMap.get(longDefoodKey);
            FoodItem extraItem = (FoodItem) AFUtil.clone(foodItem);
            extraItem.setInstalled(UFDouble.ZERO_DBL);
            extraItem.setQuantity(quantity.sub(installQuantity));
            extraItem.setRemain(quantity.sub(installQuantity));
            extraItem.setUniqueID(UUID.randomUUID().toString());
            extraList.add(extraItem);
            extraMap.put(longDefoodKey, extraList);

            AFLogger.info("trolley " + reposItem.getDeviceNbr() + " " + reposItem.getStartIndex() +
                    (reposItem.gethPart().equals("1")?" A ":" B ") +
                    reposItem.isFree() +
                    " installed " + BaseDocUtil.getMaterialVO(foodItem.getPkMaterial()).getName() +
                    " " + installQuantity);
            installedResults.add(foodItem);
            return reposItem.install(category, foodItem, installQuantity);
        }
    }

    private UFDouble queryCapacity(String category) {
        for (TrolleyItem item : origRepos) {
            if (item.getCategories().contains(category)) {
                return item.getCapacity(category);
            }
        }
        return UFDouble.ONE_DBL; /*默认一份*/
    }

    private boolean hasSameDefood(List<TrolleyItem> repos, int index, String longDefood) {
        String deviceID = repos.get(index).getDeviceID();
        for (TrolleyItem reposItem : repos) {
            if (reposItem.getDeviceID().equals(deviceID)) {
                Set<String> defoodSet = reposItem.getLongDefoodKey();
                if (defoodSet.size() > 1) {
                    return false;
                }
                if (defoodSet.size() == 1 && !defoodSet.contains(longDefood)) {
                    return false;
                }
            }
        }
        return !isEmpty(repos, index);
    }


    private boolean isEmpty(List<TrolleyItem> repos, int index) {
        String deviceID = repos.get(index).getDeviceID();
        for (TrolleyItem reposItem : repos) {
            if (reposItem.getDeviceID().equals(deviceID) && reposItem.getLongDefoodKey().size() > 0) {
                return false;
            }
        }
        return true;
    }

}
