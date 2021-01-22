package com.bora.selenium;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.bora.utilities.DriverFactory;
import com.bora.utilities.UI_Utils;

public class BoraCommunityAddExperienceTest {

	public static WebDriver driver = null;

	public static void main(String[] args) throws IOException {

		driver = DriverFactory.getInstance();

		String jobTitle = "Cashier";
		String company = "";
		String location = "Fairfax, VA";
		String fromDate = "";
		boolean currentJob = false;
		String toDate = "01/21/2021";
		String jobDescription = "Counting money every day!";
		boolean isHappy = false;
		String[] expectedErrors = { "Title is required", "Company is required", "From date is required" };

		try {
			System.out.println("==> Test Started...");
			driver.get("https://boratech.herokuapp.com/");

			// Log in
			driver.findElement(By.linkText("Login")).click();
			driver.findElement(By.name("email")).sendKeys("murad@test.com");
			driver.findElement(By.name("password")).sendKeys("murad001");
			driver.findElement(By.xpath("//input[@type='submit']")).click();

			// Add experience
			driver.findElement(By.xpath("//a[@href='/add-experience']")).click();
			String actualUrl = driver.getCurrentUrl();
			String expectedUrl = "https://boratech.herokuapp.com/add-experience";
			compareExpectedAndActual("Page URL", actualUrl, expectedUrl);

			driver.findElement(By.xpath("//input[@name='title']")).sendKeys(jobTitle);
			driver.findElement(By.xpath("//input[@name='company']")).sendKeys(company);
			driver.findElement(By.xpath("//input[@name='from']")).sendKeys(fromDate);
			driver.findElement(By.xpath("//input[@name='location']")).sendKeys(location);
			if (currentJob) {
				driver.findElement(By.xpath("//input[@name='current']")).click();
			} else {
				driver.findElement(By.xpath("//input[@name='to']")).sendKeys(toDate);
			}
			driver.findElement(By.xpath("//textarea[@name='description']")).sendKeys(jobDescription);
			driver.findElement(By.xpath("//input[@type='submit']")).click();

			UI_Utils.waitFor(1);

			// validation
			actualUrl = driver.getCurrentUrl();
			if (isHappy) {
				expectedUrl = "https://boratech.herokuapp.com/dashboard";
			} else {
				expectedUrl = "https://boratech.herokuapp.com/add-experience";
			}
			compareExpectedAndActual("Page URL", actualUrl, expectedUrl);

			if (isHappy) {
				WebElement alert = driver.findElement(By.xpath("//div[@class='alert alert-success']"));
				String expectedAlertText = "Experience Added";
				String actualAlertText = alert.getText();
				compareExpectedAndActual("Success Alert Text", actualAlertText, expectedAlertText);
			} else {
				List<WebElement> errorAlerts = driver.findElements(By.xpath("//div[@class='alert alert-danger']"));
				List<String> actualAlertTexts = new ArrayList<String>();
				errorAlerts.forEach(errorAlert -> actualAlertTexts.add(errorAlert.getText()));
				List<String> expectedAlertTexts = Arrays.asList(expectedErrors);
				Collections.sort(expectedAlertTexts);
				Collections.sort(actualAlertTexts);

				int numberOfExpectedAlerts = expectedAlertTexts.size();
				int numberOfActualAlerts = actualAlertTexts.size();
				compareExpectedAndActual("Number of alerts", numberOfExpectedAlerts + "", numberOfActualAlerts + "");

				for (int i = 0; i < numberOfActualAlerts; i++) {
					String currentExpectedAlertText = expectedAlertTexts.get(i);
					String currentActualAlertText = actualAlertTexts.get(i);
					compareExpectedAndActual("Error Alert", currentExpectedAlertText, currentActualAlertText);
				}
			}

			System.out.println("==> Test Passed!");

		} catch (Exception e) {
			System.out.println("==> Reason: " + e.getMessage());
			System.out.println("==> Test Failed");
		} finally {
			DriverFactory.cleanUp();
		}

	}

	public static void compareExpectedAndActual(String whatIsCompared, String actualValue, String expectedValue)
			throws Exception {
		if (!actualValue.equals(expectedValue)) {
			String errorMessage = whatIsCompared + " Doesn't Match";
			errorMessage += "\nExpected " + whatIsCompared + ":\t" + expectedValue;
			errorMessage += "\nActual " + whatIsCompared + ":\t" + actualValue;
			throw new Exception(errorMessage);
		}
	}

}
