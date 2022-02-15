package generic;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

// 타입 매개변수가 자신을 포함하는 수식에 한정될 수 있음
// 타입의 자연율을 정의하는 Comparable 인터페이스와 가장 많이 사용됨
// *자연율 : 상식적인 관점에서 알 수 있는 순서를 말함. 문자열은 사전의 알파벳(한글은 가나다) 순, 숫자는 크기 순
public class RecursiveTypeBoundTest {

	// <T extends Comparable<T>> -> 자신과 비교될 수 있는 모든 타입 T
	// 재귀적 바운드가 존재하는 이유는 Java 언어의 문제점 보완
	// Java는 연산자 오버로딩을 지원하지 않음 -> primitive 타입에만 비교연산자 사용할 수 있음
	// 이를 해결하기 위해 Comparable 인터페이스와 재귀적 타입 바운드 활용
	@Test
	void recursiveTypeBound() {
		Product product1 = new Product(1000);
		Product product2 = new Product(1000);

		assertThat(product1.compareTo(product2)).isEqualTo(0);

		Product product3 = new Product(1000);
		Box box = new Box(1000);

		assertThat(product3.compareTo(box)).isEqualTo(0);
	}

	@Test
	void error() {
		Gun gun = new Gun(2000);
		Shotgun shotgun = new Shotgun(2000);

//		assertThat(gun.compareTo(shotgun)).isEqualTo(0); // 컴파일 에러
	}

	public <T extends Comparable<T>> int countGreaterThan(T[] anArray, T elem) {
		int count = 0;
		for (T e : anArray) {
			if (e.compareTo(elem) > 0) {
				++count;
			}
		}
		return count;
	}

	interface Shootable {

	}

	class Gun implements Shootable, Comparable<Gun> {

		private final Integer price;

		public Gun(Integer price) {
			this.price = price;
		}

		@Override
		public int compareTo(Gun o) {
			return this.price.compareTo(o.price);
		}
	}

	class Shotgun implements Shootable, Comparable<Shotgun> {

		private final Integer price;

		public Shotgun(Integer price) {
			this.price = price;
		}

		@Override
		public int compareTo(Shotgun o) {
			return this.price.compareTo(o.price);
		}
	}

	class Product implements Comparable<Product> {

		private final Integer price;

		public Product(Integer price) {
			this.price = price;
		}

		@Override
		public int compareTo(Product p) {
			return this.price.compareTo(p.price);
		}
	}

	class Box extends Product {

		public Box(Integer price) {
			super(price);
		}
	}
}
