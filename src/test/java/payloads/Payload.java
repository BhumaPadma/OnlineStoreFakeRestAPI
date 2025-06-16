package payloads;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import com.github.javafaker.Faker;

import pojo.Address;
import pojo.Cart;
import pojo.CartProduct;
import pojo.Geolocation;
import pojo.Login;
import pojo.Name;
import pojo.Product;
import pojo.Users;

public class Payload {

	private static final Faker faker = new Faker();
	private static final String catagories[] = { "Electronics", "Furniture", "Beauty", "Books", "Groceries" };

	private static final Random random = new Random();

	// product
	public static Product productPayload() {
		String title = faker.commerce().productName();
		String description = faker.lorem().sentence(10);
		double price = Double.parseDouble(faker.commerce().price());
		String image = "https:\\image.001";
		String category = catagories[random.nextInt(catagories.length)];

		return new Product(title, description, price, image, category);
	}

	// users
	public static Users usersPayload() {

		String email = faker.internet().safeEmailAddress();
		String username = faker.name().username();
		String password = faker.internet().password(6, 8);

		String firstname = faker.name().firstName();
		String lastname = faker.name().lastName();
		Name name = new Name(firstname, lastname);

		String city = faker.address().city();
		String Street = faker.address().streetName();
		Number number = faker.number().randomNumber();
		String ZipCode = faker.address().zipCode();

		String latitude = faker.address().latitude();
		String longitude = faker.address().longitude();
		Geolocation geolocation = new Geolocation(latitude, longitude);

		Address address = new Address(city, Street, number, ZipCode, geolocation);

		String phone = faker.phoneNumber().cellPhone();

		return new Users(email, username, password, name, address, phone);

	}

	// cart
	/**
	 * { id:Number, userId:Number, date:Date,
	 * products:[{productId:Number,quantity:Number}] }
	 */

	public static Cart cartPayload(int userId) {

		List<CartProduct> products = new ArrayList<>();

		int productId = random.nextInt(100);
		int quantity = random.nextInt(10) + 1;
		products.add(new CartProduct(productId, quantity));

		SimpleDateFormat newdate = new SimpleDateFormat("yyyy-mm-dd" , Locale.ENGLISH);
		String date = newdate.format(new Date());
		
		return new Cart(userId, date, products);

	}

	// login
	
	public static Login loginPayload() {
		
		String username = faker.name().username();
		String password = faker.internet().password();
		
		return new Login(username, password);
		
	}

}
