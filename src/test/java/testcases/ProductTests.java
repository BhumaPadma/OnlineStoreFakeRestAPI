package testcases;

import org.testng.annotations.Test;


import io.restassured.http.ContentType;
import io.restassured.response.Response;
import payloads.Payload;
import pojo.Product;
import routes.Routes;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;


public class ProductTests extends BaseClass {
	
	
	//Get all Products
	@Test
	public void testGetAllProducts() {
		given()
		
		.when()
			.get(Routes.GET_ALL_PRODUCTS)
		.then()
			.statusCode(200)
			.body("size()", greaterThan(0))
			.log().body();	
	}
	
	//Retrive a single Product by id
	@Test
	public void testGetSingleProductById() {

		int productId = configReader.getIntProperty("productId");
		given()
			.pathParam("id", productId )
		.when()
			.get(Routes.GET_PRODUCT_BY_ID)
		.then()
			.statusCode(200)
			.body("id", equalTo(1))
			.log().body();
			
		}
	
	//Get all Products with limit
	@Test
	public void testGetAllProductswithLimit() {
		int limit = configReader.getIntProperty("limit");
		given()
			.pathParam("limit", limit )
		.when()
			.get(Routes.GET_PRODUCTS_WITH_LIMIT)
		.then()
			.statusCode(200)
			.body("size()", equalTo(limit))
			.log().body();
			
		}
	
	//verify all the product are sorted in descending order
	@Test
	public void testGetAllProductsDesc() {
		
		Response response =given()
			.pathParam("order", "desc" )
		.when()
			.get(Routes.GET_PRODUCTS_SORTED)
		.then()
			.statusCode(200)
			.extract().response();
		
		List<Integer> productids = response.jsonPath().getList("id",Integer.class);
		assertThat(isOrderDescending(productids), is(true));
		}
	
	//verify all the product are sorted in descending order
	@Test
	public void testGetAllProductsAsc() {
			
		Response response =given()
			.pathParam("order", "asc" )
		.when()
			.get(Routes.GET_PRODUCTS_SORTED)
		.then()
			.statusCode(200)
			.extract().response();
			
		List<Integer> productids = response.jsonPath().getList("id",Integer.class);
		assertThat(isOrderAscending(productids), is(true));
		}
	
	//Retrive all categories
	@Test
	public void testGetAllCategoriesProducts() {
		given()
			
		.when()
			.get(Routes.GET_ALL_PRODUCTS_CATAGERORIES)
		.then()
			.statusCode(200)
			.body("size()", greaterThan(0))
			.log().body();	
		}
	
	//Retrive all product by category
	@Test
	public void testGetProductByCategory() {
		given()
			.pathParam("category", "electronics")
		.when()
			.get(Routes.GET_PRODUCT_BY_CATEGORY)
		.then()
			.statusCode(200)
			.body("size()", greaterThan(0))
			.body("category", everyItem(notNullValue()))
			.body("category", everyItem(equalTo("electronics")))
			.log().body();	
		}
	
	//Create product
	@Test
	public void testCreateProduct() {
		
		Product newProduct = Payload.productPayload();
		
		Response response = given()
			.contentType(ContentType.JSON)
			.body(newProduct)
		.when()
			.post(Routes.CREATE_PRODUCT)
		.then()
			.statusCode(200)
			.body("id", notNullValue())
			.body("title", equalTo(newProduct.getTitle()))
			.log().body()
			.extract().response();	
		
		int NewProductid = response.jsonPath().getInt("id");
		
		}
	
	//update product
	@Test
	public void testUpadteProduct() {
		
		int productId = configReader.getIntProperty("productId");
		Product updatedPayload = Payload.productPayload();

		given()
			.contentType(ContentType.JSON)
			.pathParam("id", productId)
			.body(updatedPayload)
		.when()
			.put(Routes.UPDATE_PRODUCT)
		.then()
			.statusCode(200)
			.body("title", equalTo(updatedPayload.getTitle()))
			.log().body();
		
		}
	
	
	//Delete product
	@Test
	public void testDeleteProduct() {

		int productId = configReader.getIntProperty("productId");
		
		given()
			.pathParam("id", productId)
		.when()
			.delete(Routes.DELETE_PRODUCT)
		.then()
			.statusCode(200);
		
		}

}
