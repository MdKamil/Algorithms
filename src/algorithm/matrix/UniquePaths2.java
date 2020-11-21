package algorithm.matrix;

public class UniquePaths2 {

	public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
		int noOfWays = 0;
		if (obstacleGrid != null && obstacleGrid.length > 0 && obstacleGrid[0] != null && obstacleGrid[0].length > 0) {
			int MAX_ROW = obstacleGrid.length - 1;
			int MAX_COL = obstacleGrid[0].length - 1;
			int[][] ways = new int[MAX_ROW + 1][MAX_COL + 1];
			for (int col = MAX_COL; col >= 0; --col) {
				if (obstacleGrid[MAX_ROW][col] == 1) {
					break;
				}
				ways[MAX_ROW][col] = 1;
			}
			for (int row = MAX_ROW; row >= 0; --row) {
				if (obstacleGrid[row][MAX_COL] == 1) {
					break;
				}
				ways[row][MAX_COL] = 1;
			}
			for (int row = MAX_ROW - 1; row >= 0; --row) {
				for (int col = MAX_COL - 1; col >= 0; --col) {
					if (obstacleGrid[row][col] != 1) {
						ways[row][col] = ways[row + 1][col] + ways[row][col + 1];
					}

				}
			}
			noOfWays = ways[0][0];
		}
		return noOfWays;
	}

	public static void main(String[] args) {
		int[][] obstacleGrid = { { 0, 0, 0 }, { 1, 1, 1 }, { 0, 0, 0 } };
		int ways = uniquePathsWithObstacles(obstacleGrid);
		System.out.println(ways);
	}

}
