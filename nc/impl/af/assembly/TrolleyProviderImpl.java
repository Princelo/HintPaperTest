package nc.impl.af.assembly;

import nc.pub.af.utils.BaseDocUtil;
import nc.pub.af.utils.R;
import nc.test.T;
import nc.vo.af.assembly.AssemblyBVO;
import nc.vo.af.assembly_details.DeviceItem;
import nc.vo.af.assembly_details.TrolleyItem;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;

import java.io.Serializable;
import java.util.*;

/**
 * Created by Princelo on 24/4/2017.
 */
public class TrolleyProviderImpl implements Serializable {
    public AssemblyBVO[] require(String pkAirplane, String pkModel, boolean boolDomain, UFDateTime validTime) {
        return null;
    }

    public List<TrolleyItem> getRepos(String pkAirplane, String pkModel, boolean boolDomain, String pkFltno, UFDateTime validTime) throws BusinessException {
        List<TrolleyItem> t1 = T1();
        List<TrolleyItem> t2 = T2();
        List<TrolleyItem> t3 = T3();
        List<TrolleyItem> t4 = T4();
        List<TrolleyItem> t5 = T5();
        List<TrolleyItem> t6 = T6();
        List<TrolleyItem> t7 = T7();
        List<TrolleyItem> t8 = T8();
        List<TrolleyItem> t9 = T9();
        t1.addAll(T15());
        t1.addAll(t2);
        t1.addAll(t3);
        t1.addAll(t4);
        t1.addAll(t5);
        t1.addAll(t6);
        t1.addAll(t7);
        t1.addAll(t8);
        t1.addAll(t9);
        return t1;
    }

    private List<TrolleyItem> T9() {
        List<TrolleyItem> ret = new ArrayList<>();
        for (int i = 1; i <= 14; i++) {
            TrolleyItem item = new TrolleyItem();
            item.setRecipe(T.RECIPE2);
            item.setSrcAssemblyB("" + i);
            item.setSpace(T.BSPACE);
            item.setPlanServer("PLANSERVER");
            item.setPlanSeat("PLANSEAT");
            item.setKitchen("KITCHEN");
            item.setInstalledMap(new HashMap<String, UFDouble>());
            item.setDeviceID("deviceid000000000009");
            item.setDeviceType("JK1CZ001");
            item.setDeviceNbr("devicenbr09");
            item.setStartIndex(i);
            item.setEndIndex(i);
            item.sethPart(i <= 14 ? "1" : "2");
            item.setvPart("1");
            item.setWide(false);
            item.setLongDefoodKey(new LinkedHashSet<String>());
            item.setShortDefoodKey(new LinkedHashSet<String>());
            item.setSpecialPriority(false);
            item.setInstalledIDs(new LinkedHashSet<String>());
            item.setvCellNumber(1);
            item.settCellNumber(1);
            item.setFree(false);
            item.setNote("");
            item.setSpecicalInstalled(false);
            item.setForHot(true);
            item.setCategories(new HashSet<String>() {{
                add(T.DEFOOD5 + T.CATE2);
            }});
            OptionCateSet optionCateSet = new OptionCateSet(item.getCategories(),
                    new HashSet<UFDouble>() {{
                        add(new UFDouble(2));
                    }},
                    new HashMap<String, UFDouble>() {{
                        put(T.DEFOOD5 + T.CATE2, new UFDouble(2));
                    }});
            item.setCateCapMap(optionCateSet.getCateCapMap());
            item.setOrigCateCapMap(optionCateSet.getCateCapMap());
            ret.add(item);
        }
        return ret;
    }

