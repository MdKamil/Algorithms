package algorithm.matrix;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class SnakeAndLadder {

	public static int snakesAndLadders(int[][] board) {
		Queue<Integer> queue = new ArrayDeque<>();
		int startPoint = 1;
		int endPoint = board.length * board.length;
		int boardSize = board.length;
		Map<Integer, Integer> moveMap = new HashMap<>();
		queue.add(startPoint);
		moveMap.put(startPoint, 0);
		while (!queue.isEmpty()) {
			int cellVal = queue.poll();
			int nextDistance = moveMap.get(cellVal) + 1;
			for (int val = 1; val <= 6; ++val) {
				int destCellVal = cellVal + val;
				if (destCellVal > endPoint) {
					continue;
				}
				int row = (boardSize - 1) - ((destCellVal - 1) / boardSize);
				int col = -1;
				if (((destCellVal - 1) / boardSize) % 2 == 0) {
					// quotient is even so Left -> Right
					col = (destCellVal - 1) % boardSize;
				} else {
					// quotient is odd;
					col = (boardSize - 1) - ((destCellVal - 1) % boardSize);
				}
				if (board[row][col] != -1 && (board[row][col] > destCellVal || board[row][col] < destCellVal)) {
					// A snake/ladder
					if (!moveMap.containsKey(board[row][col]) || nextDistance < moveMap.get(board[row][col])) {
						queue.add(board[row][col]);
						moveMap.put(board[row][col], nextDistance);
					}
				} else {
					// No snake/ladder
					if (!moveMap.containsKey(destCellVal) || nextDistance < moveMap.get(destCellVal)) {
						queue.add(destCellVal);
						moveMap.put(destCellVal, nextDistance);
					}
				}
			}
		}
		Integer minMoves = moveMap.get(endPoint);
		if (minMoves == null) {
			minMoves = -1;
		}
		return minMoves;
	}

	public static void main(String[] args) {
		// int[][] board = { { -1, 2, 3, 4, 5, 6 }, { -1, -1, -1, -1, -1, 7 }, {
		// -1, -1, -1, -1, -1, -1 },
		// { -1, -1, -1, -1, 13, -1 }, { -1, -1, -1, -1, -1, -1 }, { -1, 15, -1,
		// -1, -1, -1 } };

		// int[][] board = { { -1, -1 }, { -1, 1 } };

		// int[][] board = { { -1, -1, -1 }, { -1, 9, 8 }, { -1, 8, 9 } };

		int[][] board = { { -1, -1, -1, 46, 47, -1, -1, -1 }, { 51, -1, -1, 63, -1, 31, 21, -1 },
				{ -1, -1, 26, -1, -1, 38, -1, -1 }, { -1, -1, 11, -1, 14, 23, 56, 57 },
				{ 11, -1, -1, -1, 49, 36, -1, 48 }, { -1, -1, -1, 33, 56, -1, 57, 21 },
				{ -1, -1, -1, -1, -1, -1, 2, -1 }, { -1, -1, -1, 8, 3, -1, 6, 56 } };

		int minMoves = snakesAndLadders(board);
		System.out.println(minMoves);
	}

}
