package com.bora.pratice.karen;

import java.util.ArrayList;
import java.util.HashMap;

import com.bora.utilities.BoraAPIs;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BoraAPITestProfileInfo {

	public static void main(String[] args) {
		
		RestAssured.baseURI="https://boratech.herokuapp.com";
		String endpoint ="/api/profile/me";
		RequestSpecification request = RestAssured.given();
		request.header("x-auth-token", BoraAPIs.login("myemail@gmail.com", "boratech123"));
		
		Response response = request.get(endpoint);
		
		JsonPath jasonpath = response.jsonPath();
		ArrayList<HashMap<String,String>> experiences = jasonpath.get("experience");
		for (HashMap<String,String> experience : experiences) {
			System.out.println(experience);
		}
	}

}
