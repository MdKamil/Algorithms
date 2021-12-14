package algorithm.math;

import java.util.Arrays;

public class PerfectSquares {

	public static int numSquares(int n) {
		int[] squares = new int[(int) Math.sqrt(n) + 1];
		for (int val = 1; val <= squares.length - 1; ++val) {
			squares[val] = val * val;
		}
		int[] dp = new int[n + 1];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dfs(squares.length - 1, squares, n, 0, 0, dp);
		return dp[n];
	}

	private static void dfs(int fromIdx, int[] squares, int n, int val, int count, int[] dp) {
		if (dp[n] < count) {
			return;
		}
		for (int idx = fromIdx; idx >= 1; --idx) {
			val += squares[idx];
			if (val == n) {
				if (dp[val] > count + 1) {
					dp[val] = count + 1;
				}
				break;
			} else if (val < n) {
				if (dp[val] > count + 1) {
					dp[val] = count + 1;
					dfs(idx, squares, n, val, count + 1, dp);
				}
			}
			val -= squares[idx];
		}
	}

	public static void main(String[] args) {
		System.out.println(numSquares(13));
	}

}
