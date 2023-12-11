package api.test;


import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

//Data Driven test
public class DDTests {
	
	@Test(priority = 1, dataProvider = "Data", dataProviderClass = DataProviders.class)//Here since dataprovider class is provided in another package we have to mention dataProviderClass also, if they are in same package then not required.
	public void testPostUser(String userID, String userName, String fname , String lname, String useremail, String pwd, String ph ){//Here the order should be exactly as in excel sheet
		
		User userPayload = new User();
		
		userPayload.setId(Integer.parseInt(userID));
		userPayload.setUsername(userName);
		userPayload.setFirstName(fname);
		userPayload.setLastName(lname);
		userPayload.setEmail(useremail);
		userPayload.setPassword(pwd);
		userPayload.setPhone(ph);
		
		
		Response response = UserEndPoints.createUser(userPayload);
		//response.then().log().all();
		
		//response.then().log().body().statusCode(200); or
		Assert.assertEquals(response.getStatusCode(), 200);
		
	}
	
	
	
	@Test(priority = 2, dataProvider = "UserNames", dataProviderClass = DataProviders.class)//Here since dataprovider class is provided in another package we have to mention dataProviderClass also, if they are in same package then not required.
	public void testDeleteUserByName(String userName) {
		
		Response response = UserEndPoints.deleteUser(userName);
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
	}

}
