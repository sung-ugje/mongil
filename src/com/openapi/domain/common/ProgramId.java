/**
 * 
 */
package com.openapi.domain.common;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("programId")
@JsonSerialize(include=Inclusion.NON_DEFAULT)
public class ProgramId extends BaseDomain {

	private static final long serialVersionUID = -8889938936983558648L;

	/****************************************************
	 * MC_OA_PGM_ID
	 ****************************************************/
	/** 프로그램 ID */
	private String pgmId;
	
	/** SQL Mapper Method */
	private String mapperApi;
	
	/** 프로그램 설명 */
	private String pgmDesc;

	/**
	 * 필드 {@link #pgmId}의 값을 반환한다.
	 * @return {@link #pgmId}의 값
	 */
	public String getPgmId() {
		return pgmId;
	}

	/**
	 * 지정한 파라메타로 필드 {@link #pgmId}의 값을 지정한다.
	 * @param pgmId 필드 {@link #pgmId}의 값
	 */
	public void setPgmId(String pgmId) {
		this.pgmId = pgmId;
	}

	/**
	 * 필드 {@link #mapperApi}의 값을 반환한다.
	 * @return {@link #mapperApi}의 값
	 */
	public String getMapperApi() {
		return mapperApi;
	}

	/**
	 * 지정한 파라메타로 필드 {@link #mapperApi}의 값을 지정한다.
	 * @param mapperApi 필드 {@link #mapperApi}의 값
	 */
	public void setMapperApi(String mapperApi) {
		this.mapperApi = mapperApi;
	}

	/**
	 * 필드 {@link #pgmDesc}의 값을 반환한다.
	 * @return {@link #pgmDesc}의 값
	 */
	public String getPgmDesc() {
		return pgmDesc;
	}

	/**
	 * 지정한 파라메타로 필드 {@link #pgmDesc}의 값을 지정한다.
	 * @param pgmDesc 필드 {@link #pgmDesc}의 값
	 */
	public void setPgmDesc(String pgmDesc) {
		this.pgmDesc = pgmDesc;
	}


}
