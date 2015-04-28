/**
 * 관리/전시 브랜드 ServiceImpl
 */
package com.openapi.service.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openapi.domain.monster.Monster;
import com.openapi.mapper.monster.MonsterMapper;
import com.openapi.mapper.token.TokenMapper;
import com.openapi.service.BaseServiceImpl;
import com.openapi.service.monster.MonsterService;

@Service
public class MonsterServiceImpl extends BaseServiceImpl implements MonsterService {
	
	@Autowired private MonsterMapper monsterMapper;
	@Autowired private TokenMapper tokenMapper;
	

	/* (non-Javadoc)
	 * @see com.openapi.service.Monster.MonsterService#getDispBrandList
	 */
	
	public List<Monster> getMonsterList(Monster monster) throws Exception {
		
		return monsterMapper.selectMonsterList(monster);
	}

}
