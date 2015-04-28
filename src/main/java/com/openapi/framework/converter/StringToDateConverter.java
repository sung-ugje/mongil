/**
 * String -> Date Converter
 */
package com.openapi.framework.converter;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

import com.openapi.framework.utils.StringUtil;


public class StringToDateConverter implements Converter<String, Date> {

	public Date convert(String source) {
		if (!"".equals(StringUtil.trimToEmpty(source))) {
			Date parsed = null;
			SimpleDateFormat formatyMd = new SimpleDateFormat("yyyyMMdd");
			SimpleDateFormat formatyMdhm = new SimpleDateFormat("yyyyMMddHHmm");
			SimpleDateFormat formatyMdHms = new SimpleDateFormat("yyyyMMddHHmmss");
			
			try {
				String trimSource = StringUtil.remove(source, "-");
				trimSource = StringUtil.remove(trimSource, "/");
				trimSource = StringUtil.remove(trimSource, ".");
				trimSource = StringUtil.remove(trimSource, ":");
				trimSource = StringUtil.remove(trimSource, " ");
				
				formatyMd.setLenient(false);
				formatyMdhm.setLenient(false);
				formatyMdHms.setLenient(false);
				
				if (trimSource.length() == 8) {
					parsed = formatyMd.parse(trimSource);
				} else if (trimSource.length() == 12) {
					parsed = formatyMdhm.parse(trimSource);
				} else if (trimSource.length() == 14) {
					parsed = formatyMdHms.parse(trimSource);
				}
				
			} catch (Exception e) {
				/*입력된 문자열이 날짜 형식이 아님*/
				StringUtil.printExceptionTrace(e);
				
				return null;
			}
			
			return parsed;
			
		} else {
			return null;
		}
	}
}