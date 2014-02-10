/**
 * String -> MultipartFile Converter
 */
package com.openapi.framework.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.openapi.framework.utils.StringUtil;


public class StringToMultipartFileConverter implements Converter<String, MultipartFile> {
	
	@Autowired private MultipartHttpServletRequest request;
	
	@Override
	public MultipartFile convert(String source) {
		if (!"".equals(StringUtil.trimToEmpty(source))) {
			MultipartFile mfile = request.getFile(source);
			
			return mfile;
			
		} else {
			return null;
		}
	}
}
