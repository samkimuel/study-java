package designpattern.strategy;

public class MuteQuack implements QuackBehavior {

	@Override
	public void quack() {
		System.out.println("아무 소리 못냄ㅠ");
	}
}
