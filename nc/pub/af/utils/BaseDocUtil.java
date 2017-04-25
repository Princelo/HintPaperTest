package nc.pub.af.utils;

/**
 * Created by Princelo on 24/4/2017.
 */
public class BaseDocUtil {
    public static MaterialVO getMaterialVO(String pkMaterial) {
        return new MaterialVO(pkMaterial, pkMaterial);
    }
}
