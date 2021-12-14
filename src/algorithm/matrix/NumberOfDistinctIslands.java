package algorithm.matrix;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class NumberOfDistinctIslands {

	public static int numDistinctIslands(int[][] grid) {
		int distinctIslands = 0;
		Queue<int[]> queue = new ArrayDeque<>();
		Set<String> set = new HashSet<>();
		char[] c = { 'r', 'l', 'u', 'd' };
		int[][] dirs = { { 0, 1 }, { 0, -1 }, { -1, 0 }, { 1, 0 } };
		for (int row = 0; row <= grid.length - 1; ++row) {
			for (int col = 0; col <= grid[row].length - 1; ++col) {
				if (grid[row][col] == 1) {
					queue.add(new int[] { row, col });
					StringBuilder builder = new StringBuilder();
					grid[row][col] = 0;
					while (!queue.isEmpty()) {
						int[] cell = queue.poll();
						builder.append(1);
						for (int dir = 0; dir <= dirs.length - 1; ++dir) {
							int nextRow = cell[0] + dirs[dir][0];
							int nextCol = cell[1] + dirs[dir][1];
							if (nextRow >= 0 && nextRow <= grid.length - 1 && nextCol >= 0
									&& nextCol <= grid[0].length - 1 && grid[nextRow][nextCol] == 1) {
								builder.append(c[dir]);
								queue.add(new int[] { nextRow, nextCol });
								grid[nextRow][nextCol] = 0;
							}
						}
					}
					String str = builder.toString();
					if (!set.contains(str)) {
						set.add(str);
						++distinctIslands;
					}
				}
			}
		}
		System.out.println(set);
		return distinctIslands;
	}

	public static void main(String[] args) {
		int[][] grid = { { 1, 1, 0, 0, 0 }, { 1, 1, 0, 0, 0 }, { 0, 0, 0, 1, 1 }, { 0, 0, 0, 1, 1 } };
		System.out.println(numDistinctIslands(grid));
	}

}
