package algorithm.matrix;

public class NumberOfClosedIslands {

	public static int closedIsland(int[][] grid) {
		int closedIslands = 0;
		for (int row = 0; row <= grid.length - 1; ++row) {
			for (int col = 0; col <= grid[row].length - 1; ++col) {
				if (grid[row][col] == 0) {
					closedIslands += (dfs(row, col, grid, false) ? 0 : 1);
				}
			}
		}
		return closedIslands;
	}

	private static int[][] dirs = { { 0, 1 }, { 0, -1 }, { -1, 0 }, { 1, 0 } };

	private static boolean dfs(int row, int col, int[][] grid, boolean hasReachedBoundary) {
		if (isBoundary(row, col, grid.length - 1, grid[row].length - 1)) {
			hasReachedBoundary = true;
		}
		grid[row][col] = 1;
		for (int dir = 0; dir <= dirs.length - 1; ++dir) {
			int nextRow = row + dirs[dir][0];
			int nextCol = col + dirs[dir][1];
			if (validCell(nextRow, nextCol, grid.length - 1, grid[0].length - 1) && grid[nextRow][nextCol] == 0) {
				hasReachedBoundary = dfs(nextRow, nextCol, grid, hasReachedBoundary);
			}
		}
		return hasReachedBoundary;
	}

	private static boolean validCell(int row, int col, int MAX_ROW, int MAX_COL) {
		return row >= 0 && row <= MAX_ROW && col >= 0 && col <= MAX_COL;
	}

	private static boolean isBoundary(int row, int col, int MAX_ROW, int MAX_COL) {
		return row == 0 || row == MAX_ROW || col == 0 || col == MAX_COL;
	}

	public static void main(String[] args) {
		int[][] grid = { { 1, 1, 1, 1, 1, 1, 1 }, { 1, 0, 0, 0, 0, 0, 1 }, { 1, 0, 1, 1, 1, 0, 1 },
				{ 1, 0, 1, 0, 1, 0, 1 }, { 1, 0, 1, 1, 1, 0, 1 }, { 1, 0, 0, 0, 0, 0, 1 }, { 1, 1, 1, 1, 1, 1, 1 } };
		System.out.println(closedIsland(grid));
	}

}
