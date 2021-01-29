package com.bora.api;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Login {

	public static void main(String[] args) {

		try {
			RestAssured.baseURI = "https://boratech.herokuapp.com";
			String endpoint = "/api/auth";
			RequestSpecification request = RestAssured.given();
			request.header("Content-Type", "application/json");

			Map<String, String> mapBody = new HashMap<String, String>();
			mapBody.put("email", "murad@test.com");
			mapBody.put("password", "murad001");
			JSONObject body = new JSONObject(mapBody);

			request.body(body);
			Response response = request.post(endpoint);

			int expectedStatusCode = 200;
			int statusCode = response.getStatusCode();
			if (statusCode != expectedStatusCode) {
				String errorMessage = "Reason: status code doesn't match!" + "\nExpected Status Code:\t"
						+ expectedStatusCode + "\nActual Status Code:\t" + statusCode;
				throw new Exception(errorMessage);
			}

			JsonPath jsonPath = response.jsonPath();
			String noTokenError = "Reason: no token value found!";
			try {
				String token = jsonPath.get("token");
				if (token.isEmpty()) {
					throw new Exception(noTokenError);
				}
			} catch (NullPointerException e) {
				throw new Exception(noTokenError);
			}
			
			System.out.println("==> Test Passed!");
		} catch (Exception e) {
			System.out.println("==> Test Failed!");
			System.out.println(e.getMessage());
		}

	}

}
