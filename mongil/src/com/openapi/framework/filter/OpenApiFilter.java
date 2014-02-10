/**
 * Open API Filter
 */
package com.openapi.framework.filter;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URLDecoder;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

import com.openapi.framework.constants.MgConstants;
import com.openapi.framework.utils.DateUtils;
import com.openapi.framework.utils.StringUtil;
import com.openapi.framework.wrapper.OpenApiHttpServletRequestWrapper;


public class OpenApiFilter extends OncePerRequestFilter {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private StringBuilder sb = new StringBuilder();
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		OpenApiHttpServletRequestWrapper openApiRequest = new OpenApiHttpServletRequestWrapper(request);
		
		Long startTimestamp = System.currentTimeMillis();
		openApiRequest.setAttribute("startTimestamp", startTimestamp);
		
		logger.info("*** [" + openApiRequest.getMethod() + "-S] " + openApiRequest.getRequestURI() + " : " + DateUtils.getDateByPattern(new Date(startTimestamp), "yyyy-MM-dd HH:mm:ss"));
		
		try {
			/**
			 * Request
			 */
			String requestUrl = StringUtil.trimToEmpty(openApiRequest.getRequestURL().toString());
			String requestUri = StringUtil.trimToEmpty(openApiRequest.getRequestURI());
			String queryString = URLDecoder.decode(StringUtil.trimToEmpty(openApiRequest.getQueryString()), "UTF-8");
			
			openApiRequest.setAttribute("requestUrl", requestUrl);
			openApiRequest.setAttribute("requestUri", requestUri);
			openApiRequest.setAttribute("queryString", queryString);
			
			/**
			 * 파라미터 정리
			 */
			this.changeParamValue(openApiRequest);
			
			/**
			 *
			 */
			filterChain.doFilter(openApiRequest, response);
			
			/**
			 * 메모리 상태 출력
			 */
			this.printMemoryStatus();
			
		} catch (Exception e) {
			StringUtil.printExceptionTrace(e);
		}
		
		Long endTimestamp = System.currentTimeMillis();
		Long elapsedTime = endTimestamp - startTimestamp;
		openApiRequest.setAttribute("elapsedTime", elapsedTime);
		
		logger.info("*** [" + openApiRequest.getMethod() + "-E] " + openApiRequest.getRequestURI() + " : " + DateUtils.getDateByPattern(new Date(endTimestamp), "yyyy-MM-dd HH:mm:ss") + " | elapsed : " + elapsedTime + " ms");
	}
	
	/**
	 * 파라미터 재정리
	 * @param request
	 * @throws Exception
	 */
	public void changeParamValue(OpenApiHttpServletRequestWrapper request) throws Exception {
		if (request != null) {
			Enumeration<String> e = request.getParameterNames();
			
			if (e != null) {
				sb.setLength(0);
				
				while (e.hasMoreElements()) {
					String paramName = e.nextElement();
					String[] paramValues = request.getParameterValues(paramName);
					
					sb.append("-------------------------------------------------------------" + MgConstants.CRLF);
					sb.append("paramName : " + StringUtil.trimToEmpty(paramName) + MgConstants.CRLF);
					
					if (paramValues != null && paramValues.length > 0) {
						String[] chgParamValues = ArrayUtils.toArray("");
						
						for (String str : paramValues) {
							/*파라미터 NULL 처리 및 디코딩*/
							chgParamValues = ArrayUtils.add(chgParamValues, URLDecoder.decode(StringUtil.trimToEmpty(str), "UTF-8"));
						}
						
						chgParamValues = ArrayUtils.remove(chgParamValues, 0);
						
						sb.append("paramValues : " + ArrayUtils.toString(chgParamValues) + MgConstants.CRLF);
						
						request.setParameter(paramName, chgParamValues);
						
					} else {
						request.setParameter(paramName, "");
					}
				}
				
				logger.debug(MgConstants.CRLF + sb.toString());
			}
		}
	}
	
	/**
	 * 메모리 상태 출력
	 * @throws Exception
	 */
	public void printMemoryStatus() throws Exception {
		long total = Runtime.getRuntime().totalMemory();
		long free  = Runtime.getRuntime().freeMemory();
	    long max   = Runtime.getRuntime().maxMemory();
	    
	    sb.setLength(0);
	    
	    sb.append("------------------------------ Memory Status Start -----------------------------" + MgConstants.CRLF);
	    
	    sb.append("Total Memory : " + new BigDecimal(total).divide((new BigDecimal(1024*1024))).setScale(3, RoundingMode.CEILING).toString() + " MB" + MgConstants.CRLF);
	    sb.append("Free Memory : " + new BigDecimal(free).divide((new BigDecimal(1024*1024))).setScale(3, RoundingMode.CEILING).toString() + " MB" + MgConstants.CRLF);
	    sb.append("Max Memory : " + new BigDecimal(max).divide((new BigDecimal(1024*1024))).setScale(3, RoundingMode.CEILING).toString() + " MB" + MgConstants.CRLF);
	    
	    sb.append("------------------------------ Memory Status End -----------------------------" + MgConstants.CRLF);
	    
	    logger.debug(MgConstants.CRLF + sb.toString());
	}	
}
