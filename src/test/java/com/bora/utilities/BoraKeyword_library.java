package com.bora.utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.google.inject.spi.Element;



public class BoraKeyword_library {

	private WebDriver driver;

	public BoraKeyword_library(WebDriver driverWithValue) {
		this.driver = driverWithValue; 
	}

	public WebElement getElement(By locator) {
		return driver.findElement(locator);
	}
	public void openUrl(String url) {	
		driver.get(url);
		}
	public String getUrl() {
		
		return driver.getCurrentUrl();
	}
	
	public void clickElement(By locator) {
		getElement(locator).click();
	}

	public void sumbitKey(By loactor, String data) {
		getElement(loactor).sendKeys(data);
	}

	public void login(String userName, String password) {
		sumbitKey(By.name("email"), userName);
		sumbitKey(By.name("password"), password);
		clickElement(By.xpath("//input[@type='submit']"));

	}

}