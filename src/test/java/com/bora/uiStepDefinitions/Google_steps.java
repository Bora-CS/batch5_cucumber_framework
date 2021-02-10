package com.bora.uiStepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.junit.Assert;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.bora.utilities.DriverFactory;

public class Google_steps {
	
	private WebDriver driver = DriverFactory.getInstance();

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
			Assert.assertTrue("Result stats is not displayed", stats.isDisplayed());
		} catch (NoSuchElementException e) {
			Assert.assertTrue("Result stats element is not found", false);
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
		Assert.assertTrue("Found less than 3 results that's related to " + searchString, count >= 3);
	}

}
