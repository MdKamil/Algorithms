package algorithm.matrix;

import java.util.*;

public class GameOfLife {

	public static void gameOfLifeV1(int[][] board) {
		if (board != null && board.length > 0 && board[0].length > 0) {
			int[] prevRow = new int[board[0].length];
			int[] currRow = new int[board[0].length];
			int prevLeftCell = -1;
			int currentCell = -1;
			for (int row = 0; row <= board.length - 1; ++row) {
				prevLeftCell = -1;
				currRow = Arrays.copyOf(board[row], board[row].length);
				for (int col = 0; col <= board[0].length - 1; ++col) {
					currentCell = board[row][col];
					int liveNeighBour = 0;
					// top
					if (row > 0 && prevRow[col] == 1) {
						++liveNeighBour;
					}
					// top left
					if (row > 0 && col > 0 && prevRow[col - 1] == 1) {
						++liveNeighBour;
					}
					// top right
					if (row > 0 && col < board[0].length - 1 && prevRow[col + 1] == 1) {
						++liveNeighBour;
					}
					// bottom
					if (row < board.length - 1 && board[row + 1][col] == 1) {
						++liveNeighBour;
					}
					// bottom left
					if (row < board.length - 1 && col > 0 && board[row + 1][col - 1] == 1) {
						++liveNeighBour;
					}
					// bottom right
					if (row < board.length - 1 && col < board[0].length - 1 && board[row + 1][col + 1] == 1) {
						++liveNeighBour;
					}
					// left
					if (col > 0 && prevLeftCell == 1) {
						++liveNeighBour;
					}
					// right
					if (col < board[0].length - 1 && board[row][col + 1] == 1) {
						++liveNeighBour;
					}
					if (board[row][col] == 1) {
						if (liveNeighBour < 2 || liveNeighBour > 3) {
							board[row][col] = 0;
						}
					} else if (board[row][col] == 0) {
						if (liveNeighBour == 3) {
							board[row][col] = 1;
						}
					}
					prevLeftCell = currentCell;
				}
				prevRow = Arrays.copyOf(currRow, currRow.length);
			}
		}
	}

	public static void main(String[] args) {
		int[][] board = { { 0, 1, 0 }, { 0, 0, 1 }, { 1, 1, 1 }, { 0, 0, 0 } };
		System.out.println("Init State");
		for (int[] row : board) {
			System.out.println(Arrays.toString(row));
		}
		System.out.println();
		gameOfLifeV1(board);
		System.out.println("Next State");
		for (int[] row : board) {
			System.out.println(Arrays.toString(row));
		}
	}
}
