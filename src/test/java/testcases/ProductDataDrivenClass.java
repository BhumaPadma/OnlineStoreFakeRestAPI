package testcases;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import pojo.Product;
import routes.Routes;

public class ProductDataDrivenClass extends BaseClass{
	
	@Test(dataProvider = "jsonDataProvider" , dataProviderClass = utils.DataProviders.class)
	public void testAddNewProduct(Map<String,String> data) {
		
		String title = data.get("title");
		String description = data.get("description");
		double price = Double.parseDouble(data.get("price"));
		String image = data.get("image");
		String category = data.get("category");
		
		Product newProduct = new Product(title, description, price, image, category);
		
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
		
		int Productid = response.jsonPath().getInt("id");
		System.out.println("New Product Id======>  "+ Productid) ;
		
		given()
			.pathParam("id", Productid)
		.when()
			.delete(Routes.DELETE_PRODUCT)
		.then()
			.statusCode(200);
		
		System.out.println("Deleted Product ID======>  "+ Productid) ;
		
	}

}
