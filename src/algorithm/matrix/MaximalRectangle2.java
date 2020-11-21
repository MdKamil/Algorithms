package algorithm.matrix;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Queue;

public class MaximalRectangle2 {
	private static class Location {
		int row;
		int col;

		public Location(int row, int col) {
			this.row = row;
			this.col = col;
			// System.out.println(toString());
		}

		@Override
		public String toString() {
			return "Row: " + row + " Col: " + col;
		}
	}

	public static int maximalRectangleV2(char[][] matrix) {
		int maxRectangleArea = 0;
		List<Location> list = new ArrayList<>();
		if (matrix != null && matrix.length > 0 && matrix[0] != null && matrix[0].length > 0) {
			int MAX_ROW = matrix.length - 1;
			int MAX_COL = matrix[0].length - 1;
			boolean[][] visited = new boolean[MAX_ROW + 1][MAX_COL + 1];
			Queue<Location> queue = new ArrayDeque<>();
			queue.add(new Location(0, 0));
			visited[0][0] = true;
			boolean flag = false;
			while (!queue.isEmpty()) {
				flag = false;
				Location currLocation = queue.poll();
				list.add(currLocation);
				int row = currLocation.row;
				int col = currLocation.col;
				if (row <= MAX_ROW && col <= MAX_COL) {
					if (matrix[row][col] == '1') {
						int currMaxCol = col;
						int currMaxRow = row;
						while (currMaxRow <= MAX_ROW || currMaxCol <= MAX_COL) {
							if (currMaxCol <= MAX_COL) {
								for (int rowIter = row; rowIter <= (currMaxRow > MAX_ROW ? MAX_ROW
										: currMaxRow); ++rowIter) {
									if (matrix[rowIter][currMaxCol] != '1') {
										int area = 0;
										area = traverseRight2(row, rowIter, col, currMaxCol, MAX_COL, matrix, queue,
												visited, MAX_ROW, MAX_COL);
										maxRectangleArea = Math.max(maxRectangleArea, area);
										area = traverseDown2(row, currMaxRow, MAX_ROW, col, currMaxCol, matrix, queue,
												visited, MAX_ROW, MAX_COL);
										maxRectangleArea = Math.max(maxRectangleArea, area);
										if ((row <= MAX_ROW && currMaxCol + 1 <= MAX_COL)
												&& !visited[row][currMaxCol + 1]) {
											queue.add(new Location(row, currMaxCol + 1));
											visited[row][currMaxCol + 1] = true;
										}
										if ((rowIter + 1 <= MAX_ROW && col <= MAX_COL) && !visited[rowIter + 1][col]) {
											queue.add(new Location(rowIter + 1, col));
											visited[rowIter + 1][col] = true;
										}
										flag = true;
										break;
									}
								}
							}
							if (flag) {
								break;
							}
							if (currMaxRow <= MAX_ROW) {
								for (int colIter = col; colIter <= (currMaxCol > MAX_COL ? MAX_COL
										: currMaxCol); ++colIter) {
									if (matrix[currMaxRow][colIter] != '1') {
										int area = 0;
										area = traverseRight2(row, currMaxRow, col, currMaxCol, MAX_COL, matrix, queue,
												visited, MAX_ROW, MAX_COL);
										maxRectangleArea = Math.max(maxRectangleArea, area);
										area = traverseDown2(row, currMaxRow, MAX_ROW, col, colIter, matrix, queue,
												visited, MAX_ROW, MAX_COL);
										maxRectangleArea = Math.max(maxRectangleArea, area);
										if ((row <= MAX_ROW && colIter + 1 <= MAX_COL) && !visited[row][colIter + 1]) {
											queue.add(new Location(row, colIter + 1));
											visited[row][colIter + 1] = true;
										}
										if ((currMaxRow + 1 <= MAX_ROW && col <= MAX_COL)
												&& !visited[currMaxRow + 1][col]) {
											queue.add(new Location(currMaxRow + 1, col));
											visited[currMaxRow + 1][col] = true;
										}
										flag = true;
										break;
									}
								}
							}
							if (flag) {
								break;
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
					} else {
						if (row <= MAX_ROW && col + 1 <= MAX_COL && !visited[row][col + 1]) {
							queue.add(new Location(row, col + 1));
							visited[row][col + 1] = true;
						}
						if (row + 1 <= MAX_ROW && col <= MAX_COL && !visited[row + 1][col]) {
							queue.add(new Location(row + 1, col));
							visited[row + 1][col] = true;
						}
					}
				}
			}
		}
		Comparator<Location> c = new Comparator<Location>() {
			@Override
			public int compare(Location o1, Location o2) {
				int val = 0;
				val = Integer.valueOf(o1.row).compareTo(Integer.valueOf(o2.row));
				if (val == 0) {
					val = Integer.valueOf(o1.col).compareTo(Integer.valueOf(o2.col));
				}
				return val;
			}
		};
		// list.sort(c);
		for (Location loc : list) {
			System.out.println("Row:" + loc.row + " Col:" + loc.col);
		}
		return maxRectangleArea;
	}

	private static int calculateRectangleArea(int fromRow, int toRow, int fromCol, int toCol) {
		return (toRow - fromRow) * (toCol - fromCol);
	}

	private static int traverseRight2(int fromRow, int maxRow, int fromCol, int currCol, int maxCol, char[][] matrix,
			Queue<Location> queue, boolean[][] visited, int MAX_ROW, int MAX_COL) {
		int maxRectArea = 0;
		while (currCol <= maxCol) {
			int rowIdx = fromRow;
			for (; rowIdx < maxRow; ++rowIdx) {
				if (matrix[rowIdx][currCol] != '1') {
					if (fromRow <= MAX_ROW && currCol + 1 <= MAX_COL && !visited[fromRow][currCol + 1]) {
						queue.add(new Location(fromRow, currCol + 1));
						visited[fromRow][currCol + 1] = true;
					}
					if (rowIdx + 1 <= MAX_ROW && fromCol <= MAX_COL && !visited[rowIdx + 1][fromCol]) {
						queue.add(new Location(rowIdx + 1, fromCol));
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

	private static int traverseDown2(int fromRow, int currRow, int maxRow, int fromCol, int maxCol, char[][] matrix,
			Queue<Location> queue, boolean[][] visited, int MAX_ROW, int MAX_COL) {
		int maxRectArea = 0;
		while (currRow <= maxRow) {
			int colIdx = fromCol;
			for (; colIdx < maxCol; ++colIdx) {
				if (matrix[currRow][colIdx] != '1') {
					maxCol = colIdx;
					if (fromRow <= MAX_ROW && colIdx + 1 <= MAX_COL && !visited[fromRow][colIdx + 1]) {
						queue.add(new Location(fromRow, colIdx + 1));
						visited[fromRow][colIdx + 1] = true;
					}
					if (currRow + 1 <= MAX_ROW && fromCol <= MAX_COL && !visited[currRow + 1][fromCol]) {
						queue.add(new Location(currRow + 1, fromCol));
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
}
