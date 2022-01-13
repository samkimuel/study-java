package stream.mock;

import java.util.ArrayList;
import java.util.List;

public class Product {

	private String name;
	private long price;
	private int amount;

	public Product() {
	}

	public Product(String name, long price, int amount) {
		this.name = name;
		this.price = price;
		this.amount = amount;
	}

	public static List<Product> productList() {
		List<Product> productList = new ArrayList<>();
		productList.add(new Product("product1", 10000L, 3));
		productList.add(new Product("product1", 20000L, 4));
		productList.add(new Product("product1", 30000L, 5));
		productList.add(new Product("product1", 35000L, 6));
		return productList;
	}

	public String getName() {
		return name;
	}

	public long getPrice() {
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
