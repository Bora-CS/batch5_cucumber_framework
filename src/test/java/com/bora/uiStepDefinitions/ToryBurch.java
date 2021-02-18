package com.bora.uiStepDefinitions;

import java.security.Key;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.bora.utilities.BoraKeyword_library;
import com.bora.utilities.DriverFactory;
import com.bora.utilities.UI_Utils;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ToryBurch {

	private BoraKeyword_library lib = new BoraKeyword_library(DriverFactory.getInstance());

	By searchButton = By.xpath("//span[@class='search-btn__text-3aJ']");
	String searchPageUrl = "https://www.toryburch.com/";

	@Given("I'm on the toryburch.com homepage")
	public void i_m_on_the_toryburch_com_homepage() {
		lib.openUrl("https://www.toryburch.com/");
	}

	@Given("I click search button")
	public void i_click_search_button() {
		lib.clickElement(searchButton);
	}

	@When("I should be on the search page")
	public void i_should_be_on_the_search_page() {
		Assert.assertTrue(lib.compareUrl(searchPageUrl));
	}

	@When("I search for {string} on the ToryBurch")
	public void i_search_for_on_the_tory_burch(String string) throws InterruptedException {
		lib.elementSendKeys(By.id("mpp-sticky-filters-portal"), "sneakers" + Keys.ENTER);
		
		UI_Utils.waitFor(1);

	}

	@Then("I will see result page sneakers")
	public void i_will_see_result_page_sneakers() {
//		WebElement searchField =driver.findElement(By.id("mpp-sticky-filters-portal"));
//		searchField.sendKeys("sneakers"+Keys.ENTER);
		lib.elementSendKeys(By.id("mpp-sticky-filters-portal"), "sneakers" + Keys.ENTER);

	}

}
