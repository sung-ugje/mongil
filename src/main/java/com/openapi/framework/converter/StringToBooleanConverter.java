/**
 * String -> Boolean Converter
 */
package com.openapi.framework.converter;

import org.springframework.core.convert.converter.Converter;

import com.openapi.framework.utils.StringUtil;


public class StringToBooleanConverter implements Converter<String, Boolean> {

	public Boolean convert(String source) {
		if (!"".equals(StringUtil.trimToEmpty(source))) {
			if ("true".equalsIgnoreCase(source)) {
				return true;
				
			} else if ("false".equalsIgnoreCase(source)) {
				return false;
			}
			
		} else {
			return false;
		}
		
		return false;
	}
}