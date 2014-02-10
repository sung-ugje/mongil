package com.openapi.framework.component;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;

import com.openapi.framework.utils.StringUtil;
import com.openapi.framework.utils.WebPathUtils;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

@Component
public class ThumnailerComponent extends BaseComponent {

	public void createThumbnail(
		String sourceFullPathFileName
		, String targetFullPathFileName
		, int width
		, int height
		) {

		float jpegQuality = 0.95f;

		if (width == 0 || height == 0) {
			logger.error("width, height 는 0 보다 커야 함.");
			return;
		}
		
		if (StringUtil.isEmpty(sourceFullPathFileName)) {
			logger.error("소스 경로 누락");
			return;
		}
		
		if (StringUtil.isEmpty(targetFullPathFileName)) {
			targetFullPathFileName = sourceFullPathFileName;
		}

		sourceFullPathFileName = WebPathUtils.getAbsPathFromWebPath(sourceFullPathFileName);
		targetFullPathFileName = WebPathUtils.getAbsPathFromWebPath(targetFullPathFileName);
		
		logger.debug("소스 실제 경로 : {}", sourceFullPathFileName);
		logger.debug("타겟 실제 경로 : {}", targetFullPathFileName);

		String srcFileExtension = FilenameUtils.getExtension(sourceFullPathFileName).toLowerCase();
		String targetFullPath = FilenameUtils.getFullPath(targetFullPathFileName);
		String targetFileName = FilenameUtils.getName(targetFullPathFileName);
		String targetFileExtension = FilenameUtils.getExtension(targetFileName).toLowerCase();
		targetFileName.replace("." + targetFileExtension, ".jpg");
		targetFullPathFileName = FilenameUtils.concat(targetFullPath, targetFileName);
		
		try {
			FileUtils.forceMkdir(new File(FilenameUtils.getFullPath(targetFullPathFileName)));
			
		} catch (IOException e) {
			logger.error("타겟 디렉토리 생성 오류 - " + FilenameUtils.getFullPath(targetFullPathFileName));
			com.openapi.framework.utils.StringUtil.printExceptionTrace(e);
			return;
		}
		
		Image srcImg = null;
		if (srcFileExtension.equals("bmp") || srcFileExtension.equals("png") || srcFileExtension.equals("gif")) {
			try {
				srcImg = ImageIO.read(new File(sourceFullPathFileName));
				
			} catch (IOException e) {
				logger.error("소스 이미지 로드 오류 - " + sourceFullPathFileName);
				com.openapi.framework.utils.StringUtil.printExceptionTrace(e);
				return;
			}
			
		} else {
			srcImg = new ImageIcon(sourceFullPathFileName).getImage();
		}
		
		
		if (srcImg != null) {
			int srcWidth = srcImg.getWidth(null);
			int srcHeight= srcImg.getHeight(null);
			
			if (srcWidth == width && srcHeight == height) {
				jpegQuality = 1.0f;
			}
			
			float cvtWidth = width;
			float cvtHeight = height;
			int startX = 0;
			int startY = 0;
			//int endX = width;
			//int endY = height;
	
			if (srcWidth >= srcHeight) {
				// 가로가 더 긴 경우 -> 세로를 먼저 맞춤
				cvtHeight = height;
				cvtWidth = srcWidth * height / srcHeight;
				startX = (int)((cvtWidth - width) / 2); //(int) ((width / 2) - (cvtWidth / 2));
				//endX = startX + width;
				
			} else if (srcWidth < srcHeight) {
				// 세로가 더 긴 경우 -> 가로를 먼저 맞춤
				cvtWidth = width;
				cvtHeight = srcHeight * width / srcWidth;
				startY = (int)((cvtHeight - height) / 2); //(int)((height / 2) - (cvtHeight / 2));
				//endY = startY + height;
			}
			
			/*
			if ( (srcWidth >= srcHeight && width <= height) || (srcWidth < srcHeight && width < height) ) {
				// 원본이 가로가 길고 가로세로가 일치 또는 세로가 긴 크기로 변환하거나, 원본이 세로가 길고 세로가 긴 크기로 변환하는 경우
				// -> 가로 길이를 맞춤
				cvtWidth = width;
				cvtHeight = (int)(srcHeight * width / srcWidth);
				startY = (int)((cvtHeight - height) / 2); //(int)((height / 2) - (cvtHeight / 2));
				endY = startY + height;
				
			} else {
				// 원본이 가로가 길고 가로가 긴 크기로 변환하거나, 원본이 세로가 길고 가로세로가 일치 또는 가로가 긴 크기로 변환하는 경우
				// -> 세로 길이를 맞춤
				cvtHeight = height;
				cvtWidth = (int) (srcWidth * height / srcHeight);
				startX = (int)((cvtWidth - width) / 2); //(int) ((width / 2) - (cvtWidth / 2));
				endX = startX + width;
			}
			*/
	
			int iCvtWidth = (int)cvtWidth;
			int iCvtHeight = (int)cvtHeight;
			
			logger.debug("원본크기 : " + srcWidth + "*" + srcHeight + ", 변환크기 : " + width + "*" + height + ", 변환 이미지 사이즈 : " + iCvtWidth + ", " + iCvtHeight + ", 시작위치 : (" +startX + ", " + startY + "), jpeg 품질 : " + jpegQuality);
	
			Image imgTarget = srcImg.getScaledInstance(iCvtWidth, iCvtHeight, Image.SCALE_SMOOTH);
			int pixels[] = new int[width * height];
			PixelGrabber pg = new PixelGrabber(imgTarget, startX, startY, width, height, pixels, 0, width);
			
			try {
				pg.grabPixels();
				
			} catch (InterruptedException e1) {
				com.openapi.framework.utils.StringUtil.printExceptionTrace(e1);
			}
			
			BufferedImage destImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			Graphics2D g2 = destImg.createGraphics();
			g2.setBackground(Color.WHITE);
			g2.clearRect(0, 0, width, height);
			g2.dispose();
			destImg.setRGB(0, 0, width, height, pixels, 0, width);
			
			OutputStream os = null;
			
			try {
				os = new FileOutputStream(targetFullPathFileName);
				JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(os);
				JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(destImg);
				param.setQuality(jpegQuality, true);
				encoder.encode(destImg, param);
				
			} catch(IOException io) {
				logger.error("썸네일 저장 오류가 발생했습니다.");
				com.openapi.framework.utils.StringUtil.printExceptionTrace(io);
			} finally {
				IOUtils.closeQuietly(os);
			}
			
		} else {
			logger.error("썸네일 이미지 로드 오류");
		}
		
		//logger.info("이미지 저장 : " + ((float)(System.currentTimeMillis() - startTime5) / 1000) + " 초");
		//logger.info("리사이징 걸린 시간 : {}", ((float)(System.currentTimeMillis() - startTime) / 1000) + " 초");
	}
}
