package algorithm.matrix;

public class DungeonGame {
	public static int calculateMinimumHPV1(int[][] dungeon) {
		int MAX_ROW = dungeon.length - 1;
		int MAX_COL = dungeon[0].length - 1;
		int[][] minimumHealthAt = new int[MAX_ROW + 1][MAX_COL + 1];
		for (int row = MAX_ROW; row >= 0; --row) {
			for (int col = MAX_COL; col >= 0; --col) {
				if (row == MAX_ROW && col == MAX_COL) {
					if (dungeon[MAX_ROW][MAX_COL] < 0) {
						minimumHealthAt[MAX_ROW][MAX_COL] = Math.abs(dungeon[MAX_ROW][MAX_COL]) + 1;
					}else {
						minimumHealthAt[MAX_ROW][MAX_COL] = 1;
					}
				} else if (row == MAX_ROW) {
					calculateRight(minimumHealthAt, dungeon, row, col, col + 1);
				} else if (col == MAX_COL) {
					calculateDown(minimumHealthAt, dungeon, row, col, row + 1);
				} else {
					calculateRight(minimumHealthAt, dungeon, row, col, col + 1);
					calculateDown(minimumHealthAt, dungeon, row, col, row + 1);
				}
			}
		}
		return minimumHealthAt[0][0];
	}

	private static void calculateRight(int[][] minimumHealthAt, int[][] dungeon, int row, int col, int nextCol) {
		int val = minimumHealthAt[row][nextCol]
				+ (dungeon[row][col] > 0 ? -dungeon[row][col] : Math.abs(dungeon[row][col]));
		update(minimumHealthAt, dungeon, row, col, val);
	}

	private static void calculateDown(int[][] minimumHealthAt, int[][] dungeon, int row, int col, int nextRow) {
		int val = minimumHealthAt[nextRow][col]
				+ (dungeon[row][col] > 0 ? -dungeon[row][col] : Math.abs(dungeon[row][col]));
		update(minimumHealthAt, dungeon, row, col, val);
	}

	private static void update(int[][] minimumHealthAt, int[][] dungeon, int row, int col, int val) {
		if (val <= 0) {
			val = 1;
		}
		if (minimumHealthAt[row][col] == 0) {
			minimumHealthAt[row][col] = val;
		} else {
			minimumHealthAt[row][col] = Math.min(minimumHealthAt[row][col], val);
		}
	}

	public static void main(String[] args) {
		int[][] dungeon = { { -2, -3, 3 }, { -5, -10, 1 }, { 10, 30, -5 } };
		int minimumHealth = calculateMinimumHPV1(dungeon);
		System.out.println(minimumHealth);
	}
}
