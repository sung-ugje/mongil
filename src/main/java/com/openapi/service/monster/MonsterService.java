/**
 * 관리/전시 브랜드 Service I/F
 */
package com.openapi.service.monster;

import java.util.List;

import com.openapi.domain.monster.Monster;

public interface MonsterService {
	


	/**
	 * 특정 관리브랜드에 대한 하위 전시브랜드 목록 조회
	 * @param brndNo : 관리 브랜드 번호
	 * @return
	 * @throws Exception
	 */
	public List<Monster> getMonsterList(Monster monster) throws Exception;
	

}
