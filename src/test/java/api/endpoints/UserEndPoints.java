package api.endpoints;
import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

//userEndPoints.java
//create to perform create,read,update,Delete request the user API
public class UserEndPoints {
	
	static ResourceBundle getUrl(){
		ResourceBundle routes=ResourceBundle.getBundle("routes");
		return routes;
	}
	
	
	public static Response createUser(User payload){
		
		String post_url=getUrl().getString("post_url");
		
		Response res=given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payload)
		.when()
//		.post(Routes.post_Create_user);
		.post(post_url);
		
		return res;
	}
	
	public static Response getUser(String username){
		
		String get_url=getUrl().getString("get_url");
		
		Response res=given()
			.pathParam("username", username)	
			.accept(ContentType.JSON)		
		.when()
			//.get(Routes.get_user);	
			.get(get_url);
		return res;	
	}
	
	public static Response updateUser(User payload,String username){
		
		String update_url=getUrl().getString("update_url");
			
			Response res=given()
				.pathParam("username", username)	
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)
			.when()
				//.put(Routes.update_user);
				.put(update_url);
			
			return res;
		}
	
	public static Response deleteUser(String username){
		
		String delete_url=getUrl().getString("delete_url");
		
		Response res=given()
			.pathParam("username", username)	
			.accept(ContentType.JSON)		
		.when()
			//.delete(Routes.delete_user);	
			.delete(delete_url);
		return res;	
	}

}
