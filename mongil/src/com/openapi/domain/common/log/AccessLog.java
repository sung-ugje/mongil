/**
 * 오픈API 접속 로그 VO
 */
package com.openapi.domain.common.log;

import com.openapi.domain.common.BaseDomain;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import com.thoughtworks.xstream.annotations.XStreamAlias;


@XStreamAlias("accessLog")
@JsonSerialize(include=Inclusion.NON_DEFAULT)
public class AccessLog extends BaseDomain {
	
	private static final long serialVersionUID = 8951050963468611787L;

	/****************************************************
	 * MC_OA_LOG_BASE
	 ****************************************************/
	/**로그 순번*/
	private Integer accessSeq;
	
	/**API 접속일자*/
	private String accessDtime;
	
	/**접속 IP*/
	private String accessIp;
	
	/**대행업체 코드 (CG_MC059)*/
	private String solutionTpCd;
	
	/**CRUD TYPE (조회:R, 등록:C, 수정:U, 삭제:D)*/
	private String crudType;
	
	/**API 접속 URL*/
	private String accessUrl;
	
	/**실패 유무*/
	private String failYn;
	
	/**실패 사유*/
	private String failCause;
	
	/**등록/수정 JSON 파라미터*/
	private String jsonParam;
	
	/**
	 * Constructor
	 */
	public AccessLog() {}

	/**
	 * 필드 {@link #accessSeq}의 값을 반환한다.
	 * @return {@link #accessSeq}의 값
	 */
	public Integer getAccessSeq() {
		return accessSeq;
	}

	/**
	 * 지정한 파라메타로 필드 {@link #accessSeq}의 값을 지정한다.
	 * @param accessSeq 필드 {@link #accessSeq}의 값
	 */
	public void setAccessSeq(Integer accessSeq) {
		this.accessSeq = accessSeq;
	}

	/**
	 * 필드 {@link #accessDtime}의 값을 반환한다.
	 * @return {@link #accessDtime}의 값
	 */
	public String getAccessDtime() {
		return accessDtime;
	}

	/**
	 * 지정한 파라메타로 필드 {@link #accessDtime}의 값을 지정한다.
	 * @param accessDtime 필드 {@link #accessDtime}의 값
	 */
	public void setAccessDtime(String accessDtime) {
		this.accessDtime = accessDtime;
	}

	/**
	 * 필드 {@link #accessIp}의 값을 반환한다.
	 * @return {@link #accessIp}의 값
	 */
	public String getAccessIp() {
		return accessIp;
	}

	/**
	 * 지정한 파라메타로 필드 {@link #accessIp}의 값을 지정한다.
	 * @param accessIp 필드 {@link #accessIp}의 값
	 */
	public void setAccessIp(String accessIp) {
		this.accessIp = accessIp;
	}

	/**
	 * 필드 {@link #solutionTpCd}의 값을 반환한다.
	 * @return {@link #solutionTpCd}의 값
	 */
	public String getSolutionTpCd() {
		return solutionTpCd;
	}

	/**
	 * 지정한 파라메타로 필드 {@link #solutionTpCd}의 값을 지정한다.
	 * @param solutionTpCd 필드 {@link #solutionTpCd}의 값
	 */
	public void setSolutionTpCd(String solutionTpCd) {
		this.solutionTpCd = solutionTpCd;
	}

	/**
	 * 필드 {@link #crudType}의 값을 반환한다.
	 * @return {@link #crudType}의 값
	 */
	public String getCrudType() {
		return crudType;
	}

	/**
	 * 지정한 파라메타로 필드 {@link #crudType}의 값을 지정한다.
	 * @param crudType 필드 {@link #crudType}의 값
	 */
	public void setCrudType(String crudType) {
		this.crudType = crudType;
	}

	/**
	 * 필드 {@link #accessUrl}의 값을 반환한다.
	 * @return {@link #accessUrl}의 값
	 */
	public String getAccessUrl() {
		return accessUrl;
	}

	/**
	 * 지정한 파라메타로 필드 {@link #accessUrl}의 값을 지정한다.
	 * @param accessUrl 필드 {@link #accessUrl}의 값
	 */
	public void setAccessUrl(String accessUrl) {
		this.accessUrl = accessUrl;
	}

	/**
	 * 필드 {@link #failYn}의 값을 반환한다.
	 * @return {@link #failYn}의 값
	 */
	public String getFailYn() {
		return failYn;
	}

	/**
	 * 지정한 파라메타로 필드 {@link #failYn}의 값을 지정한다.
	 * @param failYn 필드 {@link #failYn}의 값
	 */
	public void setFailYn(String failYn) {
		this.failYn = failYn;
	}

	/**
	 * 필드 {@link #failCause}의 값을 반환한다.
	 * @return {@link #failCause}의 값
	 */
	public String getFailCause() {
		return failCause;
	}

	/**
	 * 지정한 파라메타로 필드 {@link #failCause}의 값을 지정한다.
	 * @param failCause 필드 {@link #failCause}의 값
	 */
	public void setFailCause(String failCause) {
		this.failCause = failCause;
	}

	/**
	 * 필드 {@link #jsonParam}의 값을 반환한다.
	 * @return {@link #jsonParam}의 값
	 */
	public String getJsonParam() {
		return jsonParam;
	}

	/**
	 * 지정한 파라메타로 필드 {@link #jsonParam}의 값을 지정한다.
	 * @param jsonParam 필드 {@link #jsonParam}의 값
	 */
	public void setJsonParam(String jsonParam) {
		this.jsonParam = jsonParam;
	}


}
