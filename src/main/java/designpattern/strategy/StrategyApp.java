package designpattern.strategy;

public class StrategyApp {

	public static void main(String[] args) {
		Duck mallardDuck = new MallardDuck();
		mallardDuck.performQuack();
		mallardDuck.performFly();

		Duck model = new ModelDuck();
		model.performFly();
		model.setFlyBehavior(new FlyRocketPowered()); // 실행 중에 오리의 행동 바꾸기
		model.performFly();
	}
}
