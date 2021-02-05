package com.bora.api;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.bora.apiDataObjects.Post;
import com.bora.utilities.BoraAPIs;
import com.bora.utilities.DriverFactory;
import com.bora.utilities.UI_Utils;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class PostIntegration {

	public static void main(String[] args) {

		String email = "murad@test.com";
		String password = "murad001";
		String postMessage = "Yala habibi, get some work done!";
		postMessage += UI_Utils.getTimeStamp();

		WebDriver driver = DriverFactory.getInstance();
		try {
			driver.get("https://boratech.herokuapp.com/");
			driver.findElement(By.linkText("Login")).click();
			driver.findElement(By.name("email")).sendKeys(email);
			driver.findElement(By.name("password")).sendKeys(password);
			driver.findElement(By.xpath("//input[@type='submit']")).click();
			driver.findElement(By.linkText("Posts")).click();
			driver.findElement(By.tagName("textarea")).sendKeys(postMessage);
			driver.findElement(By.xpath("//input[@type='submit']")).click();
			UI_Utils.waitFor(3);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DriverFactory.cleanUp();
		}

		// Authentication
		String token = BoraAPIs.login(email, password);

		// Get current user profile
		String getCurrentUserProfileEndpoint = "/api/profile/me";
		RestAssured.baseURI = "https://boratech.herokuapp.com";
		RequestSpecification request = RestAssured.given();
		request.header("x-auth-token", token);
		String userName = request.get(getCurrentUserProfileEndpoint).jsonPath().get("user.name");

		// Get all posts
		String getAllPostsEndpoint = "/api/posts";
		List<Post> posts = request.get(getAllPostsEndpoint).jsonPath().getList("", Post.class);

		// Validation
		boolean found = false;
		for (Post post : posts) {
			if (post.name.equals(userName) && post.text.equals(postMessage)) {
				found = true;
				break;
			}
		}

		if (found) {
			System.out.println("Test Passed! Yay!");
		} else {
			System.out.println("Test Failed.");
		}

	}

}
