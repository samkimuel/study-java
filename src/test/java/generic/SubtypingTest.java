package generic;

import java.util.List;
import org.junit.jupiter.api.Test;

public class SubtypingTest {

	@Test
	void subtyping() {
		// is-a 관계
		someMethod(new Integer(10));
		someMethod(new Double(10.1));

		Box<Number> box = new Box<>();
		box.add(new Integer(10));
		box.add(new Double(10.1));
	}

	@Test
	void error() {
		// Box<Double>, Box<Integer>는 Box<Number>의 서브타입이 아님
		// 매개변수화 타입은 무공변(invariant)이기 때문에 Box<Number>는 Box<Integer>의 서브타입도, 슈퍼타입도 아님
//		boxTest(new Box<Double>()); // 컴파일 에러
//		boxTest(new Box<Integer>()); // 컴파일 에러
	}

	@Test
	void fixError() {

	}

	public void someMethod(Number number) {

	}

	public void boxTest(Box<Number> n) {

	}

	// 제네릭 클래스나 인터페이스를 상속 관계로 정의하고 싶다면 클래스 or 인터페이스의 상속관계를 정의해야 함
	// List<String>의 서브타입
	// PayloadList<String, String>, PayloadList<String, Integer>, PayloadList<String, Exception>
	interface PayloadList<E, P> extends List<E> {

		void setPayload(int index, P val);
	}

	class Box<T> {

		public void add(T number) {

		}
	}
}
