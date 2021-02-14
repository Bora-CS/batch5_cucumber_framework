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

	private BoraKeyword_library lib = new BoraKeyword_library(DriverFactory.getInstance());// (driver)==need to create
																							// constructor
	By loginButton = By.linkText("Login");
	By loginPage_erroMessage = By.xpath("//div[@class='alert alert-danger']");
	By editProfileLink = By.xpath("//*[@href='/edit-profile']");
	By addEducationLink = By.xpath("//*[@href='/add-education\']");
	String editProfilePageUrl = "https://boratech.herokuapp.com/edit-profile";
	String addEucationPageUrl = "https://boratech.herokuapp.com/add-education";

	@Given("I'm on the Bora Community homepage")
	public void i_m_on_the_bora_community_homepage() {

		lib.openUrl("https://boratech.herokuapp.com/");
	}

	@When("I click on login button")
	public void i_click_on_login_button() {
		lib.clickElement(loginButton);
		// lib.getElement(By.linkText("Login")).click();
		// diver.findElement(By.linkText("Login")).click();
	}

	@When("I log in with email {string} and password {string}")
	public void i_log_in_with_email_and_password(String userEmail, String userPassword) throws InterruptedException {

		lib.login(userEmail, userPassword);
		UI_Utils.waitFor(1);
	}

	@Then("I should still be on the login page")
	public void i_should_still_be_on_the_login_page() {
		String actualUrl = lib.getUrl();
		String expectedUrl = "https://boratech.herokuapp.com/login";
		Assert.assertEquals(expectedUrl, actualUrl);
	}

	@Then("I will see an error message that says {string}")
	public void i_will_see_an_error_message_that_says(String expectedAlertText) {
		WebElement alert = lib.getElement(loginPage_erroMessage);
		Assert.assertTrue(alert.isDisplayed());
		String actualAlertText = alert.getText();
		Assert.assertEquals(expectedAlertText, actualAlertText);
	}

	@When("I click on Edit Profile link")
	public void i_click_on_edit_profile_link() {
		lib.clickElement(editProfileLink);
	}

	@Then("The edit profile page should display")
	public void the_edit_profile_page_shold_display() {
		Assert.assertTrue(lib.compareUrl(editProfilePageUrl));
	}

	@When("I click on add education link")
	public void i_click_on_add_education_link() {
		lib.clickElement(addEducationLink);
	}

	@Then("The add education page should display")
	public void the_add_education_page_shold_display() {
		Assert.assertTrue(lib.compareUrl(addEucationPageUrl));
	}

}
