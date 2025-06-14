package testcases;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import payloads.Payload;
import pojo.Users;
import routes.Routes;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

public class UsersTests extends BaseClass {

	// Get all users
	@Test(priority = 1)
	public void testGetAllUsers() {
		given()

		.when()
			.get(Routes.GET_ALL_USERS)
		.then()
			.statusCode(200)
			.body("size()", greaterThan(0))
			;
	}
	
	//Get user by id
	@Test(priority = 2)
	public void testGetUserById() {
		
		int userId = configReader.getIntProperty("userId");
		given()
			.pathParam("id", userId)
		.when()
			.get(Routes.GET_USER_BY_ID)
		.then()
			.statusCode(200)
			.body("id", equalTo(1))
			;
	}
	
	//Get users with limit
	@Test(priority = 3)
	public void testGetUsersWithLimit() {
			
		int limit = configReader.getIntProperty("limit");
		given()
			.pathParam("limit", limit)
		.when()
			.get(Routes.GET_ALL_USERS_WITH_LIMIT)
		.then()
			.statusCode(200)
			.body("size()", equalTo(5))
			;
		}
	
	//verify all the users are sorted in descending order
	@Test(priority = 4)
	public void testGetUsersWithDescOrder() {
			
		Response response = given()
			.pathParam("order", "desc")
		.when()
			.get(Routes.GET_ALL_USERS_SORTED)
		.then()
			.statusCode(200)
			.log().body()
			.extract().response();
		
		List<Integer> userids = response.jsonPath().getList("id" , Integer.class);
		assertThat(isOrderDescending(userids), is(true));
		}
	
	//verify all the users are sorted in ascending order
	@Test(priority = 5)
	public void testGetUsersWithAsccOrder() {
			
		Response response = given()
			.pathParam("order", "asc")
		.when()
			.get(Routes.GET_ALL_USERS_SORTED)
		.then()
			.statusCode(200)
			.extract().response();
		
		List<Integer> userids = response.jsonPath().getList("id" , Integer.class);
		assertThat(isOrderAscending(userids), is(true));
		}
	
	//Create users
	@Test(priority = 6)
	public void testCreateUser() {
		
		Users newUserdata = Payload.usersPayload();
		
		given()
			.contentType(ContentType.JSON)
			.body(newUserdata)
		.when()
			.post(Routes.CREATE_USERS)
		.then()
			.statusCode(200)
			.log().body()
			;
	}
	
	//update users
	@Test(priority = 7)
	public void testUpdateUser() {
		
		int userId = configReader.getIntProperty("userId");
		Users updatedUserdata = Payload.usersPayload();
		
		given()
			.contentType(ContentType.JSON)
			.pathParam("id", userId)
			.body(updatedUserdata)
		.when()
			.put(Routes.UPDATE_USER)
		.then()
			.statusCode(200)
			.log().body()
			.body("username", equalTo(updatedUserdata.getUsername()));
	}
	
	//Delete user
	@Test(priority = 8)
	public void testDeleteUser() {

		int userId = configReader.getIntProperty("userId");
		
		given()
			.pathParam("id", userId)
		.when()
			.delete(Routes.DELETE_USER)
		.then()
			.statusCode(200)
			;
		
		}

}
