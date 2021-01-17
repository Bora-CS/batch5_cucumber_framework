package com.bora.selenium;

import org.openqa.selenium.WebDriver;

import com.bora.utilities.DriverFactory;

public class SampleUITest {

	public static WebDriver driver = null;

	public static void main(String[] args) {

		driver = DriverFactory.getInstance();

		try {

			driver.get("https://www.google.com/");

			if (driver.getTitle().equalsIgnoreCase("Google")) {
				System.out.println("Test passed!");
			} else {
				System.out.println("Something went wrong, test failed!");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			driver.close();
			driver.quit();
		}

	}

}