    private List<TrolleyItem> T8() {
        List<TrolleyItem> ret = new ArrayList<>();
        for (int i = 1; i <= 28; i++) {
            TrolleyItem item = new TrolleyItem();
            item.setRecipe(T.RECIPE2);
            item.setSrcAssemblyB("" + i);
            item.setSpace(T.BSPACE);
            item.setPlanServer("PLANSERVER");
            item.setPlanSeat("PLANSEAT");
            item.setKitchen("KITCHEN");
            item.setInstalledMap(new HashMap<String, UFDouble>());
            item.setDeviceID("deviceid000000000008");
            item.setDeviceType("JC1CZ737");
            item.setDeviceNbr("devicenbr08");
            item.setStartIndex(i);
            item.setEndIndex(i);
            item.sethPart(i <= 14 ? "1" : "2");
            item.setvPart("1");
            item.setWide(false);
            item.setLongDefoodKey(new LinkedHashSet<String>());
            item.setShortDefoodKey(new LinkedHashSet<String>());
            item.setSpecialPriority(false);
            item.setInstalledIDs(new LinkedHashSet<String>());
            item.setvCellNumber(1);
            item.settCellNumber(1);
            item.setFree(false);
            item.setNote("");
            item.setSpecicalInstalled(false);
            item.setForHot(false);
            item.setCategories(new HashSet<String>() {{
                add(T.DEFOOD5 + T.CATE4);
            }});
            OptionCateSet optionCateSet = new OptionCateSet(item.getCategories(),
                    new HashSet<UFDouble>() {{
                        add(new UFDouble(2));
                    }},
                    new HashMap<String, UFDouble>() {{
                        put(T.DEFOOD5 + T.CATE4, new UFDouble(2));
                    }});
            item.setCateCapMap(optionCateSet.getCateCapMap());
            item.setOrigCateCapMap(optionCateSet.getCateCapMap());
            ret.add(item);
        }
        return ret;
    }

    private List<TrolleyItem> T7() {
        List<TrolleyItem> ret = new ArrayList<>();
        for (int i = 1; i <= 28; i++) {
            TrolleyItem item = new TrolleyItem();
            item.setRecipe(T.RECIPE2);
            item.setSrcAssemblyB("" + i);
            item.setSpace(T.FSPACE);
            item.setPlanServer("PLANSERVER");
            item.setPlanSeat("PLANSEAT");
            item.setKitchen("KITCHEN");
            item.setInstalledMap(new HashMap<String, UFDouble>());
            item.setDeviceID("deviceid000000000007");
            item.setDeviceType("JC1CZ737");
            item.setDeviceNbr("devicenbr07");
            item.setStartIndex(i);
            item.setEndIndex(i);
            item.sethPart(i <= 14 ? "1" : "2");
            item.setvPart("1");
            item.setWide(false);
            item.setLongDefoodKey(new LinkedHashSet<String>());
            item.setShortDefoodKey(new LinkedHashSet<String>());
            item.setSpecialPriority(false);
            item.setInstalledIDs(new LinkedHashSet<String>());
            item.setvCellNumber(1);
            item.settCellNumber(1);
            item.setFree(false);
            item.setNote("");
            item.setSpecicalInstalled(false);
            item.setForHot(false);
            item.setCategories(new HashSet<String>() {{
                add(T.DEFOOD4 + T.CATE3);
            }});
            OptionCateSet optionCateSet = new OptionCateSet(item.getCategories(),
                    new HashSet<UFDouble>() {{
                        add(new UFDouble(2));
                    }},
                    new HashMap<String, UFDouble>() {{
                        put(T.DEFOOD4 + T.CATE3, new UFDouble(2));
                    }});
            item.setCateCapMap(optionCateSet.getCateCapMap());
            item.setOrigCateCapMap(optionCateSet.getCateCapMap());
            ret.add(item);
        }
        return ret;
    }

