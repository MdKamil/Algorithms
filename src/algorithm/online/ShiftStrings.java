package algorithm.online;

public class ShiftStrings {

	public static String getShiftedString(String s, int leftShifts, int rightShifts) {
		String intermediate = null;
		int stringLen = s.length();
		if (leftShifts % stringLen != 0) {
			int actual = leftShifts % stringLen;
			int start = stringLen - (stringLen - actual);
			String left = s.substring(start);
			String right = s.substring(0, (start - 1) + 1);
			intermediate = left.concat(right);
			s = intermediate;
		}
		if (rightShifts % stringLen != 0) {
			int actual = rightShifts % stringLen;
			int start = stringLen - (stringLen - (stringLen - actual));
			String left = s.substring(start);
			String right = s.substring(0, (start - 1) + 1);
			intermediate = left.concat(right);
			s = intermediate;
		}
		return s;
	}

	public static void main(String[] args) {
		String s = "ab";
		int leftShifts = 3;
		int rightShifts = 2;
		String result = getShiftedString(s, leftShifts, rightShifts);
		System.out.println(result);
	}
}
