package algorithm.matrix;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class FloodFill {

	public static int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
		if (newColor != image[sr][sc]) {
			Queue<Integer> rowQueue = new ArrayDeque<Integer>();
			Queue<Integer> colQueue = new ArrayDeque<Integer>();
			rowQueue.add(sr);
			colQueue.add(sc);
			int[] rowDir = { -1, 1, 0, 0 };
			int[] colDir = { 0, 0, -1, 1 };
			int matchColor = image[sr][sc];
			while (!(rowQueue.isEmpty() && colQueue.isEmpty())) {
				int currRow = rowQueue.poll();
				int currCol = colQueue.poll();
				image[currRow][currCol] = newColor;
				for (int dir = 0; dir <= 3; ++dir) {
					int nextRow = currRow + rowDir[dir];
					int nextCol = currCol + colDir[dir];
					if (nextRow < 0 || nextRow > image.length - 1 || nextCol < 0 || nextCol > image[0].length - 1
							|| image[nextRow][nextCol] != matchColor) {
						continue;
					}
					rowQueue.add(nextRow);
					colQueue.add(nextCol);
				}
			}
		}
		return image;
	}

	public static void main(String[] args) {
		int[][] image = { { 2, 0, 1, 0 }, { 1, 1, 2, 1 }, { 0, 1, 1, 0 }, { 1, 2, 1, 1 } };
		int sr = 1;
		int sc = 1;
		int newColor = 2;
		floodFill(image, sr, sc, newColor);
		for (int[] row : image) {
			System.out.println(Arrays.toString(row));
		}
	}

}
