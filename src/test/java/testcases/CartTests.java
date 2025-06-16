package testcases;

import static io.restassured.RestAssured.given;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import java.util.List;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import payloads.Payload;
import pojo.Cart;
import routes.Routes;

public class CartTests extends BaseClass{

	//Get all cart items
	@Test
	public void testGetAllCartItems() {
		given()
			
		.when()
			.get(Routes.GET_ALL_CART_ITEMS)
		.then()
			.statusCode(200)
			.body("size()", greaterThan(0))
			.log().body();	
	}
	
	//Get cart items by user id
	@Test
	public void testGetCartItemsById() {
		
		int cartId = configReader.getIntProperty("cartId");
		
		given()
			.pathParam("id", cartId)
		.when()
			.get(Routes.GET_CART_ITEMS_BY_ID)
		.then()
			.statusCode(200)
			.body("id", equalTo(cartId))
			.log().body();	
	}
	
	//Get all cart items with limit
	@Test
	public void testGetCartItemsWithLimit() {
		
		int limit = configReader.getIntProperty("limit");
		
		given()
			.pathParam("limit", limit)
		.when()
			.get(Routes.GET_CART_ITEMS_WITH_LIMIT)
		.then()
			.statusCode(200)
			.body("size()", equalTo(limit))
			.log().body();	
	}
	
	//GET_ALL_CART_ITEMS_BY_RANGE
	@Test
	public void testGetCartsByDateRange() {
		
		String startdate = configReader.getProperty("startdate");
		String enddate = configReader.getProperty("enddate");
		
		Response response =given()
			.pathParam("startdate", startdate)
			.pathParam("enddate", enddate)
		.when()
			.get(Routes.GET_ALL_CART_ITEMS_BY_RANGE)
		.then()
			.statusCode(200)
			.extract().response();	
		
		List<String> cartdates = response.jsonPath().getList("date");
		
		validateCartDatesWithinRange(cartdates, startdate, enddate);
		assertThat(validateCartDatesWithinRange(cartdates, startdate, enddate) , is(true));
	}
	
	
	//Get user cart 
	@Test
	public void testGetUserCart() {
		
		int userId = configReader.getIntProperty("userId");
		
		given()
			.pathParam("userId", userId)
		.when()
			.get(Routes.GET_CART_ITEMS_BY_USERID)
		.then()
			.statusCode(200)
			.body("userId", everyItem(equalTo(userId)))
			.log().body();	
	}
	
	//Create cart
	@Test
	public void testCreateCart() {
		
		int userId = configReader.getIntProperty("userId");
		
		Cart cartPayload = Payload.cartPayload(userId);
		
		given()
			.contentType(ContentType.JSON)
			.body(cartPayload)
		.when()
			.post(Routes.CREATE_CART)
		.then()
			.statusCode(200)
			.body("id", equalTo(11))
			.log().body();	
	}
	
	//update cart
	@Test
	public void testUpdateCart() {
		
		int cartId = configReader.getIntProperty("cartId");
		int userId = configReader.getIntProperty("userId");
		
		Cart Updatedcart = Payload.cartPayload(userId);
		
		given()
			.contentType(ContentType.JSON)
			.pathParam("id", cartId)
			.body(Updatedcart)
		.when()
			.put(Routes.UPDATE_CART)
		.then()
			.statusCode(200)
			.body("id", equalTo(cartId))
			.log().body();	
	}
	
	//Delete cart
	@Test
	public void testDeleteCart() {
		
		int cartId = configReader.getIntProperty("cartId");
		
		given()
			.pathParam("id", cartId)
		.when()
			.delete(Routes.DELETE_CART)
		.then()
			.statusCode(200);
	}
}
