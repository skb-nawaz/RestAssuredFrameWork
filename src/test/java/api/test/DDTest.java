package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DDTest {
	
	public Logger logger;
	
	
	
	@Test(priority=1,dataProvider="Data",dataProviderClass=DataProviders.class)
	public void test_createUser(String userID,String userName,String fname,String lname,String useremail,String pwd,String ph) {
		
		logger =LogManager.getLogger(this.getClass());
		
		logger.info("*****Creating user********");
		
		User userPayload =new User();
		
		userPayload.setId(Integer.parseInt(userID));
		userPayload.setUsername(userName);
		userPayload.setFirstName(fname);
		userPayload.setLastName(lname);
		userPayload.setEmail(useremail);
		userPayload.setPassword(ph);
		userPayload.setPhone(ph);
		
		Response response=UserEndPoints.createUser(userPayload);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(),200);
		
		logger.info("*****User is created********");
	}
	
	@Test(priority=2,dataProvider = "UserNames",dataProviderClass = DataProviders.class)
	public void test_DeleteUserByName(String userName) {
		logger.info("*****Deleting user info********");
		
		Response res=UserEndPoints.deleteUser(userName);
		res.then().log().all();
		
		Assert.assertEquals(res.getStatusCode(), 200);
		logger.info("*****User info is deleted********");
		
	}

}
