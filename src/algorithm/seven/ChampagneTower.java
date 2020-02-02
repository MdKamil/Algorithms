package algorithm.seven;

public class ChampagneTower {

	public static double champagneTower(int poured, int query_row, int query_glass) {
		double result[][] = new double[101][101];
		result[0][0] = poured;
		for (int i = 0; i <= query_row; i++) {
			for (int j = 0; j <= i; j++) {
				if (result[i][j] >= 1) {
					result[i + 1][j] += (result[i][j] - 1) / 2.0;
					result[i + 1][j + 1] += (result[i][j] - 1) / 2.0;
					result[i][j] = 1;
				}
			}
		}
		return result[query_row][query_glass];
	}

	public static void main(String[] args) {
		int poured = 3;
		int query_row = 1;
		int query_glass = 1;
		double quantity = champagneTower(poured, query_row, query_glass);
		System.out.println(quantity);
	}
}
