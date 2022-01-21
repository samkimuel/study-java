package functionalinterface;

import static org.assertj.core.api.Assertions.*;

import java.util.function.Predicate;
import org.junit.jupiter.api.Test;

public class PredicateTest {

	@Test
	void test() {
		// Predicate<T>는 T타입 인자를 받고 결과로 boolean을 리턴
		Predicate<Integer> isGreaterThanTen = num -> num > 10;
		assertThat(isGreaterThanTen.test(15)).isTrue();
	}

	@Test
	void andor() {
		// and()와 or()는 다른 Predicate와 함께 사용됨
		// and()는 두개의 Predicate가 true일 때 true를 리턴
		Predicate<Integer> isGreaterThanFive = num -> num > 5;
		Predicate<Integer> isLessThanTen = num -> num < 10;
		assertThat(isGreaterThanFive.and(isLessThanTen).test(3)).isFalse();

		// or()는 두개 중에 하나만 true이면 true를 리턴함
		assertThat(isGreaterThanFive.or(isLessThanTen).test(3)).isTrue();
	}

	@Test
	void isEqual() {
		// isEqual()은 static 메소드로, 인자로 전달되는 객체와 같은지 체크하는 Predicate 객체를 만들어 줌
		Predicate<String> isEqual = Predicate.isEqual("Samkim");
		assertThat(isEqual.test("samkim")).isFalse();
	}

	@Test
	void negate() {
		// negate()는 Predicate의 결과가 true이면 false를 false이면 true를 리턴하는 새로운 Predicate를 생성
		Predicate<String> predicate = string -> string.contains("sam");
		assertThat(predicate.negate().test("Samkim")).isTrue();

		Predicate<String> not = Predicate.not(predicate);
		assertThat(not.test("SamKim")).isTrue();
	}
}
