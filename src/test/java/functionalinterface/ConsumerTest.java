package functionalinterface;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import org.junit.jupiter.api.Test;

// Consumer<T> : T 타입의 객체를 인자로 받고 소모, 리턴 값은 없음
// BiConsumer<T> : 2개의 인자를 받고 소모, 리턴 값은 없음
public class ConsumerTest {

	@Test
	void accept() {
		Consumer<String> printString = string -> System.out.println("I am " + string);
		printString.accept("Sam");
	}

	@Test
	void andThen() {
		// 두 개 이상의 Consumer 연속적으로 실행
		Consumer<String> printString = string -> System.out.println("I am " + string);
		Consumer<String> printText = text -> System.out.println(text + " is the BEST");
		printString.andThen(printText).accept("Sam");
	}

	@Test
	void biConsumer() {
		BiConsumer<String, String> biConsumer =
			(string1, string2) -> System.out.println(string1 + " " + string2);
		biConsumer.accept("Hello", "Sam!");
	}

	@Test
	void mapForEach() {
		// Map.forEach() 는 인자로 BiConsumer 받음
		Map<String, Integer> map = new HashMap<>();
		map.put("one", 1);
		map.put("two", 2);
		map.put("three", 3);

		BiConsumer<String, Integer> biConsumer =
			(key, value) -> System.out.println("열쇠 : " + key + ", 값 : " + value);
		map.forEach(biConsumer);
	}
}
