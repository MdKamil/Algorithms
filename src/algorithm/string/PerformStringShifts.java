package algorithm.string;

public class PerformStringShifts {

	public static String stringShift(String s, int[][] shift) {
		int headIdx = 0;
		int strLength = s.length() - 1;
		char[] strArray = s.toCharArray();
		if (strLength > 1) {
			for (int idx = 0; idx <= shift.length - 1; ++idx) {
				int dir = shift[idx][0];
				int amount = shift[idx][1] % (strLength + 1);
				if (amount == 0 || amount == strLength + 1) {
					continue;
				}
				if (dir == 0) {
					// left-shift
					if (headIdx - amount < 0) {
						amount -= headIdx;
						headIdx = strLength + 1;
					}
					headIdx -= amount;
				} else {
					// right-shift
					if (headIdx + amount > strLength) {
						amount -= (strLength - headIdx);
						headIdx = -1;
					}
					headIdx += amount;
				}
			}
			if (headIdx != 0) {
				int idx1 = 0;
				for (int idx = headIdx; idx <= strLength; ++idx) {
					strArray[idx] = s.charAt(idx1++);
				}
				for (int idx = 0; idx < headIdx; ++idx) {
					strArray[idx] = s.charAt(idx1++);
				}
			}
		}
		return new String(strArray);
	}

	public static void main(String[] args) {
		String s = "abcdefg";
		int[][] shift = { { 1, 6 }, { 1, 6 } };
		String result = stringShift(s, shift);
		System.out.println(result);
	}

}
