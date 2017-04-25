package nc.vo.af.fltservice;

/**
 * Created by Princelo on 24/4/2017.
 */
public class AggFltserviceVO {
    private FltserviceHVO hvo;
    private Fltservice_defoodVO[] dvos;

    public FltserviceHVO getParentVO() {
        if (null == hvo) {
            hvo = new FltserviceHVO();
        }
        return hvo;
    }

    public Fltservice_defoodVO[] getFltservice_defoodVO() {
        if (null == dvos) {
            dvos = new Fltservice_defoodVO[] {
                    new Fltservice_defoodVO(),
                    new Fltservice_defoodVO(),
                    new Fltservice_defoodVO(),
                    new Fltservice_defoodVO(),
                    new Fltservice_defoodVO()
            };
        }
        return dvos;
    }
}
