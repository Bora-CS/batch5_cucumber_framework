package com.bora.uiStepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.junit.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.bora.utilities.BoraKeyword_library;
import com.bora.utilities.DriverFactory;
import com.bora.utilities.UI_Utils;

public class BoraCommunity_steps {
<<<<<<< HEAD
	private WebDriver driver = DriverFactory.getInstance();
	private BoraKeyword_library lib = new BoraKeyword_library(driver);
=======

	private BoraKeyword_library lib = new BoraKeyword_library(DriverFactory.getInstance());

	
	
	
	By editProfileLink = By.xpath("//*[@href=\"/edit-profile\"]");
	By loginButton = By.linkText("Login");
	By loginPage_errorMessage = By.xpath("//div[@class='alert alert-danger']");
>>>>>>> main

	String editProfilePageURL = lib.getUrlFromDataReader("editProfilePageURL");
	String loginPageURL = lib.getUrlFromDataReader("loginPageURL");

	
	
	
	
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
		lib.login(userEmail,userPassword);
		UI_Utils.waitFor(1);
	}
=======
>>>>>>> main

		lib.login(userEmail, userPassword);

		UI_Utils.waitFor(1);
	}

	@Then("I will see an error message that says {string}")
	public void i_will_see_an_error_message_that_says(String expectedAlertText) {
		WebElement alert = lib.getElement(loginPage_errorMessage);
		Assert.assertTrue(alert.isDisplayed());
		String actualAlertText = alert.getText();
		Assert.assertEquals(expectedAlertText, actualAlertText);
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
