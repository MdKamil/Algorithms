package algorithm.matrix;

import java.util.ArrayDeque;
import java.util.Queue;

public class ShortestBridge {

	static int[][] dirs = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };

	public static int shortestBridge(int[][] A) {
		int flips = 0;
		int islandNo = 2;
		Queue<int[]> queue = new ArrayDeque<>();
		for (int row = 0; row <= A.length - 1; ++row) {
			for (int col = 0; col <= A[row].length - 1; ++col) {
				if (A[row][col] == 1) {
					A[row][col] = islandNo;
					dfs(A, row, col, queue);
					++islandNo;
				}
			}
		}
		outer: while (!queue.isEmpty()) {
			int size = queue.size();
			for (int count = 1; count <= size; ++count) {
				int[] cell = queue.poll();
				for (int[] dir : dirs) {
					int nextRow = cell[0] + dir[0];
					int nextCol = cell[1] + dir[1];
					if (nextRow >= 0 && nextRow <= A.length - 1 && nextCol >= 0 && nextCol <= A[0].length - 1) {
						if (A[nextRow][nextCol] == 3) {
							break outer;
						} else if (A[nextRow][nextCol] == 0) {
							A[nextRow][nextCol] = 2;
							queue.add(new int[] { nextRow, nextCol });
						}
					}
				}
			}
			++flips;
		}
		return flips;
	}

	private static void dfs(int[][] A, int row, int col, Queue<int[]> queue) {
		boolean isNextToIsland = false;
		for (int[] dir : dirs) {
			int nextRow = row + dir[0];
			int nextCol = col + dir[1];
			if (nextRow >= 0 && nextRow <= A.length - 1 && nextCol >= 0 && nextCol <= A[0].length - 1) {
				if (A[nextRow][nextCol] == 1) {
					A[nextRow][nextCol] = A[row][col];
					dfs(A, nextRow, nextCol, queue);
				} else if (A[nextRow][nextCol] == 0) {
					isNextToIsland = true;
				}
			}
		}
		if (isNextToIsland && A[row][col] == 2) {
			queue.add(new int[] { row, col });
		}
	}

	public static void main(String[] args) {
		int[][] A = { { 1, 1, 1, 0, 0, 0 }, { 1, 1, 0, 0, 0, 0 }, { 1, 0, 0, 0, 0, 0 }, { 1, 0, 0, 0, 0, 1 },
				{ 1, 0, 0, 0, 1, 1 } };
		System.out.println(shortestBridge(A));
	}

}
