package com.bora.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.bora.apiDataObjects.Experience;
import com.bora.utilities.BoraAPIs;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetCurrentUserProfile {

	public static void main(String[] args) {

		String endpoint = "/api/profile/me";
		RestAssured.baseURI = "https://boratech.herokuapp.com";
		RequestSpecification request = RestAssured.given();
		request.header("x-auth-token", BoraAPIs.login("murad@test.com", "murad001"));

		Response response = request.get(endpoint);
		JsonPath jp = response.jsonPath();
		ArrayList<String> skills = jp.get("skills");
		System.out.println("Skills: " + skills);
		String id = jp.get("_id");
		System.out.println("ID: " + id);

		HashMap<String, String> userInfo = jp.get("user");
		for (String key : userInfo.keySet()) {
			System.out.println("User Info - " + key + ": " + userInfo.get(key));
		}

//		ArrayList<HashMap<String, Object>> experiences = jp.get("experience");
//		for (HashMap<String, Object> experience : experiences) {
//			System.out.println("Company Name:" + experience.get("company") + "\t Current Job:" + experience.get("current"));
//		}

		List<Experience> experiences = jp.getList("experience", Experience.class);
		for (Experience experience : experiences) {
			System.out.println("Company Name: " + experience.company + "\t Current Job: " + experience.current);
		}

	}

}
