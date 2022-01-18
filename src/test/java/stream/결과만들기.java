package stream;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.IntSummaryStatistics;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.OptionalInt;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import stream.mock.Product;

public class 결과만들기 {

	@DisplayName("Calculating")
	@Test
	void calculating() {
		// 최소, 최대, 합, 평균 등 기본형 타입으로 결과 만들기
		long count = IntStream.of(1, 3, 5, 7, 9).count();
		assertThat(count).isEqualTo(5);

		long sum = IntStream.of(1, 3, 5, 7, 9).sum();
		assertThat(sum).isEqualTo(25);

		// 스트림이 비어 있는 경우 count와 sum은 0을 출력
		int sumZero = IntStream.of().sum();
		assertThat(sumZero).isEqualTo(0);

		// 최대, 최소의 경우에는 표현할 수 없기 때문에 Optional 리턴
		OptionalInt min = IntStream.of(1, 3, 5, 7, 9).min();
		assertThat(min).hasValue(1);
		OptionalInt max = IntStream.of(1, 3, 5, 7, 9).max();
		assertThat(max).hasValue(9);

		// 스트림에서 바로 ifPresent 이용해서 Optional 처리
		DoubleStream.of(1.1, 2.2, 3.3, 4.4, 5.5)
			.average()
			.ifPresent(System.out::println);
	}

	@DisplayName("Reduction")
	@Test
	void reduction() {
		// 스트림에 있는 여러 요소의 총합
		// reduce 메서드는 세 가지 파라미터 받을 수 있음
		// accumulator : 각 요소를 처리하는 계산 로직. 각 요소가 올 때마다 중간 결과를 생성하는 로직.
		// identity : 계산을 위한 초기값으로 스트림이 비어서 계산할 내용이 없더라도 이 값은 리턴.
		// combiner : 병렬(parallel) 스트림에서 나눠 계산한 결과를 하나로 합치는 동작하는 로직.

		// 인자가 하나만 있는 경우
		// BinaryOperator<T> - 같은 타임의 인자 두 개를 받아 같은 타입의 결과를 반환하는 함수형 인터페이스
		OptionalInt reduced =
			IntStream.range(1, 4)
				.reduce((a, b) -> {
					return Integer.sum(a, b);
				});
		assertThat(reduced).hasValue(6);

		// 두 개의 인자를 받는 경우
		// 초기값, 스트림 내 값 계산
		int reducedTwoParams =
			IntStream.range(1, 4)
				.reduce(10, Integer::sum); // 10 + 1 + 2 + 3
		assertThat(reducedTwoParams).isEqualTo(16);

		// 세 개의 인자를 받는 경우
		// 마지막 인자 combiner는 실행 안 됨 -> ??
		// Combiner는 병렬 스트림에서만 동작함!!
		Integer reducedParams =
			Stream.of(1, 2, 3)
				.reduce(
					10,
					Integer::sum,
					(a, b) -> {
						System.out.println("combiner was called");
						return a + b;
					}
				);

		// 병렬 스트림이 무조건 시퀀셜보다 좋은 것은 아님
		// 간단한 경우에는 부가적인 처리가 필요하기 때문에 오히려 느릴 수도 있음
		// accumulator 세 번 동작 - 10 + 1, 10 + 2, 10 + 3
		// combiner 두 번 호출 - 12 + 13 = 25, 11 + 25 = 36
		Integer reducedParallel =
			Arrays.asList(1, 2, 3)
				.parallelStream()
				.reduce(
					10,
					Integer::sum,
					(a, b) -> {
						System.out.println("combiner was called " + a + " " + b);
						return a + b;
					}
				);
	}

