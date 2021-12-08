package reserve;

import java.time.LocalDate;
import java.util.Calendar;

public class SampleCalendar {
	private static int year; //thisYear+aが入る予定
	private static int month; //bが入る予定
	private static int firstDay;
	private static int lastDay;

	SampleCalendar(int y, int m) {
		year = y;
		month = m;
	}

	public static void calc() {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(year, month - 1, 1);
		firstDay = calendar.get(Calendar.DAY_OF_WEEK);
		int day = 1 - (firstDay - 1) % 7;
		calendar.add(Calendar.MONTH, 1);
		calendar.add(Calendar.DATE, -1);
		lastDay = calendar.get(Calendar.DATE);
		int dayOfYear = 0;

		String p = "";
		int counter = 0;

		while (day <= lastDay) {
			for (int ii = 0; ii < 7 && day <= lastDay; ii++) {

				LocalDate date1 = LocalDate.now();

				if (day >= 1) {
					date1 = LocalDate.of(year, month, day);
					dayOfYear = date1.getDayOfYear();

					//日の全時間帯に座席が無いか求める

					for (int i = 0; i < ReservationStart.memory[i].length; i++) {

						if (month == 12 && day == 31)
							dayOfYear = 365;

						int remain = ReservationStart.memory[dayOfYear - 1][i];

						//その１日の残った座席数の合計を計算
						counter += remain;

					}

					if (counter == 0)
						p = "×";
					else if (0 < counter && counter < 121)
						p = "△";
					else if (120 < counter && counter < 171)
						p = "○";
					else if (171 < counter && counter < 221)
						p = "◎";
					else if (220 < counter && counter < 351)
						p = "☆";

					counter = 0;
				}

				System.out.printf(day < 1 ? "    " : "%2d" + p, day);
				day++;
			}
			System.out.println();
		}
		System.out.println();
	}
}
