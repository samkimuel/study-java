package generic;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class WildcardSubtypingTest {

	@Test
	void wildcardSubtyping() {
		List<Integer> list = new ArrayList<>();
//		addAll(list); // 컴파일 에러

		// 와일드카드 타입을 사용하여 에러 해결
		List<Integer> list1 = new ArrayList<>();
		List<Float> list2 = new ArrayList<>();
		addAll(list1);
		addAll(list2);
	}

//	public void addAll(List<Number> list) {
//
//	}

	// 무공변의 성질을 가지는 매개변수화 티입의 문제점을 해결하기 위해 바운드 와일드카드 타입 사용
	public void addAll(List<? extends Number> list) {

	}
}
