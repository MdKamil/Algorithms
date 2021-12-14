package algorithm.matrix;

public class LongestLineOfConsecutiveOneInMatrix {
	public static int longestLine(int[][] mat) {
		int longestLine = 0;
		int[][] horizontal = new int[mat.length][mat[0].length];
		int[][] vertical = new int[mat.length][mat[0].length];
		int[][] diagonal = new int[mat.length][mat[0].length];
		int[][] antiDiagonal = new int[mat.length][mat[0].length];
		for (int row = 0; row <= mat.length - 1; ++row) {
			for (int col = 0; col <= mat[row].length - 1; ++col) {
				if (mat[row][col] == 1) {
					if (col - 1 >= 0 && mat[row][col - 1] == 1) {
						horizontal[row][col] = horizontal[row][col - 1] + 1;
					} else {
						horizontal[row][col] = mat[row][col];
					}
					longestLine = Math.max(longestLine, horizontal[row][col]);

					if (row - 1 >= 0 && mat[row - 1][col] == 1) {
						vertical[row][col] = vertical[row - 1][col] + 1;
					} else {
						vertical[row][col] = mat[row][col];
					}
					longestLine = Math.max(longestLine, vertical[row][col]);

					if ((row - 1 >= 0 && col - 1 >= 0) && mat[row - 1][col - 1] == 1) {
						diagonal[row][col] = diagonal[row - 1][col - 1] + 1;
					} else {
						diagonal[row][col] = mat[row][col];
					}
					longestLine = Math.max(longestLine, diagonal[row][col]);

					if ((row - 1 >= 0 && col + 1 <= mat[row - 1].length - 1) && mat[row - 1][col + 1] == 1) {
						antiDiagonal[row][col] = antiDiagonal[row - 1][col + 1] + 1;
					} else {
						antiDiagonal[row][col] = mat[row][col];
					}
					longestLine = Math.max(longestLine, antiDiagonal[row][col]);
				}
			}
		}
		return longestLine;
	}

	public static void main(String[] args) {
		int[][] mat = { { 0, 1, 1, 1 }, { 0, 1, 1, 0 }, { 0, 1, 0, 0 } };
		System.out.println(longestLine(mat));
	}

}
