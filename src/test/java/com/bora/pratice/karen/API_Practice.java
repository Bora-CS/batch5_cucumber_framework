package com.bora.pratice.karen;

import java.util.HashMap;

import org.json.simple.JSONObject;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class API_Practice {

	public static void main(String[] args) {
		
		RestAssured.baseURI="https://boratech.herokuapp.com";
		String endpoint="/api/auth";
		RequestSpecification request = RestAssured.given();
		request.header("Content-Type", "application/json");
		HashMap<String, String> mapBody= new HashMap<String , String>();
		
		mapBody.put("email", "271866163kk@gmail.com");
		mapBody.put("password", "Iliveindc0412");

		JSONObject body= new JSONObject(mapBody);
		request.body(body);
		
		Response response = request.post(endpoint);
		
		int statusCode = response.getStatusCode();
		System.out.println("Rsponse Status Code :"+ statusCode);
		System.out.println("Respose  Body :"+ response.getBody().asString());
		
		
		
	}

}
