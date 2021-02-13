package com.bora.karenhomework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.bora.utilities.DriverFactory;

public class BoraCommiuntyAddExperienceUITast {

	public static WebDriver driver= null;

	public static void main(String[] args) {
		
		driver=DriverFactory.getInstance();
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
			//Log in
			driver.get("https://boratech.herokuapp.com/");
			driver.findElement(By.linkText("Login")).click();
			driver.findElement(By.name("email")).sendKeys("myemail@gmail.com");
			driver.findElement(By.name("password")).sendKeys("boratech123");
			driver.findElement(By.xpath("//input[@type='submit']")).click();
			
			//Add experience 
			driver.findElement(By.xpath("//a[@href='/add-experience']")).click();
			driver.findElement(By.xpath("//input[@name='title']")).sendKeys(jobTitle);
			driver.findElement(By.xpath("//input[@name='company']")).sendKeys(company);
			driver.findElement(By.xpath("//input[@name='from']")).sendKeys(fromDate);
			driver.findElement(By.xpath("//input[@name='location']")).sendKeys(location);
			if (currentJob) {
				driver.findElement(By.xpath("//input[@name='current']")).click();
			}else {
				driver.findElement(By.xpath("//input[@name='to']")).sendKeys(toDate);
			}
			driver.findElement(By.xpath("//textarea[@name='description']")).sendKeys(jobDescription);
			driver.findElement(By.xpath("//input[@type='submit']")).click();
			
			
		} catch (Exception e) {

			
			
		}

	}

}
