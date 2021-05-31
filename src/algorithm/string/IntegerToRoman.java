package algorithm.string;

// https://leetcode.com/problems/integer-to-roman
public class IntegerToRoman {
	public String intToRoman(int num) {
		StringBuilder roman = new StringBuilder();
		int pos = 1;
		while (num > 0) {
			int rem = num % 10;
			if (pos == 1) {
				append(roman, rem, 'I', 'V', 'X');
			} else if (pos == 10) {
				append(roman, rem, 'X', 'L', 'C');
			} else if (pos == 100) {
				append(roman, rem, 'C', 'D', 'M');
			} else if (pos == 1000) {
				for (int count = 1; count <= rem; ++count) {
					roman.append("M");
				}
			}
			num = num / 10;
			pos *= 10;
		}
		return roman.reverse().toString();
	}

	// append the digits in reverse order
	private void append(StringBuilder roman, int rem, char c1, char c2, char c3) {
		if (rem > 0 && rem < 4) {
			for (int count = 1; count <= rem; ++count) {
				roman.append(c1);
			}
		} else if (rem == 4) {
			roman.append(c2);
			roman.append(c1);
		} else if (rem == 5) {
			roman.append(c2);
		} else if (rem > 5 && rem < 9) {
			for (int count = 1; count <= rem - 5; ++count) {
				roman.append(c1);
			}
			roman.append(c2);
		} else if (rem == 9) {
			roman.append(c3);
			roman.append(c1);
		}
	}
}
