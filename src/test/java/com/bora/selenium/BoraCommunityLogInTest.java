package com.bora.selenium;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.bora.utilities.DriverFactory;
import com.bora.utilities.UI_Utils;

public class BoraCommunityLogInTest {

	public static WebDriver driver = null;
	public static int testStep = 1;
	public static String testCaseId;
	public static String userEmail;
	public static String userPassword;
	public static String scenarioType;

	public static void main(String[] args) throws IOException {

		Map<String, String[]> testData = new HashMap<String, String[]>();
		testData.put("BT_001", new String[] { "murad@test.com", "murad001", "happy" });
		testData.put("BT_002", new String[] { "murad@test.com", "hahaha111", "error" });
		testData.put("BT_003", new String[] { "invalidHahaha@hahaha.com", "murad001", "error" });

		for (String tcId : testData.keySet()) {
			testStep = 1;
			testCaseId = tcId;
			String[] individualTestData = testData.get(tcId);
			userEmail = individualTestData[0];
			userPassword = individualTestData[1];
			scenarioType = individualTestData[2];

			loginTest();
		}

	}

	public static void loginTest() throws IOException {
		System.out.println("------------------------------");
		System.out.println("==> Test Case ID [" + testCaseId + "]");
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
			emailField.sendKeys(userEmail);
			String enteredEmail = emailField.getAttribute("value");
			compareExpectedAndActual("Entered Email", enteredEmail, userEmail);

			// 4
			testStep++;
			WebElement passwordField = driver.findElement(By.name("password"));
			passwordField.sendKeys(userPassword);
			String enteredPassword = passwordField.getAttribute("value");
			compareExpectedAndActual("Entered Password", enteredPassword, userPassword);

			// 5
			testStep++;
			driver.findElement(By.xpath("//input[@type='submit']")).click();
			UI_Utils.waitFor(1);
			if (scenarioType.equals("happy")) {
				actualUrl = driver.getCurrentUrl();
				expectedUrl = "https://boratech.herokuapp.com/dashboard";
				compareExpectedAndActual("URL", actualUrl, expectedUrl);
			} else if (scenarioType.equals("error")) {
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
			UI_Utils.takeScreenShot(driver, testCaseId + "_S" + testStep + "_");
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