    private List<TrolleyItem> T6() {
        List<TrolleyItem> ret = new ArrayList<>();
        for (int i = 1; i <= 28; i++) {
            TrolleyItem item = new TrolleyItem();
            item.setRecipe(T.RECIPE1);
            item.setSrcAssemblyB("" + i);
            item.setSpace(T.ESPACE);
            item.setPlanServer("PLANSERVER");
            item.setPlanSeat("PLANSEAT");
            item.setKitchen("KITCHEN");
            item.setInstalledMap(new HashMap<String, UFDouble>());
            item.setDeviceID("deviceid000000000006");
            item.setDeviceType("JK1CZ737");
            item.setDeviceNbr("devicenbr06");
            item.setStartIndex(i);
            item.setEndIndex(i);
            item.sethPart(i <= 14 ? "1" : "2");
            item.setvPart("1");
            item.setWide(false);
            item.setLongDefoodKey(new LinkedHashSet<String>());
            item.setShortDefoodKey(new LinkedHashSet<String>());
            item.setSpecialPriority(false);
            item.setInstalledIDs(new LinkedHashSet<String>());
            item.setvCellNumber(1);
            item.settCellNumber(1);
            item.setFree(false);
            item.setNote("");
            item.setSpecicalInstalled(false);
            item.setForHot(true);
            item.setCategories(new HashSet<String>() {{
                add(T.DEFOOD3 + T.CATE1);
            }});
            OptionCateSet optionCateSet = new OptionCateSet(item.getCategories(),
                    new HashSet<UFDouble>() {{
                        add(new UFDouble(2));
                    }},
                    new HashMap<String, UFDouble>() {{
                        put(T.DEFOOD3 + T.CATE1, new UFDouble(2));
                    }});
            item.setCateCapMap(optionCateSet.getCateCapMap());
            item.setOrigCateCapMap(optionCateSet.getCateCapMap());
            ret.add(item);
        }
        return ret;
    }

    private List<TrolleyItem> T5() {
        List<TrolleyItem> ret = new ArrayList<>();
        for (int i = 1; i <= 28; i++) {
            TrolleyItem item = new TrolleyItem();
            item.setRecipe(T.RECIPE1);
            item.setSrcAssemblyB("" + i);
            item.setSpace(T.ESPACE);
            item.setPlanServer("PLANSERVER");
            item.setPlanSeat("PLANSEAT");
            item.setKitchen("KITCHEN");
            item.setInstalledMap(new HashMap<String, UFDouble>());
            item.setDeviceID("deviceid000000000005");
            item.setDeviceType("JC1CZ737");
            item.setDeviceNbr("devicenbr05");
            item.setStartIndex(i);
            item.setEndIndex(i);
            item.sethPart(i <= 14 ? "1" : "2");
            item.setvPart("1");
            item.setWide(false);
            item.setLongDefoodKey(new LinkedHashSet<String>());
            item.setShortDefoodKey(new LinkedHashSet<String>());
            item.setSpecialPriority(false);
            item.setInstalledIDs(new LinkedHashSet<String>());
            item.setvCellNumber(1);
            item.settCellNumber(1);
            item.setFree(false);
            item.setNote("");
            item.setSpecicalInstalled(false);
            item.setForHot(false);
            item.setCategories(new HashSet<String>() {{
                add(T.DEFOOD3 + T.CATE7);
            }});
            OptionCateSet optionCateSet = new OptionCateSet(item.getCategories(),
                    new HashSet<UFDouble>() {{
                        add(new UFDouble(2));
                    }},
                    new HashMap<String, UFDouble>() {{
                        put(T.DEFOOD3 + T.CATE7, new UFDouble(2));
                    }});
            item.setCateCapMap(optionCateSet.getCateCapMap());
            item.setOrigCateCapMap(optionCateSet.getCateCapMap());
            ret.add(item);
        }
        return ret;
    }

