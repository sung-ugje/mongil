/**
 * 몬스터 VO
 */
package com.openapi.domain.monster;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import com.openapi.domain.common.BaseDomain;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

@XStreamAlias("monster")
@JsonSerialize(include = Inclusion.NON_DEFAULT)
public class Monster extends BaseDomain {


	private static final long serialVersionUID = -3337454870045931067L;

	/*****************************************************
	 * MG_MONSTER_BASE 몬스터 기본
	 *****************************************************/
	/** 몬스터 번호 */
    @JsonIgnore
    @XStreamOmitField
	private Integer monNo;

	/** 몬스터명 */
	private String monNm;

	/** 몬스터 유형 */
	private String monTp;

	/** 등급 */
	private Integer grade;
	
	/** 체력 */
	private Integer stamina;
	
	/** 공격력 */
	private Integer attack;
	
	/** 방어력 */
	private Integer defense;

	/**
	 * 필드 {@link #monNo}의 값을 반환한다.
	 * @return {@link #monNo}의 값
	 */
	public Integer getMonNo() {
		return monNo;
	}

	/**
	 * 지정한 파라메타로 필드 {@link #monNo}의 값을 지정한다.
	 * @param monNo 필드 {@link #monNo}의 값
	 */
	public void setMonNo(Integer monNo) {
		this.monNo = monNo;
	}

	/**
	 * 필드 {@link #monNm}의 값을 반환한다.
	 * @return {@link #monNm}의 값
	 */
	public String getMonNm() {
		return monNm;
	}

	/**
	 * 지정한 파라메타로 필드 {@link #monNm}의 값을 지정한다.
	 * @param monNm 필드 {@link #monNm}의 값
	 */
	public void setMonNm(String monNm) {
		this.monNm = monNm;
	}

	/**
	 * 필드 {@link #monTp}의 값을 반환한다.
	 * @return {@link #monTp}의 값
	 */
	public String getMonTp() {
		return monTp;
	}

	/**
	 * 지정한 파라메타로 필드 {@link #monTp}의 값을 지정한다.
	 * @param monTp 필드 {@link #monTp}의 값
	 */
	public void setMonTp(String monTp) {
		this.monTp = monTp;
	}

	/**
	 * 필드 {@link #grade}의 값을 반환한다.
	 * @return {@link #grade}의 값
	 */
	public Integer getGrade() {
		return grade;
	}

	/**
	 * 지정한 파라메타로 필드 {@link #grade}의 값을 지정한다.
	 * @param grade 필드 {@link #grade}의 값
	 */
	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	/**
	 * 필드 {@link #stamina}의 값을 반환한다.
	 * @return {@link #stamina}의 값
	 */
	public Integer getStamina() {
		return stamina;
	}

	/**
	 * 지정한 파라메타로 필드 {@link #stamina}의 값을 지정한다.
	 * @param stamina 필드 {@link #stamina}의 값
	 */
	public void setStamina(Integer stamina) {
		this.stamina = stamina;
	}

	/**
	 * 필드 {@link #attack}의 값을 반환한다.
	 * @return {@link #attack}의 값
	 */
	public Integer getAttack() {
		return attack;
	}

	/**
	 * 지정한 파라메타로 필드 {@link #attack}의 값을 지정한다.
	 * @param attack 필드 {@link #attack}의 값
	 */
	public void setAttack(Integer attack) {
		this.attack = attack;
	}

	/**
	 * 필드 {@link #defense}의 값을 반환한다.
	 * @return {@link #defense}의 값
	 */
	public Integer getDefense() {
		return defense;
	}

	/**
	 * 지정한 파라메타로 필드 {@link #defense}의 값을 지정한다.
	 * @param defense 필드 {@link #defense}의 값
	 */
	public void setDefense(Integer defense) {
		this.defense = defense;
	}

	
	

}
