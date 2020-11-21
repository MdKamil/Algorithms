package algorithm.string;

public class DayOfTheYear {

	public static int dayOfYear(String date) {
		int dayOfYear = 0;

		int place = 1000;
		int year = 0;
		for (int i = 0; i <= 3; ++i) {
			int digit = Character.getNumericValue(date.charAt(i));
			digit = digit * place;
			place = place / 10;
			year = year + digit;
		}
		boolean isLeapYear = false;
		if (year % 4 != 0) {
			isLeapYear = false;
		} else if (year % 400 == 0) {
			isLeapYear = true;
		} else if (year % 100 == 0) {
			isLeapYear = false;
		} else {
			isLeapYear = true;
		}
		int month = 0;
		place = 10;
		for (int i = 5; i <= 6; ++i) {
			int digit = Character.getNumericValue(date.charAt(i));
			digit = digit * place;
			place = place / 10;
			month = month + digit;
		}
		int day = 0;
		place = 10;
		for (int i = 8; i <= 9; ++i) {
			int digit = Character.getNumericValue(date.charAt(i));
			digit = digit * place;
			place = place / 10;
			day = day + digit;
		}

		for (int i = 1; i <= month - 1; ++i) {
			if (i == 2) {
				if (isLeapYear) {
					dayOfYear += 29;
				} else {
					dayOfYear += 28;
				}
			} else if (i == 1 || i == 3 || i == 5 || i == 7 || i == 8 || i == 10 || i == 12) {
				dayOfYear += 31;
			} else {
				dayOfYear += 30;
			}
		}
		dayOfYear += day;
		return dayOfYear;
	}

	public static void main(String[] args) {
		String date = "1900-03-25";
		int dayOfYear = dayOfYear(date);
		System.out.println(dayOfYear);
	}
}
