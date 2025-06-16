package pojo;

public class Product {

	private String title;
	private String description;
	private double price;
	private String image;
	private String category;

	// constructor
	public Product(String title, String description, double price, String image, String category) {
		this.category = category;
		this.title = title;
		this.price = price;
		this.image = image;
		this.description = description;
	}

	// Getters and setters
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

}
