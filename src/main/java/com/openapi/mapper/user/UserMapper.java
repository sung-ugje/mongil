/**
 * 관리/전시 브랜드 Mapper
 */
package com.openapi.mapper.user;

import java.util.List;

import com.openapi.domain.user.User;

import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
	
	/**
	 * 관리브랜드 목록 조회
	 * @param brand
	 * @return
	 * @throws Exception
	 */
	public Integer insertUser(User user) throws Exception;
	
	/**
	 * 특정 관리브랜드에 대한 하위 전시브랜드 목록 조회
	 * @param brand
	 * @return
	 * @throws Exception
	 */
	public List<User> selectUserList(User user) throws Exception;
	
	/**
	 * 전시브랜드번호에 대한 전시브랜드속성번호 목록 조회
	 * @param brand
	 * @return
	 * @throws Exception
	 */
	public List<User> selectDispBrandAttrNoList(User brand) throws Exception;
}
