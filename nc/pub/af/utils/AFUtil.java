package nc.pub.af.utils;

/**
 * AF工具类
 * 
 * @author thx
 * 
 */
public class AFUtil {

	/**
	 * clone Serializable 对象
	 * 
	 * @param object
	 * @return
	 */
	public static java.lang.Object clone(java.io.Serializable object) {
		return org.apache.commons.lang3.SerializationUtils.clone(object);
	}

	
	
}
