package functionalinterface;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import mock.Product;
import org.junit.jupiter.api.Test;

// Supplier<T> : 인자를 받지 않고 T 타입의 객체를 리턴
public class SupplierTest {

	@Test
	void primitive() {
		Supplier<String> getString = () -> "Hello World!";
		String str = getString.get();

		assertThat(str).isEqualTo("Hello World!");
	}

	@Test
	void collection() {
		Supplier<List<Product>> productSupplier = Product::productList;

		assertThat(productSupplier.get().size()).isEqualTo(5);
		assertThat(productSupplier.get().get(0).getName()).isEqualTo("product1");
	}

	@Test
	void exception() throws Exception {
		Supplier<Exception> exceptionSupplier = Exception::new;
		List<Product> products = Optional.of(Product.productList())
			.orElseThrow(exceptionSupplier);

		assertThatThrownBy(() -> products.get(5)).isInstanceOf(Exception.class);
	}
}
