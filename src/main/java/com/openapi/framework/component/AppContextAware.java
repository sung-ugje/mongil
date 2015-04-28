/**
 * 
 */
package com.openapi.framework.component;

import java.io.File;
import java.io.IOException;

import com.openapi.framework.utils.StringUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;


@Component
public class AppContextAware implements ApplicationContextAware {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		/*업로드 임시 디렉토리 설정*/
		CommonsMultipartResolver commonsMultipartResolver = applicationContext.getBean(CommonsMultipartResolver.class);

		File uploadTempDir = new File(System.getProperty("webApp.root") + "__upload_temp/");
		logger.info("*** 업로드 임시 디렉토리 경로 : {}", uploadTempDir.getAbsolutePath());

		if (!uploadTempDir.exists()) {
			uploadTempDir.mkdirs();
		}
		
		FileSystemResource uploadTempDirResource = new FileSystemResource(uploadTempDir);
		
		try {
			commonsMultipartResolver.setUploadTempDir(uploadTempDirResource);
			
		} catch (IOException e) {
			logger.error("*** 업로드 임시 디렉토리 설정 오류 : ");
			StringUtil.printExceptionTrace(e);
		}
	}
}
