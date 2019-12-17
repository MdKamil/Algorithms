package algorithm.two;

import java.util.Arrays;

public class LCS {

	public static int computeLCSLength(String X, String Y) {
		return computeLCSLengthNaive(X, X.length(), Y, Y.length());
	}

	private static int computeLCSLengthNaive(String X, int xLen, String Y, int yLen) {
		if (xLen == 0) {
			return 0;
		} else if (yLen == 0) {
			return 0;
		}

		int cost = 0;
		if (X.charAt(xLen - 1) == Y.charAt(yLen - 1)) {
			cost = 1;
		}
		int shrinkX = computeLCSLengthNaive(X, xLen - 1, Y, yLen);
		int shrinkY = computeLCSLengthNaive(X, xLen, Y, yLen - 1);
		int shrinkBoth = computeLCSLengthNaive(X, xLen - 1, Y, yLen - 1) + cost;

		return Math.max(Math.max(shrinkX, shrinkY), shrinkBoth);
	}

	public static int computeLCSLengthDP(String X, String Y) {
		int xLen = X.length();
		int yLen = Y.length();
		int dp[][] = new int[xLen + 1][yLen + 1];
		for (int i = 0; i <= xLen; ++i) {
			for (int j = 0; j <= yLen; ++j) {
				if (i == 0 || j == 0) {
					dp[i][j] = 0;
				} else if (X.charAt(i - 1) == Y.charAt(j - 1)) {
					dp[i][j] = dp[i - 1][j - 1] + 1;
				} else {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
				}
			}
		}
		return dp[xLen][yLen];
	}

	public static void main(String[] args) {
		String X = "XMJYAUZ";
		String Y = "MZJAWXU";
		System.out.println(computeLCSLength(X, Y));
		System.out.println(computeLCSLengthDP(X, Y));
	}
}
