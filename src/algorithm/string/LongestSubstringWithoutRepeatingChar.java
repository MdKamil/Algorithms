package algorithm.string;

import java.util.Arrays;

public class LongestSubstringWithoutRepeatingChar {

	public static int lengthOfLongestSubstring(String s) {
		int maxLength = 0;
		if (s != null && s.length() > 0) {
			int[] charCount = new int[256];
			int start = 0;
			while (start <= s.length() - 1) {
				int idx = start;
				for (; idx <= s.length() - 1; ++idx) {
					++charCount[s.charAt(idx)];
					if (charCount[s.charAt(idx)] == 2) {
						break;
					}
				}
				maxLength = Math.max(maxLength, idx - start);
				++start;
				Arrays.fill(charCount, 0);
			}
		}
		return maxLength;
	}

	public static int lengthOfLongestSubstringV2(String s) {
		int maxLength = 0;
		if (s != null && s.length() > 0) {
			int[] charCount = new int[256];
			int[] charLoc = new int[256];
			int start = 0;
			int idx = 0;
			for (; idx <= s.length() - 1; ++idx) {
				++charCount[s.charAt(idx)];
				if (charCount[s.charAt(idx)] == 2) {
					maxLength = Math.max(maxLength, idx - start);
					int prevIdxOfRepeatedChar = charLoc[s.charAt(idx)];
					for (int from = start; from <= prevIdxOfRepeatedChar - 1; ++from) {
						charCount[s.charAt(from)] = 0;
					}
					charCount[s.charAt(idx)] = 1;
					start = prevIdxOfRepeatedChar + 1;
				}
				charLoc[s.charAt(idx)] = idx;
			}
			maxLength = Math.max(maxLength, idx - start);
		}
		return maxLength;
	}

	public static void main(String[] args) {
		String s = "abcdefcghifmnopq";
		int maxLength = lengthOfLongestSubstring(s);
		System.out.println(maxLength);
	}
}