    private List<TrolleyItem> T4() {
        List<TrolleyItem> ret = new ArrayList<>();
        for (int i = 1; i <= 28; i++) {
            TrolleyItem item = new TrolleyItem();
            item.setRecipe(T.RECIPE1);
            item.setSrcAssemblyB("" + i);
            item.setSpace(T.BSPACE);
            item.setPlanServer("PLANSERVER");
            item.setPlanSeat("PLANSEAT");
            item.setKitchen("KITCHEN");
            item.setInstalledMap(new HashMap<String, UFDouble>());
            item.setDeviceID("deviceid000000000004");
            item.setDeviceType("JC1CZ737");
            item.setDeviceNbr("devicenbr04");
            item.setStartIndex(i);
            item.setEndIndex(i);
            item.sethPart(i <= 14 ? "1" : "2");
            item.setvPart("1");
            item.setWide(false);
            item.setLongDefoodKey(new LinkedHashSet<String>());
            item.setShortDefoodKey(new LinkedHashSet<String>());
            item.setSpecialPriority(false);
            item.setInstalledIDs(new LinkedHashSet<String>());
            item.setvCellNumber(1);
            item.settCellNumber(1);
            item.setFree(false);
            item.setNote("");
            item.setSpecicalInstalled(false);
            item.setForHot(false);
            item.setCategories(new HashSet<String>() {{
                add(T.DEFOOD2 + T.CATE7);
            }});
            OptionCateSet optionCateSet = new OptionCateSet(item.getCategories(),
                    new HashSet<UFDouble>() {{
                        add(new UFDouble(2));
                    }},
                    new HashMap<String, UFDouble>() {{
                        put(T.DEFOOD2 + T.CATE7, new UFDouble(2));
                    }});
            item.setCateCapMap(optionCateSet.getCateCapMap());
            item.setOrigCateCapMap(optionCateSet.getCateCapMap());
            ret.add(item);
        }
        return ret;
    }

    private List<TrolleyItem> T3() {
        List<TrolleyItem> ret = new ArrayList<>();
        for (int i = 1; i <= 28; i++) {
            TrolleyItem item = new TrolleyItem();
            item.setRecipe(T.RECIPE1);
            item.setSrcAssemblyB("" + i);
            item.setSpace(T.BSPACE);
            item.setPlanServer("PLANSERVER");
            item.setPlanSeat("PLANSEAT");
            item.setKitchen("KITCHEN");
            item.setInstalledMap(new HashMap<String, UFDouble>());
            item.setDeviceID("deviceid000000000003");
            item.setDeviceType("JC1CZ737");
            item.setDeviceNbr("devicenbr03");
            item.setStartIndex(i);
            item.setEndIndex(i);
            item.sethPart(i <= 14 ? "1" : "2");
            item.setvPart("1");
            item.setWide(false);
            item.setLongDefoodKey(new LinkedHashSet<String>());
            item.setShortDefoodKey(new LinkedHashSet<String>());
            item.setSpecialPriority(false);
            item.setInstalledIDs(new LinkedHashSet<String>());
            item.setvCellNumber(1);
            item.settCellNumber(1);
            item.setFree(false);
            item.setNote("");
            item.setSpecicalInstalled(false);
            item.setForHot(false);
            item.setCategories(new HashSet<String>() {{add(T.DEFOOD2 + T.CATE6);}});
            OptionCateSet optionCateSet = new OptionCateSet(item.getCategories(),
                    new HashSet<UFDouble>() {{add(new UFDouble(2));}},
                    new HashMap<String, UFDouble>() {{put(T.DEFOOD2 + T.CATE6, new UFDouble(2));}});
            item.setCateCapMap(optionCateSet.getCateCapMap());
            item.setOrigCateCapMap(optionCateSet.getCateCapMap());
            ret.add(item);
        }
        return ret;
    }

