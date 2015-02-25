package com.openapi.service.token;
import com.openapi.domain.token.TokenBase;

/** 
 * 오픈 API 인증키 관리 (Service)
 * 
 * @author    OhEunJu 
 * @date      2013. 6. 20.
 * @version   1.0 
 */
public interface TokenService {
	
	/**
	 * API 인증키로 거래선 인증키 정보 조회
	 * @param token
	 * @return
	 * @throws Exception
	 */
	public TokenBase getTokenInfo(String token) throws Exception;
	
	/**
	 * USR ID값으로 API 인증키 조회 (인증키를 거래선에서 요구할 경우)
	 * @param useId
	 * @return
	 * @throws Exception
	 */
	public TokenBase getTokenInfoByUsrId(Integer usrNo) throws Exception;
	
	/**
	 * 거래처에 인증키 등록
	 * @param tokenBase
	 * @throws Exception
	 */
	public void insertToken(TokenBase tokenBase) throws Exception;
	
	/**
	 * 인증키 갱신
	 * @param newToken : 신규토큰
	 * @throws Exception
	 */
	public void updateTokenKey(String newToken) throws Exception;
	
	/**
	 * 인증키 사용유무 수정
	 * @param tokenBase
	 * @throws Exception
	 */
	public void updateTokenUseYn(TokenBase tokenBase) throws Exception;
}
