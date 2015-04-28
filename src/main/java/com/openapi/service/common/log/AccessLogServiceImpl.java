/**
 * 오픈API 접속로그 ServiceImpl
 */
package com.openapi.service.common.log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMethod;

import com.openapi.domain.common.log.AccessLog;
import com.openapi.framework.utils.StringUtil;
import com.openapi.mapper.common.log.AccessLogMapper;
import com.openapi.service.BaseServiceImpl;


@Service
public class AccessLogServiceImpl extends BaseServiceImpl implements AccessLogService {
	
	@Autowired private AccessLogMapper accessLogMapper;

	
	public void insertAccessLog(String failYn, String failCause, String jsonParam) throws Exception {
		String requestUrl = StringUtil.trimToEmpty((String) request.getAttribute("requestUrl"));
		String requestUri = StringUtil.trimToEmpty((String) request.getAttribute("requestUri"));
		String queryString = StringUtil.trimToEmpty((String) request.getAttribute("queryString"));
		String requestMethod = StringUtil.trimToEmpty(request.getMethod());
		
		if (!"".equals(queryString)) {
			requestUrl += "?" + queryString;
		}
		
		/**
		 * CRUD TYPE 체크
		 */
		String crudType = "R";
		
		if (RequestMethod.POST.toString().equalsIgnoreCase(requestMethod)) {
			crudType = "C";
			
		} else if (RequestMethod.PUT.toString().equalsIgnoreCase(requestMethod)) {
			crudType = "U";
			
		} else if (RequestMethod.DELETE.toString().equalsIgnoreCase(requestMethod)) {
			crudType = "D";
		}
		
		/**
		 * 실패사유 4000바이트 제한
		 */
		int failCauseLen = StringUtil.trimToEmpty(failCause).getBytes("UTF-8").length;
		if (failCauseLen > 4000) {
			failCause = StringUtil.cropByte(failCause, 3990, "...");
		}
		
		/**
		 * 등록/수정 JSON 파라미터 4000바이트 제한
		 * 상품 등록/수정 파라미터는 goodsNo, stylCd, goodsUnitList.dptsCd, goodsUnitList.untCd만 기록한다.
		 */
		if ("C".equals(crudType)
			|| "U".equals(crudType)
		) {
			
			/*상품 등록 / 수정시*/
			if (requestUri.startsWith("/api/goods")) {
				/*실패사유가 JSON 파싱 오류일때는 JSON 파싱을 하지 않는다.*/
				if (failCause.indexOf("EC01116") >= 0) {
					int jsonParamLen = StringUtil.trimToEmpty(jsonParam).getBytes("UTF-8").length;
					
					if (jsonParamLen > 4000) {
						jsonParam = StringUtil.cropByte(jsonParam, 3990, "...");
					}
					
				} else {
					jsonParam = sb.toString();
				}
				
			} else {
				int jsonParamLen = StringUtil.trimToEmpty(jsonParam).getBytes("UTF-8").length;
				
				if (jsonParamLen > 4000) {
					jsonParam = StringUtil.cropByte(jsonParam, 3990, "...");
				}
			}
			
			logger.debug("jsonParam : {}", jsonParam);
		}
		
		/**
		 * 로그 등록
		 */
		if (sessionFactory.getClass() != null
			&& sessionFactory.getObject().isLogin()
			&& !"".equals(sessionFactory.getObject().getLoginInfo().getToken())
		) {
			
			AccessLog accessLog = new AccessLog();
			accessLog.setAccessIp(sessionFactory.getObject().getLoginIp());
			accessLog.setToken(sessionFactory.getObject().getLoginInfo().getToken());
			accessLog.setLoginUsrNo(sessionFactory.getObject().getLoginInfo().getUsrNo());
			accessLog.setCrudType(crudType);
			accessLog.setAccessUrl(requestUrl);
			accessLog.setFailYn(failYn);
			accessLog.setFailCause(failCause);
			accessLog.setJsonParam(jsonParam);
			
			accessLogMapper.insertAccessLog(accessLog);
		}
	}
}