    private List<TrolleyItem> T2() {
        List<TrolleyItem> ret = new ArrayList<>();
        for (int i = 1; i <= 28; i++) {
            TrolleyItem item = new TrolleyItem();
            item.setRecipe(T.RECIPE1);
            item.setSrcAssemblyB("" + i);
            item.setSpace(T.FSPACE);
            item.setPlanServer("PLANSERVER");
            item.setPlanSeat("PLANSEAT");
            item.setKitchen("KITCHEN");
            item.setInstalledMap(new HashMap<String, UFDouble>());
            item.setDeviceID("deviceid000000000002");
            item.setDeviceType("JC1CZ737");
            item.setDeviceNbr("devicenbr02");
            item.setStartIndex(i);
            item.setEndIndex(i);
            item.sethPart(i <= 14 ? "1" : "2");
            item.setvPart("1");
            item.setWide(false);
            item.setLongDefoodKey(new LinkedHashSet<String>());
            item.setShortDefoodKey(new LinkedHashSet<String>());
            item.setSpecialPriority(false);
            item.setInstalledIDs(new LinkedHashSet<String>());
            item.setvCellNumber(1);
            item.settCellNumber(1);
            item.setFree(false);
            item.setNote("");
            item.setSpecicalInstalled(false);
            item.setForHot(false);
            item.setCategories(new HashSet<String>() {{add(T.DEFOOD1 + T.CATE5);}});
            OptionCateSet optionCateSet = new OptionCateSet(item.getCategories(),
                    new HashSet<UFDouble>() {{add(new UFDouble(2));}},
                    new HashMap<String, UFDouble>() {{put(T.DEFOOD1 + T.CATE5, new UFDouble(2));}});
            item.setCateCapMap(optionCateSet.getCateCapMap());
            item.setOrigCateCapMap(optionCateSet.getCateCapMap());
            ret.add(item);
        }
        return ret;
    }

    private List<TrolleyItem> T15() {
        List<TrolleyItem> ret = new ArrayList<>();
        for (int i = 1; i <= 28; i++) {
            TrolleyItem item = new TrolleyItem();
            item.setRecipe(T.RECIPE1);
            item.setSrcAssemblyB("" + i);
            item.setSpace(T.FSPACE);
            item.setPlanServer("PLANSERVER");
            item.setPlanSeat("PLANSEAT");
            item.setKitchen("KITCHEN");
            item.setInstalledMap(new HashMap<String, UFDouble>());
            item.setDeviceID("deviceid000000000015");
            item.setDeviceType("JK1CZ001");
            item.setDeviceNbr("devicenbr015");
            item.setStartIndex(i);
            item.setEndIndex(i);
            item.sethPart(i <= 14 ? "1" : "2");
            item.setvPart("1");
            item.setWide(false);
            item.setLongDefoodKey(new LinkedHashSet<String>());
            item.setShortDefoodKey(new LinkedHashSet<String>());
            item.setSpecialPriority(true);
            item.setInstalledIDs(new LinkedHashSet<String>());
            item.setvCellNumber(1);
            item.settCellNumber(1);
            item.setFree(false);
            item.setNote("");
            item.setSpecicalInstalled(false);
            item.setForHot(true);
            item.setCategories(new HashSet<String>() {{add(T.DEFOOD1 + T.CATE1);}});
            OptionCateSet optionCateSet = new OptionCateSet(item.getCategories(),
                    new HashSet<UFDouble>() {{add(new UFDouble(2));}},
                    new HashMap<String, UFDouble>() {{put(T.DEFOOD1 + T.CATE1, new UFDouble(2));}});
            item.setCateCapMap(optionCateSet.getCateCapMap());
            item.setOrigCateCapMap(optionCateSet.getCateCapMap());
            ret.add(item);
        }
        return ret;
    }

