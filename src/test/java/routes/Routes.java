package routes;

public class Routes {
	
	public static final String BASE_URL = "https://fakestoreapi.com";
	
	//product
	public static final String GET_ALL_PRODUCTS = "/products";
	public static final String GET_PRODUCT_BY_ID = "/products/{id}";
	public static final String GET_PRODUCTS_WITH_LIMIT = "/products?limit={limit}";
	public static final String GET_PRODUCTS_SORTED = "/products?sort={order}";
	public static final String GET_ALL_PRODUCTS_CATAGERORIES = "/products/categories";
	public static final String GET_PRODUCT_BY_CATEGORY = "/products/category/{category}";
	public static final String CREATE_PRODUCT = "/products";
	public static final String UPDATE_PRODUCT = "/products/{id}";
	public static final String DELETE_PRODUCT = "/products/{id}";
	
	//cart
	public static final String GET_ALL_CART_ITEMS = "/carts:";
	public static final String GET_CART_ITEMS_BY_ID = "/carts/{id}";
	public static final String GET_CART_ITEMS_WITH_LIMIT = "/carts?limit={limit}";
	public static final String GET_CART_ITEMS_SORTED = "/carts?sort={order}";
	public static final String GET_ALL_CART_ITEMS_BY_DATE = "/carts?startDate={startdate}&endDate={enddate}";
	public static final String GET_CART_ITEMS_BY_USERID = "/carts/user/{userId}:";
	public static final String CREATE_CART = "/carts";
	public static final String UPDATE_CART = "/carts/{id}";
	public static final String DELETE_CART = "/carts/{id}";
	
	//user
	public static final String GET_ALL_USERS = "/users";
	public static final String GET_USER_BY_ID = "/users/{id}";
	public static final String GET_ALL_USERS_WITH_LIMIT = "/users?limit={limit}";
	public static final String GET_ALL_USERS_SORTED = "/users?sort={order}";
	public static final String CREATE_USERS = "/users";
	public static final String UPDATE_USER = "/users/{id}";
	public static final String DELETE_USER = "/users/{id}";

	//login
	public static final String LOGIN = "/auth/login";
	
	
}
