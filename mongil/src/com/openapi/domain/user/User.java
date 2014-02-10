/**
 * 브랜드 VO
 */
package com.openapi.domain.user;

import java.sql.Date;

import com.openapi.domain.common.BaseDomain;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

@XStreamAlias("user")
@JsonSerialize(include = Inclusion.NON_DEFAULT)
public class User extends BaseDomain {

	private static final long serialVersionUID = 6111528523272119746L;

	/*****************************************************
	 * MPD_BRND_BASE
	 *****************************************************/
	/** 사용자 번호 */
    @JsonIgnore
    @XStreamOmitField
	private Integer usrNo;

	/** 사용자 아이디 */
	private String usrId;

	/** 비밀번호 */
    @JsonIgnore
    @XStreamOmitField
	private String pwd;

	/** 사용자명 */
	private String usrNm;

	/** 등록일시 */
	private Date regDtime;

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
	 * @return 필드 {@link #usrId}의 값을 반환한다.
	 */
	public String getUsrId() {
		return usrId;
	}

	/**
	 * 지정한 파라메타로 필드 {@link #usrId}의 값을 지정한다.
	 * @param usrId
	 */
	public void setUsrId(String usrId) {
		this.usrId = usrId;
	}

	/**
	 * @return 필드 {@link #pwd}의 값을 반환한다.
	 */
	public String getPwd() {
		return pwd;
	}

	/**
	 * 지정한 파라메타로 필드 {@link #pwd}의 값을 지정한다.
	 * @param pwd
	 */
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	/**
	 * @return 필드 {@link #usrNm}의 값을 반환한다.
	 */
	public String getUsrNm() {
		return usrNm;
	}

	/**
	 * 지정한 파라메타로 필드 {@link #usrNm}의 값을 지정한다.
	 * @param usrNm
	 */
	public void setUsrNm(String usrNm) {
		this.usrNm = usrNm;
	}

	/**
	 * @return 필드 {@link #regDtime}의 값을 반환한다.
	 */
	public Date getRegDtime() {
		return regDtime;
	}

	/**
	 * 지정한 파라메타로 필드 {@link #regDtime}의 값을 지정한다.
	 * @param regDtime
	 */
	public void setRegDtime(Date regDtime) {
		this.regDtime = regDtime;
	}


	

}
