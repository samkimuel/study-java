package designpattern.observer;

public class ForecastDisplay implements Observer, DisplayElement {

	private float currentPressure = 29.92f;
	private float lastPressure;
	private WeatherData weatherData;

	public ForecastDisplay(WeatherData weatherData) {
		this.weatherData = weatherData;
		weatherData.registerObserver(this);
	}

	@Override
	public void update() {
		lastPressure = currentPressure;
		currentPressure = weatherData.getPressure();

		display();
	}

	@Override
	public void display() {
		System.out.print("기상 예보 : ");

		if (currentPressure > lastPressure) {
			System.out.println("날씨가 좋아지고 있습니다.");
		} else if (currentPressure == lastPressure) {
			System.out.println("비슷비슷");
		} else if (currentPressure < lastPressure) {
			System.out.println("쌀쌀, 비올듯요");
		}
	}
}
