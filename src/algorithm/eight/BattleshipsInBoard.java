package algorithm.eight;

public class BattleshipsInBoard {

	public static int countBattleships(char[][] board) {
		int noOfBattleShips = 0;
		for (int row = 0; row <= board.length - 1; ++row) {
			for (int col = 0; col <= board[row].length - 1; ++col) {
				if (board[row][col] == 'X') {
					if (board.length == 1 && board[0].length == 1) {
						++noOfBattleShips;
					} else if ((row - 1 < 0 || board[row - 1][col] == '.')
							&& (row + 1 > board.length - 1 || board[row + 1][col] == '.')
							&& (col - 1 < 0 || board[row][col - 1] == '.')
							&& (col + 1 > board[0].length - 1 || board[row][col + 1] == '.')) {
						++noOfBattleShips;
					} else {
						if (col == board[row].length - 1 && col - 1 >= 0 && board[row][col - 1] == 'X') {
							++noOfBattleShips;
						} else if (row == board.length - 1 && row - 1 >= 0 && board[row - 1][col] == 'X') {
							++noOfBattleShips;
						}
					}
				} else {
					if (row - 2 >= 0 && board[row - 1][col] == 'X' && board[row - 2][col] == 'X') {
						++noOfBattleShips;
					}
					if (col - 2 >= 0 && board[row][col - 1] == 'X' && board[row][col - 2] == 'X') {
						++noOfBattleShips;
					}
				}
			}
		}
		return noOfBattleShips;
	}

	public static void main(String[] args) {
		char[][] board = { { 'X', '.', 'X', 'X' }, { 'X', '.', '.', '.' }, { 'X', '.', 'X', 'X' } };
		int noOfBattleShips = countBattleships(board);
		System.out.println(noOfBattleShips);
	}

}
