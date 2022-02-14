package generic;

import org.junit.jupiter.api.Test;

// 바운드 타입 : 특정 타입으로 제한한다는 의미
// 특정 타입의 서브타입으로만 제한을 시킴
public class BoundedTypeTest {

	@Test
	void boundedType() {
		Box<Integer> box = new Box<>();
//		box.set("Hi"); // Number의 서브타입만 허용 - String 컴파일 에러
	}

	@Test
	void multiBoundedType() {
		// Java는 다중 상속이 안됨
		// 다중 바운드 타입은 클래스 하나와 인터페이스 여러 개 선언할 수 있음
		// class D 선언 - T를 class A, interface B, interface C의 서브 타입으로 선언
		// 클래스는 한 개만 허용, 인터페이스는 여러 개 선언할 수 있음
//		D<B> d = new D<>(); // 컴파일 에러
		D<E> d = new D<>();
	}

	interface B {

	}

	interface C {

	}

	static class A {

	}

	static class D<T extends A & B & C> {

	}

	static class E extends A implements B, C {

	}

	static class Box<T extends Number> {

		private T value;

		public void set(T value) {
			this.value = value;
		}
	}
}
