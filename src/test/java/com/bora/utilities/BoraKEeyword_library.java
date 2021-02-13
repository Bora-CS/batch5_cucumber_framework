package com.bora.utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.google.inject.spi.Element;

public class BoraKEeyword_library {
	private WebDriver driver;

	public void login(String userName,String password) {
		
		 elementSendKey(By.name("email"),userName);
		 elementSendKey(By.name("password")).password;
		 clickElement(By.xpath("//input[@type='submit']"));
	}

	public void elementSendKey(By locator, String data) {
		getElemnet(locator).sendKeys(data);
	}

	public WebElement getElemnet(By locator) {
		return driver.findElement(locator);
	}

}
