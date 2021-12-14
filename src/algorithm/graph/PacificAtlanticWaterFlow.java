package algorithm.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

public class PacificAtlanticWaterFlow {

	public static List<List<Integer>> pacificAtlantic(int[][] heights) {
		List<List<Integer>> list = new ArrayList<>();
		int MAX_ROW = heights.length - 1;
		int MAX_COL = heights[0].length - 1;
		Queue<int[]> queue = new ArrayDeque<>();
		int[][] island = new int[MAX_ROW + 1][MAX_COL + 1];
		int[][] dirs = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
		for (int row = 0; row <= MAX_ROW; ++row) {
			fromPacific(heights, island, queue, MAX_ROW, MAX_COL, dirs, row, 0);
		}
		for (int col = 0; col <= MAX_COL; ++col) {
			fromPacific(heights, island, queue, MAX_ROW, MAX_COL, dirs, 0, col);
		}

		for (int row = 0; row <= MAX_ROW; ++row) {
			fromAtlantic(heights, island, queue, MAX_ROW, MAX_COL, dirs, row, MAX_COL, list);
		}
		for (int col = 0; col <= MAX_COL; ++col) {
			fromAtlantic(heights, island, queue, MAX_ROW, MAX_COL, dirs, MAX_ROW, col, list);
		}
		return list;
	}

	private static void fromPacific(int[][] heights, int[][] island, Queue<int[]> queue, int MAX_ROW, int MAX_COL,
			int[][] dirs, int fromRow, int fromCol) {
		if (island[fromRow][fromCol] == 0) {
			queue.add(new int[] { fromRow, fromCol });
			island[fromRow][fromCol] = 1;
			while (!queue.isEmpty()) {
				int[] cell = queue.poll();
				for (int dir = 0; dir <= dirs.length - 1; ++dir) {
					int nextRow = cell[0] + dirs[dir][0];
					int nextCol = cell[1] + dirs[dir][1];
					if (nextRow >= 0 && nextRow <= MAX_ROW && nextCol >= 0 && nextCol <= MAX_COL
							&& island[nextRow][nextCol] != 1 && island[nextRow][nextCol] != 3) {
						if (heights[cell[0]][cell[1]] <= heights[nextRow][nextCol]) {
							island[nextRow][nextCol] = 1;
							queue.add(new int[] { nextRow, nextCol });
						}
					}
				}
			}
		}
	}

	private static void fromAtlantic(int[][] heights, int[][] island, Queue<int[]> queue, int MAX_ROW, int MAX_COL,
			int[][] dirs, int fromRow, int fromCol, List<List<Integer>> list) {
		if (island[fromRow][fromCol] == 1 || island[fromRow][fromCol] == 0) {
			queue.add(new int[] { fromRow, fromCol });
			island[fromRow][fromCol] = (island[fromRow][fromCol] == 1 ? 3 : 2);
			if (island[fromRow][fromCol] == 3) {
				list.add(Arrays.asList(fromRow, fromCol));
			}
			while (!queue.isEmpty()) {
				int[] cell = queue.poll();
				for (int dir = 0; dir <= dirs.length - 1; ++dir) {
					int nextRow = cell[0] + dirs[dir][0];
					int nextCol = cell[1] + dirs[dir][1];
					if (nextRow >= 0 && nextRow <= MAX_ROW && nextCol >= 0 && nextCol <= MAX_COL
							&& island[nextRow][nextCol] != 2 && island[nextRow][nextCol] != 3) {
						if (heights[cell[0]][cell[1]] <= heights[nextRow][nextCol]) {
							if (island[nextRow][nextCol] == 1) {
								island[nextRow][nextCol] = 3;
								list.add(Arrays.asList(nextRow, nextCol));
							} else {
								island[nextRow][nextCol] = 2;
							}
							queue.add(new int[] { nextRow, nextCol });
						}
					}
				}
			}
		}
	}

	public static void main(String[] args) {
//		int[][] heights = { { 1, 2, 2, 3, 5 }, { 3, 2, 3, 4, 4 }, { 2, 4, 5, 3, 1 }, { 6, 7, 1, 4, 5 },
//				{ 5, 1, 1, 2, 4 } };
		int[][] heights = { { 1 }, { 1 }, { 1 }, { 1 } };
		List<List<Integer>> gridList = pacificAtlantic(heights);
		for (List<Integer> list : gridList) {
			System.out.println(list);
		}
	}

}
