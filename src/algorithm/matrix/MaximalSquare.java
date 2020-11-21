package algorithm.matrix;

public class MaximalSquare {
	public static int maximalSquare(char[][] matrix) {
		int maxSquareArea = 0;
		if (matrix != null && matrix.length > 0 && matrix[0] != null && matrix[0].length > 0) {
			int maxSquareSize = Math.min(matrix.length, matrix[0].length);
			for (int row = 0; row <= matrix.length - 1; ++row) {
				for (int col = 0; col <= matrix[row].length - 1; ++col) {
					from: if (matrix[row][col] == '1') {
						int currMaxCol = col;
						int currMaxRow = row;
						for (int size = 1; size <= maxSquareSize && currMaxRow <= matrix.length - 1
								&& currMaxCol <= matrix[0].length - 1; ++size) {
							for (int rowIter = row; rowIter <= currMaxRow; ++rowIter) {
								if (matrix[rowIter][currMaxCol] != '1') {
									maxSquareArea = Math.max(maxSquareArea, ((size - 1) * (size - 1)));
									col = currMaxCol;
									break from;
								}
							}
							for (int colIter = col; colIter <= currMaxCol; ++colIter) {
								if (matrix[currMaxRow][colIter] != '1') {
									maxSquareArea = Math.max(maxSquareArea, ((size - 1) * (size - 1)));
									col = colIter;
									break from;
								}
							}
							maxSquareArea = Math.max(maxSquareArea, size * size);
							++currMaxRow;
							++currMaxCol;
						}
					}
				}
			}
		}
		return maxSquareArea;
	}

	public static void main(String[] args) {
		char[][] matrix = { { '1', '0', '1', '0', '0' }, { '1', '0', '1', '1', '1' }, { '1', '1', '1', '0', '0' },
				{ '1', '1', '1', '1', '1' }, { '1', '0', '1', '0', '1' }, { '1', '1', '0', '0', '1' },
				{ '0', '1', '1', '1', '1' }, { '0', '1', '1', '1', '1' } };
		int maxSquareArea = maximalSquare(matrix);
		System.out.println(maxSquareArea);
	}

}
