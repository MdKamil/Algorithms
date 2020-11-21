package algorithm.matrix;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class SurroundedRegions {

	public static void solve(char[][] board) {
		if (board != null && board.length > 0 && board[0].length > 0) {
			boolean[][] isReachableFromBoundary = new boolean[board.length][board[0].length];
			Queue<Integer> rowQueue = new ArrayDeque<>();
			Queue<Integer> colQueue = new ArrayDeque<>();
			int[] rowDir = { 1, -1, 0, 0 };
			int[] colDir = { 0, 0, 1, -1 };
			for (int row = 0; row <= board.length - 1; ++row) {
				int col = 0;
				while (col <= board[row].length - 1) {
					if (board[row][col] == 'O' && !isReachableFromBoundary[row][col]
							&& (row == 0 || row == board.length - 1 || col == 0 || col == board[row].length - 1)) {
						rowQueue.add(row);
						colQueue.add(col);
						isReachableFromBoundary[row][col] = true;
						while (!(rowQueue.isEmpty() && colQueue.isEmpty())) {
							int currRow = rowQueue.poll();
							int currCol = colQueue.poll();
							for (int d = 0; d <= 3; ++d) {
								int r = currRow + rowDir[d];
								int c = currCol + colDir[d];
								if (r < 0 || c < 0 || r > board.length - 1 || c > board[currRow].length - 1
										|| board[r][c] == 'X' || isReachableFromBoundary[r][c]) {
									continue;
								}
								rowQueue.add(r);
								colQueue.add(c);
								isReachableFromBoundary[r][c] = true;
							}

						}
					}

					if (row > 0 && row < board.length - 1) {
						col += board[row].length - 1;
					} else {
						++col;
					}
				}
			}
			for (int row = 0; row <= board.length - 1; ++row) {
				for (int col = 0; col <= board[row].length - 1; ++col) {
					if (board[row][col] == 'O' && !isReachableFromBoundary[row][col]) {
						board[row][col] = 'X';
					}
				}
			}
		}
	}

	public static void main(String[] args) {
		char[][] board = { { 'O', 'O' }, { 'O', 'O' } };
		System.out.println("Before");
		for (char[] row : board) {
			System.out.println(Arrays.toString(row));
		}
		solve(board);
		System.out.println();
		System.out.println("After");
		for (char[] row : board) {
			System.out.println(Arrays.toString(row));
		}
	}

}
