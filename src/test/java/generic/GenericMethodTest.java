package generic;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

// 메서드에 제네릭 적용
// 주로 static 유틸리티 메서드에 유용하게 쓰일 수 있음
public class GenericMethodTest {

	@Test
	void genericMethod() {
		Pair<Integer, String> p1 = new Pair<>(1, "apple");
		Pair<Integer, String> p2 = new Pair<>(2, "pear");

		// Util.<Integer, String>compare(p1, p2);
		// 매개변수화 타입 정의 -> 타입 추론에 의해 생략할 수 있음
		assertThat(Util.compare(p1, p2)).isTrue();

		Pair<String, Integer> p3 = new Pair<>("banana", 3);
//		assertThat(Util.compare(p1, p3)).isTrue(); // 컴파일 에러
	}

	static class Pair<K, V> {

		private final K key;
		private final V value;

		public Pair(K key, V value) {
			this.key = key;
			this.value = value;
		}

		public K getKey() {
			return key;
		}

		public V getValue() {
			return value;
		}
	}

	static class Util {

		// 제네릭 메서드의 타입 매개 변수를 선언할 때 타입 매개변수 위치는 메서드의 접근 지시자와 반환 타입 사이
		public static <K, V> boolean compare(Pair<K, V> p1, Pair<K, V> p2) {
			return p1.getKey().equals(p2.getKey()) && p1.getValue().equals(p2.getValue());
		}
	}
}
