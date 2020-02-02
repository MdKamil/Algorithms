package algorithm.seven;

import java.util.Arrays;

public class DiagonalTraverse {

	public static int[] findDiagonalOrder(int[][] matrix) {
		int[] elements = null;
		if (matrix == null) {
			elements = null;
		} else if (matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
			elements = new int[0];
		} else {
			int MAX_ROW = matrix.length - 1;
			int MAX_COL = matrix[0].length - 1;
			elements = new int[(MAX_ROW + 1) * (MAX_COL + 1)];
			int idx = 0;
			int currRow = 0;
			int currCol = 0;
			boolean isEndReached = false;
			while (true) {
				// traverse-up
				while (true) {
					elements[idx] = matrix[currRow][currCol];
					++idx;
					if (currRow == MAX_ROW && currCol == MAX_COL) {
						isEndReached = true;
						break;
					}
					if (currCol == MAX_COL) {
						++currRow;
						break;
					}
					if (currRow == 0) {
						++currCol;
						break;
					}
					++currCol;
					--currRow;
				}
				if (isEndReached) {
					break;
				}

				// traverse-down
				while (true) {
					elements[idx] = matrix[currRow][currCol];
					++idx;
					if (currRow == MAX_ROW && currCol == MAX_COL) {
						isEndReached = true;
						break;
					}
					if (currRow == MAX_ROW) {
						++currCol;
						break;
					}
					if (currCol == 0) {
						++currRow;
						break;
					}
					--currCol;
					++currRow;
				}
				if (isEndReached) {
					break;
				}
			}
		}
		return elements;
	}

	public static void main(String[] args) {
		int[][] matrix = { { 1, 2 }, { 3, 4 } };
		int[] elements = findDiagonalOrder(matrix);
		System.out.println(Arrays.toString(elements));
	}

}
