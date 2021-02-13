package com.bora.utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BoraAddExpericenKeyword {

	private WebDriver driver;
	
	public void boraLogin(String userName , String password) {
		
	}
	
	public void clickBoraElement(By locator) {
		
		getBoraElement(locator).click();
	}
	
	public WebElement getBoraElement(By locator) {
		
		return driver.findElement(locator);
	}
	
	public void boraSendKeyElement(By locator , String data) {
		
		getBoraElement(locator).sendKeys(data);
	}
}
