package algorithm.matrix;

import java.util.ArrayDeque;
import java.util.Deque;

public class MaximalRectangle4 {

	public static int maximalRectangle(char[][] matrix) {
		int maxRectangleArea = 0;
		if (matrix != null && matrix.length > 0 && matrix[0] != null && matrix[0].length > 0) {
			int[] heightAtEachRow = new int[matrix[0].length];
			maxRectangleArea = largestRectangleArea(heightAtEachRow);
			for (int row = 0; row <= matrix.length - 1; ++row) {
				for (int col = 0; col <= matrix[row].length - 1; ++col) {
					if ((int) (matrix[row][col]) - 48 == 0) {
						heightAtEachRow[col] = 0;
					} else {
						heightAtEachRow[col] = heightAtEachRow[col] + ((int) (matrix[row][col])) - 48;
					}
				}
				maxRectangleArea = Math.max(maxRectangleArea, largestRectangleArea(heightAtEachRow));
			}
		}
		return maxRectangleArea;
	}

	private static int largestRectangleArea(int[] heights) {
		int maxArea = Integer.MIN_VALUE;
		Deque<Integer> deque = new ArrayDeque<>();
		for (int idx = 0; idx <= heights.length; ++idx) {
			while (!deque.isEmpty() && ((idx == heights.length) || (heights[idx] < heights[deque.peekLast()]))) {
				int currIdx = deque.pollLast();
				int distRight = (idx - currIdx) - 1;
				int prevIdx = deque.peekLast() == null ? -1 : deque.peekLast();
				int distLeft = (currIdx - prevIdx);
				distLeft = (distLeft == 0 ? distLeft : distLeft - 1);
				int currRectArea = (heights[currIdx] * 1) + heights[currIdx] * (distRight + distLeft);
				maxArea = Math.max(maxArea, currRectArea);
			}
			if (idx <= heights.length - 1) {
				deque.add(idx);
			}
		}
		return maxArea;
	}

	public static void main(String[] args) {
		char[][] matrix = { { '1', '1', '1', '1', '1' }, { '1', '1', '1', '1', '1' }, { '1', '1', '1', '1', '1' },
				{ '1', '1', '1', '0', '1' }, { '1', '1', '1', '1', '1' }, { '0', '1', '1', '1', '1' },
				{ '0', '1', '1', '1', '1' }, { '1', '1', '1', '1', '1' }, { '1', '1', '1', '1', '1' } };
		int maxRectArea = maximalRectangle(inputs());
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
