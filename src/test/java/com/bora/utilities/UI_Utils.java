package com.bora.utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class UI_Utils {

	public static void takeScreenShot(WebDriver driver, String filePrefix) throws IOException {
		if (filePrefix == null)
			filePrefix = "";
		File screenShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenShot, new File("target/screenShots/" + filePrefix + getTimeStamp() + ".png"));
	}

	public static String getTimeStamp() {
		SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyyMMddHHmmssS");
		Date date = new Date();
		String timeStamp = simpleFormat.format(date);
		return timeStamp;
	}

	public static void waitFor(int seconds) throws InterruptedException {
		Thread.sleep(seconds * 1000);
	}

	/**
	 * This function checks to see if an element exists in the html regardless if
	 * the element is visually displayed or not
	 * 
	 * @param: locator By object
	 * 
	 * @param: driver  WebDriver instance
	 * 
	 * @author: murad
	 */
	public static boolean isElementExist(WebDriver driver, By locator) {
		try {
			driver.findElement(locator);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public static void input(WebDriver driver, By locator, String inputString) {
		driver.findElement(locator).sendKeys(inputString);
	}

	public static void selectRadioByValue(WebDriver driver, By locator, String optionValue) {
		List<WebElement> options = driver.findElements(locator);
		options.forEach(option -> {
			if (option.getAttribute("value").equalsIgnoreCase(optionValue)) {
				option.click();
			}
		});
	}

	public static void selectDropdownByValue(WebDriver driver, By locator, String value) {
		Select dropdown = new Select(driver.findElement(locator));
		dropdown.selectByValue(value);
	}

	public static void click(WebDriver driver, By locator) {
		driver.findElement(locator).click();
	}

	public static String getText(WebDriver driver, By locator) {
		return driver.findElement(locator).getText();
	}

	public static boolean isTextFieldEmpty(WebDriver driver, By locator, boolean throwsException) throws Exception {
		boolean isEmpty = driver.findElement(locator).getText().isEmpty();

		if (!isEmpty && throwsException) {
			throw new Exception("Element value was expected to be empty.");
		}

		return isEmpty;
	}

	public static boolean isTextFieldEmpty(WebDriver driver, By locator) {
		try {
			return isTextFieldEmpty(driver, locator, false);
		} catch (Exception e) {
			return false;
		}
	}

}
