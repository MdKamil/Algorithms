package algorithm.matrix;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Queue;

public class MaximalRectangle {

	public static int maximalRectangle(char[][] matrix) {
		int maxRectangleArea = 0;
		if (matrix != null && matrix.length > 0 && matrix[0] != null && matrix[0].length > 0) {
			int MAX_ROW = matrix.length - 1;
			int MAX_COL = matrix[0].length - 1;
			for (int row = 0; row <= matrix.length - 1; ++row) {
				for (int col = 0; col <= matrix[row].length - 1; ++col) {
					jump: if (matrix[row][col] == '1') {
						int currMaxCol = col;
						int currMaxRow = row;
						while (currMaxRow <= MAX_ROW || currMaxCol <= MAX_COL) {
							if (currMaxCol <= MAX_COL) {
								for (int rowIter = row; rowIter <= (currMaxRow > MAX_ROW ? MAX_ROW
										: currMaxRow); ++rowIter) {
									if (matrix[rowIter][currMaxCol] != '1') {
										int area = 0;
										area = traverseRight(row, rowIter, col, currMaxCol, MAX_COL, matrix);
										maxRectangleArea = Math.max(maxRectangleArea, area);
										area = traverseDown(row, currMaxRow, MAX_ROW, col, currMaxCol, matrix);
										maxRectangleArea = Math.max(maxRectangleArea, area);
										break jump;
									}
								}
							}
							if (currMaxRow <= MAX_ROW) {
								for (int colIter = col; colIter <= (currMaxCol > MAX_COL ? MAX_COL
										: currMaxCol); ++colIter) {
									if (matrix[currMaxRow][colIter] != '1') {
										int area = 0;
										area = traverseRight(row, currMaxRow, col, currMaxCol, MAX_COL, matrix);
										maxRectangleArea = Math.max(maxRectangleArea, area);
										area = traverseDown(row, currMaxRow, MAX_ROW, col, colIter, matrix);
										maxRectangleArea = Math.max(maxRectangleArea, area);
										break jump;
									}
								}
							}
							int area = calculateRectangleArea(row, (currMaxRow > MAX_ROW ? MAX_ROW : currMaxRow) + 1,
									col, (currMaxCol > MAX_COL ? MAX_COL : currMaxCol) + 1);
							maxRectangleArea = Math.max(maxRectangleArea, area);
							if (currMaxRow <= MAX_ROW) {
								++currMaxRow;
							}
							if (currMaxCol <= MAX_COL) {
								++currMaxCol;
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

	private static int traverseRight(int fromRow, int maxRow, int fromCol, int currCol, int maxCol, char[][] matrix) {
		int maxRectArea = 0;
		while (currCol <= maxCol) {
			int rowIdx = fromRow;
			for (; rowIdx < maxRow; ++rowIdx) {
				if (matrix[rowIdx][currCol] != '1') {
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

	private static int traverseDown(int fromRow, int currRow, int maxRow, int fromCol, int maxCol, char[][] matrix) {
		int maxRectArea = 0;
		while (currRow <= maxRow) {
			int colIdx = fromCol;
			for (; colIdx < maxCol; ++colIdx) {
				if (matrix[currRow][colIdx] != '1') {
					maxCol = colIdx;
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
		int maxRectArea = maximalRectangle(matrix);
		System.out.println(maxRectArea);
	}

	private static void inputs() {
		char[][] matrix = { { '0', '0', '0', '1' }, { '0', '0', '0', '1' }, { '0', '0', '1', '1' },
				{ '0', '0', '0', '1' } };
		char[][] matrix2 = { { '0' } };
		char[][] matrix3 = { { '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '1', '1', '1', '1' },
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
	}

}
