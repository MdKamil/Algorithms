package algorithm.online;

import java.util.Arrays;
import java.util.List;
import java.util.Queue;

public class DiamondMine {
	public static int collectMax(List<List<Integer>> mat) {
		int maximumDiamondCollected = 0;
		int[][] dp = new int[mat.size()][mat.get(0).size()];
		int startRow = 0;
		int startCol = 0;
		int endRow = mat.size() - 1;
		int endCol = mat.get(0).size() - 1;
		if (mat.get(endRow).get(endCol) == 1) {
			dp[endRow][endCol] = 1;
		}
		int[] rowDownDir = { 1, 0 };
		int[] colRightDir = { 0, 1 };
		visitMine(mat, dp, startRow, startCol, endRow, endCol, rowDownDir, colRightDir);

		if (mat.get(startRow).get(startCol) == 1) {
			mat.get(0).set(0, 0);
		}
		setZero(mat, dp, startRow, startCol, endRow, endCol, rowDownDir, colRightDir);
		for (int[] row : dp) {
			Arrays.fill(row, 0);
		}
		int[] rowUpDir = { -1, 0 };
		int[] colLeftDir = { 0, -1 };
		visitMine(mat, dp, endRow, endCol, startRow, startCol, rowUpDir, colLeftDir);
		maximumDiamondCollected = dp[0][0];
		return maximumDiamondCollected;
	}

	private static int visitMine(List<List<Integer>> mat, int[][] dp, int currRow, int currCol, int endRow, int endCol,
			int[] rowDir, int[] colDir) {
		int maxDiamond = 0;
		if (currRow == endRow && currCol == endCol) {
			int max = 0;
			if (mat.get(currRow).get(currCol) == 1) {
				max = 1;
			}
			return max;
		}
		for (int dir = 0; dir <= 1; ++dir) {
			int nextRow = currRow + rowDir[dir];
			int nextCol = currCol + colDir[dir];
			if (nextRow > endRow || nextCol > endCol) {
				continue;
			}
			if (mat.get(nextRow).get(nextCol) == -1) {
				continue;
			}
			if (dp[nextRow][nextCol] != 0) {
				int max = dp[nextRow][nextCol];
				if (mat.get(nextRow).get(nextCol) == 1) {
					++max;
				}
				maxDiamond = Math.max(maxDiamond, max);
				continue;
			}
			int max = visitMine(mat, dp, nextRow, nextCol, endRow, endCol, rowDir, colDir);
			if (mat.get(nextRow).get(nextCol) == 1) {
				++max;
			}
			maxDiamond = Math.max(maxDiamond, max);
		}
		dp[currRow][currCol] = maxDiamond;

		return maxDiamond;
	}

	private static void setZero(List<List<Integer>> mat, int[][] dp, int currRow, int currCol, int endRow, int endCol,
			int[] rowDir, int[] colDir) {
		if (currRow == endRow && currCol == endCol) {
			return;
		}
		int max = Integer.MIN_VALUE;
		int nextRow = -1;
		int nextCol = -1;
		for (int dir = 0; dir <= 1; ++dir) {
			int row = currRow + rowDir[dir];
			int col = currCol + colDir[dir];
			if (row > endRow || col > endCol) {
				continue;
			}
			if (dp[row][col] > max) {
				nextRow = row;
				nextCol = col;
				max = dp[row][col];
			}
		}
		if (nextRow != -1 && nextCol != -1) {
			mat.get(nextRow).set(nextCol, 0);
			setZero(mat, dp, nextRow, nextCol, endRow, endCol, rowDir, colDir);
		}
	}

	public static void main(String[] args) {

	}

}
