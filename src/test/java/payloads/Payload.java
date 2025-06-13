package payloads;

import java.util.Random;

import com.github.javafaker.Faker;

import pojo.Address;
import pojo.Geolocation;
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

		String phone = faker.phoneNumber().phoneNumber();

		return new Users(email, username, password, name, address, phone);

	}

	// cart

	// login

}
