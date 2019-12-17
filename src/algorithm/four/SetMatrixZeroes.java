package algorithm.four;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SetMatrixZeroes {

	public static void setZeroes(int[][] matrix) {
		if (matrix != null && matrix.length > 0 && matrix[0].length > 0) {
			Set<Integer> colList = new HashSet<>();
			for (int row = 0; row <= matrix.length - 1; ++row) {
				boolean zeroPresent = false;
				for (int col = 0; col <= matrix[row].length - 1; ++col) {
					if (matrix[row][col] == 0) {
						zeroPresent = true;
						colList.add(col);
					}
				}
				if (zeroPresent) {
					for (int col = 0; col <= matrix[row].length - 1; ++col) {
						matrix[row][col] = 0;
					}
				}
			}
			for (int col : colList) {
				for (int row = 0; row <= matrix.length - 1; ++row) {
					matrix[row][col] = 0;
				}
			}
		}
	}

	public static void main(String[] args) {
		int[][] matrix = { { 1, 1, 1 }, { 1, 0, 1 }, { 1, 1, 1 } };
		for (int[] row : matrix) {
			System.out.println(Arrays.toString(row));
		}
		System.out.println();
		setZeroes(matrix);
		for (int[] row : matrix) {
			System.out.println(Arrays.toString(row));
		}
	}

}
