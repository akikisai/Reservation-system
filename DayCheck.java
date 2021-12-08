package reserve;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class DayCheck {
	LocalDate localDate = LocalDate.now();

	public int GetDate(int year, int month, int day) {
		localDate = LocalDate.of(year, month, day);
		//dayOfYearの範囲：1 - 365
		int dayOfYear = localDate.getDayOfYear();
		return dayOfYear;
	}

	public String GetDay(int year, int month, int day) {

		localDate = LocalDate.of(year, month, day);

		//　曜日の決定
		String dayOfWeekStr = "";
		DayOfWeek dayOfWeek = localDate.getDayOfWeek();

		if (dayOfWeek == DayOfWeek.SUNDAY) {
			dayOfWeekStr = "(日)";
		} else if (dayOfWeek == DayOfWeek.MONDAY) {
			dayOfWeekStr = "(月)";
		} else if (dayOfWeek == DayOfWeek.TUESDAY) {
			dayOfWeekStr = "(火)";
		} else if (dayOfWeek == DayOfWeek.WEDNESDAY) {
			dayOfWeekStr = "(水)";
		} else if (dayOfWeek == DayOfWeek.THURSDAY) {
			dayOfWeekStr = "(木)";
		} else if (dayOfWeek == DayOfWeek.FRIDAY) {
			dayOfWeekStr = "(金)";
		} else {
			dayOfWeekStr = "(土)";
		}

		return dayOfWeekStr;

	}
}
