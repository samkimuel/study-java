package generic;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

// List<?> 와 같은 타입을 언바운드 와일드카드 타입이라고 함
// 어떤 타입이 오든 관계가 없다는 것
// 언바운드 와일드카드 타입이 사용될 수 있는 시나리오
// 1. Object 클래스에서 제공되는 기능을 사용하여 구현할 수 있는 메서드를 작성하는 경우
// 2. 타입 파라미터에 의존적이지 않은 일반 클래스의 메서드를 사용하는 경우 - List.clear, List.size, Class<?>
public class UnboundedWildcardTypeTest {

	@Test
	void unboundedWildcard() {
		List<String> list = new ArrayList<>();
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("d");

		printList(list);
		clearList(list);
	}

	// 메서드 내부에서 List 타입에 의존하지 않는 코드를 수행함
	// 컴파일 된 코드에서도 타입에 의존하는 코드는 없음, 소거자에 의해 <?>가 지워지진 않음
	private void printList(List<?> list) {
		for (Object elem : list) {
			System.out.println(elem + " ");
		}
	}

	private void clearList(List<?> list) {
		list.clear();
		System.out.println("list cleared");

		printList(list);
	}
}