	@DisplayName("Collecting")
	@Test
	void collecting() {
		List<Product> productList = Product.productList();

		// Collector 타입의 인자를 받아서 처리
		// 자주 사용하는 작업은 Collectors 객체에서 제공하고 있음

		// Collectors.toList()
		// 스트림에서 작업한 결과를 담은 리스트로 반환
		List<String> collectorCollection =
			productList.stream()
				.map(Product::getName)
				.collect(Collectors.toList());
		collectorCollection.forEach(System.out::println);

		// Collector.joining()
		// 스트림에서 작업한 결과를 하나의 스트링으로 이어 붙일 수 있음
		String listToString =
			productList.stream()
				.map(Product::getName)
				.collect(Collectors.joining());
		System.out.println(listToString);

		// Collectors.averagingInt()
		// 숫자 값의 평균
		Double averageAmount =
			productList
				.stream()
				.collect(Collectors.averagingInt(Product::getAmount));
		System.out.println(averageAmount);

		// Collectors.summingInt()
		// 숫자 값의 합
		Integer summingAmount =
			productList
				.stream()
				.collect(Collectors.summingInt(Product::getAmount));
		System.out.println(summingAmount);
		// mapToInt 메서드 사용해서 좀 더 간단하게 표현
		Integer mapToIntAmount =
			productList
				.stream()
				.mapToInt(Product::getAmount)
				.sum();
		System.out.println(mapToIntAmount);

		// Collectors.summarizingInt()
		// 합계와 평균 모두 필요하다면? -> 한 번에 얻을 수 있는 방법
		// collect 전에 통계 작업을 위한 map 호출 필요 없음
		IntSummaryStatistics statistics =
			productList.stream()
				.collect(Collectors.summarizingInt(Product::getAmount));
		// statistics.getMax(), getAverage() 등
		System.out.println(statistics);

		// Collectors.groupingBy()
		// 특정 조건으로 요소들을 그룹 짓기
		// 받는 인자는 함수형 인터페이스 Function
		// 결과 - Map 타입, 같은 수량이면 리스트로 묶어서 보여줌
		Map<Integer, List<Product>> collectMapOfLists =
			productList.stream()
				.collect(Collectors.groupingBy(Product::getAmount));
		System.out.println(collectMapOfLists);

		// Collectors.partitioningBy()
		// 함수형 인터페이스 Predicate를 받음
		// 스트림 내 요소들을 true / false 두 가지로 나눔
		Map<Boolean, List<Product>> mapPartitioned = productList.stream()
			.collect(Collectors.partitioningBy(product -> product.getAmount() > 4));
		System.out.println(mapPartitioned);
		// get(true), get(false)
		System.out.println(mapPartitioned.get(true));

		// Collectors.collectingAndThen()
		// 특정 타입으로 결과를 collect 한 이후에 추가 작업이 필요한 경우 사용
		Set<Product> unmodifiableSet = productList.stream()
			.collect(Collectors.collectingAndThen(
				Collectors.toSet(),
				Collections::unmodifiableSet
			));
		System.out.println(unmodifiableSet);

		// Collector.of()
		// 직접 collector 만들기
		Collector<Product, LinkedList<Product>, LinkedList<Product>> toLinkedList =
			Collector.of(
				LinkedList::new,
				LinkedList::add,
				(first, second) -> {
					first.addAll(second);
					return first;
				}
			);

		LinkedList<Product> linkedListOfProducts = productList.stream()
			.collect(toLinkedList);
		System.out.println(linkedListOfProducts);
	}

	@DisplayName("Matching")
	@Test
	void matching() {
		List<Product> productList = Product.productList();
		// Predicate를 받아서 해당 조건을 만족하는 요소가 있는지 체크한 결과 리턴
		// anyMatch - 하나라도 조건을 만족하는 요소가 있는지
		boolean anyMatch =
			productList.stream()
				.anyMatch(product -> product.getPrice() == 40000);
		assertThat(anyMatch).isTrue();

		// allMatch - 모두 조건을 만족하는지
		boolean allMatch =
			productList.stream()
				.allMatch(product -> product.getName().contains("product"));
		assertThat(allMatch).isTrue();

		// noneMatch - 모두 조건을 만족하지 않는지
		boolean noneMatch =
			productList.stream()
				.noneMatch(product -> product.getAmount() > 5);
		assertThat(noneMatch).isFalse();
	}

	@DisplayName("Iterating")
	@Test
	void iterating() {
		// forEach 요소를 돌면서 실행되는 최종 작업
		// peek과는 중간 작업과 최종 작업의 차이가 있음
		Product.productList().stream()
			.forEach(System.out::println);
	}

}
