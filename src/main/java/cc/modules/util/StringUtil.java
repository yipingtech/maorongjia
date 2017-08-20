package cc.modules.util;

/**
 * @author Administrator
 * @version 1.1
 */
public class StringUtil {

	public static boolean isBlank(String str) {
		boolean event = false;
		if ("".equals(str) || str == null)
			event = true;
		return event;
	}

	public static boolean isNotBlank(String str) {
		boolean event = false;
		if ((!"".equals(str)) && str != null)
			event = true;
		return event;
	}

	public static String getGetterMethodName(String fieldName) {
		String getter = "get" + getFirstLetter(fieldName) + fieldName.substring(1);
		return getter;
	}

	public static String getSetterMethodName(String fieldName) {
		String setter = "set" + getFirstLetter(fieldName) + fieldName.substring(1);
		return setter;
	}

	private static String getFirstLetter(String str) {
		String firstLetter = str.substring(0, 1).toUpperCase();
		return firstLetter;
	}

}
