package com.openapi.domain.common;

import java.io.Serializable;

/**
 * 로그인 인증 정보 도메인
 *
 */
public class LoginCredentials implements Serializable {

	private static final long serialVersionUID = -5533240523696512796L;
	/**
	 * 로그인 아이디
	 */
	private String id = "";
	/**
	 * 로그인 비밀번호
	 */
	private String passwd = "";
	
	/**
	 * 필드 {@link #id}의 값을 반환한다.
	 * @return {@link #id}의 값
	 */
	public String getId() {
		return id;
	}
	/**
	 * 지정한 파라메타로 필드 {@link #id}의 값을 지정한다.
	 * @param id 필드 {@link #id}의 값
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 필드 {@link #passwd}의 값을 반환한다.
	 * @return {@link #passwd}의 값
	 */
	public String getPasswd() {
		return passwd;
	}
	/**
	 * 지정한 파라메타로 필드 {@link #passwd}의 값을 지정한다.
	 * @param passwd 필드 {@link #passwd}의 값
	 */
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	
}