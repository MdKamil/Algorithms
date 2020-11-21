package algorithm.string;

public class PalindromicSubstrings {

	public static int countSubstrings(String s) {
		int noOfPalindromicSubstrings = 0;
		if (s != null && s.length() > 0) {
			for (int i = 0; i < s.length(); i++) {
				int count1 = expandAroundCenter(s, i, i);
				int count2 = expandAroundCenter(s, i, i + 1);
				noOfPalindromicSubstrings += count1 + count2;
			}
		}
		return noOfPalindromicSubstrings;
	}

	private static int expandAroundCenter(String s, int left, int right) {
		int count = 0;
		int L = left, R = right;
		while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
			++count;
			L--;
			R++;
		}
		return count;
	}

	public static void main(String[] args) {
		String s = "abc";
		int count = countSubstrings(s);
		System.out.println(count);
	}

}
