package algorithm.eight;

public class MinimumPathSum {

	public static int minPathSumV1(int[][] grid) {
		int minPathSum = 0;
		if (grid != null && grid.length > 0 && grid[0] != null && grid[0].length > 0) {
			int[][] minFrom = new int[grid.length][grid[0].length];
			int row = grid.length - 1;
			int col = grid[0].length - 1;
			minFrom[row][col] = grid[row][col];
			--col;
			for (; col >= 0; --col) {
				minFrom[row][col] = grid[row][col] + minFrom[row][col + 1];
			}
			col = grid[0].length - 1;
			row = grid.length - 2;
			for (; row >= 0; --row) {
				minFrom[row][col] = grid[row][col] + minFrom[row + 1][col];
			}
			row = grid.length - 2;
			col = grid[0].length - 2;
			for (; row >= 0; --row) {
				for (; col >= 0; --col) {
					minFrom[row][col] = Math.min(minFrom[row][col + 1], minFrom[row + 1][col]) + grid[row][col];
				}
				col = grid[0].length - 2;
			}
			minPathSum = minFrom[0][0];
		}
		return minPathSum;
	}

	public static int minPathSumV2(int[][] grid) {
		int minPathSum = 0;
		if (grid != null && grid.length > 0 && grid[0] != null && grid[0].length > 0) {
			int[][] minFrom = new int[grid.length][grid[0].length];
			for (int row = grid.length - 1; row >= 0; --row) {
				for (int col = grid[row].length - 1; col >= 0; --col) {
					if (row == grid.length - 1 && col == grid[row].length - 1) {
						minFrom[row][col] = grid[row][col];
					} else if (row == grid.length - 1) {
						minFrom[row][col] = grid[row][col] + minFrom[row][col + 1];
					} else if (col == grid[row].length - 1) {
						minFrom[row][col] = grid[row][col] + minFrom[row + 1][col];
					} else {
						minFrom[row][col] = Math.min(minFrom[row][col + 1], minFrom[row + 1][col]) + grid[row][col];
					}
				}
			}
			minPathSum = minFrom[0][0];
		}
		return minPathSum;
	}

	public static void main(String[] args) {
		int[][] grid = { { 1, 3, 1 }, { 1, 5, 1 }, { 4, 2, 1 } };
		int minPathSum = minPathSumV2(grid);
		System.out.println(minPathSum);
	}

}
