package algorithm.matrix;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class SpiralMatrix2 {
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int row = 0;
        int col = 0;
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{0, 1});
        queue.add(new int[]{1, 0});
        queue.add(new int[]{0, -1});
        queue.add(new int[]{-1, 0});
        matrix[row][col] = 1;
        int value = 2;
        while (value <= n * n) {
            int[] direction = queue.poll();
            queue.add(direction);
            while (true) {
                if (direction[0] == 0 && direction[1] == 1) {
                    if (isBoundary(row, col + 1, n) || cellFilled(matrix, row, col + 1)) {
                        break;
                    }
                    ++ col;
                } else if (direction[0] == 1 && direction[1] == 0) {
                    if (isBoundary(row + 1, col, n) || cellFilled(matrix, row + 1, col)) {
                        break;
                    }
                    ++row;
                } else if (direction[0] == 0 && direction[1] == -1) {
                    if (isBoundary(row, col - 1, n) || cellFilled(matrix, row, col - 1)) {
                        break;
                    }
                    --col;
                } else if (direction[0] == -1 && direction[1] == 0) {
                    if (isBoundary(row - 1, col, n) || cellFilled(matrix, row - 1, col)) {
                        break;
                    }
                    --row;
                }
                matrix[row][col] = value++;
            }
        }
        return matrix;
    }

    private boolean isBoundary(int row, int col, int n) {
        return row == -1 || row == n || col == -1 || col == n;
    }

    private boolean cellFilled(int[][] matrix, int row, int col) {
        return matrix[row][col] != 0;
    }

    public static void main(String[] args) {
        int n = 2;
        SpiralMatrix2 spiralMatrix2 = new SpiralMatrix2();
        int[][] matrix = spiralMatrix2.generateMatrix(n);
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }
}
