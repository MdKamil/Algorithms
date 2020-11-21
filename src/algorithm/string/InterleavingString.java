package algorithm.string;

public class InterleavingString {
	public static boolean isInterleave(String s1, String s2, String s3) {
		boolean isInterleaving = false;
		if (s1 != null && s2 != null && s3 != null && s3.length() == s1.length() + s2.length()) {
			StringBuilder builder = new StringBuilder(s1.length() + s2.length());
			int s1Idx = 0;
			int s2Idx = 0;
			int s1Len = s1.length() == 0 ? 0 : s1.length();
			int s2Len = s2.length() == 0 ? 0 : s2.length();
			isInterleaving = recurse(s1, s1Len, s1Idx, s2, s2Len, s2Idx, s3, builder);
		}
		return isInterleaving;
	}

	private static boolean recurse(String s1, int s1Len, int s1Idx, String s2, int s2Len, int s2Idx, String s3,
			StringBuilder builder) {
		if (builder.length() > 0 && builder.charAt((s1Idx + s2Idx) - 1) != s3.charAt((s1Idx + s2Idx) - 1)) {
			return false;
		}
		if (s1Idx >= s1Len && s2Idx >= s2Len) {
			System.out.println(builder.toString());
			return true;
		}
		boolean isInterleaving = false;
		if (s1Idx < s1Len) {
			builder.append(s1.charAt(s1Idx));
			isInterleaving = recurse(s1, s1Len, s1Idx + 1, s2, s2Len, s2Idx, s3, builder);
			builder.deleteCharAt(builder.length() - 1);
		}

		if (!isInterleaving && s2Idx < s2Len) {
			builder.append(s2.charAt(s2Idx));
			isInterleaving = recurse(s1, s1Len, s1Idx, s2, s2Len, s2Idx + 1, s3, builder);
			builder.deleteCharAt(builder.length() - 1);
		}
		return isInterleaving;
	}

	public static boolean isInterleaveV2(String s1, String s2, String s3) {
		if (s3.length() != s1.length() + s2.length()) {
			return false;
		}
		boolean dp[][] = new boolean[s1.length() + 1][s2.length() + 1];
		for (int i = 0; i <= s1.length(); i++) {
			for (int j = 0; j <= s2.length(); j++) {
				if (i == 0 && j == 0) {
					dp[i][j] = true;
				} else if (i == 0) {
					dp[i][j] = dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1);
				} else if (j == 0) {
					dp[i][j] = dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1);
				} else {
					dp[i][j] = (dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1))
							|| (dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1));
				}
			}
		}
		return dp[s1.length()][s2.length()];
	}

	public static void main(String[] args) {
		String s1 = "abc";
		String s2 = "def";
		String s3 = "abcdef";
		boolean isInterleaving = isInterleave(s1, s2, s3);
		System.out.println(isInterleaving);
	}
}
