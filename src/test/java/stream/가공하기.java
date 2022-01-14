package stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import stream.mock.Product;

public class 가공하기 {

	@DisplayName("Filtering")
	@Test
	void filtering() {
		// 스트림 내 요소들을 하나씩 평가해서 걸러내는 작업
		// 인자로 Predicate 받음 - boolean을 리턴하는 함수형 인터페이스
		List<String> names = Arrays.asList("Samuel", "Sam", "SamKim");

		Stream<String> stream = names.stream()
			.filter(name -> name.contains("Kim"));
		stream.toList().forEach(System.out::println);

		List<Product> products = Product.productList();
		products.stream()
			.filter(product -> product.getAmount() >= 5)
			.toList()
			.forEach(System.out::println);
	}
}
