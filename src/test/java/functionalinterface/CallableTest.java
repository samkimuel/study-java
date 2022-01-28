package functionalinterface;

import static org.assertj.core.api.Assertions.*;

import java.security.InvalidParameterException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;

// Callable<V> : 아무런 인자를 받지 않고 V 타입 객체 리턴
// https://www.baeldung.com/java-runnable-callable
public class CallableTest {

	@Test
	void call() throws ExecutionException, InterruptedException {
		// ExecutorService를 이용하면 스레드풀을 생성하여 병렬처리를 할 수 있음
		// newSingleThreadExecutor() : 스레드 1개인 ExecutorService 리턴
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		FactorialTask task = new FactorialTask(5);

		// submit()의 인자 task -> Callable
		// Future : 비동기적인 연산의 결과를 표현하는 클래스
		// 멀티쓰레드 환경에서 처리된 어떤 데이터를 다른 쓰레드에 전달할 수 있음
		// 내부적으로 Thread-Safe 하도록 구현되어있음
		Future<Integer> future = executorService.submit(task);

		executorService.shutdown();

		System.out.println(future.get().intValue());
		assertThat(future.get().intValue()).isEqualTo(120);
	}

	@Test
	void exception() {
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		FactorialTask task = new FactorialTask(-5);
		Future<Integer> future = executorService.submit(task);

		assertThat(future.isDone()).isFalse();
		assertThatThrownBy(() -> future.get().intValue())
			.isInstanceOf(ExecutionException.class);
	}

	@Test
	void manyThread() throws InterruptedException {
		ExecutorService executor = Executors.newFixedThreadPool(4);

		Callable<String> callable1 = () -> {
			String threadName = Thread.currentThread().getName();
			System.out.println("Job1 " + threadName);
			return threadName;
		};

		Callable<String> callable2 = () -> {
			String threadName = Thread.currentThread().getName();
			System.out.println("Job2 " + threadName);
			return threadName;
		};

		Callable<String> callable3 = () -> {
			String threadName = Thread.currentThread().getName();
			Thread.sleep(6000);
			System.out.println("Job3 " + threadName);
			return threadName;
		};

		Callable<String> callable4 = () -> {
			String threadName = Thread.currentThread().getName();
			System.out.println("Job4 " + threadName);
			return threadName;
		};

		executor.submit(callable1);
		executor.submit(callable2);
		executor.submit(callable3);
		executor.submit(callable4);

		// 작업이 모두 완료되면 쓰레드풀 종료
		executor.shutdown();

		// shutdown() 호출 전에 등록된 Task 중에 아직 완료되지 않은 Task가 있을 수 있음
		// Timeout을 20초 설정하고 완료되기를 기다림
		// 5초 전에 완료되면 true를 리턴하며, 5초가 지나도 완료되지 않으면 false를 리턴
		if (executor.awaitTermination(5, TimeUnit.SECONDS)) {
			System.out.println("All jobs are terminated");
		} else {
			System.out.println("some jobs are not terminated");
			executor.shutdownNow(); // 모든 Task 를 강제 종료
		}

		System.out.println("end");
	}

	public static class FactorialTask implements Callable<Integer> {

		int number;

		public FactorialTask(int number) {
			this.number = number;
		}

		@Override
		public Integer call() throws InvalidParameterException {
			if (number < 0) {
				throw new InvalidParameterException("number는 양수!");
			}

			int fact = 1;

			for (int count = number; count > 1; count--) {
				fact = fact * count;
			}

			return fact;
		}
	}
}
