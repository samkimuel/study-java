package customcollection;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

public class MyCollectionTest {

	@Test
	void foreach() {
		new MyCollection<>(Arrays.asList(1, 2, 3, 4, 5))
			.foreach(System.out::println);

		new MyCollection<>(Arrays.asList("A", "B", "C", "D", "E"))
			.foreach(System.out::println);
	}

	@Test
	void map() {
		MyCollection<String> c1 = new MyCollection<>(Arrays.asList(
			"A",
			"CA",
			"DSB",
			"ASDC",
			"ASDFE"
		));
		MyCollection<Integer> c2 = c1.map(String::length);
		c2.foreach(System.out::println);

		// 메서드 체이닝
		new MyCollection<>(Arrays.asList("A", "CA", "DSB", "ASDC", "ASDFE"))
			.map(String::length)
			.foreach(System.out::println);
	}

	@Test
	void filter() {
		new MyCollection<>(Arrays.asList("A", "CA", "DSB", "ASDC", "ASDFE"))
			.map(String::length)
			.filter(i -> i % 2 == 1)
			.foreach(System.out::println);
	}

	@Test
	void size() {
		int size = new MyCollection<>(Arrays.asList("A", "CA", "DSB", "ASDC", "ASDFE"))
			.map(String::length)
			.filter(i -> i % 2 == 1)
			.size();
		assertThat(size).isEqualTo(3);
	}

	@Test
	void user() {
		new MyCollection<>(
			Arrays.asList(
				new User(15, "AAA"),
				new User(16, "BBB"),
				new User(17, "CCC"),
				new User(18, "DDD"),
				new User(19, "EEE"),
				new User(20, "FFF"),
				new User(21, "GGG"),
				new User(22, "HHH")
			)
		)
			.filter(User::isOver19)
			.foreach(System.out::println);
	}
}
