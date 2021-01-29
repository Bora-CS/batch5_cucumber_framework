package com.bora.pratice.karen;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.bora.dataObjects.SearchResult;
import com.bora.utilities.DriverFactory;
import com.bora.utilities.UI_Utils;

public class AmazonDataCollectionWithPOJO {

	public static WebDriver driver;

	public static void main(String[] args) {

		String itemToSearch = "Shampoo";
		int id = 1;
		ArrayList<SearchResult> results = new ArrayList<SearchResult>();
		try {
			driver = DriverFactory.getInstance();
			driver.get("https://www.amazon.com/");
			WebElement searchPlaceHolde = driver.findElement(By.id("twotabsearchtextbox"));
			searchPlaceHolde.sendKeys(itemToSearch);
			searchPlaceHolde.submit();

			String containerXpath = "(//div[@data-component-type='s-search-result'])";
			String titleXpath = "//h2[contains(@class,'a-size-mini a-spacing-none')]";
			String priceXpath = "//span[@class='a-price']";
			int listsOfResults = driver.findElements(By.xpath(containerXpath)).size();
			for (int i = 1; i <= listsOfResults; i++) {
				String title;
				String price;
				try {
					title = driver.findElement(By.xpath(containerXpath + "[" + i + "]" + titleXpath)).getText();
					price = driver.findElement(By.xpath(containerXpath + "[" + i + "]" + priceXpath)).getText();

				} catch (NoSuchElementException e) {
					continue;
				}
				price.replace("/n", ".");
				if (id <= 200) {

					results.add(new SearchResult(id++, price, title));
				} else {
					break;
				}
				if (id == 201) {
					break;
				}
				try {
					driver.findElement(By.xpath("//li[@class='a-last']")).click();
					UI_Utils.waitFor(2);
				} catch (NoSuchElementException e) {

					System.out.println();
				}

			}
			// writ result to excel

			File file = new File("src\\test\\resources\\excels\\AmazonDataCollectionFile.xlsx");
			FileOutputStream fos = new FileOutputStream(file);

			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet(UI_Utils.getTimeStamp());
			XSSFRow row = sheet.createRow(0);

			row.createCell(0).setCellValue("ID");
			row.createCell(1).setCellValue("Price");
			row.createCell(2).setCellValue("Title");

			int rowNum = 1;
			for (SearchResult result : results) {
				XSSFRow rowReault = sheet.createRow(rowNum++);
				rowReault.createCell(0).setCellValue(result.id);
				rowReault.createCell(1).setCellValue(result.price);
				rowReault.createCell(2).setCellValue(result.title);

			}
			workbook.write(fos);
			workbook.close();
			fos.close();
		} catch (Exception e) {
			System.out.println("Execution Failed ");
			System.out.println(e.getMessage());
		} finally {
			DriverFactory.cleanUp();
		}

	}
}
