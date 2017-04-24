package nc.impl.af.fltservice;

import nc.test.T;
import nc.vo.af.assembly_details.FoodItem;
import nc.vo.af.fltservice.AggFltserviceVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;

import java.util.*;

/**
 * Created by Princelo on 24/4/2017.
 */
public class RecipeAnalysisImpl {
    public Map<String, List<FoodItem>> analyze(AggFltserviceVO[] aggVOs) throws BusinessException {
        Map<String, List<FoodItem>> ret = new HashMap<>();
        String key1 = T.RECIPE1 + T.DEFOOD1 + T.CYCLE;
        String key2 = T.RECIPE1 + T.DEFOOD2 + T.CYCLE;
        String key3 = T.RECIPE1 + T.DEFOOD3 + T.CYCLE;
        String key4 = T.RECIPE2 + T.DEFOOD4 + T.CYCLE;
        String key5 = T.RECIPE2 + T.DEFOOD5 + T.CYCLE;
        String key6 = T.SPECRECIPE + T.SPECDEFOOD + T.CYCLE;
        final FoodItem f01 = M(T.MATERIAL1,  T.CATE1, 50, T.FSPACE, UFBoolean.FALSE, UFBoolean.FALSE, UFBoolean.TRUE, key1);
        final FoodItem f02 = M(T.MATERIAL2,  T.CATE5, 60, T.FSPACE, UFBoolean.FALSE, UFBoolean.FALSE, UFBoolean.FALSE, key1);
        final FoodItem f03 = M(T.MATERIAL3,  T.CATE5, 50, T.FSPACE, UFBoolean.FALSE, UFBoolean.FALSE, UFBoolean.FALSE, key1);
        final FoodItem f04 = M(T.MATERIAL4,  T.CATE6, 70, T.BSPACE, UFBoolean.FALSE, UFBoolean.FALSE, UFBoolean.FALSE, key2);
        final FoodItem f05 = M(T.MATERIAL5,  T.CATE7, 40, T.BSPACE, UFBoolean.FALSE, UFBoolean.FALSE, UFBoolean.FALSE, key2);
        final FoodItem f06 = M(T.MATERIAL6,  T.CATE7, 30, T.ESPACE, UFBoolean.FALSE, UFBoolean.FALSE, UFBoolean.FALSE, key3);
        final FoodItem f07 = M(T.MATERIAL7,  T.CATE7, 60, T.ESPACE, UFBoolean.FALSE, UFBoolean.FALSE, UFBoolean.FALSE, key3);
        final FoodItem f08 = M(T.MATERIAL8,  T.CATE1, 50, T.ESPACE, UFBoolean.FALSE, UFBoolean.FALSE, UFBoolean.TRUE, key3);
        final FoodItem f09 = M(T.MATERIAL9,  T.CATE3, 30, T.FSPACE, UFBoolean.FALSE, UFBoolean.FALSE, UFBoolean.FALSE, key4);
        final FoodItem f10 = M(T.MATERIAL10, T.CATE3, 30, T.FSPACE, UFBoolean.FALSE, UFBoolean.FALSE, UFBoolean.FALSE, key4);
        final FoodItem f11 = M(T.MATERIAL11, T.CATE3, 30, T.FSPACE, UFBoolean.FALSE, UFBoolean.FALSE, UFBoolean.FALSE, key4);
        final FoodItem f12 = M(T.MATERIAL12, T.CATE4, 60, T.BSPACE, UFBoolean.FALSE, UFBoolean.FALSE, UFBoolean.FALSE, key5);
        final FoodItem f13 = M(T.MATERIAL13, T.CATE2, 20, T.BSPACE, UFBoolean.FALSE, UFBoolean.FALSE, UFBoolean.TRUE, key5);
        final FoodItem f14 = M(T.MATERIAL14, T.CATE2, 20, T.BSPACE, UFBoolean.FALSE, UFBoolean.FALSE, UFBoolean.TRUE, key5);
        ret.put(key1, new ArrayList<FoodItem>() {{
            add(f01);
            add(f02);
            add(f03);
        }});
        ret.put(key2, new ArrayList<FoodItem>() {{
            add(f04);
            add(f05);
        }});
        ret.put(key3, new ArrayList<FoodItem>(){{
            add(f06);
            add(f07);
            add(f08);
        }});
        ret.put(key4, new ArrayList<FoodItem>(){{
            add(f09);
            add(f10);
            add(f11);
        }});
        ret.put(key5, new ArrayList<FoodItem>(){{
            add(f12);
            add(f13);
            add(f14);
        }});
        ret.put(key6, L(50));
        return ret;
    }

