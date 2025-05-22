package code;

import java.time.LocalDate;

public class NextdDayCalculator {
	public static String calculatedNextDay(int day, int month, int year) {
		LocalDate currentDate = LocalDate.of(year, month, day);
		LocalDate nextDate = currentDate.plusDays(1);

		String resultString = nextDate.getDayOfMonth() + "/" + nextDate.getMonthValue() + "/" + nextDate.getYear();
		return resultString;
	}
}
