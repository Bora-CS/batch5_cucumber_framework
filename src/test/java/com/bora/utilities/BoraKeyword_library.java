package com.bora.utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BoraKeyword_library {

<<<<<<< HEAD
	private WebDriver driver;

	public BoraKeyword_library(WebDriver driverWithValue) {
		this.driver = driverWithValue; 
	}

	public WebElement getElement(By locator) {
		return driver.findElement(locator);
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
=======
	private WebDriver driver; // default value is null
	
	

	public BoraKeyword_library(WebDriver driver) {
		this.driver = driver;
	}


	public void login(String userName, String password) {

		elementSendKey(By.name("email"), userName);

		elementSendKey(By.name("password"), password);

>>>>>>> main
		clickElement(By.xpath("//input[@type='submit']"));

	}
	
<<<<<<< HEAD
	public void openUrl (String url) {
		driver.get(url);
	}
	public String getUrl(){
		return driver.getCurrentUrl();
	}

	public String getTitle() {
		return driver.getTitle();
	}
	public boolean isDisplay(By loactor){
		return getElement(loactor).isDisplayed();
	}
=======
	
	
	public void openUrl(String url) {
		driver.get(url);
	}
	
	public String getUrl() {
		return driver.getCurrentUrl();
	}
	
	

	public void elementSendKey(By locator, String data) {
		getElement(locator).sendKeys(data);
	}

	public WebElement getElement(By locator) {
		return driver.findElement(locator);
	}

	public void clickElement(By locator) {
		getElement(locator).click();
	}
	
	public boolean compareUrl(String expectingUrl) {
		String actualUrl = getUrl();
		
		return actualUrl.equals(expectingUrl);
	}
	
	
	
	
	

>>>>>>> main
}
