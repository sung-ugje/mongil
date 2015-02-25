/**
 * 인증 토큰 Constants
 */
package com.openapi.framework.utils;

import java.util.UUID;



public class TokenUtils {

	/**
	 * Constructor
	 */
	private TokenUtils() {}
	
	/**
	 * 인증 토큰 생성
	 * @return
	 */
	public static String makeToken() {
		return DateUtils.getFullDate().replaceAll("[\\s-:]", "") + UUID.randomUUID().toString().replace("-", "");
	}
}
