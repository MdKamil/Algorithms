package algorithm.six;

public class MaximalRectangle3 {
	public static int maximalRectangle(char[][] matrix) {
		int maxRectangleArea = 0;
		if (matrix != null && matrix.length > 0 && matrix[0] != null && matrix[0].length > 0) {
			int MAX_ROW = matrix.length - 1;
			int MAX_COL = matrix[0].length - 1;
			boolean[][] visited = new boolean[MAX_ROW + 1][MAX_COL + 1];
			visited[0][0] = true;
			for (int row = 0; row <= matrix.length - 1; ++row) {
				for (int col = 0; col <= matrix[row].length - 1; ++col) {
					if (visited[row][col]) {
						jump: if (matrix[row][col] == '1') {
							int currMaxCol = col;
							int currMaxRow = row;
							while (currMaxRow <= MAX_ROW || currMaxCol <= MAX_COL) {
								if (currMaxCol <= MAX_COL) {
									for (int rowIter = row; rowIter <= (currMaxRow > MAX_ROW ? MAX_ROW
											: currMaxRow); ++rowIter) {
										if (matrix[rowIter][currMaxCol] != '1') {
											int area = 0;
											area = traverseRight(row, rowIter, col, currMaxCol, MAX_COL, matrix,
													visited, MAX_ROW, MAX_COL);
											maxRectangleArea = Math.max(maxRectangleArea, area);
											area = traverseDown(row, currMaxRow, MAX_ROW, col, currMaxCol, matrix,
													visited, MAX_ROW, MAX_COL);
											maxRectangleArea = Math.max(maxRectangleArea, area);
											if ((row <= MAX_ROW && currMaxCol + 1 <= MAX_COL)) {
												visited[row][currMaxCol + 1] = true;
											}
											if ((rowIter + 1 <= MAX_ROW && col <= MAX_COL)) {
												visited[rowIter + 1][col] = true;
											}
											break jump;
										}
									}
								}
								if (currMaxRow <= MAX_ROW) {
									for (int colIter = col; colIter <= (currMaxCol > MAX_COL ? MAX_COL
											: currMaxCol); ++colIter) {
										if (matrix[currMaxRow][colIter] != '1') {
											int area = 0;
											area = traverseRight(row, currMaxRow, col, currMaxCol, MAX_COL, matrix,
													visited, MAX_ROW, MAX_COL);
											maxRectangleArea = Math.max(maxRectangleArea, area);
											area = traverseDown(row, currMaxRow, MAX_ROW, col, colIter, matrix, visited,
													MAX_ROW, MAX_COL);
											maxRectangleArea = Math.max(maxRectangleArea, area);
											if ((row <= MAX_ROW && colIter + 1 <= MAX_COL)) {
												visited[row][colIter + 1] = true;
											}
											if ((currMaxRow + 1 <= MAX_ROW && col <= MAX_COL)) {
												visited[currMaxRow + 1][col] = true;
											}
											break jump;
										}
									}
								}
								int area = calculateRectangleArea(row,
										(currMaxRow > MAX_ROW ? MAX_ROW : currMaxRow) + 1, col,
										(currMaxCol > MAX_COL ? MAX_COL : currMaxCol) + 1);
								maxRectangleArea = Math.max(maxRectangleArea, area);
								if (currMaxRow <= MAX_ROW) {
									++currMaxRow;
								}
								if (currMaxCol <= MAX_COL) {
									++currMaxCol;
								}
							}
						} else {
							if (row <= MAX_ROW && col + 1 <= MAX_COL) {
								visited[row][col + 1] = true;
							}
							if (row + 1 <= MAX_ROW && col <= MAX_COL) {
								visited[row + 1][col] = true;
							}
						}
					}
				}
			}
		}
		return maxRectangleArea;
	}

	private static int calculateRectangleArea(int fromRow, int toRow, int fromCol, int toCol) {
		return (toRow - fromRow) * (toCol - fromCol);
	}

	private static int traverseRight(int fromRow, int maxRow, int fromCol, int currCol, int maxCol, char[][] matrix,
			boolean[][] visited, int MAX_ROW, int MAX_COL) {
		int maxRectArea = 0;
		while (currCol <= maxCol) {
			int rowIdx = fromRow;
			for (; rowIdx < maxRow; ++rowIdx) {
				if (matrix[rowIdx][currCol] != '1') {
					if (fromRow <= MAX_ROW && currCol + 1 <= MAX_COL) {
						visited[fromRow][currCol + 1] = true;
					}
					if (rowIdx + 1 <= MAX_ROW && fromCol <= MAX_COL) {
						visited[rowIdx + 1][fromCol] = true;
					}
					maxRow = rowIdx;
					break;
				}
			}
			int area = calculateRectangleArea(fromRow, maxRow, fromCol, currCol + 1);
			maxRectArea = Math.max(maxRectArea, area);
			++currCol;
		}
		return maxRectArea;
	}

	private static int traverseDown(int fromRow, int currRow, int maxRow, int fromCol, int maxCol, char[][] matrix,
			boolean[][] visited, int MAX_ROW, int MAX_COL) {
		int maxRectArea = 0;
		while (currRow <= maxRow) {
			int colIdx = fromCol;
			for (; colIdx < maxCol; ++colIdx) {
				if (matrix[currRow][colIdx] != '1') {
					maxCol = colIdx;
					if (fromRow <= MAX_ROW && colIdx + 1 <= MAX_COL) {
						visited[fromRow][colIdx + 1] = true;
					}
					if (currRow + 1 <= MAX_ROW && fromCol <= MAX_COL) {
						visited[currRow + 1][fromCol] = true;
					}
					break;
				}
			}
			int area = calculateRectangleArea(fromRow, currRow + 1, fromCol, maxCol);
			maxRectArea = Math.max(maxRectArea, area);
			++currRow;
		}
		return maxRectArea;
	}

	public static void main(String[] args) {
		char[][] matrix = { { '1', '1', '1', '1', '1' }, { '1', '1', '1', '1', '1' }, { '1', '1', '1', '1', '1' },
				{ '1', '1', '1', '0', '1' }, { '1', '1', '1', '1', '1' }, { '0', '1', '1', '1', '1' },
				{ '0', '1', '1', '1', '1' }, { '1', '1', '1', '1', '1' }, { '1', '1', '1', '1', '1' } };
		int maxRectArea = maximalRectangle(matrix);
		System.out.println(maxRectArea);
	}

	private static char[][] inputs() {
		char[][] matrix = { { '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '1', '1', '1', '1' },
				{ '1', '0', '1', '1', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1' },
				{ '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1' },
				{ '0', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '0', '1', '1', '1' },
				{ '1', '0', '0', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1' },
				{ '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1' },
				{ '1', '1', '1', '0', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1' },
				{ '1', '1', '1', '1', '0', '0', '0', '1', '1', '1', '1', '1', '0', '1', '0' },
				{ '1', '0', '1', '1', '0', '0', '0', '1', '1', '1', '1', '0', '1', '0', '1' },
				{ '1', '0', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '0', '1', '1' },
				{ '1', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1' },
				{ '1', '1', '1', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1' },
				{ '1', '1', '1', '0', '0', '0', '1', '0', '1', '1', '1', '1', '1', '1', '1' },
				{ '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '1', '1', '1', '1' },
				{ '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '1', '0', '1' } };
		return matrix;
	}

}
