/**
 * Base VO
 */
package com.openapi.domain.common;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.thoughtworks.xstream.annotations.XStreamOmitField;


public class BaseDomain implements Serializable {

    private static final long serialVersionUID = -8108515514623955507L;
    
    /** 인증토큰 */
    @JsonIgnore
    @XStreamOmitField
    private String token;
    
    /**세션 로그인 사용자 번호*/
    @JsonIgnore
    @XStreamOmitField
    private Integer loginUsrNo;
    
    /** 프로그램ID */
    @JsonIgnore
    @XStreamOmitField
    private String pgmId;
    
    /** 생성일시 */
	private String cretDtime;
	
	/** 생성자 번호 */
    @JsonIgnore
    @XStreamOmitField
	private Integer cretUsrNo;
    
	/** 생성프로그램ID */
    @JsonIgnore
    @XStreamOmitField
	private String cretPgmId;
	
	/** 변경일시 */
	private String chgDtime;
	
	/** 변경자 번호 */
    @JsonIgnore
    @XStreamOmitField
	private Integer chgUsrNo;
    
	
	/** 변경 프로그램ID */
    @JsonIgnore
    @XStreamOmitField
	private String chgPgmId;
	
	/** 자바에서 셋팅한 현재 일시 */
    @JsonIgnore
    @XStreamOmitField
	private String curDtime;

	/** 페이징 사용여부 */
    @JsonIgnore
    @XStreamOmitField
	private Boolean isPaging = false;
	
	/** 현재 페이지 번호 */
    @JsonIgnore
    @XStreamOmitField
	private Integer pg = 1;
	
	/** 페이징 사이즈 */
    @JsonIgnore
    @XStreamOmitField
	private Integer ps = 10;
	
	/** 페이징 블럭 */
    @JsonIgnore
    @XStreamOmitField
	private Integer pb = 10;
	
	/** 행 번호 */
	private Integer rowNumber = 0;
	
	/** 행 번호 역순 */
	private Integer reverseRowNumber = 0;
	
	/** 총 레코드 수 */
	private Integer totalRecordCount = 0;
	
	/** 총 페이지 수 */
	private Integer totalPages = 0;
	
	/**검색기간 타입*/
	private String srchDateType;
	
	/**검색기간 시작일*/
	private String srchStrtDtime;
	
	/**검색기간 종료일*/
	private String srchEndDtime;
	
	/**조회조건*/
	private String srchType;
	
	/**조회 키워드*/
	private String srchKeyword;
	
    /**
     * Constructor
     */
    public BaseDomain() {}

	/**
	 * @return 필드 {@link #token}의 값을 반환한다.
	 */
	public String getToken() {
		return token;
	}

	/**
	 * 지정한 파라메타로 필드 {@link #token}의 값을 지정한다.
	 * @param token
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * @return 필드 {@link #loginUsrNo}의 값을 반환한다.
	 */
	public Integer getLoginUsrNo() {
		return loginUsrNo;
	}

	/**
	 * 지정한 파라메타로 필드 {@link #loginUsrNo}의 값을 지정한다.
	 * @param loginUsrNo
	 */
	public void setLoginUsrNo(Integer loginUsrNo) {
		this.loginUsrNo = loginUsrNo;
	}

	/**
	 * @return 필드 {@link #pgmId}의 값을 반환한다.
	 */
	public String getPgmId() {
		return pgmId;
	}

	/**
	 * 지정한 파라메타로 필드 {@link #pgmId}의 값을 지정한다.
	 * @param pgmId
	 */
	public void setPgmId(String pgmId) {
		this.pgmId = pgmId;
	}

	/**
	 * @return 필드 {@link #cretDtime}의 값을 반환한다.
	 */
	public String getCretDtime() {
		return cretDtime;
	}

	/**
	 * 지정한 파라메타로 필드 {@link #cretDtime}의 값을 지정한다.
	 * @param cretDtime
	 */
	public void setCretDtime(String cretDtime) {
		this.cretDtime = cretDtime;
	}

