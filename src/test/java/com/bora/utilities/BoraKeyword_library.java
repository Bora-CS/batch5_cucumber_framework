package com.bora.utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;



public class BoraKeyword_library {
	
	private WebDriver driver;
	
	public BoraKeyword_library(WebDriver driver) {
		this.driver = driver;
	}
	public void login (String userName, String password ) {
		elementSendKeys(By.name("email"), userName);
		elementSendKeys(By.name("password"), password);
		clickElement(By.xpath("//input[@type='submit']"));
	}
	public void elementSendKeys(By locator,String data) {
		getElement(locator).sendKeys(data);

	}

	public WebElement getElement(By locator) {

		return driver.findElement(locator);
	}
	public void clickElement(By locator) {
		// driver.findElement(locator).click()====>replace to below as
		getElement(locator).click();
	}

	

	
		
	}
	
	
	


