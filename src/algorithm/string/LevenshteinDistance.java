package algorithm.string;

public class LevenshteinDistance {

	public static int getMinEditDistance(String a, int aLen, String b, int bLen) {
		int cost = 0;
		if (aLen == 0) {
			return bLen;
		}
		if (bLen == 0) {
			return aLen;
		}
		if (a.charAt(aLen - 1) == b.charAt(bLen - 1)) {
			cost = 0;
		} else {
			cost = 1;
		}
		int aDel = getMinEditDistance(a, aLen - 1, b, bLen) + 1;
		int bDel = getMinEditDistance(a, aLen, b, bLen - 1) + 1;
		int cDel = getMinEditDistance(a, aLen - 1, b, bLen - 1) + cost;
		return Math.min(Math.min(aDel, bDel), cDel);
	}

	public static int getMinEditDistanceDP(String a, int aLen, String b, int bLen) {
		int[][] dp = new int[aLen + 1][bLen + 1];
		for (int i = 0; i <= aLen; ++i) {
			for (int j = 0; j <= bLen; ++j) {
				if (i == 0) {
					dp[i][j] = j;
				} else if (j == 0) {
					dp[i][j] = i;
				} else if (a.charAt(i - 1) != b.charAt(j - 1)) {
					dp[i][j] = 1 + Math.min(Math.min(dp[i][j - 1], dp[i - 1][j]), dp[i - 1][j - 1]);
				} else {
					dp[i][j] = dp[i - 1][j - 1];
				}
			}
		}
		return dp[aLen][bLen];
	}

	public static void main(String[] args) {
		String a = "an";
		String b = "a";
		System.out.println(getMinEditDistance(a, a.length(), b, b.length()));
		System.out.println(getMinEditDistanceDP(a, a.length(), b, b.length()));
	}

}
