package com.openapi.service.token;

import com.openapi.domain.token.TokenBase;
import com.openapi.mapper.token.TokenMapper;
import com.openapi.service.BaseServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/** 
 * 오픈 API 인증키 관리 (Service Impl)
 * @author    OhEunJu 
 * @date      2013. 6. 20.
 * @version   1.0 
 */
@Service
public class TokenServiceImpl extends BaseServiceImpl implements TokenService {
	
	@Autowired private TokenMapper tokenMapper;
	
	/**
	 * API 인증키로 거래선 인증키 정보 조회
	 * @param token
	 * @return
	 * @throws Exception
	 */
	@Override
	public TokenBase getTokenInfo(String token) throws Exception {
		TokenBase tokenBase = new TokenBase();
		tokenBase.setToken(token);
		
		validator.checkEmpty(tokenBase.getToken(), "API 인증키");
		
		tokenBase.setPgmId(programIdCache.getPgmId("TokenMapper.selectTokenInfo"));
		TokenBase tokenInfo = tokenMapper.selectTokenInfo(tokenBase);
		
		return tokenInfo;
	}
	
	/**
	 * ENTR_ID값으로 API 인증키 조회 (인증키를 거래선에서 요구할 경우)
	 * @param entrId
	 * @return
	 * @throws Exception
	 */
	@Override
	public TokenBase getTokenInfoByUsrId(Integer usrNo) throws Exception {
		TokenBase tokenBase = new TokenBase();
		tokenBase.setLoginUsrNo(usrNo);
		
		validator.checkEmpty(tokenBase.getLoginUsrNo(), "사용자 ID");
		
		TokenBase tokenInfo = tokenMapper.selectTokenInfoByUsrId(tokenBase);
		
		return tokenInfo;
	}
	
	/**
	 * 거래처에 인증키 등록
	 * @param tokenBase
	 * @throws Exception
	 */
	@Override
	@Transactional
	public void insertToken(TokenBase tokenBase) throws Exception {
		tokenBase.setLoginUsrNo(sessionFactory.getObject().getLoginInfo().getUsrNo());
		tokenBase.setPgmId(programIdCache.getPgmId("TokenMapper.insertToken"));
		
		tokenMapper.insertToken(tokenBase);
	}
	
	/**
	 * 인증키 갱신
	 * @param tokenBase
	 * @throws Exception
	 */
	@Override
	@Transactional
	public void updateTokenKey(String newToken) throws Exception {
		validator.checkEmpty(newToken, "갱신된 API 인증키");
		
		TokenBase tokenBase = new TokenBase();
		tokenBase.setToken(sessionFactory.getObject().getLoginInfo().getToken());
		
		TokenBase tokenInfo = tokenMapper.selectTokenInfo(tokenBase);
		
		/*신규토큰으로 수정*/
		tokenInfo.setToken(newToken);
		tokenInfo.setLoginUsrNo(sessionFactory.getObject().getLoginInfo().getUsrNo());
		tokenInfo.setPgmId(programIdCache.getPgmId("TokenMapper.updateTokenKey"));
		
		logger.debug("token : {}", tokenInfo.getToken());
		logger.debug("loginUsrNo : {}", tokenInfo.getLoginUsrNo());
		
		tokenMapper.updateTokenKey(tokenInfo);
	}
	
	/**
	 * 인증키 사용유무 수정
	 * @param tokenBase
	 * @throws Exception
	 */
	@Override
	@Transactional
	public void updateTokenUseYn(TokenBase tokenBase) throws Exception {
		tokenBase.setLoginUsrNo(sessionFactory.getObject().getLoginInfo().getUsrNo());
		tokenBase.setPgmId(programIdCache.getPgmId("TokenMapper.updateTokenUseYn"));
		
		tokenMapper.updateTokenUseYn(tokenBase);
	}
}
