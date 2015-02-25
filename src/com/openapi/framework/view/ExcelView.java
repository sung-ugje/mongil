package com.openapi.framework.view;

import java.beans.PropertyDescriptor;
import java.net.URLEncoder;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.springframework.web.servlet.view.document.AbstractJExcelView;

import com.openapi.framework.utils.StringUtil;

public class ExcelView extends AbstractJExcelView {

	@Override
	protected void buildExcelDocument(
		Map<String, Object> model
		, WritableWorkbook workbook
		, HttpServletRequest request
		, HttpServletResponse response
	) throws Exception {
		
		SimpleDateFormat excelFileNameSuffixFormat = new SimpleDateFormat("yyyyMMddHHmm");
		String excelFileName = StringUtil.defaultString((String)model.get("excelFileName"), "excel");

		String[] excelSheetNames = {"Sheet1"};

		if (model.get("excelSheetNames") != null) {
			excelSheetNames = (String[]) model.get("excelSheetNames");
		}

		String saveExcelFileName =  URLEncoder.encode(excelFileName + "_" + excelFileNameSuffixFormat.format(new Date()) + ".xls", "UTF-8");

		if ( request.getHeader("User-Agent").indexOf("MSIE 5.5") >= 0 ) {
			response.setContentType("doesn/matter");
			response.setHeader("Content-Disposition","filename=\"" + saveExcelFileName + "\"");

		} else {
			response.setHeader("Content-Disposition","attachment; filename=\"" + saveExcelFileName + "\"");
		}

		int iSheet = 0;
		int iRow = 0;
		int iCol = 0;

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		NumberFormat nf = NumberFormat.getInstance();

		for (String sheetName : excelSheetNames) {
			String[] arrayColumnNames = (String[]) model.get("excelColumnNames");
			String[] arrayColumnFields = (String[]) model.get("excelColumnFields");

			List<?> excelList = (List<?>) model.get("excelList");
			WritableSheet sheet = workbook.createSheet(sheetName, iSheet++);

			iRow = 0;
			for (String columnsName : arrayColumnNames) {
				sheet.addCell(new Label(iCol, iRow, columnsName));
				sheet.setColumnView(iCol, 20);
				iCol++;
			}

			iRow++;

			for (Object excelRow : excelList) {
				iCol = 0;
				for (String arrayColumnField : arrayColumnFields) {
					Object objRowCol = new PropertyDescriptor(arrayColumnField, excelRow.getClass()).getReadMethod().invoke(excelRow);
					String objValue = "";

					if (objRowCol instanceof Date) {
						objValue = sdf.format(objRowCol);

					} else if (objRowCol instanceof java.lang.Number) {
						objValue = nf.format(objRowCol);

					} else if (objRowCol == null) {
						objValue = "";

					} else {
						objValue = objRowCol.toString();
					}

					sheet.addCell(new Label(iCol++, iRow, objValue));
				}

				iRow++;
			}
		}
	}
}
