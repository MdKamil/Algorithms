package algorithm.matrix;

public class IslandPerimeter {

	public static int islandPerimeter(int[][] grid) {
		int islandPerimeter = 0;
		for (int row = 0; row <= grid.length - 1; ++row) {
			for (int col = 0; col <= grid[row].length - 1; ++col) {
				if (grid[row][col] == 1) {
					// Up
					if (row == 0) {
						++islandPerimeter;
					} else {
						if (grid[row - 1][col] == 0) {
							++islandPerimeter;
						}
					}
					// Down
					if (row == grid.length - 1) {
						++islandPerimeter;
					} else {
						if (grid[row + 1][col] == 0) {
							++islandPerimeter;
						}
					}
					// Left
					if (col == 0) {
						++islandPerimeter;
					} else {
						if (grid[row][col - 1] == 0) {
							++islandPerimeter;
						}
					}
					// Right
					if (col == grid[row].length - 1) {
						++islandPerimeter;
					} else {
						if (grid[row][col + 1] == 0) {
							++islandPerimeter;
						}
					}
				}
			}
		}
		return islandPerimeter;
	}

	public static void main(String[] args) {
		int[][] grid = { { 1, 0 } };
		int islandPerimeter = islandPerimeter(grid);
		System.out.println(islandPerimeter);
	}

}
