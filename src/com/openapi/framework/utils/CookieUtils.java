package com.openapi.framework.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtils {

	/**
	 * 쿠키 저장
	 * @param response
	 * @param cookieName
	 * @param cookieValue
	 * @param iExpireDate
	 */
	public static void setCookie(HttpServletResponse response, String cookieDomain, String cookieName, String cookieValue, int iExpireDate) {
		Cookie cookie = null;
		
		try {
			cookie = new Cookie(cookieName, URLEncoder.encode(cookieValue, "UTF-8"));
			
			if (iExpireDate > 0) {
				cookie.setMaxAge(60 * 60 * 24 * iExpireDate);
			}
			
			if (StringUtil.isNotEmpty(cookieDomain)) {
				cookie.setDomain(cookieDomain);
			}
			
			cookie.setPath("/");
			
			response.addCookie(cookie);
			
		} catch (UnsupportedEncodingException uee) {
			StringUtil.printExceptionTrace(uee);
		}
	}

	/**
	 * 쿠키값 조회
	 * 
	 * @param request
	 * @param cookieName
	 * @return
	 */
	public static String getCookie(HttpServletRequest request, String cookieName) {
		String strResult = "";
		Cookie cookie = getCookieObject(request, cookieName);
		
		try {
			if (cookie != null) {
				strResult = URLDecoder.decode(StringUtil.trimToEmpty(cookie.getValue()), "UTF-8");
			}
			
		} catch (UnsupportedEncodingException uee) {
			StringUtil.printExceptionTrace(uee);
		}
		
		return strResult;
	}

	public static Cookie getCookieObject(HttpServletRequest request, String cookieName) {
		Cookie[] cookies = request.getCookies();
		
		if (cookies != null && cookies.length > 0) {
			for (Cookie cookie : cookies) {
				if (StringUtil.trimToEmpty(cookieName).equalsIgnoreCase(StringUtil.trimToEmpty(cookie.getName()))) {
					return cookie;
				}
			}
		}
		
		return null;
	}

	/**
	 * 쿠키 삭제
	 * 
	 * @param request
	 * @param cookieName
	 */
	public static void removeCookie(HttpServletRequest request, HttpServletResponse response, String cookieDomain, String cookieName) {
		Cookie cookie = getCookieObject(request, cookieName);
		
		if (cookie != null) {
			cookie.setMaxAge(-1);
			if (StringUtil.isNotEmpty(cookieDomain)) {
				cookie.setDomain(cookieDomain);
			}
			
			cookie.setPath("/");
			response.addCookie(cookie);
		}
	}
}