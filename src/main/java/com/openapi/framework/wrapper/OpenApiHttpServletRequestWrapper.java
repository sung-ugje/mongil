/**
 * 
 */
package com.openapi.framework.wrapper;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;


public class OpenApiHttpServletRequestWrapper extends HttpServletRequestWrapper {
	
	private Map<String, Object> paramsMap = null;

	/**
	 * Constructor
	 */
	public OpenApiHttpServletRequestWrapper(HttpServletRequest request) {
		super(request);
		paramsMap = new HashMap<String, Object>(request.getParameterMap());
	}
	
	
	public String getParameter(String name) {
		String returnValue = null;
		
		String[] paramArray = getParameterValues(name);
		
		if (paramArray != null && paramArray.length > 0) {
			returnValue = paramArray[0];
		}
		
		return returnValue;
	}
	
//	
//	public Map<String, String[]> getParameterMap() {
////		return Collections.unmodifiableMap(paramsMap);
//	}
	
	
	public Enumeration<String> getParameterNames() {
		return Collections.enumeration(paramsMap.keySet());
	}

	
	public String[] getParameterValues(String name) {
		String[] result = null;
		String[] temp = (String[]) paramsMap.get(name);
		
		if (temp != null) {
			result = new String[temp.length];
			
			System.arraycopy(temp, 0, result, 0, temp.length);
		}
		
		return result;
	}

	public void setParameter(String name, String value){
		String[] oneParam = {value};
		setParameter(name, oneParam);
	}

	public void setParameter(String name, String[] value){
		paramsMap.put(name, value);
	}
}
