package com.bora.utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BoraKeyword_library {


	private WebDriver driver;// default value is null
	private PropertyReader reader = new PropertyReader();

	public BoraKeyword_library(WebDriver driverWithValue) {
		this.driver = driverWithValue; 
	}
	public By getLocator (String key){
		return reader.readLocator(key);
	}
	public String getURLFromDataReader (String key){
		return reader.readURL(key);
	}
	public String getUserEmail (String key){
		return reader.userEmail(key);
	}
	public String getUserPassword (String key){
		return reader.userPassword(key);
	}
	public String getPost (String key){
		return reader.postMessage(key);
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
		sumbitKey(By.name("password"), password);}

	


	public String getTitle() {
		return driver.getTitle();
	}
	public boolean isDisplay(By loactor){
		return getElement(loactor).isDisplayed();
	}

	
	
	public void openUrl(String url) {
		driver.get(url);
	}
	
	public String getUrl() {
		return driver.getCurrentUrl();
	}
	
	

	public void elementSendKey(By locator, String data) {
		getElement(locator).sendKeys(data);
	}

	
	
	public boolean compareUrl(String expectingUrl) {
		String actualUrl = getUrl();
		
		return actualUrl.equals(expectingUrl);
	}
	
	
	
	
	


}
