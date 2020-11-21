package algorithm.matrix;

import java.util.Arrays;
import java.util.List;

public class LeftmostColumnWithOne {

	interface BinaryMatrix {
		public int get(int x, int y);

		public List<Integer> dimensions();
	};

	private static class Matrix implements BinaryMatrix {

		private int[][] matrix = { { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 1, 1 }, { 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0 }, { 0, 0, 1, 1, 1, 1 } };

		@Override
		public int get(int x, int y) {
			return matrix[x][y];
		}

		@Override
		public List<Integer> dimensions() {
			return Arrays.asList(matrix.length, matrix[0].length);
		}

	}

	public static int leftMostColumnWithOneV1(BinaryMatrix binaryMatrix) {
		List<Integer> dimension = binaryMatrix.dimensions();
		int MAX_ROW = dimension.get(0);
		int MAX_COL = dimension.get(1);
		int leftMostColIdx = Integer.MAX_VALUE;
		for (int row = 0; row < MAX_ROW; ++row) {
			int left = 0;
			int right = MAX_COL - 1;
			int maxLeft = -1;
			while (left <= right) {
				int mid = (left + right) / 2;
				int midValue = binaryMatrix.get(row, mid);
				if (midValue == 1) {
					maxLeft = mid;
					right = mid - 1;
				} else if (midValue == 0) {
					left = mid + 1;
				}
			}
			if (maxLeft != -1) {
				leftMostColIdx = Math.min(leftMostColIdx, maxLeft);
			}
		}
		return leftMostColIdx == Integer.MAX_VALUE ? -1 : leftMostColIdx;
	}

	public static int leftMostColumnWithOneV2(BinaryMatrix binaryMatrix) {
		List<Integer> dimension = binaryMatrix.dimensions();
		int MAX_ROW = dimension.get(0);
		int MAX_COL = dimension.get(1);
		int leftMostColIdx = -1;
		int row = 0;
		int col = MAX_COL - 1;
		while (row <= MAX_ROW - 1 && col >= 0) {
			int val = binaryMatrix.get(row, col);
			if (val == 0) {
				++row;
			} else if (val == 1) {
				leftMostColIdx = col;
				--col;
			}
		}
		return leftMostColIdx;
	}

	public static void main(String[] args) {
		BinaryMatrix binaryMatrix = new Matrix();
		int leftMostColIdx = leftMostColumnWithOneV2(binaryMatrix);
		System.out.println(leftMostColIdx);
	}

}
