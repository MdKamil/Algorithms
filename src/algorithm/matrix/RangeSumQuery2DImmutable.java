package algorithm.matrix;

import java.util.Arrays;

public class RangeSumQuery2DImmutable {
    private final int[][] matrix;
    private final int[][] matrixPrefixSum;
    private final int MAX_COL;
    private final int MAX_ROW;

    public RangeSumQuery2DImmutable(int[][] matrix) {
        this.matrix = matrix;
        MAX_ROW = matrix.length;
        MAX_COL = matrix[0].length;
        this.matrixPrefixSum = new int[MAX_ROW][MAX_COL];
        populate();
    }

    private void populate() {
        for (int row = 0; row <= MAX_ROW - 1; ++row) {
            int currRowPrefixSum = 0;
            for (int col = 0; col <= MAX_COL - 1; ++col) {
                currRowPrefixSum += matrix[row][col];
                matrixPrefixSum[row][col] = (row == 0 ? currRowPrefixSum : (matrixPrefixSum[row - 1][col] + currRowPrefixSum));
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        int areaSum = matrixPrefixSum[row2][col2];
        int upperSum = row1 == 0 ? 0 : matrixPrefixSum[row1 - 1][col2];
        int leftSum = col1 == 0 ? 0 : matrixPrefixSum[row2][col1 - 1];
        int commonSum = (row1 - 1 >= 0 && col1 - 1 >= 0) ? matrixPrefixSum[row1 - 1][col1 - 1] : 0;
        return areaSum - (upperSum + leftSum) + commonSum;
    }


    public static void main(String[] args) {
//        int[][] matrix = {{3,  0, 1, 4, 2}, {5, 6, 3, 2, 1}, {1, 2, 0, 1, 5}, {4, 1, 0, 1, 7}, {1, 0, 3, 0, 5}};
        int[][] matrix = {{-1, -2, -1}, {-1, -2, -1}, {-1, -2, -1}};
        RangeSumQuery2DImmutable rangeSumQuery2DImmutable = new RangeSumQuery2DImmutable(matrix);
        System.out.println(Arrays.deepToString(rangeSumQuery2DImmutable.matrixPrefixSum));

        int regionSum = rangeSumQuery2DImmutable.sumRegion(0, 2, 0, 2);
        System.out.println(regionSum);
    }
}
