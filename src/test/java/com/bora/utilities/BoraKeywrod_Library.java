package com.bora.utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BoraKeywrod_Library {

	private WebDriver driver;

	public BoraKeywrod_Library(WebDriver driver2) {

		this.driver=driver2;
	}

	public void login(String userName, String passdword) {

		elementSendKey(By.name("email"), userName);

		elementSendKey(By.name("password"), passdword);

		clickElement(By.xpath("//input[@type='submit']"));
	}

	public void elementSendKey(By locator, String data) {

		getElment(locator).sendKeys(data);
		
	}

	public WebElement getElment(By locator) {

		return driver.findElement(locator);

	}

	public void clickElement(By locator) {

		getElment(locator).click();
	}

}
