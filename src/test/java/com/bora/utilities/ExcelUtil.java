package com.bora.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {

	public static void main(String[] args) {

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
