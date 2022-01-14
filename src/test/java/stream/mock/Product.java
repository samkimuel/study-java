package stream.mock;

import java.util.ArrayList;
import java.util.List;

public class Product {

	private String name;
	private int price;
	private int amount;

	public Product() {
	}

	public Product(String name, int price, int amount) {
		this.name = name;
		this.price = price;
		this.amount = amount;
	}

	public static List<Product> productList() {
		List<Product> productList = new ArrayList<>();
		productList.add(new Product("product1", 10000, 3));
		productList.add(new Product("product2", 20000, 4));
		productList.add(new Product("product3", 30000, 5));
		productList.add(new Product("product4", 35000, 6));
		return productList;
	}

	public String getName() {
		return name;
	}

	public int getPrice() {
		return price;
	}

	public int getAmount() {
		return amount;
	}

	@Override
	public String toString() {
		return "Product{" +
			"name='" + name + '\'' +
			", price=" + price +
			", amount=" + amount +
			'}';
	}
}
