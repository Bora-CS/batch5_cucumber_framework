package com.bora.uiStepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

<<<<<<< HEAD
import com.bora.utilities.BoraKeywrod_Library;
=======
import com.bora.utilities.BoraKeyword_library;
>>>>>>> main
import com.bora.utilities.DriverFactory;
import com.bora.utilities.UI_Utils;

public class BoraCommunity_steps {

<<<<<<< HEAD

	private BoraKeywrod_Library lib = new BoraKeywrod_Library(DriverFactory.getInstance());
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
		String actualUrl = lib.getUrl();
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
		WebElement alert = lib.getElment(By.xpath("//div[@class='alert alert-danger']"));
=======
		WebElement alert = lib.getElement(loginPage_errorMessage);
>>>>>>> main
		Assert.assertTrue(alert.isDisplayed());
		String actualAlertText = alert.getText();
		Assert.assertEquals(expectedAlertText, actualAlertText);
	}
	//-------------------------------------------------------------------------------------------------------


	@When("I click on edit profile link")
	public void i_click_on_edit_profile_link() {
	   lib.clickElement(By.xpath("//a[@href='/edit-profile']"));
	}

	@Then("The edit profile page should display")
	public void the_edit_profilr_page_should_display() {
	    String actualProfliePage=lib.getUrl();
	    String expectedProfliePage="https://boratech.herokuapp.com/edit-profile";
	    Assert.assertEquals(expectedProfliePage, actualProfliePage);
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
