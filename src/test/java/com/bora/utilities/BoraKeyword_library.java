package com.bora.utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BoraKeyword_library {

	private WebDriver driver; // default value is null
	private PropertyReader reader = new PropertyReader();


	public BoraKeyword_library(WebDriver driver) {
		this.driver = driver;
	}


	public By getLocator(String key) {
		return reader.locatorReader(key);
	}
	
	public String getUrlFromDataReader(String key) {
		return reader.urlData(key);
	}
	
	
	
	
	
	
	public void login(String userName, String password) {

		elementSendKey(By.name("email"), userName);

		elementSendKey(By.name("password"), password);

		clickElement(By.xpath("//input[@type='submit']"));

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
	
	
	
	
	

}
