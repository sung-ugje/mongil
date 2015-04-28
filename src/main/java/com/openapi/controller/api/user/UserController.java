/**
 * 사용자 Controller
 */
package com.openapi.controller.api.user;

import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.openapi.controller.BaseOpenApiController;
import com.openapi.domain.user.User;
import com.openapi.framework.exception.FailureException;
import com.openapi.framework.utils.ValueObjectUtils;
import com.openapi.service.user.UserService;

@Controller
@RequestMapping("/api")
public class UserController extends BaseOpenApiController {

	@Autowired
	private UserService userService;


	@RequestMapping(value = "/user", method = RequestMethod.POST)
	@ResponseBody
	@Transactional
	public HashMap<String, String> createUser(@RequestBody String jsonData)
			throws Exception {
		validator.checkEmpty(jsonData, "사용자 정보");
		User user = null;
		try {
			user = (User) JSONObject.toBean(JSONObject.fromObject(jsonData),
					User.class);
		} catch (Exception e) {
			throw new FailureException("EC01116");
		}
		ValueObjectUtils.setDecodingData(user);
		HashMap<String, String> rtnMap = new HashMap<String, String>();
		rtnMap.put("Token", userService.createUser(user));
		return rtnMap;
	}

	/**
	 * 사용자리스트
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/user/list", method = RequestMethod.GET)
	@ResponseBody
	public List<User> getUserList(
			@RequestParam(value="isPaging", required=false, defaultValue="true") boolean isPaging,
			@RequestParam(value="pg", required=false, defaultValue="1") int pg,
			@RequestParam(value="ps", required=false, defaultValue="10") int ps
			)
			throws Exception {

		User user = new User();
		user.setPg(pg);
		user.setPs(ps);
		user.setIsPaging(isPaging);
		return userService.getUserList(user);
	}

}
