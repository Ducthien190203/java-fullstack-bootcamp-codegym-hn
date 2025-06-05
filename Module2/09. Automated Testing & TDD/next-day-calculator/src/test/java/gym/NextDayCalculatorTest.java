package gym;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import code.NextdDayCalculator;

class NextDayCalculatorTest {

	@Test
	void testResultNextDay() {
		String resultString = NextdDayCalculator.calculatedNextDay(1, 1, 2018);
		assertEquals("2/1/2018", resultString);
	}

	@Test
	void testEndOfMonth1() {
		String resultString = NextdDayCalculator.calculatedNextDay(31, 1, 2018);
		assertEquals("1/2/2018", resultString);
	}

	@Test
	void testEndOfMonth4() {
		String resultString = NextdDayCalculator.calculatedNextDay(30, 4, 2018);
		assertEquals("1/5/2018", resultString);
	}

	@Test
	void testEndOfMonth2NotLeap() {
		String resultString = NextdDayCalculator.calculatedNextDay(28, 2, 2018);
		assertEquals("1/3/2018", resultString);
	}

	@Test
	void testEndOfMonth2Leap() {
		String resultString = NextdDayCalculator.calculatedNextDay(29, 2, 2020);
		assertEquals("1/3/2020", resultString);
	}

	@Test
	void testEndOfMonth12() {
		String resultString = NextdDayCalculator.calculatedNextDay(31, 12, 2018);
		assertEquals("1/1/2019", resultString);
	}

}
