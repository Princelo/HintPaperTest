package nc.vo.pubapp.pattern.exception;

import nc.vo.pub.BusinessException;

/**
 * Created by Princelo on 24/4/2017.
 */
public class ExceptionUtils {
    public static void wrappBusinessException(String message) {
        throw new RuntimeException(new BusinessException(message));
    }

}
