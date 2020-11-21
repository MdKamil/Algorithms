package algorithm.matrix;

import java.util.ArrayDeque;
import java.util.Queue;

public class RottenOranges {

	private static class Cell {
		int row;
		int col;

		public Cell(int row, int col) {
			this.row = row;
			this.col = col;
		}

		@Override
		public String toString() {
			return row + "," + col;
		}
	}

	public static int orangesRotting(int[][] grid) {
		int minTime = -1;
		Queue<Cell> queue = new ArrayDeque<>();
		int ROW_MAX = grid.length - 1;
		int COL_MAX = grid[0].length - 1;
		int[][] minTimeToRot = new int[grid.length][grid[0].length];
		int goodOranges = 0;
		for (int row = 0; row <= grid.length - 1; ++row) {
			for (int col = 0; col <= grid[row].length - 1; ++col) {
				if (grid[row][col] == 2) {
					queue.add(new Cell(row, col));
				} else if (grid[row][col] == 1) {
					++goodOranges;
				}
			}
		}
		if (goodOranges > 0) {
			int goodOrangesRotten = 0;
			while (!queue.isEmpty()) {
				Cell currCell = queue.poll();
				if (currCell.col + 1 <= COL_MAX && grid[currCell.row][currCell.col + 1] == 1
						&& minTimeToRot[currCell.row][currCell.col + 1] == 0) {
					minTimeToRot[currCell.row][currCell.col + 1] = minTimeToRot[currCell.row][currCell.col] + 1;
					++goodOrangesRotten;
					minTime = Math.max(minTime, minTimeToRot[currCell.row][currCell.col + 1]);
					queue.add(new Cell(currCell.row, currCell.col + 1));
				}
				if (currCell.col - 1 >= 0 && grid[currCell.row][currCell.col - 1] == 1
						&& minTimeToRot[currCell.row][currCell.col - 1] == 0) {
					minTimeToRot[currCell.row][currCell.col - 1] = minTimeToRot[currCell.row][currCell.col] + 1;
					++goodOrangesRotten;
					minTime = Math.max(minTime, minTimeToRot[currCell.row][currCell.col - 1]);
					queue.add(new Cell(currCell.row, currCell.col - 1));
				}
				if (currCell.row + 1 <= ROW_MAX && grid[currCell.row + 1][currCell.col] == 1
						&& minTimeToRot[currCell.row + 1][currCell.col] == 0) {
					minTimeToRot[currCell.row + 1][currCell.col] = minTimeToRot[currCell.row][currCell.col] + 1;
					++goodOrangesRotten;
					minTime = Math.max(minTime, minTimeToRot[currCell.row + 1][currCell.col]);
					queue.add(new Cell(currCell.row + 1, currCell.col));
				}
				if (currCell.row - 1 >= 0 && grid[currCell.row - 1][currCell.col] == 1
						&& minTimeToRot[currCell.row - 1][currCell.col] == 0) {
					minTimeToRot[currCell.row - 1][currCell.col] = minTimeToRot[currCell.row][currCell.col] + 1;
					++goodOrangesRotten;
					minTime = Math.max(minTime, minTimeToRot[currCell.row - 1][currCell.col]);
					queue.add(new Cell(currCell.row - 1, currCell.col));
				}
			}
			if (goodOranges != goodOrangesRotten) {
				minTime = -1;
			}
		} else {
			minTime = 0;
		}
		return minTime;
	}

	public static void main(String[] args) {
		int[][] grid = { { 2, 1, 1, 0, 1 }, { 0, 1, 1, 0, 2 }, { 0, 0, 0, 0, 1 }, { 2, 1, 1, 1, 2 } };
		int minTime = orangesRotting(grid);
		System.out.println(minTime);
	}

}
