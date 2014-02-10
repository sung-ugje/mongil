/**
 * Base Client Controller
 */
package com.openapi.controller.client;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import com.openapi.controller.BaseController;
import com.openapi.domain.common.Failure;
import com.openapi.domain.session.SessionInfo;
import com.openapi.framework.constants.ErrorCodeConstants;
import com.openapi.framework.utils.MessageUtils;
import com.openapi.framework.utils.StringUtil;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public class BaseClientController extends BaseController {

	@Resource(name="apiSessionFactory")
	protected ObjectFactory<SessionInfo> sessionFactory;
	
	/**
	 * RuntimeException
	 * @param response
	 * @param e
	 * @return
	 */
	@ExceptionHandler(RuntimeException.class)
	@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	@Transactional
	public Failure handleException(HttpServletResponse response, RuntimeException e) {
		logger.error("*** RuntimeException Handle : " + MessageUtils.getMessage(ErrorCodeConstants.EC_INTERNAL_SERVER_ERROR));
		StringUtil.printExceptionTrace(e);
		
		return new Failure(ErrorCodeConstants.EC_INTERNAL_SERVER_ERROR, "클라이언트 오류~!");
	}
}