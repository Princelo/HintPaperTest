import nc.impl.af.fltservice.HintPaperGeneratorImpl;
import nc.vo.af.fltservice.AggFltserviceVO;
import nc.vo.pub.BusinessException;

/**
 * Created by Princelo on 28/3/2017.
 */
public class Main {
    public static void main(String[] args) {
        HintPaperGeneratorImpl generator = new HintPaperGeneratorImpl();
        try {
            generator.generate(new AggFltserviceVO[] { new AggFltserviceVO() });
        } catch (BusinessException e) {
            e.printStackTrace();
        }
    }
}
