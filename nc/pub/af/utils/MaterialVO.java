package nc.pub.af.utils;

/**
 * Created by Princelo on 24/4/2017.
 */
public class MaterialVO {
    private String name;
    private String code;
    public MaterialVO(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
