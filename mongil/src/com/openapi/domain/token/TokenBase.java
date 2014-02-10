/**
 * 
 */
package com.openapi.domain.token;


import com.openapi.domain.common.BaseDomain;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/** 
 * 오픈 API 사용자 인증키 관리
 * @author    OhEunJu 
 * @date      2013. 6. 24.
 * @version   1.0 
 */
@XStreamAlias("tokenBase")
@JsonSerialize(include=Inclusion.NON_DEFAULT)
public class TokenBase extends BaseDomain {

	private static final long serialVersionUID = 7086557439230046368L;

	/** 사용자 번호 */
	private Integer usrNo;
	
	/**사용유무*/
	private String useYn;
	
	/**승인상태코드*/
	private String confStatCd;
	
	/**오픈API 접속 유형코드 (CG_MC058)*/
	private String connectTpCd;
	
	/**오픈API 솔루션 유형코드 (CG_MC059)*/
	private String solutionTpCd;
	
	
	public TokenBase() {}


	/**
	 * @return 필드 {@link #usrNo}의 값을 반환한다.
	 */
	public Integer getUsrNo() {
		return usrNo;
	}


	/**
	 * 지정한 파라메타로 필드 {@link #usrNo}의 값을 지정한다.
	 * @param usrNo
	 */
	public void setUsrNo(Integer usrNo) {
		this.usrNo = usrNo;
	}


	/**
	 * @return 필드 {@link #useYn}의 값을 반환한다.
	 */
	public String getUseYn() {
		return useYn;
	}


	/**
	 * 지정한 파라메타로 필드 {@link #useYn}의 값을 지정한다.
	 * @param useYn
	 */
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}


	/**
	 * @return 필드 {@link #confStatCd}의 값을 반환한다.
	 */
	public String getConfStatCd() {
		return confStatCd;
	}


	/**
	 * 지정한 파라메타로 필드 {@link #confStatCd}의 값을 지정한다.
	 * @param confStatCd
	 */
	public void setConfStatCd(String confStatCd) {
		this.confStatCd = confStatCd;
	}


	/**
	 * @return 필드 {@link #connectTpCd}의 값을 반환한다.
	 */
	public String getConnectTpCd() {
		return connectTpCd;
	}


	/**
	 * 지정한 파라메타로 필드 {@link #connectTpCd}의 값을 지정한다.
	 * @param connectTpCd
	 */
	public void setConnectTpCd(String connectTpCd) {
		this.connectTpCd = connectTpCd;
	}


	/**
	 * @return 필드 {@link #solutionTpCd}의 값을 반환한다.
	 */
	public String getSolutionTpCd() {
		return solutionTpCd;
	}


	/**
	 * 지정한 파라메타로 필드 {@link #solutionTpCd}의 값을 지정한다.
	 * @param solutionTpCd
	 */
	public void setSolutionTpCd(String solutionTpCd) {
		this.solutionTpCd = solutionTpCd;
	}

}
