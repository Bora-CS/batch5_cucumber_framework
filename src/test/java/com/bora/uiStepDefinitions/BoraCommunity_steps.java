package com.bora.uiStepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.junit.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.bora.utilities.BoraKeyword_library;
import com.bora.utilities.DriverFactory;
import com.bora.utilities.UI_Utils;

public class BoraCommunity_steps {
<<<<<<< HEAD
	
	private BoraKeyword_library lib = new BoraKeyword_library(DriverFactory.getInstance());
	
=======

	private BoraKeyword_library lib = new BoraKeyword_library(DriverFactory.getInstance());

	By editProfileLink = By.xpath("//*[@href=\"/edit-profile\"]");
	By loginButton = By.linkText("Login");
	By loginPage_errorMessage = By.xpath("//div[@class='alert alert-danger']");

	String editProfilePageURL = "https://boratech.herokuapp.com/edit-profile";
	String loginPageURL = "https://boratech.herokuapp.com/login";

>>>>>>> main
	@Given("I'm on the Bora Community homepage")
	public void i_m_on_the_bora_community_homepage() {
		lib.openUrl("https://boratech.herokuapp.com/");
	}

	@When("I click on login button")
	public void i_click_on_login_button() {
<<<<<<< HEAD
		lib.clickElement(By.linkText("Login"));
		
=======
		lib.clickElement(loginButton);
>>>>>>> main
	}

	@When("I log in with email {string} and password {string}")
	public void i_log_in_with_email_and_password(String userEmail, String userPassword) throws InterruptedException {
<<<<<<< HEAD
		lib.login(userEmail, userPassword);
		UI_Utils.waitFor(1);
	}

	@Then("I should still be on the login page")
	public void i_should_still_be_on_the_login_page() {
		
		String actualUrl =lib.getUrl();
		String expectedUrl = "https://boratech.herokuapp.com/login";
		Assert.assertEquals(expectedUrl, actualUrl);
=======

		lib.login(userEmail, userPassword);

		UI_Utils.waitFor(1);
>>>>>>> main
	}

	@Then("I will see an error message that says {string}")
	public void i_will_see_an_error_message_that_says(String expectedAlertText) {
<<<<<<< HEAD
		WebElement alert = lib.getElement(By.xpath("//div[@class='alert alert-danger']"));
=======
		WebElement alert = lib.getElement(loginPage_errorMessage);
>>>>>>> main
		Assert.assertTrue(alert.isDisplayed());
		String actualAlertText = alert.getText();
		Assert.assertEquals(expectedAlertText, actualAlertText);
	}
	@When("I click the Edit Profile link")
	public void i_click_the_edit_profile_link() {
	  lib.clickElement(By.xpath("//a[text()=' Edit Profile']"));
	}

	@Then("The edit profile page should be displayed")
	public void the_edit_profile_page_should_be_displayed() {
	 Assert.assertTrue(lib.isDisplay(By.xpath("//h1[text()='Create Your Profile']")));
	}
	@When("I click the post link")
	public void i_click_the_post_link() {
	    lib.clickElement(By.xpath("//a[text()='Posts']"));
	}

	@Then("The post should be displayed")
	public void the_post_should_be_displayed() {
		 Assert.assertTrue(lib.isDisplay(By.xpath("//h1[text()='Posts']")));
		
	}
	@Given("I logged into the bora community")
	public void i_logged_into_the_bora_community_and_i_am_on_the_post_page() throws InterruptedException {
		lib.openUrl("https://boratech.herokuapp.com/");
		lib.clickElement(By.linkText("Login"));
		lib.login("lilywang0427@gmail.com", "Xiaohulideai123l");
		UI_Utils.waitFor(1);
	}

	@When("I click on the post button we are on the post page")
	public void i_click_on_the_create_a_post_box() {
		lib.clickElement(By.xpath("//a[text()='Posts']"));
	}

	@When("put in the {string} message in create a post box")
	public void put_in_the_message_in(String postMessage) {
		lib.sumbitKey(By.xpath("//textarea[@name ='text']"),postMessage);
	}

	@When("I click the sumbit button")
	public void i_click_the_sumbit_button() {
		lib.clickElement(By.xpath("//input[@type ='submit']"));
	}

	@Then("The {string} alert should appear")
	public void the_alert_should_appare(String expectedAlert) {
		WebElement alert = lib.getElement(By.xpath("//div[@class='alert alert-success']"));
		Assert.assertTrue(alert.isDisplayed());
		String actualAlertText = alert.getText();
		Assert.assertEquals(expectedAlert, actualAlertText);
	}


	@When("I click on Edit Profile link")
	public void i_click_on_edit_profile_link() {
		lib.clickElement(editProfileLink);
	}

	@Then("The edit profile page should display")
	public void the_edit_profile_page_should_display() {

		Assert.assertTrue("Login page didn't display", lib.compareUrl(editProfilePageURL));

	}

	@Then("I should still be on the login page")
	public void i_should_still_be_on_the_login_page() {
		Assert.assertTrue(lib.compareUrl(loginPageURL));
	}

}
