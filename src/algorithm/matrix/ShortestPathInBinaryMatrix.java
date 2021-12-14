package algorithm.matrix;

import java.util.ArrayDeque;
import java.util.Queue;

public class ShortestPathInBinaryMatrix {

	public static int shortestPathBinaryMatrix(int[][] grid) {
		int shortestPathLength = -1;
		int[][] dirs = { { 0, 1 }, { 0, -1 }, { -1, 0 }, { 1, 0 }, { 1, 1 }, { 1, -1 }, { -1, 1 }, { -1, -1 } };
		int maxRow = grid.length - 1;
		int maxCol = grid[0].length - 1;
		boolean found = false;
		Queue<int[]> queue = new ArrayDeque<>();
		if (grid.length == 1 && grid[0].length == 1 && grid[0][0] == 0) {
			shortestPathLength = 1;
			found = true;
		} else {
			if (grid[0][0] == 0) {
				queue.add(new int[] { 0, 0, 1 });
				grid[0][0] = 1;
			}
			outer: while (!queue.isEmpty()) {
				int[] cell = queue.poll();
				for (int dir = 0; dir <= dirs.length - 1; ++dir) {
					int nextRow = cell[0] + dirs[dir][0];
					int nextCol = cell[1] + dirs[dir][1];
					if (nextRow == maxRow && nextCol == maxCol && grid[nextRow][nextCol] == 0) {
						found = true;
						shortestPathLength = cell[2] + 1;
						queue.clear();
						break outer;
					} else if (nextRow >= 0 && nextRow <= maxRow && nextCol >= 0 && nextCol <= maxCol
							&& grid[nextRow][nextCol] == 0) {
						queue.add(new int[] { nextRow, nextCol, cell[2] + 1 });
						grid[nextRow][nextCol] = 1;
					}
				}
			}
		}
		return found ? shortestPathLength : -1;
	}

	public static void main(String[] args) {
		int[][] grid = { { 0, 1 }, { 1, 0 } };
		System.out.println(shortestPathBinaryMatrix(grid));
	}
}
