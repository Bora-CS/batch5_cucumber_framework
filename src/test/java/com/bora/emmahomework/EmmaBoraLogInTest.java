package com.bora.emmahomework;

import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.bora.utilities.DriverFactory;
import com.bora.utilities.UI_Utils;

import bora.emmaObjects.LogInTestData;

public class EmmaBoraLogInTest {

	public static WebDriver driver = null;
	public static int testStep = 1;
	public static String testCaseID;
	public static String userEmail;
	public static String userPassword;
	public static String scearioType;

	public static void main(String[] args) throws IOException {

		ArrayList<LogInTestData> logInTestData = new ArrayList<LogInTestData>();
		logInTestData.add(new LogInTestData("Bora_001", "myemail@gmail.com", "boratech123", "Happy"));
		logInTestData.add(new LogInTestData("Bora_002", "myemail@gmail.com", "boratech1123", "Error"));
		logInTestData.add(new LogInTestData("Bora_003", "invalidmyemail@gmail.com", "boratech123", "Error"));

		for (LogInTestData data : logInTestData) {
			testStep = 1;

			logInTest(data);
		}

	}

	private static void logInTest(LogInTestData data) throws IOException {
		driver = DriverFactory.getInstance();
		System.out.println("===>Test Case ID " + "[" + data.testCaseId + "]");

		try {

			System.out.println("===> Test stated .......");
			driver.get("https://boratech.herokuapp.com/");
			String actualTitle = driver.getTitle();
			String expectTitle = "BoraTech";
			compareMethod("Title", actualTitle, expectTitle);
			// 2
			testStep++;
			driver.findElement(By.linkText("Login")).click();
			String actualUrl = driver.getCurrentUrl();
			String expectedUrl = "https://boratech.herokuapp.com/login";
			compareMethod("URL", actualUrl, expectedUrl);

			// 3
			testStep++;
			WebElement emailFeild = driver.findElement(By.name("email"));
			emailFeild.sendKeys(data.email);
			String enterEmail = emailFeild.getAttribute("value");
			compareMethod("Entered Email : ", enterEmail, data.email);

			// 4
			testStep++;
			WebElement passwordFeild = driver.findElement(By.name("password"));
			passwordFeild.sendKeys(data.password);
			String enterPassword = passwordFeild.getAttribute("value");
			compareMethod("Entered Password :", enterPassword, data.password);
			// 5
			testStep++;
			driver.findElement(By.xpath("//input[@type='submit']")).click();
			UI_Utils.waitFor(2);
			if (data.scenarioType.equals("Happy")) {
				actualUrl = driver.getCurrentUrl();
				expectedUrl = "https://boratech.herokuapp.com/dashboard";
				compareMethod("Dash URL", actualUrl, expectedUrl);

			} else if (data.scenarioType.equals("Error")) {
				actualUrl = driver.getCurrentUrl();
				expectedUrl = "https://boratech.herokuapp.com/login";
				compareMethod("Dash URL", actualUrl, expectedUrl);

				try {
					WebElement alert = driver.findElement(By.xpath("//*[@class='alert alert-danger']"));
					String actualAlertText = alert.getText();
					String expectAlertText = "Invalid credentials";
					compareMethod("Alert Text", actualAlertText, expectAlertText);
				} catch (NoSuchElementException e) {
					throw new Exception("An alert is expected , but not found . ");
				}

			} else {
				throw new Exception("Scenario Type is undefined . ");
			}

			System.out.println("===> **Test Passed !**");

		} catch (Exception e) {
			System.out.println("Step " + testStep + " ===> Failed !");
			System.out.println("Reson :" + e.getMessage());

			UI_Utils.takeScreenShot(driver, testCaseID + "_S" + testStep);
			System.out.println("===> ##Test Failed !##");
		} finally {
			DriverFactory.cleanUp();
		}
	}

	private static void compareMethod(String whatsCompare, String actualValue, String expectValue) throws Exception {
		if (actualValue.equals(expectValue)) {
			System.out.println("Step " + testStep + "  ===> Passed");
		} else {

			String erroMessage = whatsCompare + " Does't Match";
			erroMessage += "\nExpected  : \t " + whatsCompare + ":\t" + expectValue;
			erroMessage += "\nActual  : \t " + whatsCompare + ":\t" + actualValue;
			throw new Exception(erroMessage);
		}
	}

}
