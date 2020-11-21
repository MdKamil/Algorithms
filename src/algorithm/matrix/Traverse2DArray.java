package algorithm.matrix;

import java.util.Arrays;

public class Traverse2DArray {

	private static int countTraversal() {
		int[][] input = new int[5][5];
		Arrays.fill(input[0], 1);
		for (int i = 1; i <= input.length - 1; ++i) {
			input[i][0] = 1;
		}
		for (int i = 1; i <= input.length - 1; ++i) {
			for (int j = 1; j <= input[i].length - 1; ++j) {
				input[i][j] = input[i - 1][j] + input[i][j - 1];
			}
		}
		return input[input.length - 1][input[0].length - 1];
	}

	public static void main(String[] args) {
		countTraversal();
	}
}
