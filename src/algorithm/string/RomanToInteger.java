package algorithm.string;

// https://leetcode.com/problems/roman-to-integer/
public class RomanToInteger {

	public int romanToInt(String s) {
		int value = 0;
		for (int idx = 0; idx <= s.length() - 1; ++idx) {
			if (s.charAt(idx) == 'I') {
				value += 1;
			} else if (s.charAt(idx) == 'V') {
				value += 5;
				if (idx != 0 && s.charAt(idx - 1) == 'I') {
					value -= 2;
				}
			} else if (s.charAt(idx) == 'X') {
				value += 10;
				if (idx != 0 && s.charAt(idx - 1) == 'I') {
					value -= 2;
				}
			} else if (s.charAt(idx) == 'L') {
				value += 50;
				if (idx != 0 && s.charAt(idx - 1) == 'X') {
					value -= 20;
				}
			} else if (s.charAt(idx) == 'C') {
				value += 100;
				if (idx != 0 && s.charAt(idx - 1) == 'X') {
					value -= 20;
				}
			} else if (s.charAt(idx) == 'D') {
				value += 500;
				if (idx != 0 && s.charAt(idx - 1) == 'C') {
					value -= 200;
				}
			} else if (s.charAt(idx) == 'M') {
				value += 1000;
				if (idx != 0 && s.charAt(idx - 1) == 'C') {
					value -= 200;
				}
			}
		}
		return value;
	}
}
