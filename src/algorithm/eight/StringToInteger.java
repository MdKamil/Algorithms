package algorithm.eight;

public class StringToInteger {

	public static int myAtoi(String str) {
		int integer = 0;
		if (str != null && str.length() > 0) {
			boolean isNegativeNo = false;
			boolean isIntegerEncountered = false;
			long multiplyBy = 1l;
			long longNumber = 0l;
			for (int idx = 0; idx <= str.length() - 1; ++idx) {
				if (str.charAt(idx) == ' ') {
					if (isIntegerEncountered) {
						break;
					}
					continue;
				} else if (str.charAt(idx) == '-' || str.charAt(idx) == '+') {
					if (isIntegerEncountered) {
						break;
					}
					if (str.charAt(idx) == '-') {
						isNegativeNo = true;
					}
					if (idx + 1 <= str.length() - 1
							&& ((int) str.charAt(idx + 1) < 48 || (int) str.charAt(idx + 1) > 57)) {
						break;
					}
				} else if ((int) str.charAt(idx) < 48 || (int) str.charAt(idx) > 57) {
					break;
				} else {
					isIntegerEncountered = true;
					longNumber = (longNumber * multiplyBy) + ((int) str.charAt(idx)) - 48;
					if (longNumber > Integer.MAX_VALUE) {
						break;
					}
					multiplyBy = 10;
				}
			}
			if (longNumber != 0l) {
				if (isNegativeNo) {
					longNumber *= -1l;
				}
				if (longNumber > Integer.MAX_VALUE) {
					integer = Integer.MAX_VALUE;
				} else if (longNumber < Integer.MIN_VALUE) {
					integer = Integer.MIN_VALUE;
				} else {
					integer = (int) longNumber;
				}
			}
		}
		return integer;
	}

	public static void main(String[] args) {
		String str = "0-1";
		int integer = myAtoi(str);
		System.out.println(integer);
	}

}
