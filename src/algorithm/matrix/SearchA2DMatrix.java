package algorithm.matrix;

public class SearchA2DMatrix {

	public static boolean searchMatrix(int[][] matrix, int target) {
		boolean isPresent = false;
		if (matrix != null && matrix.length > 0 && matrix[0] != null && matrix[0].length > 0) {
			int[] targetRow = null;
			for (int[] row : matrix) {
				if (target <= row[row.length - 1]) {
					targetRow = row;
					break;
				}
			}
			if (targetRow != null) {
				int front = 0;
				int back = targetRow.length - 1;
				while (front <= back) {
					int mid = (front + back) / 2;
					if (targetRow[mid] == target) {
						isPresent = true;
						break;
					} else if (target < targetRow[mid]) {
						back = mid - 1;
					} else {
						front = mid + 1;
					}
				}
			}
		}
		return isPresent;
	}

	public static void main(String[] args) {
		int[][] matrix = { { 1, 3, 5, 7 }, { 10, 11, 16, 20 }, { 23, 30, 34, 50 } };
		int target = 73;
		boolean isPresent = searchMatrix(matrix, target);
		System.out.println(isPresent);
	}

}
