package templatecallback;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

class CalcSumTest {

    Calculator calculator;
    String numFilepath;

    @BeforeEach
    void setUp() {
        this.calculator = new Calculator();
        this.numFilepath = getClass().getResource("/numbers.txt").getPath();
    }

    @Test
    void calcSum() throws IOException {
        assertThat(calculator.calcSum(numFilepath)).isEqualTo(20);
    }

    @Test
    void calcMultiply() throws IOException {
        assertThat(calculator.calcMultiply(numFilepath)).isEqualTo(300);
    }
}
