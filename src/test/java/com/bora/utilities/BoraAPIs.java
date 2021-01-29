package com.bora.utilities;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BoraAPIs {

	public static String login(String email, String password) {
		try {
			RestAssured.baseURI = "https://boratech.herokuapp.com";
			String endpoint = "/api/auth";
			RequestSpecification request = RestAssured.given();
			request.header("Content-Type", "application/json");

			Map<String, String> mapBody = new HashMap<String, String>();
			mapBody.put("email", email);
			mapBody.put("password", password);
			JSONObject body = new JSONObject(mapBody);

			request.body(body);
			Response response = request.post(endpoint);

			JsonPath jsonPath = response.jsonPath();
			String token = jsonPath.get("token");
			return token;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

}
