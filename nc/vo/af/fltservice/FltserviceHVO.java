package nc.vo.af.fltservice;

import nc.vo.pub.lang.UFDateTime;

/**
 * Created by Princelo on 24/4/2017.
 */
public class FltserviceHVO {
    public String getPk_legofavoyage() {
        return "leg";
    }
    public String getPk_airplane() {
        return "airplane";
    }
    public String getPk_airplanemodel() {
        return "airplanemodel";
    }
    public UFDateTime getDt_plandeparturetime() {
        return new UFDateTime();
    }
    public String getPk_fltno() {
        return "fltno";
    }
}
