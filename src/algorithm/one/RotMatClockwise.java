package algorithm.one;

import java.util.Arrays;

public class RotMatClockwise {

	public static void rotate(int[][] matrix) {
		int iteration = matrix.length / 2;
		int rs = 0;
		int re = matrix.length - 1;
		int cs = 0;
		int ce = matrix.length - 1;
		int swapIteration = matrix.length;
		for (int i = 1; i <= iteration; ++i) {
			for (int j = 1; j <= swapIteration - 1; ++j) {
				int temp = matrix[rs][cs + (j - 1)];
				matrix[rs][cs + (j - 1)] = matrix[re - (j - 1)][cs];
				matrix[re - (j - 1)][cs] = matrix[re][ce - (j - 1)];
				matrix[re][ce - (j - 1)] = matrix[rs + (j - 1)][ce];
				matrix[rs + (j - 1)][ce] = temp;
			}
			swapIteration -= 2;
			rs += 1;
			re -= 1;
			cs += 1;
			ce -= 1;
		}
	}

	public static void rotateV2(int[][] matrix) {
		int iteration = matrix.length / 2;
		int rs = 0;
		int re = matrix.length - 1;
		int cs = 0;
		int ce = matrix.length - 1;
		int swapIteration = matrix.length - 1;
		for (int i = 1; i <= iteration; ++i) {
			for (int j = 0; j <= swapIteration - 1; ++j) {
				int temp = matrix[rs][cs + j];
				matrix[rs][cs + j] = matrix[re - j][cs];
				matrix[re - j][cs] = matrix[re][ce - j];
				matrix[re][ce - j] = matrix[rs + j][ce];
				matrix[rs + j][ce] = temp;
			}
			swapIteration -= 2;
			rs += 1;
			re -= 1;
			cs += 1;
			ce -= 1;
		}
	}

	public static void main(String[] args) {
		int[][] matrix = getMatrix3();
		System.out.println("Before Rotation\n");
		print(matrix);
		rotateV2(matrix);
		System.out.println("\nAfter Rotation\n");
		print(matrix);
	}

	private static void print(int[][] matrix) {
		for (int i = 1; i <= matrix.length; ++i) {
			System.out.println(Arrays.toString(matrix[i - 1]));
		}
	}

	private static int[][] getMatrix1() {
		int[][] matrix = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 }, { 13, 14, 15, 16 } };
		return matrix;
	}

	private static int[][] getMatrix2() {
		int[][] matrix = {};
		return matrix;
	}

	private static int[][] getMatrix3() {
		int[][] matrix = { { 1, 2, 3, 4, 5, 6 }, { 7, 8, 9, 10, 11, 12 }, { 13, 14, 15, 16, 17, 18 },
				{ 19, 20, 21, 22, 23, 24 }, { 25, 26, 27, 28, 29, 30 }, { 31, 32, 33, 34, 35, 36 } };
		return matrix;
	}

}
