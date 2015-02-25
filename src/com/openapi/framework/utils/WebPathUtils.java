/**
 * 디렉토리 경로 Utils
 */
package com.openapi.framework.utils;

import java.io.File;

import org.apache.commons.io.FilenameUtils;

public class WebPathUtils {

	/**
	 * 웹 경로를 입력받아 물리적 절대경로를 리턴
	 * (ex) /blah/product/image/ -> D:\foo\bar\blah\product\image\ (윈도우)
	 * /blah/product/image/ -> /foo/bar/blah/product/image/ (리눅스)
	 * @param webPath
	 * @return
	 */
	public static String getAbsPathFromWebPath(String webPath) {
		return FilenameUtils.getFullPathNoEndSeparator(System.getProperty("webApp.root")) + FilenameUtils.separatorsToSystem(webPath);
	}

	/**
	 * 웹 경로를 입력받아 파일객체로 전환하여 리턴
	 * @param webPath
	 * @return
	 */
	public static File getFileFromWebPath(String webPath) {
		if (!StringUtil.isEmpty(webPath)) {
			return new File(getAbsPathFromWebPath(webPath));
		}
		
		return null;
	}
}