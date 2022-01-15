package stream;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import stream.mock.Product;

public class 가공하기 {

	private List<String> names = Arrays.asList("Samuel", "Sam", "SamKim");

	@DisplayName("Filtering")
	@Test
	void filtering() {
		// 스트림 내 요소들을 하나씩 평가해서 걸러내는 작업
		// 인자로 Predicate 받음 - boolean을 리턴하는 함수형 인터페이스
		Stream<String> stream = names.stream()
			.filter(name -> name.contains("Kim"));
		stream.toList().forEach(System.out::println);

		List<Product> products = Product.productList();
		products.stream()
			.filter(product -> product.getAmount() >= 5)
			.toList()
			.forEach(System.out::println);
	}

	@DisplayName("map")
	@Test
	void map() {
		// map은 스트림 내 요소들을 하니씩 특정 값으로 변환해줌
		// 값을 변한하기 위한 람다를 인자로 받음
		Stream<String> stream = names.stream()
			.map(String::toUpperCase);
		stream.toList().forEach(System.out::println);

		// Product 객체의 수량 꺼내오기
		// 상품 -> 상품의 수량 으로 맵핑
		Product.productList().stream()
			.map(Product::getAmount)
			.toList()
			.forEach(System.out::println);
	}

	@DisplayName("flatMap")
	@Test
	void flatMap() {
		// 중첩 구조를 한 단계 제거하고 단일 컬렉션으로 만들어주는 역할 - flattening
		// 인자로 mapper 받음 - 리턴 타입이 Stream
		// 새로운 스트림을 생성해서 리턴하는 람다를 넘겨야 함
		List<List<String>> list = Arrays.asList(
			Arrays.asList("a"),
			Arrays.asList("b")
		); // [[a], [b]]

		List<String> flatList = list.stream()
			.flatMap(Collection::stream)
			.toList();
		flatList.forEach(System.out::println);

		Product.productList().stream()
			.flatMapToInt(product -> IntStream.of(product.getAmount()))
			.average()
			.ifPresent(average -> System.out.println(Math.round(average * 10) / 10.0));
	}

	@DisplayName("Sorting")
	@Test
	void sorting() {
		// Comparator 이용
		List<Integer> integerList = IntStream.of(14, 11, 20, 39, 23)
			.sorted()
			.boxed()
			.toList();
		integerList.forEach(System.out::println);
		System.out.println("---");

		// 인자를 넘기는 경우
		List<String> lang =
			Arrays.asList("Java", "C++", "Python", "Go", "Swift");

		lang.stream()
			.sorted()
			.toList()
			.forEach(System.out::println);
		System.out.println("---");

		// Comparator를 넘겨 역순으로 정렬
		lang.stream()
			.sorted(Comparator.reverseOrder())
			.toList()
			.forEach(System.out::println);
		System.out.println("---");

		// Comparator의 compare 메서드
		// 두 인자를 비교해서 값을 리턴
		lang.stream()
			.sorted(Comparator.comparingInt(String::length))
			.toList()
			.forEach(System.out::println);
		System.out.println("---");

		lang.stream()
			.sorted((value1, value2) -> value2.length() - value1.length())
			.toList()
			.forEach(System.out::println);
	}
}
