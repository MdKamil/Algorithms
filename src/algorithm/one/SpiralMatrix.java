package algorithm.one;

public class SpiralMatrix {

	public static void printSpiral(int[][] matrix) {
		if (matrix == null || matrix.length == 0) {
			return;
		}
		int r = matrix.length;
		int c = matrix[0].length;
		int min = Math.min(r, c);
		int maxIteration = (min % 2 == 0) ? min / 2 : (min / 2) + 1;
		int rStart = 0;
		int rEnd = r - 1;
		int cStart = 0;
		int cEnd = c - 1;
		for (int iter = 1; iter <= maxIteration; ++iter) {
			for (int i = cStart; i <= cEnd; ++i) {
				System.out.print(matrix[rStart][i] + " ");
			}
			for (int i = rStart + 1; i <= rEnd; ++i) {
				System.out.print(matrix[i][cEnd] + " ");
			}
			for (int i = cEnd - 1; i > cStart && (rStart < rEnd); --i) {
				System.out.print(matrix[rEnd][i] + " ");
			}
			for (int i = rEnd; i > rStart && (cStart < cEnd); --i) {
				System.out.print(matrix[i][cStart] + " ");
			}
			System.out.println();
			rStart += 1;
			rEnd -= 1;
			cStart += 1;
			cEnd -= 1;
		}
	}

	public static void main(String[] args) {
		int[][] matrix = getMatrix1();
		printSpiral(matrix);
	}

	private static int[][] getMatrix1() {
		int[][] matrix = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 }, { 13, 14, 15, 16 } };
		return matrix;
	}

	private static int[][] getMatrix2() {
		int[][] matrix = { { 1, 2, 3, 4, 5, 6 }, { 7, 8, 9, 10, 11, 12 }, { 13, 14, 15, 16, 17, 18 } };
		return matrix;
	}

	private static int[][] getMatrix3() {
		int[][] matrix = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 }, { 10, 11, 12 }, { 13, 14, 15 } };
		return matrix;
	}

}
