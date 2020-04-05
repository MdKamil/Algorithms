package algorithm.eight;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Minesweeper {
	private static class Cell {
		int row;
		int col;

		public Cell(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}

	public static char[][] updateBoard(char[][] board, int[] click) {
		Queue<Cell> queue = new ArrayDeque<>();
		int MAX_ROW = board.length - 1;
		int MAX_COL = board[0].length - 1;
		boolean[][] visited = new boolean[MAX_ROW + 1][MAX_COL + 1];
		if (board[click[0]][click[1]] == 'M') {
			board[click[0]][click[1]] = 'X';
		} else {
			queue.add(new Cell(click[0], click[1]));
			while (!queue.isEmpty()) {
				Cell cell = queue.poll();
				int mineCount = 0;
				if (cell.row - 1 >= 0 && board[cell.row - 1][cell.col] == 'M') {
					++mineCount;
				}
				if (cell.row + 1 <= MAX_ROW && board[cell.row + 1][cell.col] == 'M') {
					++mineCount;
				}
				if (cell.col - 1 >= 0 && board[cell.row][cell.col - 1] == 'M') {
					++mineCount;
				}
				if (cell.col + 1 <= MAX_COL && board[cell.row][cell.col + 1] == 'M') {
					++mineCount;
				}
				if (cell.row - 1 >= 0 && cell.col + 1 <= MAX_COL && board[cell.row - 1][cell.col + 1] == 'M') {
					++mineCount;
				}
				if (cell.row - 1 >= 0 && cell.col - 1 >= 0 && board[cell.row - 1][cell.col - 1] == 'M') {
					++mineCount;
				}
				if (cell.row + 1 <= MAX_ROW && cell.col + 1 <= MAX_COL && board[cell.row + 1][cell.col + 1] == 'M') {
					++mineCount;
				}
				if (cell.row + 1 <= MAX_ROW && cell.col - 1 >= 0 && board[cell.row + 1][cell.col - 1] == 'M') {
					++mineCount;
				}

				if (mineCount > 0) {
					board[cell.row][cell.col] = Character.forDigit(mineCount, 10);
				} else {
					board[cell.row][cell.col] = 'B';
					visited[cell.row][cell.col] = true;
					// up
					if (cell.row - 1 >= 0 && board[cell.row - 1][cell.col] == 'E' && !visited[cell.row - 1][cell.col]) {
						queue.add(new Cell(cell.row - 1, cell.col));
						visited[cell.row - 1][cell.col] = true;
					}
					// down
					if (cell.row + 1 <= MAX_ROW && board[cell.row + 1][cell.col] == 'E'
							&& !visited[cell.row + 1][cell.col]) {
						queue.add(new Cell(cell.row + 1, cell.col));
						visited[cell.row + 1][cell.col] = true;
					}
					// left
					if (cell.col - 1 >= 0 && board[cell.row][cell.col - 1] == 'E' && !visited[cell.row][cell.col - 1]) {
						queue.add(new Cell(cell.row, cell.col - 1));
						visited[cell.row][cell.col - 1] = true;
					}
					// right
					if (cell.col + 1 <= MAX_COL && board[cell.row][cell.col + 1] == 'E'
							&& !visited[cell.row][cell.col + 1]) {
						queue.add(new Cell(cell.row, cell.col + 1));
						visited[cell.row][cell.col + 1] = true;
					}
					// top-right
					if (cell.row - 1 >= 0 && cell.col + 1 <= MAX_COL && board[cell.row - 1][cell.col + 1] == 'E'
							&& !visited[cell.row - 1][cell.col + 1]) {
						queue.add(new Cell(cell.row - 1, cell.col + 1));
						visited[cell.row - 1][cell.col + 1] = true;
					}
					// top-left
					if (cell.row - 1 >= 0 && cell.col - 1 >= 0 && board[cell.row - 1][cell.col - 1] == 'E'
							&& !visited[cell.row - 1][cell.col - 1]) {
						queue.add(new Cell(cell.row - 1, cell.col - 1));
						visited[cell.row - 1][cell.col - 1] = true;
					}
					// bottom-right
					if (cell.row + 1 <= MAX_ROW && cell.col + 1 <= MAX_COL && board[cell.row + 1][cell.col + 1] == 'E'
							&& !visited[cell.row + 1][cell.col + 1]) {
						queue.add(new Cell(cell.row + 1, cell.col + 1));
						visited[cell.row + 1][cell.col + 1] = true;
					}
					// bottom-left
					if (cell.row + 1 <= MAX_ROW && cell.col - 1 >= 0 && board[cell.row + 1][cell.col - 1] == 'E'
							&& !visited[cell.row + 1][cell.col - 1]) {
						queue.add(new Cell(cell.row + 1, cell.col - 1));
						visited[cell.row + 1][cell.col - 1] = true;
					}
				}
			}
		}
		return board;
	}

	public static char[][] updateBoardV2(char[][] board, int[] click) {
		if (board[click[0]][click[1]] == 'M') {
			board[click[0]][click[1]] = 'X';
		} else {
			int MAX_ROW = board.length - 1;
			int MAX_COL = board[0].length - 1;
			boolean[][] visited = new boolean[MAX_ROW + 1][MAX_COL + 1];
			int[] rowDir = { -1, 1, 0, 0, -1, -1, 1, 1 };
			int[] colDir = { 0, 0, -1, 1, -1, 1, -1, 1 };
			Queue<Cell> queue = new ArrayDeque<>();
			queue.add(new Cell(click[0], click[1]));
			while (!queue.isEmpty()) {
				Cell cell = queue.poll();
				int mineCount = 0;
				for (int dir = 0; dir <= 7; ++dir) {
					int nextRow = cell.row + rowDir[dir];
					int nextCol = cell.col + colDir[dir];
					if (nextRow < 0 || nextRow > MAX_ROW || nextCol < 0 || nextCol > MAX_COL) {
						continue;
					}
					if (board[nextRow][nextCol] == 'M') {
						++mineCount;
					}
				}
				if (mineCount > 0) {
					board[cell.row][cell.col] = Character.forDigit(mineCount, 10);
				} else {
					board[cell.row][cell.col] = 'B';
					visited[cell.row][cell.col] = true;
					for (int dir = 0; dir <= 7; ++dir) {
						int nextRow = cell.row + rowDir[dir];
						int nextCol = cell.col + colDir[dir];
						if (nextRow < 0 || nextRow > MAX_ROW || nextCol < 0 || nextCol > MAX_COL) {
							continue;
						}
						if (board[nextRow][nextCol] == 'E' && !visited[nextRow][nextCol]) {
							queue.add(new Cell(nextRow, nextCol));
							visited[nextRow][nextCol] = true;
						}
					}
				}
			}
		}
		return board;
	}

	public static void main(String[] args) {
		char[][] board = { { 'E', 'E', 'E', 'E', 'E' }, { 'E', 'E', 'M', 'E', 'E' }, { 'E', 'E', 'E', 'E', 'E' },
				{ 'E', 'E', 'E', 'E', 'E' } };
		int[] click = { 3, 0 };
		for (char[] row : board) {
			System.out.println(Arrays.toString(row));
		}
		updateBoardV2(board, click);
		System.out.println();
		for (char[] row : board) {
			System.out.println(Arrays.toString(row));
		}
	}
}
