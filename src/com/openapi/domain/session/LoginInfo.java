/**
 * 세션 로그인 정보 VO
 */
package com.openapi.domain.session;

import java.io.Serializable;

public class LoginInfo implements Serializable {

	private static final long serialVersionUID = -4185817262543192732L;

	/** 토큰 */
	private String token;
	
	/** 사용자 번호 */
	private Integer usrNo;

	
	/**
	 * Constructor
	 */
	public LoginInfo() {}


	/**
	 * 필드 {@link #token}의 값을 반환한다.
	 * @return {@link #token}의 값
	 */
	public String getToken() {
		return token;
	}


	/**
	 * 지정한 파라메타로 필드 {@link #token}의 값을 지정한다.
	 * @param token 필드 {@link #token}의 값
	 */
	public void setToken(String token) {
		this.token = token;
	}


	/**
	 * 필드 {@link #usrNo}의 값을 반환한다.
	 * @return {@link #usrNo}의 값
	 */
	public Integer getUsrNo() {
		return usrNo;
	}


	/**
	 * 지정한 파라메타로 필드 {@link #usrNo}의 값을 지정한다.
	 * @param usrNo 필드 {@link #usrNo}의 값
	 */
	public void setUsrNo(Integer usrNo) {
		this.usrNo = usrNo;
	}


}
