package stream;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import stream.mock.Product;

public class 생성하기 {

	@DisplayName("배열 스트림")
	@Test
	void array() {
		String[] arr = new String[]{"a", "b", "c"};
		Stream<String> stream = Arrays.stream(arr);
		Stream<String> streamRange = Arrays.stream(arr, 1, 3);
		stream.toList().forEach(System.out::println);
		streamRange.toList().forEach(System.out::println);
	}

	@DisplayName("컬렉션 스트림")
	@Test
	void collection() {
		// List
		List<String> list = Arrays.asList("a", "b", "c");
		Stream<String> stream = list.stream();
		stream.toList().forEach(System.out::println);

		// Set
		Set<String> foods = Set.of("pizza", "hamburger", "chicken");
		Stream<String> setStream = foods.stream();
		setStream.toList().forEach(System.out::println);
	}

	@DisplayName("Stream.builder()")
	@Test
	void streamBuilder() {
		Stream<String> buildStream =
			Stream
				.<String>builder()
				.add("Sam")
				.add("Samuel")
				.add("Samkimuel")
				.build();

		buildStream.toList().forEach(System.out::println);
	}

	@DisplayName("Stream.generate()")
	@Test
	void streamGenerator() {
		// 생성되는 스트림은 크기가 정해져있지 않고 무한하기 때문에 특정 사이즈로 최대 크기를 제한해야 함
		Stream<String> generatedStream = Stream.generate(() -> "gen").limit(5);
		generatedStream.toList().forEach(System.out::println);
	}

	@DisplayName("Stream.iterate()")
	@Test
	void streamIterate() {
		// 초기값과 해당 값을 다루는 람다를 이용해서 스트림에 들어갈 요소를 만듦
		// 요소가 다음 요소의 인풋으로 들어감
		// 특정 사이즈로 제한해야 함
		Stream<Integer> iteratedStream =
			Stream.iterate(30, integer -> integer + 2).limit(5);
		iteratedStream.toList().forEach(System.out::println);
	}

	@DisplayName("기본 타입형 스트림")
	@Test
	void primitiveTypeStream() {
		// 제네릭을 사용하지 않기 때문에 오토박싱이 일어나지 않음
		// range와 rangeClosed는 범위의 차이 - 두 번째 인자인 종료지점이 안되느냐 되느냐 차이
		IntStream intStream = IntStream.range(1, 5); // 종료지점 포함 안됨
		LongStream longStream = LongStream.rangeClosed(1, 5); // 종료지점 포함됨

		intStream.forEach(System.out::println); // [1, 2, 3, 4]
		longStream.forEach(System.out::println); // [1, 2, 3, 4, 5]

		// 필요한 경우 boxed 메서드 이용하여 박싱
//		Stream<Integer> boxedIntStream = intStream.boxed();

		// Random 클래스 - 난수를 가지고 스트림 만들기
		DoubleStream doubleStream = new Random().doubles(5);
		List<Double> doublesList = doubleStream.boxed().toList();
		doublesList.forEach(System.out::println);
	}

	@DisplayName("문자열 스트림")
	@Test
	void stringStream() {
		// 스트링의 각 문자(char)를 IntStream으로 변환
		// char는 문자이지만 본질적으로는 숫자이기 때문에 가능
		IntStream charStream = "Stream".chars();
		List<Integer> integerList = charStream.boxed().toList();
		integerList.forEach(integer -> System.out.println((char) integer.intValue())); // [S, t, r, e, a, m]

		Stream<String> stringStream =
			Pattern.compile(", ").splitAsStream("Sam, Samuel, SamKim");
		stringStream.toList().forEach(System.out::println);
	}

	@DisplayName("병렬 스트림")
	@Test
	void parallelStream() {
		List<Product> productList = Product.productList();

		// parallelStream 메서드를 사용해 병렬 스트림 생성
		Stream<Product> parallelStream = productList.parallelStream();
		// 병렬 여부 확인
		boolean isParallel = parallelStream.isParallel();
		assertThat(isParallel).isTrue();

		boolean isMany = parallelStream
			.map(product -> product.getAmount() * 10)
			.anyMatch(amount -> amount > 40);
		assertThat(isMany).isTrue();

		IntStream intStream = IntStream.rangeClosed(1, 5).parallel();
		IntStream sequentialStream = intStream.sequential();
		assertThat(sequentialStream.isParallel()).isFalse();
	}

	@DisplayName("스트림 연결하기")
	@Test
	void concatStream() {
		// 두 개의 스트림을 연결해서 새로운 스트림 만들기
		Stream<String> streamOne = Stream.of("Java", "Spring", "JPA");
		Stream<String> streamTwo = Stream.of("Python", "Django");
		Stream<String> concat = Stream.concat(streamOne, streamTwo);

		concat.toList().forEach(System.out::println);
	}
}
