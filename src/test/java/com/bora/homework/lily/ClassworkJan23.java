package com.bora.homework.lily;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ClassworkJan23 {
	public static void main(String[] args) throws IOException {
		File excelSheet = new File ("src/test/resources/excels/Test.xlsx");
		FileInputStream project1 = new FileInputStream (excelSheet);
		XSSFWorkbook workbook = new XSSFWorkbook (project1);
		XSSFSheet sheet = workbook.getSheet("Sheet 1");
		for (int rowNumber = 2; rowNumber <= sheet.getLastRowNum(); rowNumber ++){
		XSSFRow rowOfSheet1 = sheet.getRow(rowNumber);
		int nameCell = 1;
		XSSFCell cellNo2 = rowOfSheet1.getCell(nameCell);
		String cellNo2Value = cellNo2.getStringCellValue();
		System.out.println(cellNo2Value);
		}
		
	}

}
