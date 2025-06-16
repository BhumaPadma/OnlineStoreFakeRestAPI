package testcases;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import payloads.Payload;
import pojo.Login;
import routes.Routes;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;


public class LoginTest extends BaseClass {
	
	
	//Test with invalid credentials
	@Test
	public void testInvalidLogin() {
		
		Login loginPayload = Payload.loginPayload();
		
		given()
			.contentType(ContentType.JSON)
			.body(loginPayload)
		.when()
			.post(Routes.LOGIN)
		.then()
			.statusCode(401)
			.log().body()
			.body(equalTo("username or password is incorrect"))
		;
		
		
	}
	
	//Test with valid credentials
	@Test
	public void testvalidLogin() {
		
		String username = configReader.getProperty("username");
		String password = configReader.getProperty("password");
		
		Login logindata = new Login(username, password);
		
		given()
			.contentType(ContentType.JSON)
			.body(logindata)
		.when()
			.post(Routes.LOGIN)
		.then()
			.statusCode(200)
			.log().body()
			.body("token",notNullValue());
		
	}

}