    private List<TrolleyItem> T1() {
        List<TrolleyItem> ret = new ArrayList<>();
        for (int i = 1; i <= 28; i++) {
            TrolleyItem item = new TrolleyItem();
            item.setRecipe(T.RECIPE1);
            item.setSrcAssemblyB("" + i);
            item.setSpace(T.FSPACE);
            item.setPlanServer("PLANSERVER");
            item.setPlanSeat("PLANSEAT");
            item.setKitchen("KITCHEN");
            item.setInstalledMap(new HashMap<String, UFDouble>());
            item.setDeviceID("deviceid000000000001");
            item.setDeviceType("JK1CZ001");
            item.setDeviceNbr("devicenbr01");
            item.setStartIndex(i);
            item.setEndIndex(i);
            item.sethPart(i <= 14 ? "1" : "2");
            item.setvPart("1");
            item.setWide(false);
            item.setLongDefoodKey(new LinkedHashSet<String>());
            item.setShortDefoodKey(new LinkedHashSet<String>());
            item.setSpecialPriority(false);
            item.setInstalledIDs(new LinkedHashSet<String>());
            item.setvCellNumber(1);
            item.settCellNumber(1);
            item.setFree(false);
            item.setNote("");
            item.setSpecicalInstalled(false);
            item.setForHot(true);
            item.setCategories(new HashSet<String>() {{add(T.DEFOOD1 + T.CATE1);}});
            OptionCateSet optionCateSet = new OptionCateSet(item.getCategories(),
                    new HashSet<UFDouble>() {{add(new UFDouble(2));}},
                    new HashMap<String, UFDouble>() {{put(T.DEFOOD1 + T.CATE1, new UFDouble(2));}});
            item.setCateCapMap(optionCateSet.getCateCapMap());
            item.setOrigCateCapMap(optionCateSet.getCateCapMap());
            ret.add(item);
        }
        return ret;
    }

    public Map<String, Map<String, Map<String, DeviceItem>>> analyseRepos(List<TrolleyItem> repos) {
//		array(
//			"pk_space1" => [
//			                "devicetype1" =>
//			                [
//							 deviceID => {isPrior},
//							]
//			              ]
//		);
        Map<String, Map<String, Map<String, DeviceItem>>> ret = new HashMap<String, Map<String, Map<String, DeviceItem>>>();
        for (TrolleyItem reposItem : repos) {
            String space = reposItem.getSpace();
            String deviceType = reposItem.isForHot() ? "true" : "false";
            String deviceId = reposItem.getDeviceID();
            boolean isPrior4Spec = reposItem.isSpecialPriority();
            if (ret.containsKey(space)) {
                Map<String, Map<String, DeviceItem>> deviceTypeMap = ret.get(space);
                Map<String, DeviceItem> devicesMap;
                if (deviceTypeMap.containsKey(deviceType)) {
                    devicesMap = deviceTypeMap.get(deviceType);
                    if (!devicesMap.containsKey(deviceId)) {
                        DeviceItem device = new DeviceItem();
                        device.setPrior4Spec(isPrior4Spec);
                        int order = devicesMap.size() + 1;
                        device.setOrder(order);
                        devicesMap.put(deviceId, device);
                    }
                } else {
                    devicesMap = new HashMap<String, DeviceItem>();
                    DeviceItem device = new DeviceItem();
                    device.setPrior4Spec(isPrior4Spec);
                    device.setOrder(1);
                    devicesMap.put(deviceId, device);
                    deviceTypeMap.put(deviceType, devicesMap);
                }
            } else {
                Map<String, Map<String, DeviceItem>> deviceTypeMap = new HashMap<String, Map<String, DeviceItem>>();
                Map<String, DeviceItem> devicesMap = new HashMap<String, DeviceItem>();
                DeviceItem device = new DeviceItem();
                device.setPrior4Spec(isPrior4Spec);
                device.setOrder(1);
                devicesMap.put(deviceId, device);
                deviceTypeMap.put(deviceType, devicesMap);
                ret.put(space, deviceTypeMap);
            }
        }
        return ret;
    }

    final class OptionCateSet {
        private final Set<String> cateSet;
        private final Set<UFDouble> capSet;
        private final Map<String, UFDouble> cateCapMap;
        public OptionCateSet(Set<String> cateSet, Set<UFDouble> capSet, Map<String, UFDouble> cateCapMap) {
            this.cateSet = cateSet;
            this.capSet = capSet;
            this.cateCapMap = cateCapMap;
        }
        public Map<String, UFDouble> getCateCapMap() {
            return cateCapMap;
        }
        public Set<String> getCateSet() {
            return cateSet;
        }
        public Set<UFDouble> getCapSet() {
            return capSet;
        }
    }


}
