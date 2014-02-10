/**
 * 공통코드 도메인
 */
package com.openapi.domain.common;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;


@XStreamAlias("commonCode")
@JsonSerialize(include=Inclusion.NON_DEFAULT)
public class CommonCode extends BaseDomain {

	private static final long serialVersionUID = 7493508604255122376L;

	/*MC_CD_BASE*/
	/** 코드 그룹 (원본테이블 : MC_CD_GRP_BASE.CD_GRP) */
	@JsonIgnore
    @XStreamOmitField
	private String cdGrp;
	
	/** 언어 유형 */
	@JsonIgnore
    @XStreamOmitField
	private String langTp;
	
	/** 코드 */
	private String cd;
	
	/** 코드 명 */
	private String cdNm;
	
	/** 코드 순번 */
	@JsonIgnore
    @XStreamOmitField
	private Integer cdSeq;
	
	/** 코드 문자 값 1 */
	@JsonIgnore
    @XStreamOmitField
	private String cdChrVal1;
	
	/** 코드 문자 값 2 */
	@JsonIgnore
    @XStreamOmitField
	private String cdChrVal2;
	
	/** 코드 문자 값 3 */
	@JsonIgnore
    @XStreamOmitField
	private String cdChrVal3;
	
	/** 코드 숫자 값 1 */
	@JsonIgnore
    @XStreamOmitField
	private String cdNumVal1;
	
	/** 코드 숫자 값 2 */
	@JsonIgnore
    @XStreamOmitField
	private String cdNumVal2;
	
	/** 코드 숫자 값 3 */
	@JsonIgnore
    @XStreamOmitField
	private String cdNumVal3;
	
	/** 코드 설명 */
	@JsonIgnore
    @XStreamOmitField
	private String cdDesc;
	
	/** 사용여부 */
	@JsonIgnore
    @XStreamOmitField
	private String useYn;

	/**
	 * Constructor
	 */
	public CommonCode() {}

	/**
	 * 필드 {@link #cdGrp}의 값을 반환한다.
	 * @return {@link #cdGrp}의 값
	 */
	public String getCdGrp() {
		return cdGrp;
	}

	/**
	 * 지정한 파라메타로 필드 {@link #cdGrp}의 값을 지정한다.
	 * @param cdGrp 필드 {@link #cdGrp}의 값
	 */
	public void setCdGrp(String cdGrp) {
		this.cdGrp = cdGrp;
	}

	/**
	 * 필드 {@link #langTp}의 값을 반환한다.
	 * @return {@link #langTp}의 값
	 */
	public String getLangTp() {
		return langTp;
	}

	/**
	 * 지정한 파라메타로 필드 {@link #langTp}의 값을 지정한다.
	 * @param langTp 필드 {@link #langTp}의 값
	 */
	public void setLangTp(String langTp) {
		this.langTp = langTp;
	}

	/**
	 * 필드 {@link #cd}의 값을 반환한다.
	 * @return {@link #cd}의 값
	 */
	public String getCd() {
		return cd;
	}

	/**
	 * 지정한 파라메타로 필드 {@link #cd}의 값을 지정한다.
	 * @param cd 필드 {@link #cd}의 값
	 */
	public void setCd(String cd) {
		this.cd = cd;
	}

	/**
	 * 필드 {@link #cdNm}의 값을 반환한다.
	 * @return {@link #cdNm}의 값
	 */
	public String getCdNm() {
		return cdNm;
	}

	/**
	 * 지정한 파라메타로 필드 {@link #cdNm}의 값을 지정한다.
	 * @param cdNm 필드 {@link #cdNm}의 값
	 */
	public void setCdNm(String cdNm) {
		this.cdNm = cdNm;
	}

	/**
	 * 필드 {@link #cdSeq}의 값을 반환한다.
	 * @return {@link #cdSeq}의 값
	 */
	public Integer getCdSeq() {
		return cdSeq;
	}

	/**
	 * 지정한 파라메타로 필드 {@link #cdSeq}의 값을 지정한다.
	 * @param cdSeq 필드 {@link #cdSeq}의 값
	 */
	public void setCdSeq(Integer cdSeq) {
		this.cdSeq = cdSeq;
	}

