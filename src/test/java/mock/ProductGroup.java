package mock;

import java.util.Objects;

public class ProductGroup {

	private final String name;
	private final int price;
	private final int amount;

	public ProductGroup(Product product) {
		this.name = product.getName();
		this.price = product.getPrice();
		this.amount = product.getAmount();
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
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		ProductGroup that = (ProductGroup) o;
		return Objects.equals(this.name, that.name)
			&& Objects.equals(this.price, that.price)
			&& Objects.equals(this.amount, that.amount);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, price, amount);
	}

	@Override
	public String toString() {
		return "ProductGroup{" +
			"name='" + name + '\'' +
			", price=" + price +
			", amount=" + amount +
			'}';
	}
}
