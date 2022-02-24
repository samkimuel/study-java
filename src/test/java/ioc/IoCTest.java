package ioc;

import org.junit.jupiter.api.Test;

public class IoCTest {

	@Test
	void iocTest() {
		Factory factory = new Factory();
		Patissier patissier = factory.patissier();

		patissier.make();
	}
}
