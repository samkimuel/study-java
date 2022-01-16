package stream;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.OptionalInt;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
}
