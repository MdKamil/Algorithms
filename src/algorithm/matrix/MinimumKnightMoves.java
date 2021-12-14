package algorithm.matrix;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class MinimumKnightMoves {

	// BFS from source
	public int minKnightMoves(int x, int y) {
		int minMoves = 0;
		if (x == 0 && y == 0) {
			minMoves = 0;
		} else {
			Map<Integer, Map<Integer, Integer>> rowMap = new HashMap<>();
			Map<Integer, Integer> cols = new HashMap<>();
			cols.put(0, 0);
			rowMap.put(0, cols);
			Queue<int[]> queue = new ArrayDeque<>();
			queue.add(new int[] { 0, 0, 0 });
			int[][] dirs = { { -1, 2 }, { 1, 2 }, { -2, 1 }, { 2, 1 }, { -1, -2 }, { 1, -2 }, { -2, -1 }, { 2, -1 } };
			outer: while (true) {
				int[] cell = queue.poll();
				for (int dir = 0; dir <= dirs.length - 1; ++dir) {
					int nextRow = cell[0] + dirs[dir][0];
					int nextCol = cell[1] + dirs[dir][1];
					if (nextRow == x && nextCol == y) {
						minMoves = cell[2] + 1;
						break outer;
					} else {
						Map<Integer, Integer> colMap = rowMap.get(nextRow);
						if (colMap == null) {
							colMap = new HashMap<>();
							colMap.put(nextCol, cell[2] + 1);
							rowMap.put(nextRow, colMap);
							queue.add(new int[] { nextRow, nextCol, cell[2] + 1 });
						} else {
							Integer dist = colMap.get(nextCol);
							if (dist == null) {
								colMap.put(nextCol, cell[2] + 1);
								queue.add(new int[] { nextRow, nextCol, cell[2] + 1 });
							}
						}
					}
				}
			}
		}
		return minMoves;
	}

	// BFS from source and End
	public static int minKnightMovesV2(int x, int y) {
		int minMoves = 0;
		if (x == 0 && y == 0) {
			minMoves = 0;
		} else {
			Map<Integer, Map<Integer, Integer>> rowMap = new HashMap<>();
			Map<Integer, Integer> cols = new HashMap<>();
			if (x == 0) {
				cols.put(0, 1);
				cols.put(y, -1);
				rowMap.put(0, cols);
			} else {
				cols.put(0, 1);
				rowMap.put(0, cols);

				cols = new HashMap<>();
				cols.put(y, -1);
				rowMap.put(x, cols);
			}
			Queue<int[]> queue = new ArrayDeque<>();
			queue.add(new int[] { 0, 0, 1 });
			queue.add(new int[] { x, y, -1 });
			int[][] dirs = { { -1, 2 }, { 1, 2 }, { -2, 1 }, { 2, 1 }, { -1, -2 }, { 1, -2 }, { -2, -1 }, { 2, -1 } };
			outer: while (true) {
				int[] cell = queue.poll();
				for (int dir = 0; dir <= dirs.length - 1; ++dir) {
					int nextRow = cell[0] + dirs[dir][0];
					int nextCol = cell[1] + dirs[dir][1];
					Map<Integer, Integer> colMap = rowMap.get(nextRow);
					if (colMap == null) {
						colMap = new HashMap<>();
						colMap.put(nextCol, cell[2] > 0 ? cell[2] + 1 : cell[2] - 1);
						rowMap.put(nextRow, colMap);
						queue.add(new int[] { nextRow, nextCol, cell[2] > 0 ? cell[2] + 1 : cell[2] - 1 });
					} else {
						Integer dist = colMap.get(nextCol);
						if (dist == null) {
							colMap.put(nextCol, cell[2] > 0 ? cell[2] + 1 : cell[2] - 1);
							queue.add(new int[] { nextRow, nextCol, cell[2] > 0 ? cell[2] + 1 : cell[2] - 1 });
						} else {
							if ((cell[2] > 0 && dist < 0) || (cell[2] < 0 && dist > 0)) {
								minMoves = (Math.abs(cell[2]) + 1 + Math.abs(dist)) - 2;
								break outer;
							}
						}
					}
				}
			}
		}
		return minMoves;
	}

	public static void main(String[] args) {
		int x = 2;
		int y = 1;
		System.out.println(minKnightMovesV2(x, y));
	}

}
