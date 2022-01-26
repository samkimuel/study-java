package functionalinterface;

import org.junit.jupiter.api.Test;

// Runnable : 인자를 받지 않고 리턴값도 없음
public class RunnableTest {

	@Test
	void run() {
		Runnable runnable = () -> System.out.println("run run run away");
		runnable.run();
	}

	@Test
	void thread() {
		Runnable myRunnable = new MyThread();

		Thread thread1 = new Thread(myRunnable);
		Thread thread2 = new Thread(myRunnable);
		Thread thread3 = new Thread(myRunnable);

		thread1.setName("1번");
		thread2.setName("2번");
		thread3.setName("3번");

		thread1.start();
		thread2.start();
		thread3.start();
	}

	static class MyThread implements Runnable {

		@Override
		public void run() {
			for (int i = 0; i < 5; i++) {
				System.out.println("응애 나 아기 스레드 " + Thread.currentThread().getName());
			}
		}
	}
}
