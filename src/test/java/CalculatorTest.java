import static org.junit.Assert.*;

public class CalculatorTest {

    @org.junit.Test
    public void calculate() {
        Calculator calculator = new Calculator();

        String actual = calculator.calculate("2+2*2=");
        String expected = "6.0";
        assertEquals(expected, actual);

        actual = calculator.calculate("(2+2)*2=");
        expected = "8.0";
        assertEquals(expected, actual);

        actual = calculator.calculate("(1+2)/2=");
        expected = "1.5";
        assertEquals(expected, actual);
    }
}