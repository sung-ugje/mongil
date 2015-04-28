package com.openapi.framework.utils;

import java.util.ResourceBundle;


public class EnvPropUtils {

	private static ResourceBundle resourceBundle;

	/**
	 * Constructor
	 */
	private EnvPropUtils() {}

	static {
		resourceBundle = ResourceBundle.getBundle("config.env");
	}

	public static String getPropertyValue(String key) {
		return StringUtil.trimToEmpty(resourceBundle.getString(StringUtil.trimToEmpty(key)));
	}
}
