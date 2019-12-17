package algorithm.three;

public class NumberOfIsland {

	static int[] rowDir = { 1, -1, 0, 0 };
	static int[] colDir = { 0, 0, 1, -1 };

	public static int numIslands(char[][] grid) {
		int islandCount = 0;
		if (grid != null && grid.length != 0 && grid[0].length != 0) {
			int[][] status = new int[grid.length][grid[0].length];
			for (int row = 0; row <= grid.length - 1; ++row) {
				for (int col = 0; col <= grid[row].length - 1; ++col) {
					if (grid[row][col] != '0' && status[row][col] == 0) {
						dfs(grid, status, row, col);
						++islandCount;
					}
				}
			}
		}
		return islandCount;
	}

	private static void dfs(char[][] grid, int[][] status, int row, int col) {
		status[row][col] = 1;
		for (int dir = 0; dir <= 3; ++dir) {
			int r = row + rowDir[dir];
			int c = col + colDir[dir];
			if (r < 0 || c < 0 || r > grid.length - 1 || c > grid[0].length - 1 || status[r][c] != 0
					|| grid[r][c] == '0') {
				continue;
			}
			dfs(grid, status, r, c);
		}
		status[row][col] = 2;
	}

	public static void main(String[] args) {
		// char[][] grid = { { '1', '1', '0', '0', '0' }, { '1', '1', '0', '0',
		// '0' }, { '0', '0', '1', '0', '0' },
		// { '0', '0', '0', '1', '1' } };
		char[][] grid = { { '1', '0', '1', '0', '1' } };
		int islandCount = numIslands(grid);
		System.out.println(islandCount);
	}

}
