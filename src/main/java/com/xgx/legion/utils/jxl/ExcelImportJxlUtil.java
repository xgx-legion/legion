package com.xgx.legion.utils.jxl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStreamWriter;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

/**
 * jxl导入Excel文件测试
 * 
 * @class ExcelImportJxlUtil.java
 * @author xinggx
 * @date 2018年3月1日
 */
public class ExcelImportJxlUtil {
	public static final String excelPath = "D:/test/test.xls";

	public static final String targetPath = "D:/test/test.txt";

	public static void main(String[] args) {
		File file = new File(excelPath);
		try {
			readExcel(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void readExcel(File file) throws Exception {
		InputStream input = new FileInputStream(file);
		Workbook book = Workbook.getWorkbook(input);
		// sheet数量
		int sheetSize = book.getNumberOfSheets();
		// 创建sheet对象
		for (int i = 0; i < sheetSize; i++) {
			Sheet sheet = book.getSheet(i);
			// 总列数
			int colNum = sheet.getColumns();
			// 总行数
			int rowNum = sheet.getRows();
			// Cell cell = sheet.getCell(m, n);-----第m列、n行的单元格，索引为0

			OutputStreamWriter outWriter = new OutputStreamWriter(new FileOutputStream(targetPath), "UTF-8");
			BufferedWriter bufWrite = new BufferedWriter(outWriter);
			for (int n = 0; n < rowNum; n++) {
				StringBuffer line = new StringBuffer();
				line.append("INSERT INTO TEST (ID, NAME) VALUES(");

				Cell cell_1 = sheet.getCell(0, n);
				Cell cell_2 = sheet.getCell(1, n);
				line.append("\'" + cell_1.getContents() + "\',\'" + cell_2.getContents() + "\');");

				// 将Excel逐行输出到txt文本
				bufWrite.write(line.toString());
				bufWrite.newLine();
			}
			bufWrite.write("commit;");
			bufWrite.close();
			outWriter.close();
		}
	}
}
