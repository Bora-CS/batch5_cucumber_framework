package com.bora.cucumber;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.*;

import org.openqa.selenium.WebDriver;

import com.bora.utilities.DriverFactory;

public class StepDefinitions {

	@Given("I'm on the google.com homepage")
	public void i_m_on_the_google_com_homepage() {
		WebDriver driver = DriverFactory.getInstance();
	}

	@When("I search for vaccine")
	public void i_search_for_vaccine() {
		System.out.println("Running step No.2");
	}

	@Then("I should be navigated to the search result page")
	public void i_should_be_navigated_to_the_search_result_page() {
		System.out.println("Running step No.3");
	}

	@Then("I should see some results related to vaccine")
	public void i_should_see_some_results_related_to_vaccine() {
		System.out.println("Running step No.4");
	}

}
