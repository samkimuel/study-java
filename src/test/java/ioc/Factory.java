package ioc;

// 오브젝트의 생성과 관계 설정 담당
public class Factory {

	public Patissier patissier() {
		return new Patissier(pastry());
	}

	public Pastry pastry() {
		return new Cake();
	}
}
