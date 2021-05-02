package com.bora.emmahomework;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.bora.utilities.DriverFactory;

public class AmazonDataCollectionTast {

	public static WebDriver driver;
	
	

	public static void main(String[] args) {

		
		
		
		String itemToSearch = "MacBook";
		try {
			driver = DriverFactory.getInstance();

			driver.get("https://www.amazon.com/");
			WebElement searchField = driver.findElement(By.id("twotabsearchtextbox"));
			searchField.sendKeys(itemToSearch);
			searchField.submit();

			String containerXpath="(//div[@data-component-type='s-search-result'])";
			String titleXpath="//h2[@class='a-size-mini a-spacing-none a-color-base s-line-clamp-2']";
			String priceXpath="//span[@class='a-price']";
			
			int numberOfResults = driver.findElements(By.xpath(containerXpath)).size();
			for (int i = 1; i < numberOfResults; i++) {
				String title;
				String price;
				try {
					title=driver.findElement(By.xpath(containerXpath+"["+i+"]"+titleXpath)).getText();
					price=driver.findElement(By.xpath(containerXpath+"["+i+"]"+priceXpath)).getText();
				} catch (NoSuchElementException e) {
					continue;
				}
				price.replace("/n", ".");
				
				
				
				
				
			}
		} catch (Exception e) {

		} finally {
			DriverFactory.cleanUp();
		}
	}

}
