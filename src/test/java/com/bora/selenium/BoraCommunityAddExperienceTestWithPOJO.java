package com.bora.selenium;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.bora.dataObjects.AddExperienceTestData;
import com.bora.utilities.DriverFactory;
import com.bora.utilities.UI_Utils;

public class BoraCommunityAddExperienceTestWithPOJO {

	public static WebDriver driver = null;

	public static void main(String[] args) throws IOException {

		ArrayList<AddExperienceTestData> testData = new ArrayList<AddExperienceTestData>();
		testData.add(new AddExperienceTestData(true, "Cashier", "Chick-fil-a", "Fairfax", "12/12/2018", false,
				"01/01/2020", "Counting money errday!", new String[] {}));
		testData.add(new AddExperienceTestData(false, "", "Chick-fil-a", "Fairfax", "12/12/2018", false, "01/01/2020",
				"Counting money errday!", new String[] { "Title is required" }));
		testData.add(new AddExperienceTestData(false, "Cashier", "", "Fairfax", "12/12/2018", false, "01/01/2020",
				"Counting money errday!", new String[] { "Company is required" }));
		testData.add(new AddExperienceTestData(false, "Cashier", "Chick-fil-a", "Fairfax", "", false, "01/01/2020",
				"Counting money errday!", new String[] { "From date is required" }));
		testData.add(new AddExperienceTestData(false, "Cashier", "", "Fairfax", "", false, "01/01/2020",
				"Counting money errday!", new String[] { "Company is required", "From date is required" }));
		testData.add(
				new AddExperienceTestData(false, "", "", "Fairfax", "", false, "01/01/2020", "Counting money errday!",
						new String[] { "Title is required", "Company is required", "From date is required" }));

//		testData.forEach(data -> addExperienceTest(data));
		for (AddExperienceTestData data : testData) {
			addExperienceTest(data);
		}

	}

	public static void addExperienceTest(AddExperienceTestData data) {
		driver = DriverFactory.getInstance();
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

			driver.findElement(By.xpath("//input[@name='title']")).sendKeys(data.jobTitle);
			driver.findElement(By.xpath("//input[@name='company']")).sendKeys(data.company);
			driver.findElement(By.xpath("//input[@name='from']")).sendKeys(data.fromDate);
			driver.findElement(By.xpath("//input[@name='location']")).sendKeys(data.location);
			if (data.currentJob) {
				driver.findElement(By.xpath("//input[@name='current']")).click();
			} else {
				driver.findElement(By.xpath("//input[@name='to']")).sendKeys(data.toDate);
			}
			driver.findElement(By.xpath("//textarea[@name='description']")).sendKeys(data.jobDescription);
			driver.findElement(By.xpath("//input[@type='submit']")).click();

			UI_Utils.waitFor(1);

			// validation
			actualUrl = driver.getCurrentUrl();
			if (data.isHappy) {
				expectedUrl = "https://boratech.herokuapp.com/dashboard";
			} else {
				expectedUrl = "https://boratech.herokuapp.com/add-experience";
			}
			compareExpectedAndActual("Page URL", actualUrl, expectedUrl);

			if (data.isHappy) {
				WebElement alert = driver.findElement(By.xpath("//div[@class='alert alert-success']"));
				String expectedAlertText = "Experience Added";
				String actualAlertText = alert.getText();
				compareExpectedAndActual("Success Alert Text", actualAlertText, expectedAlertText);
			} else {
				List<WebElement> errorAlerts = driver.findElements(By.xpath("//div[@class='alert alert-danger']"));
				List<String> actualAlertTexts = new ArrayList<String>();
				errorAlerts.forEach(errorAlert -> actualAlertTexts.add(errorAlert.getText()));
				List<String> expectedAlertTexts = Arrays.asList(data.expectedErrors);
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
