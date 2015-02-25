/**
 * 관리/전시 브랜드 Mapper
 */
package com.openapi.mapper.monster;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.openapi.domain.monster.Monster;

@Repository
public interface MonsterMapper {
	
	/**
	 * 특정 관리브랜드에 대한 하위 전시브랜드 목록 조회
	 * @param brand
	 * @return
	 * @throws Exception
	 */
	public List<Monster> selectMonsterList(Monster monster) throws Exception;
	

}
