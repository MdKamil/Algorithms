package algorithm.eight;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class KthSmallestelementInMatrix {

	private static class Cell {
		int row;
		int col;
		Integer val;

		public Cell(int row, int col, Integer val) {
			this.row = row;
			this.col = col;
			this.val = val;
		}
	}

	public static int kthSmallest(int[][] matrix, int k) {
		int smallest = -1;
		if (matrix != null && matrix.length > 0 && matrix[0] != null && matrix[0].length > 0) {
			int ROW_MAX = matrix.length - 1;
			int COL_MAX = matrix[0].length - 1;
			int count = 0;
			Comparator<Cell> c = new Comparator<Cell>() {
				@Override
				public int compare(Cell o1, Cell o2) {
					return o1.val.compareTo(o2.val);
				}
			};
			Queue<Cell> priorityQueue = new PriorityQueue<>(c);
			boolean[][] isVisited = new boolean[ROW_MAX + 1][COL_MAX + 1];
			priorityQueue.add(new Cell(0, 0, matrix[0][0]));
			isVisited[0][0] = true;
			while (!priorityQueue.isEmpty()) {
				Cell currCell = priorityQueue.poll();
				++count;
				if (count == k) {
					smallest = currCell.val;
					break;
				}
				if (currCell.row + 1 <= ROW_MAX && !isVisited[currCell.row + 1][currCell.col]) {
					priorityQueue.add(new Cell(currCell.row + 1, currCell.col, matrix[currCell.row + 1][currCell.col]));
					isVisited[currCell.row + 1][currCell.col] = true;
				}
				if (currCell.col + 1 <= COL_MAX && !isVisited[currCell.row][currCell.col + 1]) {
					priorityQueue.add(new Cell(currCell.row, currCell.col + 1, matrix[currCell.row][currCell.col + 1]));
					isVisited[currCell.row][currCell.col + 1] = true;
				}
			}
		}
		return smallest;
	}

	public static int kthSmallestV2(int[][] matrix, int k) {
		int smallest = -1;
		if (matrix != null && matrix.length > 0 && matrix[0] != null && matrix[0].length > 0) {
			int ROW_MAX = matrix.length - 1;
			int COL_MAX = matrix[0].length - 1;
			int count = 0;
			Comparator<Cell> c = new Comparator<Cell>() {
				@Override
				public int compare(Cell o1, Cell o2) {
					return o1.val.compareTo(o2.val);
				}
			};
			Queue<Cell> priorityQueue = new PriorityQueue<>(COL_MAX + 1, c);
			for (int col = 0; col <= COL_MAX; ++col) {
				priorityQueue.add(new Cell(0, col, matrix[0][col]));
			}
			while (!priorityQueue.isEmpty()) {
				Cell currCell = priorityQueue.poll();
				++count;
				if (count == k) {
					smallest = currCell.val;
					break;
				}
				if (currCell.row + 1 <= ROW_MAX) {
					priorityQueue.add(new Cell(currCell.row + 1, currCell.col, matrix[currCell.row + 1][currCell.col]));
				}
			}
		}
		return smallest;
	}

	public static void main(String[] args) {
		int[][] matrix = { { 1, 5, 9 }, { 10, 11, 13 }, { 12, 13, 15 } };
		int k = 8;
		int smallest = kthSmallestV2(matrix, k);
		System.out.println(smallest);
	}

}
