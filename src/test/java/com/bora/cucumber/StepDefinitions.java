package com.bora.cucumber;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.*;
import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.bora.utilities.DriverFactory;

public class StepDefinitions {

	public WebDriver driver = null;

	@Before("@ui and not @api")
	public void setUp() {
		driver = DriverFactory.getInstance();
	}

	@After("@ui and not @api")
	public void cleanUp() {
		DriverFactory.cleanUp();
	}

	@Given("I'm on the google.com homepage")
	public void i_m_on_the_google_com_homepage() {
		driver.get("https://www.google.com/");
	}

	@When("I search for {string}")
	public void i_search_for(String searchString) {
		WebElement searchField = driver.findElement(By.name("q"));
		searchField.sendKeys(searchString + Keys.ENTER);
	}

	@Then("I should be navigated to the search result page")
	public void i_should_be_navigated_to_the_search_result_page() {
		try {
			WebElement stats = driver.findElement(By.id("result-stats"));
			assertTrue(stats.isDisplayed(), "Result stats is not displayed");
		} catch (NoSuchElementException e) {
			assertTrue(false, "Result stats element is not found");
		}
	}

	@Then("I should see some results related to {string}")
	public void i_should_see_some_results_related_to(String searchString) {
		List<WebElement> resultLinks = driver.findElements(By.xpath("//div[@class='g']//h3"));
		int count = 0;
		for (WebElement resultLink : resultLinks) {
			if (resultLink.getText().toLowerCase().contains(searchString.toLowerCase())) {
				count++;
			}
		}
		assertTrue(count >= 3, "Found less than 3 results that's related to " + searchString);
	}

}
