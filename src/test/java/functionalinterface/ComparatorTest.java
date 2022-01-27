package functionalinterface;

import java.util.Comparator;
import java.util.List;
import mock.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

// Comparator<T> : T 타입 인자 두 개를 받아서 int 타입 리턴
public class ComparatorTest {

	@Test
	void sort() {
		List<Product> productList = Product.productList();

		productList.sort(
			Comparator.comparing(Product::getAmount, Comparator.reverseOrder())
				.thenComparing(Product::getPrice)
		);

		System.out.println(productList);
	}

	@Test
	void lambdaSort() {
		List<Product> productList = Product.productList();

		productList.sort((p1, p2) -> Integer.compare(p1.getAmount(), p2.getAmount()));

		System.out.println(productList);
	}

	@DisplayName("reversed, reverseOrder 차이")
	@Test
	void reversedAndReverseOrder() {
		List<Product> productList1 = Product.productList();
		productList1.sort(
			Comparator.comparing(Product::getAmount)
				.reversed()
				.thenComparing(Product::getPrice)
				.reversed()
		);

		System.out.println(productList1);

		List<Product> productList2 = Product.productList();
		productList2.sort(
			Comparator.comparing(Product::getAmount, Comparator.reverseOrder())
				.thenComparing(Product::getPrice, Comparator.reverseOrder())
		);
		System.out.println(productList2);
	}
}
