/**
 * 관리/전시 브랜드 ServiceImpl
 */
package com.openapi.service.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openapi.domain.token.TokenBase;
import com.openapi.domain.user.User;
import com.openapi.framework.utils.StringUtil;
import com.openapi.mapper.token.TokenMapper;
import com.openapi.mapper.user.UserMapper;
import com.openapi.service.BaseServiceImpl;

@Service
public class UserServiceImpl extends BaseServiceImpl implements UserService {
	
	@Autowired private UserMapper userMapper;
	@Autowired private TokenMapper tokenMapper;
	
	/* (non-Javadoc)
	 * @see com.openapi.service.user.UserService#getMgmtBrandList
	 */
	@Override
	public String createUser(User user) throws Exception {
		List<User> rtnList = null;
		user.setPgmId(programIdCache.getPgmId("userMapper.insertUser"));
		Integer userNo = userMapper.insertUser(user);
		TokenBase tokenBase = new TokenBase();
		tokenBase.setUsrNo(userNo);
		tokenBase.setToken(StringUtil.uuid(22));
		tokenBase.setPgmId(programIdCache.getPgmId("tokenMapper.insertToken"));
		tokenMapper.insertToken(tokenBase);
		return tokenBase.getToken();
	}

	/* (non-Javadoc)
	 * @see com.openapi.service.user.UserService#getDispBrandList
	 */
	@Override
	public List<User> getUserList(User user) throws Exception {
		
		return userMapper.selectUserList(user);
	}
	
	/* (non-Javadoc)
	 * @see com.openapi.service.user.UserService#getDispBrandAttrNoList
	 */
	@Override
	public List<User> getDispBrandAttrNoList(Integer dispBrndNo) throws Exception {
		validator.checkEmpty(sessionFactory.getObject().getLoginInfo().getUsrNo(), "사용자 No");
		validator.checkEmpty(dispBrndNo, "전시 브랜드 번호");
		
		User user = new User();
		user.setLoginUsrNo(sessionFactory.getObject().getLoginInfo().getUsrNo());
		
		return userMapper.selectDispBrandAttrNoList(user);
	}
}
