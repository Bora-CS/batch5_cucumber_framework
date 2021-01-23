package com.bora.pratice.karen;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.github.javafaker.Faker;
import com.github.javafaker.Friends;

public class excelSheetPrint {

	public static void main(String[] args) {
		
		String filePath="src\\test\\resources\\excels\\MyFile.xlsx";
		
		Faker faker = new Faker();
		
		try {
			File excelFile=new File(filePath);
			
			FileOutputStream fos= new FileOutputStream(excelFile);
			XSSFWorkbook workBook = new XSSFWorkbook();
			XSSFSheet sheet = workBook.createSheet("StudentInfromation");
			
			XSSFRow row = sheet.createRow(0);
			row.createCell(0).setCellValue("ID");
			row.createCell(1).setCellValue("FirstName");
			row.createCell(2).setCellValue("LastName");
			row.createCell(3).setCellValue("Email");
			row.createCell(4).setCellValue("PhoneNumber");
			
			int studentID=10001;
			
			for (int i = 1; i <=10 ; i++) {
				XSSFRow currentSheet = sheet.createRow(i);
				currentSheet.createCell(0).setCellValue(""+studentID++);
				String firstName=faker.name().firstName();
				currentSheet.createCell(1).setCellValue(firstName);
				String lastName=faker.name().lastName();
				currentSheet.createCell(2).setCellValue(lastName);
				currentSheet.createCell(3).setCellValue(""+firstName+"."+lastName+"@boratech.com");
				currentSheet.createCell(4).setCellValue(faker.phoneNumber().cellPhone());
				
				
			}
			workBook.write(fos);
			fos.close();
			workBook.close();
		} catch (Exception e) {
			
		}

	}

}
