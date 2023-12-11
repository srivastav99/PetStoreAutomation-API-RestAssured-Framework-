package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.endpoints.UserEndPoints2;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests2 {
	
	Faker faker;
	User userPayload;
	public Logger logger;
	
	@BeforeClass
	public void setup() {
		
		faker = new Faker();
		userPayload = new User();
		
		userPayload.setId(faker.idNumber().hashCode());//.idNumber() might generate same number so we are giving .hashcode() so that the id generated will be unique
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5,10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		
		//logs
		logger = LogManager.getLogger(this.getClass());
			
	}
	
	@Test(priority = 1)
	public void testPostUser() {
		
		logger.info("**************Creating user*****************");
		Response response = UserEndPoints2.createUser(userPayload);
		response.then().log().all();
		
		//response.then().log().body().statusCode(200); or
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("**************User is created*****************");
		
	}
	
	@Test(priority = 2)
	public void testGetUserByName() {
		
		logger.info("**************Reading user info*****************");
		Response response =  UserEndPoints2.readUser(this.userPayload.getUsername());
		response.then().log().all();
		
		//response.then().log().body().statusCode(200); or
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("**************User info is displayed****************");
		
	}
	
	@Test(priority = 3)
	public void testUpdateUserByName() {
		
		logger.info("**************Updating user info*****************");
		//update data using payload
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		//Here we are updating based on username so we should not update username itself
		
		Response response = UserEndPoints2.updateUser(this.userPayload.getUsername(),userPayload);
		response.then().log().all();
		
		//response.then().log().body().statusCode(200); or
		Assert.assertEquals(response.getStatusCode(), 200);
		
		//Checking data after update
		Response responseAfterUpdate =  UserEndPoints2.readUser(this.userPayload.getUsername());
		
		//response.then().log().body().statusCode(200); or
		Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);
		
		logger.info("**************User info updated*****************");
	}
	
	
	@Test(priority = 4)
	public void testDeleteUserByName() {
		
		logger.info("**************Deleting user*****************");
		Response response =  UserEndPoints2.deleteUser(this.userPayload.getUsername());
		//response.then().log().body().statusCode(200); or
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("**************User deleted*****************");
		
	}

}
