package algorithm.matrix;

public class MinimumFallingPathSum {

	public int minFallingPathSum(int[][] matrix) {
		int[][] dp = new int[matrix.length][matrix.length];
		int minSum = Integer.MAX_VALUE;
		for (int row = matrix.length - 1; row >= 0; --row) {
			minSum = Integer.MAX_VALUE;
			for (int col = 0; col <= matrix[row].length - 1; ++col) {
				if (row == matrix.length - 1) {
					dp[row][col] = matrix[row][col];
				} else {
					int left = (col == 0 ? Integer.MAX_VALUE : matrix[row][col] + dp[row + 1][col - 1]);
					int down = matrix[row][col] + dp[row + 1][col];
					int right = (col == matrix[row].length - 1 ? Integer.MAX_VALUE
							: matrix[row][col] + dp[row + 1][col + 1]);
					dp[row][col] = Math.min(Math.min(left, down), right);
					minSum = Math.min(minSum, dp[row][col]);
				}
			}
		}
		return minSum;
	}
}
