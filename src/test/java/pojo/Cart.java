package pojo;

import java.util.List;

public class Cart {

	private int userId;
	private String date;
	private List<Product> products;

	// Constructor
	public Cart(int userId, String date, List<Product> products) {
		this.userId = userId;
		this.date = date;
		this.products = products;
	}

	// Getter and Setters
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

}
