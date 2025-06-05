import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AbsoluteNumberCalculatorTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testFindAbsolute() {
		int number = 0;
		int expected = 0;

		int result = AbsoluteNumberCalculator.findAbsolute(number);
		assertEquals(expected, result);
	}

	@Test
	void testFindAbsolute1() {
		int number = 1;
		int expected = 1;

		int result = AbsoluteNumberCalculator.findAbsolute(number);
		assertEquals(expected, result);
	}

	@Test
	void testFindAbsoluteNegative1() {
		int number = -1;
		int expected = 1;

		int result = AbsoluteNumberCalculator.findAbsolute(number);
		assertEquals(expected, result);
	}

}