	/**
	 * @return 필드 {@link #cretUsrNo}의 값을 반환한다.
	 */
	public Integer getCretUsrNo() {
		return cretUsrNo;
	}

	/**
	 * 지정한 파라메타로 필드 {@link #cretUsrNo}의 값을 지정한다.
	 * @param cretUsrNo
	 */
	public void setCretUsrNo(Integer cretUsrNo) {
		this.cretUsrNo = cretUsrNo;
	}

	/**
	 * @return 필드 {@link #cretPgmId}의 값을 반환한다.
	 */
	public String getCretPgmId() {
		return cretPgmId;
	}

	/**
	 * 지정한 파라메타로 필드 {@link #cretPgmId}의 값을 지정한다.
	 * @param cretPgmId
	 */
	public void setCretPgmId(String cretPgmId) {
		this.cretPgmId = cretPgmId;
	}

	/**
	 * @return 필드 {@link #chgDtime}의 값을 반환한다.
	 */
	public String getChgDtime() {
		return chgDtime;
	}

	/**
	 * 지정한 파라메타로 필드 {@link #chgDtime}의 값을 지정한다.
	 * @param chgDtime
	 */
	public void setChgDtime(String chgDtime) {
		this.chgDtime = chgDtime;
	}

	/**
	 * @return 필드 {@link #chgUsrNo}의 값을 반환한다.
	 */
	public Integer getChgUsrNo() {
		return chgUsrNo;
	}

	/**
	 * 지정한 파라메타로 필드 {@link #chgUsrNo}의 값을 지정한다.
	 * @param chgUsrNo
	 */
	public void setChgUsrNo(Integer chgUsrNo) {
		this.chgUsrNo = chgUsrNo;
	}

	/**
	 * @return 필드 {@link #chgPgmId}의 값을 반환한다.
	 */
	public String getChgPgmId() {
		return chgPgmId;
	}

	/**
	 * 지정한 파라메타로 필드 {@link #chgPgmId}의 값을 지정한다.
	 * @param chgPgmId
	 */
	public void setChgPgmId(String chgPgmId) {
		this.chgPgmId = chgPgmId;
	}

	/**
	 * @return 필드 {@link #curDtime}의 값을 반환한다.
	 */
	public String getCurDtime() {
		return curDtime;
	}

	/**
	 * 지정한 파라메타로 필드 {@link #curDtime}의 값을 지정한다.
	 * @param curDtime
	 */
	public void setCurDtime(String curDtime) {
		this.curDtime = curDtime;
	}

	/**
	 * @return 필드 {@link #isPaging}의 값을 반환한다.
	 */
	public Boolean getIsPaging() {
		return isPaging;
	}

	/**
	 * 지정한 파라메타로 필드 {@link #isPaging}의 값을 지정한다.
	 * @param isPaging
	 */
	public void setIsPaging(Boolean isPaging) {
		this.isPaging = isPaging;
	}

	/**
	 * @return 필드 {@link #pg}의 값을 반환한다.
	 */
	public Integer getPg() {
		return pg;
	}

	/**
	 * 지정한 파라메타로 필드 {@link #pg}의 값을 지정한다.
	 * @param pg
	 */
	public void setPg(Integer pg) {
		this.pg = pg;
	}

	/**
	 * @return 필드 {@link #ps}의 값을 반환한다.
	 */
	public Integer getPs() {
		return ps;
	}

	/**
	 * 지정한 파라메타로 필드 {@link #ps}의 값을 지정한다.
	 * @param ps
	 */
	public void setPs(Integer ps) {
		this.ps = ps;
	}

	/**
	 * @return 필드 {@link #pb}의 값을 반환한다.
	 */
	public Integer getPb() {
		return pb;
	}

