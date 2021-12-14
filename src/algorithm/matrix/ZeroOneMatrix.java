package algorithm.matrix;

import java.util.ArrayDeque;
import java.util.Queue;

public class ZeroOneMatrix {

	public int[][] updateMatrix(int[][] matrix) {
		int[][] dist = new int[matrix.length][matrix[0].length];
		int[][] dirs = { { 0, 1 }, { 0, -1 }, { -1, 0 }, { 1, 0 } };
		Queue<int[]> queue = new ArrayDeque<>();
		for (int row = 0; row <= matrix.length - 1; ++row) {
			for (int col = 0; col <= matrix[row].length - 1; ++col) {
				if (matrix[row][col] == 0) {
					dist[row][col] = 0;
					queue.add(new int[] { row, col });
				} else {
					dist[row][col] = Integer.MAX_VALUE;
				}
			}
		}
		while (!queue.isEmpty()) {
			int[] cell = queue.poll();
			for (int[] dir : dirs) {
				int nextRow = cell[0] + dir[0];
				int nextCol = cell[1] + dir[1];
				if (nextRow >= 0 && nextRow <= matrix.length - 1 && nextCol >= 0 && nextCol <= matrix[0].length - 1
						&& dist[cell[0]][cell[1]] + 1 < dist[nextRow][nextCol]) {
					dist[nextRow][nextCol] = dist[cell[0]][cell[1]] + 1;
					queue.add(new int[] { nextRow, nextCol });
				}
			}
		}
		return dist;
	}
}
