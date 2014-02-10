/**
 * 세션 정보 VO
 */
package com.openapi.domain.session;

import java.io.Serializable;
import java.util.Date;
import java.util.Locale;

import com.openapi.framework.utils.StringUtil;


public class SessionInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2023373395100554240L;

	/**로그인 정보*/
	private LoginInfo loginInfo;
	
	/**로그인 일시*/
	private Date loginDateTime;
	
	/**로그인 IP 주소*/
	private String loginIp;
	
	/**Language*/
	private Locale locale;
	
	/**세션 아이디*/
	private String sessionId;
	
	/**
	 * 로그인 여부 (true|false)
	 * @return
	 */
	public boolean isLogin() {
		return (this.loginInfo != null && !"".equals(StringUtil.trimToEmpty(loginInfo.getToken())));
	}
	
	public SessionInfo() {}

	/**
	 * 필드 {@link #loginInfo}의 값을 반환한다.
	 * @return {@link #loginInfo}의 값
	 */
	public LoginInfo getLoginInfo() {
		return loginInfo;
	}

	/**
	 * 지정한 파라메타로 필드 {@link #loginInfo}의 값을 지정한다.
	 * @param loginInfo 필드 {@link #loginInfo}의 값
	 */
	public void setLoginInfo(LoginInfo loginInfo) {
		this.loginInfo = loginInfo;
	}

	/**
	 * 필드 {@link #loginDateTime}의 값을 반환한다.
	 * @return {@link #loginDateTime}의 값
	 */
	public Date getLoginDateTime() {
		return loginDateTime;
	}

	/**
	 * 지정한 파라메타로 필드 {@link #loginDateTime}의 값을 지정한다.
	 * @param loginDateTime 필드 {@link #loginDateTime}의 값
	 */
	public void setLoginDateTime(Date loginDateTime) {
		this.loginDateTime = loginDateTime;
	}

	/**
	 * 필드 {@link #loginIp}의 값을 반환한다.
	 * @return {@link #loginIp}의 값
	 */
	public String getLoginIp() {
		return loginIp;
	}

	/**
	 * 지정한 파라메타로 필드 {@link #loginIp}의 값을 지정한다.
	 * @param loginIp 필드 {@link #loginIp}의 값
	 */
	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	/**
	 * 필드 {@link #locale}의 값을 반환한다.
	 * @return {@link #locale}의 값
	 */
	public Locale getLocale() {
		return locale;
	}

	/**
	 * 지정한 파라메타로 필드 {@link #locale}의 값을 지정한다.
	 * @param locale 필드 {@link #locale}의 값
	 */
	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	/**
	 * 필드 {@link #sessionId}의 값을 반환한다.
	 * @return {@link #sessionId}의 값
	 */
	public String getSessionId() {
		return sessionId;
	}

	/**
	 * 지정한 파라메타로 필드 {@link #sessionId}의 값을 지정한다.
	 * @param sessionId 필드 {@link #sessionId}의 값
	 */
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

}