	/**
	 * 지정한 파라메타로 필드 {@link #pb}의 값을 지정한다.
	 * @param pb
	 */
	public void setPb(Integer pb) {
		this.pb = pb;
	}

	/**
	 * @return 필드 {@link #rowNumber}의 값을 반환한다.
	 */
	public Integer getRowNumber() {
		return rowNumber;
	}

	/**
	 * 지정한 파라메타로 필드 {@link #rowNumber}의 값을 지정한다.
	 * @param rowNumber
	 */
	public void setRowNumber(Integer rowNumber) {
		this.rowNumber = rowNumber;
	}

	/**
	 * @return 필드 {@link #reverseRowNumber}의 값을 반환한다.
	 */
	public Integer getReverseRowNumber() {
		return reverseRowNumber;
	}

	/**
	 * 지정한 파라메타로 필드 {@link #reverseRowNumber}의 값을 지정한다.
	 * @param reverseRowNumber
	 */
	public void setReverseRowNumber(Integer reverseRowNumber) {
		this.reverseRowNumber = reverseRowNumber;
	}

	/**
	 * @return 필드 {@link #totalRecordCount}의 값을 반환한다.
	 */
	public Integer getTotalRecordCount() {
		return totalRecordCount;
	}

	/**
	 * 지정한 파라메타로 필드 {@link #totalRecordCount}의 값을 지정한다.
	 * @param totalRecordCount
	 */
	public void setTotalRecordCount(Integer totalRecordCount) {
		this.totalRecordCount = totalRecordCount;
	}

	/**
	 * @return 필드 {@link #totalPages}의 값을 반환한다.
	 */
	public Integer getTotalPages() {
		return totalPages;
	}

	/**
	 * 지정한 파라메타로 필드 {@link #totalPages}의 값을 지정한다.
	 * @param totalPages
	 */
	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}

	/**
	 * @return 필드 {@link #srchDateType}의 값을 반환한다.
	 */
	public String getSrchDateType() {
		return srchDateType;
	}

	/**
	 * 지정한 파라메타로 필드 {@link #srchDateType}의 값을 지정한다.
	 * @param srchDateType
	 */
	public void setSrchDateType(String srchDateType) {
		this.srchDateType = srchDateType;
	}

	/**
	 * @return 필드 {@link #srchStrtDtime}의 값을 반환한다.
	 */
	public String getSrchStrtDtime() {
		return srchStrtDtime;
	}

	/**
	 * 지정한 파라메타로 필드 {@link #srchStrtDtime}의 값을 지정한다.
	 * @param srchStrtDtime
	 */
	public void setSrchStrtDtime(String srchStrtDtime) {
		this.srchStrtDtime = srchStrtDtime;
	}

	/**
	 * @return 필드 {@link #srchEndDtime}의 값을 반환한다.
	 */
	public String getSrchEndDtime() {
		return srchEndDtime;
	}

	/**
	 * 지정한 파라메타로 필드 {@link #srchEndDtime}의 값을 지정한다.
	 * @param srchEndDtime
	 */
	public void setSrchEndDtime(String srchEndDtime) {
		this.srchEndDtime = srchEndDtime;
	}

	/**
	 * @return 필드 {@link #srchType}의 값을 반환한다.
	 */
	public String getSrchType() {
		return srchType;
	}

	/**
	 * 지정한 파라메타로 필드 {@link #srchType}의 값을 지정한다.
	 * @param srchType
	 */
	public void setSrchType(String srchType) {
		this.srchType = srchType;
	}

	/**
	 * @return 필드 {@link #srchKeyword}의 값을 반환한다.
	 */
	public String getSrchKeyword() {
		return srchKeyword;
	}

	/**
	 * 지정한 파라메타로 필드 {@link #srchKeyword}의 값을 지정한다.
	 * @param srchKeyword
	 */
	public void setSrchKeyword(String srchKeyword) {
		this.srchKeyword = srchKeyword;
	}

}