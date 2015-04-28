package com.openapi.mapper.token;

import com.openapi.domain.token.TokenBase;

import org.springframework.stereotype.Repository;


/** 
 * 오픈 API 사용자 인증키 관리 (Mapper)
 * @author    OhEunJu 
 * @date      2013. 6. 20.
 * @version   1.0 
 */
@Repository 
public interface TokenMapper {
	
	/**
	 * API 인증키로 거래선 인증키 정보 조회
	 * @param token
	 * @return
	 * @throws Exception
	 */
	public TokenBase selectTokenInfo(TokenBase tokenBase) throws Exception;
	
	/**
	 * 현재 거래선 API 인증키 조회 (인증키를 거래선에서 요구할 경우)
	 * @param token
	 * @return
	 * @throws Exception
	 */
	public TokenBase selectTokenInfoByUsrId(TokenBase tokenBase) throws Exception;
	
	/**
	 * 거래처에 인증키 등록
	 * @param token
	 * @throws Exception
	 */
	public void insertToken(TokenBase tokenBase) throws Exception;
	
	/**
	 * 인증키 갱신
	 * @param token
	 * @throws Exception
	 */
	public void updateTokenKey(TokenBase tokenBase) throws Exception;
	
	/**
	 * 인증키 사용유무 수정
	 * @param token
	 * @throws Exception
	 */
	public void updateTokenUseYn(TokenBase tokenBase) throws Exception;
}
