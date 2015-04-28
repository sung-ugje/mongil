/**
 * 파라미터 체크 Component
 */
package com.openapi.framework.component;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.openapi.framework.utils.EnvPropUtils;
import com.openapi.framework.utils.StringUtil;


@Component
public class ParameterCheckComponent extends BaseComponent {
	
	/**
	 * 파라미터 체크
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public boolean checkParams(HttpServletRequest request, HttpServletResponse response) throws Exception {
		boolean res = true;
		
		/**
		 * 페이지 사이즈 파라미터는 최대 1000개까지만 출력
		 */
		Integer ps = Integer.parseInt(StringUtil.defaultIfEmpty(request.getParameter("ps"), "10"));
		Integer limitPs = Integer.parseInt(StringUtil.trimToEmpty(EnvPropUtils.getPropertyValue("openapi.limit.ps")));
		
		if (ps > limitPs) {
			request.setAttribute("params", new Object[]{limitPs, ps});
			
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/api/error/EC20112");
			if (requestDispatcher != null) {
				requestDispatcher.forward(request, response);
			}
			
			res = false;
		}
		
		return res;
	}
}
