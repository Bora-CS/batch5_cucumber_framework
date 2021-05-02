package com.bora.emmahomework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.bora.utilities.DriverFactory;
import com.bora.utilities.UI_Utils;

public class DropDown {

	static WebDriver driver;

	public static void main(String[] args) {

		driver = DriverFactory.getInstance();

		try {
			driver.get("https://www.facebook.com/");
			driver.manage().window().maximize();
			String email = "271866163@qq.com";
			String password = "Iliveinny0412";
			driver.findElement(By.id("email")).sendKeys(email);
			driver.findElement(By.id("pass")).sendKeys(password);
			driver.findElement(By.id("u_0_h_6Q")).click();
			
			UI_Utils.waitFor(3);
			
			WebElement select = driver.findElement(By.xpath("//*[contains(@class,'sx_980e99')]"));
			Select obj = new Select(select);
			obj.selectByIndex(0);

		} catch (Exception e) {
			e.getMessage();
		} finally {
			DriverFactory.cleanUp();
		}

	}

}
