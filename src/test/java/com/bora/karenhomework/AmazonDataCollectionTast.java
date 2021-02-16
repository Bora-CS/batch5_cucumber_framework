package com.bora.karenhomework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.bora.utilities.DriverFactory;

public class AmazonDataCollectionTast {

	public static WebDriver driver;
	
	
	
	
	
	
	public static void revseNum(int a ,int b){
		a+=b;
		b=a-b;
		a=a-b;
		System.out.println(a);
		System.out.println(b);
	}

	public static void main(String[] args) {

		
		revseNum(10, 20);
		
		String itemToSearch = "MacBook";
		try {
			driver = DriverFactory.getInstance();

			driver.get("https://www.amazon.com/");
			WebElement searchField = driver.findElement(By.id("twotabsearchtextbox"));
			searchField.sendKeys(itemToSearch);
			searchField.submit();

		} catch (Exception e) {

		} finally {
			DriverFactory.cleanUp();
		}
	}

}
