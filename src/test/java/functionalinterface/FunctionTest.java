package functionalinterface;

import static org.assertj.core.api.Assertions.*;

import java.util.function.BiFunction;
import java.util.function.Function;
import org.junit.jupiter.api.Test;

// Function<T, R> : T타입의 인자를 받고, R타입의 객체를 리턴
public class FunctionTest {

	@Test
	void apply() {
		Function<Integer, Integer> multiply = value -> value * 2;
		Integer result = multiply.apply(3);
		assertThat(result).isEqualTo(6);
	}

	@Test
	void compose() {
		// compose() : 두 개의 Function을 조합하여 새로운 Function 객체 만듦
		// andThen()과 실행 순서 반대
		// compose()에 인자로 전달되는 Function이 먼저 수행되고 이후 호출하는 객체의 Function 수행
		Function<Integer, Integer> multiply = value -> value * 2;
		Function<Integer, Integer> add = value -> value + 3;

		Function<Integer, Integer> addThenMultiply = multiply.compose(add);
		Integer result = addThenMultiply.apply(3); // (3 + 3) * 2 = 12
		assertThat(result).isEqualTo(12);
	}

	@Test
	void biFunction() {
		// 두 개의 인자(T, U)를 받고 1개의 객체(R)을 리턴
		BiFunction<String, String, String> biFunction = (string1, string2) -> string1 + string2;
		String result = biFunction.apply("Hello ", "World!");
		assertThat(result).isEqualTo("Hello World!");
	}

	@Test
	void biFunctionAndThen() {
		BiFunction<Integer, Integer, Double> biFunction = Math::pow;
		Function<Double, String> function = d -> String.valueOf(d.intValue());

		String result = biFunction.andThen(function).apply(2, 3);

		assertThat(result).isEqualTo("8");
	}
}
