package com.bora.utilities;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverFactory {

	private static WebDriver driver = null;

	private DriverFactory() {
	}

	public static WebDriver getInstance() {
		try {
			if (driver == null) {
				System.setProperty("webdriver.chrome.driver", getDriverPath());
				driver = new ChromeDriver();
				driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
			}
			return driver;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void cleanUp() {
		if (driver != null) {
			driver.close();
			driver.quit();
			driver = null;
		}
	}

	private static String getDriverPath() throws Exception {
		String os = System.getProperty("os.name");
		if (os.toLowerCase().startsWith("mac")) {
			return Constants.CHROME_DRIVER_PATH_MAC;
		} else if (os.toLowerCase().startsWith("windows")) {
			return Constants.CHROME_DRIVER_PATH_WINDOWS;
		} else {
			throw new Exception("OS Name: " + os + ", No ChromeDriver availbale for this operating system.");
		}
	}

}
