package generic;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

// 제네릭은 클래스나 메서드에서 사용할 내부 데이터 타입을 컴파일 시에 미리 지정하는 방법이다.
// 1. 클래스나 메서드 내부에서 사용되는 객체의 타입 안정성을 높일 수 있다.
// 2. 반환값에 대한 타입 변환 및 타입 검사에 들어가는 노력을 줄일 수 있다.
public class ParameterizedTypeTest {

	@DisplayName("비 구체화 타입")
	@Test
	void nonReifiableType() {
		// 비 구체화 타입은 타입 소거자에 의해 컴파일 타임에 타입 정보가 사라진다. (런타임에 구체화하지 않는 것)
		// E, List<E>, List<String> 등
		List<String> list = new ArrayList<>();
		list.add("Hi");

		System.out.println(list.getClass()); // ArrayList - 타입 정보 사라짐
	}

	@DisplayName("구체화 타입")
	@Test
	void reifiableType() {
		// 구체화 타입은 자신의 타입 정보를 런타임 시에 알고 지키게 한다. (런타임에 구체화하는 것)
		// primitives, non-generic types, raw types / List<?>, Map<?, ?>와 같은 언바운드 와일드카드 타입 등
		Object[] array = new Long[10];
		array[0] = 1L;

		System.out.println(array.getClass()); // Object[] -> Long[] 실제 타입으로 변경
	}
}
