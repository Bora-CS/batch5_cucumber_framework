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

	private BoraKeyword_library lib = new BoraKeyword_library(DriverFactory.getInstance());


	String editProfilePageURL = lib.getURLFromDataReader("boraEditProfilePageURL");
	String loginPageURL = lib.getURLFromDataReader("boraLoginPageURL");
	String homepageURL = lib.getURLFromDataReader("boraHomePageURL");
	String userEmail = lib.getUserEmail("boraUser1");
	String userPassword = lib.getUserPassword("boraUser1");
	String postMessage = lib.getPost("boraPostMessage");
	

	@Given("I'm on the Bora Community homepage")
	public void i_m_on_the_bora_community_homepage() {
		lib.openUrl(homepageURL);
	}
	@When("I click on login button")
	public void i_click_on_login_button() {
		lib.clickElement(lib.getLocator("boraLoginButton"));
	}
	@When("I log in with email {string} and password {string}")
	public void i_log_in_with_email_and_password(String userEmail, String userPassword) throws InterruptedException {
		lib.login(userEmail, userPassword);
		UI_Utils.waitFor(1);
	}
	@Then("I should still be on the login page")
	public void i_should_still_be_on_the_login_page() {
		String actualUrl = lib.getUrl();
		String expectedUrl = loginPageURL;
		Assert.assertEquals(expectedUrl, actualUrl);	
	}
	@Then("I will see an error message that says {string}")
	public void i_will_see_an_error_message_that_says(String expectedAlertText) {
		WebElement alert = lib.getElement(lib.getLocator("boraLoginPage_errorMessage"));
		Assert.assertTrue(alert.isDisplayed());
		String actualAlertText = alert.getText();
		Assert.assertEquals(expectedAlertText, actualAlertText);
	}
	@When("I click the Edit Profile link")
	public void i_click_the_edit_profile_link() {
		lib.clickElement(lib.getLocator("boraEditProfileLink"));	
	}
	@Then("The edit profile page should be displayed")
	public void the_edit_profile_page_should_be_displayed() {
		Assert.assertTrue(lib.isDisplay(lib.getLocator("boraCreateProfile")));	
	}
	@When("I click the post link")
	public void i_click_the_post_link() {
		lib.clickElement(lib.getLocator("boraPostButton"));	
	}
	@Then("The post should be displayed")
	public void the_post_should_be_displayed() {
		Assert.assertTrue(lib.isDisplay(lib.getLocator("boraPostPageDisplay")));
	}
	@Given("I logged into the bora community with email {string} and password {string}")
	public void i_logged_into_the_bora_community_and_i_am_on_the_post_page(String userEmail, String userPassword) throws InterruptedException {
		lib.openUrl(homepageURL);
		lib.clickElement(lib.getLocator("boraLoginButton"));
		lib.login(userEmail, userPassword);
		UI_Utils.waitFor(1);
	}
	@When("I click on the post button we are on the post page")
	public void i_click_on_the_create_a_post_box() {
		lib.clickElement(lib.getLocator("boraPostButton"));
	}

	@When("put in the {string} message in create a post box")
	public void put_in_the_message_in(String postMessage) {
		lib.sumbitKey(lib.getLocator("boraPostTextarea"), postMessage);
	}
	@When("I click the sumbit button")
	public void i_click_the_sumbit_button() {
		lib.clickElement(lib.getLocator("boraSumbitButton"));
	}
	@Then("The {string} alert should appear")
	public void the_alert_should_appare(String expectedAlert) {
		WebElement alert = lib.getElement(lib.getLocator("boraPost_successMessage"));
		Assert.assertTrue(alert.isDisplayed());
		String actualAlertText = alert.getText();
		Assert.assertEquals(expectedAlert, actualAlertText);
	}
	@When("I click on Edit Profile link")
	public void i_click_on_edit_profile_link() {
		lib.clickElement(lib.getLocator("boraEditProfileLink"));
	}
	@Then("The edit profile page should display")
	public void the_edit_profile_page_should_display() {
		Assert.assertTrue("Login page didn't display", lib.compareUrl(editProfilePageURL));
	}

}
