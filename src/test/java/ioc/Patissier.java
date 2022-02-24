package ioc;

public class Patissier {

	private Pastry pastry;

	public Patissier(Pastry pastry) {
		this.pastry = pastry;
	}

	public void make() {
		System.out.println(pastry.pastry() + " 만들기");
	}
}
