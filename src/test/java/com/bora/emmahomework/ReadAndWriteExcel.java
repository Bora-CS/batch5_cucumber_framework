package com.bora.emmahomework;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.github.javafaker.Faker;

public class ReadAndWriteExcel {

	public static void main(String[] args) {
		String filePath = "src/test/resources/excels/New.xlsx";

		// Read Excel File
		try {
			File excelFile = new File(filePath);
			FileInputStream fis = new FileInputStream(excelFile);
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheet("Sheet 1");
			int lastRowNum = sheet.getLastRowNum();
			for (int i = 1; i <= lastRowNum; i++) {
				XSSFRow currentRow = sheet.getRow(i);
				int numberofCells = currentRow.getLastCellNum();
				for (int j = 0; j < numberofCells; j++) {
					XSSFCell currentCells = currentRow.getCell(j);

					String currentCellValue = "";
					CellType cellType = currentCells.getCellType();
					switch (cellType) {
					case NUMERIC:
						currentCellValue = ((int) currentCells.getNumericCellValue()) + "";
						break;
					case STRING:
						currentCellValue = currentCells.getStringCellValue();
						break;
					default:
						break;
					}
					System.out.print(currentCellValue + "\t" + "\t");
				}
				System.out.println();
			}

			fis.close();
			workbook.close();
		} catch (Exception e) {
			e.getStackTrace();
		}

		// Write Excel File

		Faker faker = new Faker();

		try {

			File targetFile = new File(filePath);
			FileOutputStream fos = new FileOutputStream(targetFile);

			int studentID = 10001;
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet("Test Data");
			XSSFRow row = sheet.createRow(0);
			row.createCell(0).setCellValue("Student ID");
			row.createCell(1).setCellValue("Student Name");

			for (int rowNum = 1; rowNum <= 10; rowNum++) {
				XSSFRow currentrow = sheet.createRow(rowNum);
				row.createCell(0).setCellValue("" + studentID++);
				row.createCell(1).setCellValue(faker.funnyName().toString());
			}
			workbook.write(fos);
			fos.close();
			workbook.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
