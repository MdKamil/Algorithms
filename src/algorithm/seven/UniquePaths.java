package algorithm.seven;

import java.util.Arrays;

public class UniquePaths {

	// m-col | n-row
	public static int uniquePaths(int m, int n) {
		int[][] noOfPath = new int[n][m];
		int MAX_ROW = n - 1;
		int MAX_COL = m - 1;
		int startRow = 0;
		int startCol = 0;
		noOfPath[0][0] = dfs(startRow, startCol, noOfPath, MAX_ROW, MAX_COL);
		return noOfPath[0][0];
	}

	private static int dfs(int row, int col, int[][] noOfPath, int MAX_ROW, int MAX_COL) {
		int ways = 0;
		if (col > MAX_COL || row > MAX_ROW) {
			return ways;
		} else if (row == MAX_ROW && col == MAX_COL) {
			return 1;
		} else if (noOfPath[row][col] != 0) {
			return noOfPath[row][col];
		}
		ways += dfs(row, col + 1, noOfPath, MAX_ROW, MAX_COL);
		ways += dfs(row + 1, col, noOfPath, MAX_ROW, MAX_COL);
		noOfPath[row][col] = ways;
		return ways;
	}

	public static int uniquePathsV2(int m, int n) {
		int[] nextRow = new int[m];
		Arrays.fill(nextRow, 1);
		int nextColVal = 1;
		for (int row = n - 2; row >= 0; --row) {
			for (int col = m - 2; col >= 0; --col) {
				if (col == m - 2) {
					nextColVal = nextRow[col] + 1;
					nextRow[col] = nextColVal;
				} else {
					nextColVal = nextRow[col] + nextColVal;
					nextRow[col] = nextColVal;
				}
			}
		}
		return nextRow[0];
	}

	public static void main(String[] args) {
		int m = 10;
		int n = 10;
		int noOfWays = uniquePaths(m, n);
		System.out.println(noOfWays);
	}

}