    FoodItem M(String material, String cate, int n, String space, UFBoolean bisspecial, UFBoolean bisdefood,
               UFBoolean bishot, String key) {
        FoodItem foodItem = new FoodItem();
        foodItem.setPkMaterial(material);
        foodItem.setCategory(cate);
        foodItem.setQuantity(new UFDouble(n));
        foodItem.setPkSpace(space);
        foodItem.setBisspecial(bisspecial);
        foodItem.setBisdefood(bisdefood);
        foodItem.setBishot(bishot);
        foodItem.setUniqueID(UUID.randomUUID().toString());
        foodItem.setLongDefoodKey(key);
        String defood = key.substring(20, 40);
        String recipe = key.substring(0, 20);
        String cycle = key.substring(40, 60);
        foodItem.setShortDefoodKey(defood);
        foodItem.setPkRecipe(recipe);
        foodItem.setPkCycle(cycle);
        foodItem.setInstalled(UFDouble.ZERO_DBL);
        foodItem.setRemain(new UFDouble(n));
        foodItem.setCategory(defood + cate);
        return foodItem;
    }

    List<FoodItem> L(int n) {
        List<FoodItem> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(S1());
        }
        for (int i = 0; i < n; i++) {
            list.add(S2());
        }
        return list;
    }

    List<FoodItem> L1(int n) {
        List<FoodItem> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(S1());
        }
        return list;
    }

    List<FoodItem> L2(int n) {
        List<FoodItem> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(S2());
        }
        return list;
    }

    FoodItem S1() {
        FoodItem r = new FoodItem();
        r.setPkMaterial("托盘");
        r.setCategory("spec_defood");
        r.setQuantity(UFDouble.ONE_DBL);
        r.setPkSpace(T.FSPACE);
        r.setBisspecial(UFBoolean.TRUE);
        r.setBisdefood(UFBoolean.TRUE);
        r.setBishot(UFBoolean.FALSE);
        r.setUniqueID(UUID.randomUUID().toString());
        r.setLongDefoodKey(T.SPECRECIPE + T.SPECDEFOOD + T.CYCLE);
        r.setShortDefoodKey(T.SPECDEFOOD);
        r.setPkRecipe(T.SPECRECIPE);
        r.setPkCycle(T.CYCLE);
        r.setInstalled(UFDouble.ZERO_DBL);
        r.setRemain(UFDouble.ONE_DBL);
        r.setCategory(T.SPECDEFOOD + "spec_defood");
        return r;
    }
    FoodItem S2() {
        FoodItem r = new FoodItem();
        r.setPkMaterial("热摆");
        r.setCategory("spec_defood");
        r.setQuantity(UFDouble.ONE_DBL);
        r.setPkSpace(T.FSPACE);
        r.setBisspecial(UFBoolean.TRUE);
        r.setBisdefood(UFBoolean.TRUE);
        r.setBishot(UFBoolean.TRUE);
        r.setUniqueID(UUID.randomUUID().toString());
        r.setLongDefoodKey(T.SPECRECIPE + T.SPECDEFOOD + T.CYCLE);
        r.setShortDefoodKey(T.SPECDEFOOD);
        r.setPkRecipe(T.SPECRECIPE);
        r.setPkCycle(T.CYCLE);
        r.setInstalled(UFDouble.ZERO_DBL);
        r.setRemain(UFDouble.ONE_DBL);
        r.setCategory(T.SPECDEFOOD + "spec_defood");
        return r;
    }
}
