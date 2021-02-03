package com.bora.api;

import java.util.ArrayList;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.bora.utilities.DriverFactory;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetAllUserProfiles {

	public static void main(String[] args) {

		HashMap<String, String> apiProfiles = new HashMap<String, String>();
		HashMap<String, String> uiProfiles = new HashMap<String, String>();

		// Collect data from API
		String endpoint = "/api/profile";
		RestAssured.baseURI = "https://boratech.herokuapp.com";
		RequestSpecification request = RestAssured.given();
		Response response = request.get(endpoint);

		JsonPath jsonPath = response.jsonPath();

		ArrayList<Object> listOfProfiles = jsonPath.get("");

		for (int i = 0; i < listOfProfiles.size(); i++) {
			String name = jsonPath.get("[" + i + "].user.name");
			String company = jsonPath.get("[" + i + "].company");
			String status = jsonPath.get("[" + i + "].status");

			apiProfiles.put(name, status + (company == null ? "" : " " + company));
		}

		// Collect data from UI
		WebDriver driver = DriverFactory.getInstance();
		driver.get("https://boratech.herokuapp.com/profiles");

		String profileContainers = "(//div[contains(@class, 'profile ')])";
		String profileName = "//h2";
		String profileStatusLine = "//p[1]";

		int numberOfProfilesOnUI = driver.findElements(By.xpath(profileContainers)).size();
		for (int index = 1; index <= numberOfProfilesOnUI; index++) {
			String name = driver.findElement(By.xpath(profileContainers + "[" + index + "]" + profileName)).getText();
			String statusLine = driver.findElement(By.xpath(profileContainers + "[" + index + "]" + profileStatusLine))
					.getText();

			uiProfiles.put(name, statusLine);
		}

		DriverFactory.cleanUp();

		// Compare & Validate
		try {
			int numberOfUIProfiles = uiProfiles.size();
			int numberOfAPIProfiles = apiProfiles.size();

			if (numberOfUIProfiles != numberOfAPIProfiles) {
				String errorMessage = "Number of profiles doesn't match." + "\nUI Profiles: " + numberOfUIProfiles
						+ "\nAPI Profiles: " + numberOfAPIProfiles;
				throw new Exception(errorMessage);
			}

			for (String key : apiProfiles.keySet()) {
				System.out.println("==> Comparing data for [" + key + "]");
				if (!uiProfiles.containsKey(key)) {
					String errorMessage = "Name [" + key + "] is not found on UI.";
					throw new Exception(errorMessage);
				}

				String uiStatus = uiProfiles.get(key).trim();
				String apiStatus = apiProfiles.get(key).trim();
				System.out.println("UI Status:\t" + uiStatus + "\nAPI Status:\t" + apiStatus);

				if (!uiStatus.equalsIgnoreCase(apiStatus)) {
					String errorMessage = "Status for [" + key + "] doesn't match. \nUI Status: " + uiStatus
							+ "\nAPI Status: " + apiStatus;
					throw new Exception(errorMessage);
				}
			}
			System.out.println("==> Test Passed.");
		} catch (Exception e) {
			System.out.println("==> Test Failed.");
			System.out.println("==> Reason: " + e.getMessage());
		}

	}

}
