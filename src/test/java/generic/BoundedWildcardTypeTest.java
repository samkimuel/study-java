package generic;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

// Upper bounded wildcard(<? extends T> - 공변), Lower bounded wildcard(? super T> - 반공변)
// 무공변(invariant) : 오로자 자기 타입만 허용 <T>
// 공변(covariant) : 구체적인 방향으로 타입 변환을 허용(자기 자신과 자식 객체만 허용) <? extends T>
// 반공변(contravariant) : 추상적인 방향으로의 타입 변환을 허용(자기 자신과 부모 객체만 허용) <? super T>
public class BoundedWildcardTypeTest {

	@Test
	void boundedWildcardType() {
		// extends/super - In, Out 개념으로 사용
		// PECS : Producer(생산자)-extends, Consumer(소비자)-super
		List<MyClass> list = new ArrayList<>();
		list.add(new MyClass("one"));
		list.add(new MyClass("two"));
		list.add(new MyClass("three"));

		// Producer-extends는 읽기만 가능하고 Consumer-super는 쓰기만 가능함
		doSomethingA(list);
		doSomethingD(list, "name");
	}

	public void copyAll(Collection<Object> to, Collection<String> from) {
//		to.addAll(from); // 컴파일 에러
	}

	public void doSomethingA(List<? extends MyClass> list) {
		for (MyClass e : list) {
			System.out.println(e);
		}
	}

	public void doSomethingB(List<? extends MyClass> list) {
//		list.add(new MyClass()); // 컴파일 에러
	}

	public void doSomethingC(List<? super MyClass> list) {
//		for (MyClass e : list) { // 컴파일 에러
//			System.out.println(e);
//		}
	}

	public void doSomethingD(List<? super MyClass> list, String name) {
		list.add(new MyClass(name));
	}

	interface Collection<E> {

		void addAll(Collection<E> item);
	}

	static class MyClass {

		private String name;

		public MyClass(String name) {
			this.name = name;
		}
	}
}
