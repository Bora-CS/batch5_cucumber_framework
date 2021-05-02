package com.bora.emmahomework;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.bora.utilities.BoraKeywrod_Library;
import com.bora.utilities.DriverFactory;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class BoraPostMassageSetpDefinitions {

	private BoraKeywrod_Library lib = new BoraKeywrod_Library(DriverFactory.getInstance());

	@Given("I am on the Bora Community home page")
	public void i_am_on_the_bora_community_home_page() {
		lib.openUrl("https://boratech.herokuapp.com/");
	}

	@When("I click Login button")
	public void i_click_login_button() {
		lib.clickElement(By.linkText("Login"));
	}

	@When("I Login with email {string} and password {string}")
	public void i_login_with_email_and_password(String email, String password) {
		lib.login(email, password);
	}

	@When("I click on Post button")
	public void i_click_on_post_button() {
		lib.clickElement(By.linkText("Posts"));
	}

	@Then("I will see the PostCommuniy page")
	public void i_will_see_the_post_communiy_page() {
		String actualURL = lib.getUrl();
		String expectURL = "https://boratech.herokuapp.com/posts";
		assertEquals(expectURL, actualURL);
	}

	@Then("I will post a {string}")
	public void i_will_post_a(String post) {
		lib.elementSendKey(By.xpath("//*[@placeholder='Create a post']"), post);
		lib.clickElement(By.xpath("//input[@type='submit']"));
	}

	@Then("post should disply")
	public void post_should_disply() {
		WebElement button = lib.getElment(By.xpath("//*[@class='btn btn-danger']"));
		assertTrue(button.isDisplayed());

	}

}
