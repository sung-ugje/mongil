/**
 * 에러 Controller
 */
package com.openapi.controller.api.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.openapi.controller.BaseOpenApiController;
import com.openapi.framework.constants.ErrorCodeConstants;
import com.openapi.framework.exception.AuthenticationException;
import com.openapi.framework.exception.FailureException;
import com.openapi.framework.exception.SimpleMappingException;
import com.openapi.framework.utils.MessageUtils;
import com.openapi.framework.utils.StringUtil;


@Controller
@RequestMapping("/api")
public class ErrorController extends BaseOpenApiController {
	
	/**
	 * RequestDispatcher에서 넘겨받은 익셉션 처리
	 * @param errorCode : 에러코드
	 * @throws Exception
	 */
	@RequestMapping(value="/error/{errorCode}")
	public void getFailure(@PathVariable(value="errorCode") String errorCode) throws Exception {
		validator.checkEmpty(errorCode, "에러코드");
		
		String statusCode = StringUtil.trimToEmpty((String) request.getAttribute("statusCode"));
		
		logger.info("statusCode : {}", statusCode);
		logger.info("errorCode : {}", errorCode);
		
		/*SimpleMappingExceptionResolverWrapper에 의한 오류가 아닐 경우*/
		if ("".equals(statusCode)) {
			/*AuthenticationException*/
			if (ErrorCodeConstants.EC_AUTHENTICATION_REQUIRED.equals(StringUtil.trimToEmpty(errorCode))) {
				throw new AuthenticationException(errorCode);
				
			}
			/*RuntimeException*/
			else if (ErrorCodeConstants.EC_INTERNAL_SERVER_ERROR.equals(StringUtil.trimToEmpty(errorCode))) {
				throw new RuntimeException(MessageUtils.getMessage(errorCode));
				
			}
			/*FailureException*/
			else {
				Object[] params = (Object[]) request.getAttribute("params");
				
				if (params == null || params.length == 0) {
					throw new FailureException(errorCode);
					
				} else {
					throw new FailureException(errorCode, params);
				}
			}
			
		/*SimpleMappingExceptionResolverWrapper에 의한 오류일 경우*/
		} else {
			throw new SimpleMappingException(Integer.parseInt(statusCode), errorCode);
		}
	}
}
