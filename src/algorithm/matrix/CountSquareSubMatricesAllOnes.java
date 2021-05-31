package algorithm.matrix;

public class CountSquareSubMatricesAllOnes {
	public int countSquares(int[][] matrix) {
		int count = 0;
		int counter[][] = new int[matrix.length][matrix[0].length];
		for (int row = 0; row <= matrix.length - 1; ++row) {
			for (int col = 0; col <= matrix[row].length - 1; ++col) {
				if ((row == 0 || col == 0) && matrix[row][col] == 1) {
					counter[row][col] = 1;
				} else if (matrix[row][col] != 0) {
					counter[row][col] = Math.min(counter[row - 1][col - 1],
							Math.min(counter[row][col - 1], counter[row - 1][col])) + 1;
				}
				count += counter[row][col];
			}
		}
		return count;
	}
}
