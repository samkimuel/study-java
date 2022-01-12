package stream;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
}
