package algorithm.four;

import java.util.Arrays;

public class LongestSubstringWithKRepeatingChars {

	public static int longestSubstringNaive(String s, int k) {
		int longestSubStringLength = Integer.MIN_VALUE;
		if (s != null && s.length() > 0 && k <= s.length()) {
			int[] charCount = new int[26];
			for (int i = 0; i <= s.length() - 1; ++i) {
				for (int j = i; j <= s.length() - 1; ++j) {
					++charCount[(int) s.charAt(j) - 97];
					boolean isPresent = true;
					for (int count : charCount) {
						if (count > 0 && count < k) {
							isPresent = false;
							break;
						}
					}
					if (isPresent) {
						longestSubStringLength = Math.max(longestSubStringLength, (j - i) + 1);
					}
				}
				Arrays.fill(charCount, 0);
			}
		}
		return longestSubStringLength == Integer.MIN_VALUE ? 0 : longestSubStringLength;
	}

	public static void main(String[] args) {
		String s = "abgcfbxgy";
		int k = 1;
		int longestSubStringLength = longestSubstringNaive(s, k);
		System.out.println(longestSubStringLength);
	}

}
