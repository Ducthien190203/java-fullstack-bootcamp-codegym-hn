import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class AbsoluteNumberCalculatorTest {

	@Test
	void testFindAbsoluteZero() {
		int number = 0;
		int expected = 0;

		int result = AbsoluteNumberCalculator.findAbsolute(number);
		assertEquals(expected, result);
	}

	@Test
	void testFindAbsolutePositive() {
		int number = 5;
		int expected = 5;

		int result = AbsoluteNumberCalculator.findAbsolute(number);
		assertEquals(expected, result);
	}

	@Test
	void testFindAbsoluteNegative() {
		int number = -7;
		int expected = 7;

		int result = AbsoluteNumberCalculator.findAbsolute(number);
		assertEquals(expected, result);
	}
}
