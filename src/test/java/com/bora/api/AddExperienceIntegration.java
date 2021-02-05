package com.bora.api;

import java.util.List;

import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.bora.utilities.BoraAPIs;
import com.bora.utilities.DriverFactory;
import com.bora.utilities.UI_Utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class AddExperienceIntegration {

	public static void main(String[] args) {

		String email = "murad@test.com";
		String password = "murad001";
		String company = "BoraTech";
		company += UI_Utils.getTimeStamp();
		String title = "Teacher";

		RestAssured.baseURI = "https://boratech.herokuapp.com";
		String endpoint = "/api/profile/experience";
		
		RequestSpecification request = RestAssured.given();
		request.header("x-auth-token", BoraAPIs.login(email, password));
		request.header("Content-type", "application/json");
		
		JSONObject body = new JSONObject();
		body.put("title", title);
		body.put("company", company);
		body.put("location", "Annandale, VA");
		body.put("from", "1992-05-29");
		body.put("to", "2018-10-10");
		body.put("current", false);
		body.put("description", "Means like I don't know");
		
		request.body(body);
		
		Response response = request.put(endpoint);
		if (response.getStatusCode() != 200) {
			System.out.println("API Failed.");
		}

		WebDriver driver = DriverFactory.getInstance();
		try {
			driver.get("https://boratech.herokuapp.com/");
			driver.findElement(By.linkText("Login")).click();
			driver.findElement(By.name("email")).sendKeys(email);
			driver.findElement(By.name("password")).sendKeys(password);
			driver.findElement(By.xpath("//input[@type='submit']")).click();
			
			UI_Utils.waitFor(2);
			List<WebElement> experiences = driver.findElements(By.xpath("//table[1]/tbody/tr"));
			boolean found = false;
			for (WebElement experience : experiences) {
				List<WebElement> cells = experience.findElements(By.tagName("td"));
				String uiCompany = cells.get(0).getText();
				String uiTitle = cells.get(1).getText();
				if (uiCompany.equals(company) && uiTitle.equals(title)) {
					found = true;
					break;
				}
			}

			if (found) {
				System.out.println("Test Passed!");
			} else {
				System.out.println("Test Failed!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DriverFactory.cleanUp();
		}

	}

}
