package com.bora.pratice.karen;

import java.util.HashMap;

import org.json.simple.JSONObject;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class API_Practice {

	public static void main(String[] args) {

		try {

			RestAssured.baseURI = "https://boratech.herokuapp.com";
			String endpoint = "/api/auth";
			RequestSpecification request = RestAssured.given();
			request.header("Content-Type", "application/json");
			HashMap<String, String> mapBody = new HashMap<String, String>();

			mapBody.put("email", "myemail@gmail.com");
			mapBody.put("password", "boratech123");

			JSONObject body = new JSONObject(mapBody);
			request.body(body);

			Response response = request.post(endpoint);

			int statusCode = response.getStatusCode();
			System.out.println("Rsponse Status Code :" + statusCode);

			JsonPath jasonpath = response.jsonPath();
			String tokenError = "Reson : No token value found  ";
			try {
				String token = jasonpath.get("token");

				if (token.isEmpty()) {
					throw new Exception(tokenError);
				}
				System.out.println("the token :" + token);
			} catch (NullPointerException e) {
			
				throw new Exception(tokenError);
				
			}

		} catch (Exception e) {

			System.out.println("==> Test Failed!");
			System.out.println(e.getMessage());
		
		}
	}

}
