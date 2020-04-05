package algorithm.eight;

public class SearchA2DMatrix2 {
	public static boolean searchMatrix(int[][] matrix, int target) {
		boolean isPresent = false;
		if (matrix != null && matrix.length > 0 && matrix[0] != null && matrix[0].length > 0) {
			int row = 0;
			int col = matrix[0].length - 1;
			while (row <= matrix.length - 1 && col >= 0) {
				if (target == matrix[row][col]) {
					isPresent = true;
					break;
				} else if (target < matrix[row][col]) {
					--col;
				} else {
					++row;
				}
			}
		}
		return isPresent;
	}

	public static void main(String[] args) {
		int[][] matrix = { { 1, 4, 7, 11, 15 }, { 2, 5, 8, 12, 19 }, { 3, 6, 9, 16, 22 }, { 10, 13, 14, 17, 24 },
				{ 18, 21, 23, 26, 30 } };
		int target = 1;
		boolean isPresent = searchMatrix(matrix, target);
		System.out.println(isPresent);
	}

}
