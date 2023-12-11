package api.endpoints;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.ResourceBundle;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

//UserEndPoints.java file --- This class is created to perform(CRUD)Create, Read , Update, Delete requests on the user API.

public class UserEndPoints2 {
	
	static ResourceBundle getURL(){//This method is created for getting URL's from properties file(routes.properties file)
		 ResourceBundle routes = ResourceBundle.getBundle("routes");//Here we are loading the routes.properties file and storing it into routes variable
		 return routes;
	}
	
	
	public static Response createUser(User payload) {//payload is basically data in request body
		
		String post_url = getURL().getString("post_url");
		Response response = given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payload)
		
		.when()
			.post(post_url);
		
		return response;
	}
	
	
	public static Response readUser(String userName) {
		
		String get_url = getURL().getString("get_url");
		Response response = given()
			.pathParam("username", userName)
			
		.when()
			.get(get_url);
		
		return response;
	}
	
	
	public static Response updateUser(String userName, User payload) {
		
		String update_url = getURL().getString("update_url");
		Response response = given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.pathParam("username", userName)
			.body(payload)
			
		
		.when()
			.put(update_url);
		
		return response;
	}
	
	public static Response deleteUser(String userName) {
		
		String delete_url = getURL().getString("delete_url");
		Response response = given()
			.pathParam("username", userName)

		.when()
			.delete(delete_url);
		
		return response;
	}

}
