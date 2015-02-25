/**
 * Spring Message Utils
 */
package com.openapi.framework.utils;

import org.springframework.context.support.MessageSourceAccessor;


public class MessageUtils {
	
	private static MessageSourceAccessor msa;

	/**
	 * Constructor
	 */
	private MessageUtils() {}

	/**
	 * @param msa the {@link #msa} to set
	 */
	public void setMsa(MessageSourceAccessor msa) {
		MessageUtils.msa = msa;
	}

	/**
	 * 코드값에 따른 메시지 출력
	 * @param code
	 * @return
	 */
	public static String getMessage(String code) {
		code = StringUtil.trimToEmpty(code);
		
		if ("".equals(code)) {
			return "";
		}
		
		return msa.getMessage(code);
	}
	
	/**
	 * 코드값에 따른 메시지 출력
	 * @param code
	 * @param params
	 * @return
	 */
	public static String getMessage(String code, Object...params) {
		code = StringUtil.trimToEmpty(code);
		
		if ("".equals(code)) {
			return "";
		}
		
		String[] params1 = null;
		
		if (params != null && params.length > 0) {
			params1 = new String[params.length];
			
			int i = 0;
			
			for (Object obj : params) {
				String param = "";
				
				if (obj != null) {
					if (obj instanceof String) {
						param = (String) obj;
						
					} else if (obj instanceof Number || obj instanceof Boolean) {
						param = String.valueOf(obj);
						
					} else {
						param = (String) obj;
					}
				}
				
				params1[i] = param;
				
				i++;
			}
		}
		
		return msa.getMessage(code, params1);
	}
}
