/**
 * 관리/전시 브랜드 Service I/F
 */
package com.openapi.service.user;

import java.util.List;

import com.openapi.domain.user.User;

public interface UserService {
	
	/**
	 * 관리브랜드 목록 조회
	 * @param cretStrtDtime : 기간검색 - 등록시작일자
	 * @param cretEndDtime : 기간검색 - 등록종료일자
	 * @param mgmtBrndNm : 관리브랜드명
	 * @param brndNo : 브랜드번호
	 * @param brndTpCd : 브랜드 유형코드
	 * @param useYn : 사용여부
	 * @param confStatCd : 승인처리상태
	 * @param isPaging : 페이징 처리 여부
	 * @param pg : 현재 페이지 번호
	 * @param ps : 페이지 사이즈
	 * @return
	 * @throws Exception
	 */
	public String createUser(User user) throws Exception;

	/**
	 * 특정 관리브랜드에 대한 하위 전시브랜드 목록 조회
	 * @param brndNo : 관리 브랜드 번호
	 * @return
	 * @throws Exception
	 */
	public List<User> getUserList(User user) throws Exception;
	
	/**
	 * 전시브랜드번호에 대한 전시브랜드속성번호 목록 조회
	 * @param dispBrndNo : 전시 브랜드 번호
	 * @return
	 * @throws Exception
	 */
	public List<User> getDispBrandAttrNoList(Integer dispBrndNo) throws Exception;
}
