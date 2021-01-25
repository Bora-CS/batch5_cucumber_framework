package com.bora.selenium;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

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

public class AmazonDataCollectionTestWithPOJO {

	public static WebDriver driver;

	public static void main(String[] args) {

		String itemToSearch = "Shampoo";
		int id = 1;
		ArrayList<SearchResult> results = new ArrayList<SearchResult>();

		try {
			driver = DriverFactory.getInstance();
			driver.get("https://www.amazon.com/");

			WebElement searchField = driver.findElement(By.id("twotabsearchtextbox"));
			searchField.sendKeys(itemToSearch);
			searchField.submit();

			while (id <= 200) {
				String containerXpath = "(//div[@data-component-type='s-search-result'])";
				String titleXpath = "//h2[contains(@class,'a-size-mini a-spacing-none')]";
				String priceXpath = "//span[@class='a-price']";

				int numberOfResults = driver.findElements(By.xpath(containerXpath)).size();

				for (int index = 1; index <= numberOfResults; index++) {
					String title;
					String price;
					try {
						title = driver.findElement(By.xpath(containerXpath + "[" + index + "]" + titleXpath)).getText();
						price = driver.findElement(By.xpath(containerXpath + "[" + index + "]" + priceXpath)).getText();
					} catch (NoSuchElementException e) {
						continue;
					}

					price = price.replace("\n", ".");
					if (id <= 200) {
						results.add(new SearchResult(id++, title, price));
					} else {
						break;
					}
				}

				if (id == 201) {
					break;
				}

				try {
					driver.findElement(By.xpath("//li[@class='a-last']/a")).click();
					UI_Utils.waitFor(2);
				} catch (NoSuchElementException e) {
					System.out.println("Ran out of results to record. Number of results recorded: " + (id - 1));
				}
			}

			// Write results to excel
			File file = new File("src/test/resources/excels/ASR_" + itemToSearch + "_" +UI_Utils.getTimeStamp() + ".xlsx");
			FileOutputStream fos = new FileOutputStream(file);
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet(UI_Utils.getTimeStamp());
			XSSFRow columnNames = sheet.createRow(0);
			columnNames.createCell(0).setCellValue("ID");
			columnNames.createCell(1).setCellValue("Price");
			columnNames.createCell(2).setCellValue("Title");

			int rowNum = 1;
			for (SearchResult result : results) {
				XSSFRow currentRow = sheet.createRow(rowNum++);
				currentRow.createCell(0).setCellValue(result.id);
				currentRow.createCell(1).setCellValue(result.price);
				currentRow.createCell(2).setCellValue(result.title);
			}

			workbook.write(fos);
			workbook.close();
			fos.close();

		} catch (Exception e) {
			System.out.println("Execution Failed");
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			DriverFactory.cleanUp();
		}

	}

}
