package algorithm.string;

public class LongestPalindrome {
	public static int longestPalindrome(String s) {
		int length = 0;
		if (s != null && s.length() > 0) {
			int[] counter = new int[58];
			for (int idx = 0; idx <= s.length() - 1; ++idx) {
				++counter[((int) s.charAt(idx)) - 65];
			}
			int oddCount = 0;
			for (int idx = 0; idx <= counter.length - 1; ++idx) {
				if (counter[idx] % 2 != 0) {
					++oddCount;
				}
				length += counter[idx];
			}
			if (oddCount > 1) {
				length -= oddCount - 1;
			}
		}
		return length;
	}

	public static void main(String[] args) {
		String s = "((int) s.charAt(idx)) - 65";
		int length = longestPalindrome(s);
		System.out.println(length);
	}

}
