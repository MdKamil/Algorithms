package algorithm.three;

import java.util.Arrays;
import java.util.Random;

public class SparseTable {

	int N;
	int max_power;
	long[] input;
	long[][] table;

	public SparseTable(int N, long[] input) {
		this.N = N;
		this.input = input;
		max_power = (int) (Math.log(N) / Math.log(2));
		table = new long[N][max_power + 1];
	}

	private void build() {
		for (int i = 0; i <= N - 1; ++i) {
			table[i][0] = input[i];
		}
		for (int i = 1; i <= max_power; ++i) {
			for (int j = 0; j <= (N - 1) - ((1 << i) - 1); ++j) {
				table[j][i] = Math.min(table[j][i - 1], table[j + (1 << (i - 1))][i - 1]);
			}
		}
	}

	private void printTable() {
		System.out.println("INPUT ARRAY: " + Arrays.toString(input));
		for (int i = 0; i <= N - 1; ++i) {
			System.out.println(Arrays.toString(table[i]));
		}
	}

	private void getMin(int l, int r) {
		long min = 0;
		if (l == r) {
			min = table[l][0];
		} else {

		}
		System.out.println(min);
	}

	public static void main(String[] args) {
		int N = 11;
		Random random = new Random();
		long[] input = generateRandomInput(N, random);
		SparseTable sparseTable = new SparseTable(N, input);
		sparseTable.build();
		sparseTable.printTable();
	}

	private static long[] generateRandomInput(int n, Random random) {
		long[] array = new long[n];
		for (int i = 0; i <= array.length - 1; ++i) {
			array[i] = 1 + random.nextInt(10);
		}
		return array;
	}
}
