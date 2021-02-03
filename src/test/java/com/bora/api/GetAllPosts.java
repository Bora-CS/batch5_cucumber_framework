package com.bora.api;

import java.util.List;

import com.bora.apiDataObjects.Comment;
import com.bora.apiDataObjects.Post;
import com.bora.utilities.BoraAPIs;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetAllPosts {

	public static void main(String[] args) {

		String token = BoraAPIs.login("murad@test.com", "murad001");

		String endpoint = "/api/posts";
		RestAssured.baseURI = "https://boratech.herokuapp.com";
		RequestSpecification request = RestAssured.given();
		request.header("x-auth-token", token);

		Response response = request.get(endpoint);
		List<Post> posts = response.jsonPath().getList("", Post.class);

		for (Post post : posts) {
			System.out.println("[Name]:\t" + post.name + "\n[Post]:\t" + post.text.trim() + "\n[Likes]:\t" + post.likes.length
					+ "\n[Comments]:\t" + post.comments.length);
			if (post.comments.length > 0) {
				for (Comment comment : post.comments) {
					System.out.println("\t[By]:" + comment.name);
					System.out.println("\t[Content]:" + comment.text.trim());
				}
			}
			System.out.println();
		}

	}

}
