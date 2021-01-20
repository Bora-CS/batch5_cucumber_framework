package com.bora.selenium;

import java.io.IOException;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.bora.dataObjects.LogInTestData;
import com.bora.utilities.DriverFactory;
import com.bora.utilities.UI_Utils;

public class BoraCommunityLogInTestWithPOJO {

	public static WebDriver driver = null;
	public static int testStep = 1;

	public static void main(String[] args) throws IOException {

		ArrayList<LogInTestData> logInTestData = new ArrayList<LogInTestData>();
		logInTestData.add(new LogInTestData("BT_001", "murad@test.com", "murad001", "happy"));
		logInTestData.add(new LogInTestData("BT_002", "murad@test.com", "hahaha111", "error"));
		logInTestData.add(new LogInTestData("BT_003", "invalidHahaha@hahaha.com", "murad001", "error"));

		for (LogInTestData data : logInTestData) {
			testStep = 1;
			loginTest(data);
		}

	}

	public static void loginTest(LogInTestData data) throws IOException {
		System.out.println("------------------------------");
		System.out.println("==> Test Case ID [" + data.testCaseId + "]");
		driver = DriverFactory.getInstance();

		try {

			// 1
			System.out.println("==> Test Started...");
			driver.get("https://boratech.herokuapp.com/");
			String actualTitle = driver.getTitle();
			String expectedTitle = "BoraTech";
			compareExpectedAndActual("Page Title", actualTitle, expectedTitle);

			// 2
			testStep++;
			driver.findElement(By.linkText("Login")).click();
			String actualUrl = driver.getCurrentUrl();
			String expectedUrl = "https://boratech.herokuapp.com/login";
			compareExpectedAndActual("URL", actualUrl, expectedUrl);

			// 3
			testStep++;
			WebElement emailField = driver.findElement(By.name("email"));
			emailField.sendKeys(data.email);
			String enteredEmail = emailField.getAttribute("value");
			compareExpectedAndActual("Entered Email", enteredEmail, data.email);

			// 4
			testStep++;
			WebElement passwordField = driver.findElement(By.name("password"));
			passwordField.sendKeys(data.password);
			String enteredPassword = passwordField.getAttribute("value");
			compareExpectedAndActual("Entered Password", enteredPassword, data.password);

			// 5
			testStep++;
			driver.findElement(By.xpath("//input[@type='submit']")).click();
			UI_Utils.waitFor(1);
			if (data.scenarioType.equals("happy")) {
				actualUrl = driver.getCurrentUrl();
				expectedUrl = "https://boratech.herokuapp.com/dashboard";
				compareExpectedAndActual("URL", actualUrl, expectedUrl);
			} else if (data.scenarioType.equals("error")) {
				actualUrl = driver.getCurrentUrl();
				expectedUrl = "https://boratech.herokuapp.com/login";
				compareExpectedAndActual("URL", actualUrl, expectedUrl);

				testStep++;
				try {
					WebElement alert = driver.findElement(By.xpath("//div[@class='alert alert-danger']"));
					String actualAlertText = alert.getText();
					String expectedAlertText = "Invalid credentials";
					compareExpectedAndActual("Alert Text", actualAlertText, expectedAlertText);
				} catch (NoSuchElementException e) {
					throw new Exception("An alert was expected, but not found.");
				}
			} else {
				throw new Exception("Scenario type is undefined.");
			}

			System.out.println("==> Test Passed");

		} catch (Exception e) {
			System.out.println("==> Step " + testStep + " failed");
			System.out.println("==> Reason: " + e.getMessage());
			UI_Utils.takeScreenShot(driver, data.testCaseId + "_S" + testStep + "_");
			System.out.println("==> Test Failed");
		} finally {
			DriverFactory.cleanUp();
		}
	}

	public static void compareExpectedAndActual(String whatIsCompared, String actualValue, String expectedValue)
			throws Exception {
		if (actualValue.equals(expectedValue)) {
			System.out.println("==> Step " + testStep + " passed");
		} else {
			String errorMessage = whatIsCompared + " Doesn't Match";
			errorMessage += "\nExpected " + whatIsCompared + ":\t" + expectedValue;
			errorMessage += "\nActual " + whatIsCompared + ":\t" + actualValue;
			throw new Exception(errorMessage);
		}
	}

}