	/**
	 * 필드 {@link #cdChrVal1}의 값을 반환한다.
	 * @return {@link #cdChrVal1}의 값
	 */
	public String getCdChrVal1() {
		return cdChrVal1;
	}

	/**
	 * 지정한 파라메타로 필드 {@link #cdChrVal1}의 값을 지정한다.
	 * @param cdChrVal1 필드 {@link #cdChrVal1}의 값
	 */
	public void setCdChrVal1(String cdChrVal1) {
		this.cdChrVal1 = cdChrVal1;
	}

	/**
	 * 필드 {@link #cdChrVal2}의 값을 반환한다.
	 * @return {@link #cdChrVal2}의 값
	 */
	public String getCdChrVal2() {
		return cdChrVal2;
	}

	/**
	 * 지정한 파라메타로 필드 {@link #cdChrVal2}의 값을 지정한다.
	 * @param cdChrVal2 필드 {@link #cdChrVal2}의 값
	 */
	public void setCdChrVal2(String cdChrVal2) {
		this.cdChrVal2 = cdChrVal2;
	}

	/**
	 * 필드 {@link #cdChrVal3}의 값을 반환한다.
	 * @return {@link #cdChrVal3}의 값
	 */
	public String getCdChrVal3() {
		return cdChrVal3;
	}

	/**
	 * 지정한 파라메타로 필드 {@link #cdChrVal3}의 값을 지정한다.
	 * @param cdChrVal3 필드 {@link #cdChrVal3}의 값
	 */
	public void setCdChrVal3(String cdChrVal3) {
		this.cdChrVal3 = cdChrVal3;
	}

	/**
	 * 필드 {@link #cdNumVal1}의 값을 반환한다.
	 * @return {@link #cdNumVal1}의 값
	 */
	public String getCdNumVal1() {
		return cdNumVal1;
	}

	/**
	 * 지정한 파라메타로 필드 {@link #cdNumVal1}의 값을 지정한다.
	 * @param cdNumVal1 필드 {@link #cdNumVal1}의 값
	 */
	public void setCdNumVal1(String cdNumVal1) {
		this.cdNumVal1 = cdNumVal1;
	}

	/**
	 * 필드 {@link #cdNumVal2}의 값을 반환한다.
	 * @return {@link #cdNumVal2}의 값
	 */
	public String getCdNumVal2() {
		return cdNumVal2;
	}

	/**
	 * 지정한 파라메타로 필드 {@link #cdNumVal2}의 값을 지정한다.
	 * @param cdNumVal2 필드 {@link #cdNumVal2}의 값
	 */
	public void setCdNumVal2(String cdNumVal2) {
		this.cdNumVal2 = cdNumVal2;
	}

	/**
	 * 필드 {@link #cdNumVal3}의 값을 반환한다.
	 * @return {@link #cdNumVal3}의 값
	 */
	public String getCdNumVal3() {
		return cdNumVal3;
	}

	/**
	 * 지정한 파라메타로 필드 {@link #cdNumVal3}의 값을 지정한다.
	 * @param cdNumVal3 필드 {@link #cdNumVal3}의 값
	 */
	public void setCdNumVal3(String cdNumVal3) {
		this.cdNumVal3 = cdNumVal3;
	}

	/**
	 * 필드 {@link #cdDesc}의 값을 반환한다.
	 * @return {@link #cdDesc}의 값
	 */
	public String getCdDesc() {
		return cdDesc;
	}

	/**
	 * 지정한 파라메타로 필드 {@link #cdDesc}의 값을 지정한다.
	 * @param cdDesc 필드 {@link #cdDesc}의 값
	 */
	public void setCdDesc(String cdDesc) {
		this.cdDesc = cdDesc;
	}

	/**
	 * 필드 {@link #useYn}의 값을 반환한다.
	 * @return {@link #useYn}의 값
	 */
	public String getUseYn() {
		return useYn;
	}

	/**
	 * 지정한 파라메타로 필드 {@link #useYn}의 값을 지정한다.
	 * @param useYn 필드 {@link #useYn}의 값
	 */
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}


}
