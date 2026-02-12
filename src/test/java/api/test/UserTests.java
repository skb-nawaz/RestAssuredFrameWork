package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;


import api.endpoints.UserEndPoints;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests {
	
	Faker faker;
	User userPayload;
	
	public Logger logger;
	@BeforeClass
	public void setup() {
		faker=new Faker();
		userPayload=new User();
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5,10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		
		//logs
		
		logger =LogManager.getLogger(this.getClass());
	
	}
	
	@Test(priority = 1)
	public void test_Post_CreateUser(){
		logger.info("*****Creating user********");
		Response res=UserEndPoints.createUser(userPayload);
		res.then().log().all();
		Assert.assertEquals(res.getStatusCode(), 200);
		logger.info("*****User is created********");
	}
	
	@Test(priority=2)
	public void test_getUser() {
		logger.info("*****Reading user info********");
		Response res=UserEndPoints.getUser(this.userPayload.getUsername());
	
		res.then().log().all();
		String username =res.jsonPath().getString("username");
		System.out.println(username + " " +userPayload.getUsername());
		Assert.assertEquals(username,userPayload.getUsername());
		logger.info("*****User info is displayed********");
	}
	
	
	@Test(priority = 3)
	public void test_updatedUser() {
		logger.info("*****Updating user info********");
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		
		Response res=UserEndPoints.updateUser(userPayload ,this.userPayload.getUsername());
		res.then().log().all();
		Assert.assertEquals(res.getStatusCode(), 200);
		
		Response Updatedres=UserEndPoints.getUser(this.userPayload.getUsername());
		Assert.assertEquals(Updatedres.getStatusCode(), 200);
		logger.info("*****User info is updated********");
	}
	
	@Test(priority=4)
	public void test_deleteUserByName() {
		logger.info("*****Deleting user info********");
		Response res =UserEndPoints.deleteUser(this.userPayload.getUsername());
		res.then().log().all();
		Response Updatedres=UserEndPoints.getUser(this.userPayload.getUsername());
		Assert.assertEquals(Updatedres.getStatusCode(), 404);
		logger.info("*****User info is deleted********");
	}

}
