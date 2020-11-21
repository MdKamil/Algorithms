package algorithm.matrix;

public class MaxIncreasingPathArray {

	static int[] rowDir = { 1, -1, 0, 0 };
	static int[] colDir = { 0, 0, 1, -1 };
	static int maxLength;

	public static int getMaxPath(int[][] matrix) {
		maxLength = 0;
		if (matrix.length == 0 || matrix[0].length == 0) {
			return maxLength;
		}
		int[][] dp = new int[matrix.length][matrix[0].length];
		for (int i = 0; i <= matrix.length - 1; ++i) {
			for (int j = 0; j <= matrix[i].length - 1; ++j) {
				if (dp[i][j] == 0) {
					dfs(i, j, -1, -1, matrix, dp);
				}
				maxLength = Math.max(dp[i][j], maxLength);
			}
		}
		return maxLength;
	}

	private static int dfs(int i, int j, int pi, int pj, int[][] matrix, int[][] dp) {
		for (int d = 0; d <= 3; ++d) {
			int r = i + rowDir[d];
			int c = j + colDir[d];
			if (r < 0 || c < 0 || r > dp.length - 1 || c > dp[0].length - 1 || matrix[r][c] <= matrix[i][j]
					|| (r == pi && c == pj)) {
				continue;
			}
			int val = 0;
			if (dp[r][c] > 0) {
				val = 1 + dp[r][c];
			} else {
				val = 1 + dfs(r, c, i, j, matrix, dp);
			}
			dp[i][j] = Math.max(val, dp[i][j]);
		}
		if (dp[i][j] == 0) {
			dp[i][j] = 1;
		}
		return dp[i][j];
	}

	public static void main(String[] args) {
		int[][] matrix = getInput1();
		System.out.println(getMaxPath(matrix));
	}

	private static int[][] getInput1() {
		int[][] matrix = {};
		return matrix;
	}
}
