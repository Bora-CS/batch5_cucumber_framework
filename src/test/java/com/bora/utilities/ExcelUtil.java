package com.bora.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.github.javafaker.Faker;

public class ExcelUtil {

	public static void writeFileExample() {

		Faker faker = new Faker();

		try {

			String targetFilePath = "src/test/resources/excels/NewFile1.xlsx";
			File targetFile = new File(targetFilePath);
			FileOutputStream fos = new FileOutputStream(targetFile);

			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet();

			// Creates column names
			XSSFRow row = sheet.createRow(0);
			row.createCell(0).setCellValue("ID");
			row.createCell(1).setCellValue("FirstName");
			row.createCell(2).setCellValue("LastName");
			row.createCell(3).setCellValue("Email");
			row.createCell(4).setCellValue("PhoneNumber");

			// Creates data
			int id = 10001;
			for (int rowNum = 1; rowNum <= 10; rowNum++) {
				XSSFRow currentRow = sheet.createRow(rowNum);
				String firstName = faker.name().firstName();
				String lastName = faker.name().lastName();
				currentRow.createCell(0).setCellValue(id++);
				currentRow.createCell(1).setCellValue(firstName);
				currentRow.createCell(2).setCellValue(lastName);
				currentRow.createCell(3).setCellValue(firstName + "." + lastName + "@boratech.com");
				currentRow.createCell(4).setCellValue(faker.phoneNumber().cellPhone());
			}

			workbook.write(fos);
			fos.close();
			workbook.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void readFileExample() {

		String filePath = "src/test/resources/excels/Test.xlsx";

		try {
			File excelFile = new File(filePath);
			FileInputStream fis = new FileInputStream(excelFile);
			XSSFWorkbook workbook = new XSSFWorkbook(fis);

			XSSFSheet sheet = workbook.getSheet("Sheet 1");
			int lastRowNum = sheet.getLastRowNum();

			for (int rowIndex = 1; rowIndex <= lastRowNum; rowIndex++) {
				XSSFRow currentRow = sheet.getRow(rowIndex);
				int numberOfCells = currentRow.getLastCellNum();

				for (int cellIndex = 0; cellIndex < numberOfCells; cellIndex++) {
					XSSFCell currentCell = currentRow.getCell(cellIndex);
					String currentCellValue = "";
					CellType cellType = currentCell.getCellType();

					switch (cellType) {
					case NUMERIC:
						currentCellValue = ((int) currentCell.getNumericCellValue()) + "";
						break;
					case STRING:
						currentCellValue = currentCell.getStringCellValue();
						break;
					default:
						break;
					}

					System.out.print(currentCellValue + "\t");
				}
				System.out.println();
			}

			fis.close();
			workbook.close();

		} catch (FileNotFoundException e) {
			System.out.println("File not found: " + filePath);
		} catch (IOException e) {
			System.out.println("Something went wrong while reading from file: " + filePath);
		}

	}

}
