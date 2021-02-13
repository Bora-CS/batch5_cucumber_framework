package com.bora.utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BoraKeyword_library {

	private WebDriver driver;// default value is null

    //one way:
	//	public BoraKeyword_library(WebDriver driverWithValue) {
	//	driver = driverWithValue;
	// Second way:
	public BoraKeyword_library(WebDriver driver){
	this.driver =driver; 

	}

	public void login(String userName, String password) {

		elementSendKeys(By.name("email"), userName);// use control space to
		// replace this ===>getElement(By.name("email")).sendKeys(userName);
		elementSendKeys(By.name("password"), password);

		// getElement(By.name("password")).sendKeys(password);
		clickElement(By.xpath("//input[@type='submit']"));

		/*
		 * WebElement emailField =
		 * lib.getElement(By.name("email"));//replace===>driver.findElement(By.name(
		 * "email")); emailField.sendKeys(userEmail); WebElement passwordField =
		 * lib.getElement(By.name("password"));//driver.findElement(By.name("password"))
		 * ; passwordField.sendKeys(userPassword);
		 * lib.getElement(By.xpath("//input[@type='submit']")).click();
		 */
	}

	public void openUrl(String url) {
		driver.get(url);
	}

	public String getUrl() {
		return driver.getCurrentUrl();

	}

	public void elementSendKeys(By locator, String data) {
		getElement(locator).sendKeys(data);

	}

	public WebElement getElement(By locator) {

		return driver.findElement(locator);
	}

	public void clickElement(By locator) {
		// driver.findElement(locator).click()====>replace to below as
		getElement(locator).click();
	}

	public boolean compareUrl(String expectingUrl) {
		String actural = getUrl();
//		System.out.println("currentUrl: is "+actural);
//		System.out.println("expectingUrl: is "+expectingUrl);
		return actural.equals(expectingUrl);
	}

}