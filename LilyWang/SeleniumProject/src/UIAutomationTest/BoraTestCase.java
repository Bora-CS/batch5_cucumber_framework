package UIAutomationTest;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class BoraTestCase {
	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "src/resource/chromedriver");
		WebDriver testDriver = new ChromeDriver();
		testDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	
		String testCaseID = "BT_002";
		String actualPageTitle = null;
		String expectedPageTitle = null;
		String actualPageURL = null;
		String expectedPaheURL = null;
		int testCaseNumber = 1;

		// second test case
		
		try {
			System.out.println("Test Case ID:" + testCaseID);
			testDriver.navigate().to("https://boratech.herokuapp.com/");
			actualPageTitle = testDriver.getTitle();
			expectedPageTitle = "BoraTech";
			if (!actualPageTitle.equals(expectedPageTitle)) {
				String failureReason = "==> Step number [" + testCaseNumber + "]\nReason: Page titke doesn't match!\n"
						+ "Actual page title:\t" + actualPageTitle + "\nExpected page title :\t" + expectedPageTitle;
				waitTime(3);
				screenshot(testDriver, testCaseID, testCaseNumber);
				throw new Exception(failureReason);
			}
			waitTime(3);
			screenshot(testDriver, testCaseID, testCaseNumber);

			testCaseNumber++; // 2
			String loginXpath = "(//a[@class='btn btn-light'])";
			WebElement loginButton = testDriver.findElement(By.xpath(loginXpath));
			loginButton.click();
			actualPageURL = testDriver.getCurrentUrl();
			expectedPaheURL = "https://boratech.herokuapp.com/login";
			if (!actualPageURL.equals(expectedPaheURL)) {
				String failureReason = "==> Step number [" + testCaseNumber + "]\nReason: Page titke doesn't match!\n"
						+ "Actual page title:\t" + actualPageTitle + "\nExpected page title :\t" + expectedPageTitle;
				waitTime(3);
				screenshot(testDriver, testCaseID, testCaseNumber);
				throw new Exception(failureReason);
			}
			waitTime(3);
			screenshot(testDriver, testCaseID, testCaseNumber);

			testCaseNumber++;// 3
			String emailXpath = "(//input[@name='email'])";
			WebElement emailField = testDriver.findElement(By.xpath(emailXpath));
			String email = "murad@test.com";
			emailField.sendKeys(email);
			waitTime(3);
			if (!emailField.getAttribute("value").equals(email)) {
				String failureReason = "==> Step number [" + testCaseNumber
						+ "]\nThere is nothing display at email field.";
				waitTime(3);
				screenshot(testDriver, testCaseID, testCaseNumber);
				throw new Exception(failureReason);
			}
			screenshot(testDriver, testCaseID, testCaseNumber);

			testCaseNumber++;// 4
			String passwordXpath = "(//input[@name='password'])";
			WebElement passwordField = testDriver.findElement(By.xpath(passwordXpath));
			String password = "hahaha111";
			passwordField.sendKeys(password);
			waitTime(3);
			if (!passwordField.getAttribute("value").equals(password)) {
				String failureReason = "==> Step number [" + testCaseNumber + "]\nThe acutal password should be: "
						+ password + "/nThe password value inputted is: " + passwordField.getAttribute("value")
						+ "/n Password doesn't match";
				waitTime(3);
				screenshot(testDriver, testCaseID, testCaseNumber);
				throw new Exception(failureReason);
			}
			screenshot(testDriver, testCaseID, testCaseNumber);

			testCaseNumber++;// 5

			WebElement clickButton = testDriver.findElement(By.xpath("//input[@type='submit']"));
			clickButton.click();
			actualPageURL=testDriver.getCurrentUrl();
			expectedPaheURL = "http://boratech.herokuapp.com/login";
			
			if (!testDriver.findElement(By.xpath("//div[@class='alert alert-danger']")).getText().equals("Invalid credentials") &&
					!actualPageURL.equals(expectedPaheURL)	){
				String failureReason = "==> Step number [" + testCaseNumber + "]\nReason: Page URL doesn't match!\n"
						+ "Actual page URL:\t" + actualPageURL + "\nExpected page title :\t" + expectedPaheURL;
				waitTime(3);
				screenshot(testDriver, testCaseID, testCaseNumber);
				throw new Exception(failureReason);
			}
			screenshot(testDriver, testCaseID, testCaseNumber);
			System.out.println("Test passed"); //------------------->>>>>>>>
			testCaseID = "BT_003";
			testCaseNumber = 1;
			System.out.println("Test Case ID:" + testCaseID);
			testDriver.navigate().to("https://boratech.herokuapp.com/");
			actualPageTitle = testDriver.getTitle();
			expectedPageTitle = "BoraTech";
			if (!actualPageTitle.equals(expectedPageTitle)) {
				String failureReason = "==> Step number [" + testCaseNumber + "]\nReason: Page titke doesn't match!\n"
						+ "Actual page title:\t" + actualPageTitle + "\nExpected page title :\t" + expectedPageTitle;
				waitTime(3);
				screenshot(testDriver, testCaseID, testCaseNumber);
				throw new Exception(failureReason);
			}
			waitTime(3);
			screenshot(testDriver, testCaseID, testCaseNumber);

			testCaseNumber++; // 2
			loginXpath = "(//a[@class='btn btn-light'])";
			loginButton = testDriver.findElement(By.xpath(loginXpath));
			loginButton.click();
			actualPageURL = testDriver.getCurrentUrl();
			expectedPaheURL = "https://boratech.herokuapp.com/login";
			if (!actualPageURL.equals(expectedPaheURL)) {
				String failureReason = "==> Step number [" + testCaseNumber + "]\nReason: Page titke doesn't match!\n"
						+ "Actual page title:\t" + actualPageTitle + "\nExpected page title :\t" + expectedPageTitle;
				waitTime(3);
				screenshot(testDriver, testCaseID, testCaseNumber);
				throw new Exception(failureReason);
			}
			waitTime(3);
			screenshot(testDriver, testCaseID, testCaseNumber);

			testCaseNumber++;// 3
			emailXpath = "(//input[@name='email'])";
			emailField = testDriver.findElement(By.xpath(emailXpath));
			email = "invalidHahaha@hahaha.com";
			emailField.sendKeys(email);
			waitTime(3);
			if (!emailField.getAttribute("value").equals(email)) {
				String failureReason = "==> Step number [" + testCaseNumber
						+ "]\nThere is nothing display at email field.";
				waitTime(3);
				screenshot(testDriver, testCaseID, testCaseNumber);
				throw new Exception(failureReason);
			}
			screenshot(testDriver, testCaseID, testCaseNumber);

			testCaseNumber++;// 4
			passwordXpath = "(//input[@name='password'])";
			passwordField = testDriver.findElement(By.xpath(passwordXpath));
			password = "murad001";
			passwordField.sendKeys(password);
			waitTime(3);
			if (!passwordField.getAttribute("value").equals(password)) {
				String failureReason = "==> Step number [" + testCaseNumber + "]\nThe acutal password should be: "
						+ password + "/nThe password value inputted is: " + passwordField.getAttribute("value")
						+ "/n Password doesn't match";
				waitTime(3);
				screenshot(testDriver, testCaseID, testCaseNumber);
				throw new Exception(failureReason);
			}
			screenshot(testDriver, testCaseID, testCaseNumber);

			testCaseNumber++;// 5

			clickButton = testDriver.findElement(By.xpath("//input[@type='submit']"));
			clickButton.click();
			actualPageURL=testDriver.getCurrentUrl();
			expectedPaheURL = "http://boratech.herokuapp.com/login";
			
			if (!testDriver.findElement(By.xpath("//div[@class='alert alert-danger']")).getText().equals("Invalid credentials") &&
					!actualPageURL.equals(expectedPaheURL)	){
				String failureReason = "==> Step number [" + testCaseNumber + "]\nReason: Page URL doesn't match!\n"
						+ "Actual page URL:\t" + actualPageURL + "\nExpected page title :\t" + expectedPaheURL;
				waitTime(3);
				screenshot(testDriver, testCaseID, testCaseNumber);
				throw new Exception(failureReason);
			}
			screenshot(testDriver, testCaseID, testCaseNumber);
			System.out.println("Test passed"); //--------------------------------->>>>>>>
			testCaseID = "BT_001";
			testCaseNumber = 1;
			System.out.println("Test Case ID:" + testCaseID);
			testDriver.navigate().to("https://boratech.herokuapp.com/");
			actualPageTitle = testDriver.getTitle();
			expectedPageTitle = "BoraTech";
			if (!actualPageTitle.equals(expectedPageTitle)) {
				String failureReason = "==> Step number [" + testCaseNumber + "]\nReason: Page titke doesn't match!\n"
						+ "Actual page title:\t" + actualPageTitle + "\nExpected page title :\t" + expectedPageTitle;
				waitTime(3);
				screenshot(testDriver, testCaseID, testCaseNumber);
				throw new Exception(failureReason);
			}
			waitTime(3);
			screenshot(testDriver, testCaseID, testCaseNumber);

			testCaseNumber++; // 2
			loginXpath = "(//a[@class='btn btn-light'])";
			loginButton = testDriver.findElement(By.xpath(loginXpath));
			loginButton.click();
			actualPageURL = testDriver.getCurrentUrl();
			expectedPaheURL = "https://boratech.herokuapp.com/login";
			if (!actualPageURL.equals(expectedPaheURL)) {
				String failureReason = "==> Step number [" + testCaseNumber + "]\nReason: Page titke doesn't match!\n"
						+ "Actual page title:\t" + actualPageTitle + "\nExpected page title :\t" + expectedPageTitle;
				waitTime(3);
				screenshot(testDriver, testCaseID, testCaseNumber);
				throw new Exception(failureReason);
			}
			waitTime(3);
			screenshot(testDriver, testCaseID, testCaseNumber);

			testCaseNumber++;// 3
			emailXpath = "(//input[@name='email'])";
			emailField = testDriver.findElement(By.xpath(emailXpath));
			email = "murad@test.com";
			emailField.sendKeys(email);
			waitTime(3);
			if (!emailField.getAttribute("value").equals(email)) {
				String failureReason = "==> Step number [" + testCaseNumber
						+ "]\nThere is nothing display at email field.";
				waitTime(3);
				screenshot(testDriver, testCaseID, testCaseNumber);
				throw new Exception(failureReason);
			}
			screenshot(testDriver, testCaseID, testCaseNumber);

			testCaseNumber++;// 4
			passwordXpath = "(//input[@name='password'])";
			passwordField = testDriver.findElement(By.xpath(passwordXpath));
			password = "murad001";
			passwordField.sendKeys(password);
			waitTime(3);
			if (!passwordField.getAttribute("value").equals(password)) {
				String failureReason = "==> Step number [" + testCaseNumber + "]\nThe acutal password should be: "
						+ password + "/nThe password value inputted is: " + passwordField.getAttribute("value")
						+ "/n Password doesn't match";
				waitTime(3);
				screenshot(testDriver, testCaseID, testCaseNumber);
				throw new Exception(failureReason);
			}
			screenshot(testDriver, testCaseID, testCaseNumber);

			testCaseNumber++;// 5

			clickButton = testDriver.findElement(By.xpath("//input[@type='submit']"));
			clickButton.click();
			waitTime(3);
			actualPageURL=testDriver.getCurrentUrl();
			expectedPaheURL = "http://boratech.herokuapp.com/dashboard";
			
			if (!testDriver.findElement(By.xpath("//h1[@class='large text-primary']")).getText().equals("Dashboard") &&
					!actualPageURL.equals(expectedPaheURL)	){
				String failureReason = "==> Step number [" + testCaseNumber + "]\nReason: Page URL doesn't match!\n"
						+ "Actual page URL:\t" + actualPageURL + "\nExpected page title :\t" + expectedPaheURL;
				waitTime(3);
				screenshot(testDriver, testCaseID, testCaseNumber);
				throw new Exception(failureReason);
			}
			screenshot(testDriver, testCaseID, testCaseNumber);
			System.out.println("Test passed");

			
		} catch (Exception e) {
			System.out.println("Test failed");
			System.out.println(e.getMessage());
		} finally {
			testDriver.close();
			testDriver.quit();
		}
	}

	public static void screenshot(WebDriver driver, String testCaseId, int testCaseNumber) throws IOException {
		File screenShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenShot, new File("src/ScreenShotHolder/" + testCaseId + testCaseNumber + ".png"));
	}

	public static void waitTime(int waitSecond) throws InterruptedException {
		int wait = waitSecond * 1000;
		Thread.sleep(wait);
	}

}