package com.bora.apiStepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static org.junit.Assert.*;

import java.util.Map;

import com.bora.utilities.BoraAPIs;

public class APIStepDefinitions {

	public String token;
	public RequestSpecification request;
	public Response response;

	@Before("@api and not @ui")
	public void setUp() {
		RestAssured.baseURI = "https://boratech.herokuapp.com";
		request = RestAssured.given();
	}

	@Given("I'm logged in with email {string} and password {string}")
	public void i_m_logged_in_with_email_and_password(String email, String password) {
		token = BoraAPIs.login(email, password);
	}

	@When("I send a requrst to add experience")
	public void i_send_a_requrst_to_add_experience(DataTable dataTable) {
		String endpoint = "/api/profile/experience";
		Map<String, String> body = dataTable.asMaps().get(0);
		request.header("x-auth-token", token);
		request.header("Content-type", "application/json");
		request.body(body);
		response = request.put(endpoint);
	}

	@Then("I should get updated profile with the new experience added")
	public void verifyAddExperience() {
		assertEquals(200, response.getStatusCode());
		// Loop through the expeirences in the returned profile, and look for the newly
		// added experience
	}

}
