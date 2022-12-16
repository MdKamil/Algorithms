package algorithm.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Triangle {
	public int minimumTotal(List<List<Integer>> triangle) {
		int[] cache = new int[triangle.size()];
		List<Integer> row = triangle.get(triangle.size() - 1);
		for (int idx = 0; idx <= row.size() - 1; ++idx) {
			cache[idx] = row.get(idx);
		}
		for (int currRow = triangle.size() - 1; currRow > 0; --currRow) {
			int prevRow = currRow - 1;
			for (int idx = 0; idx <= triangle.get(currRow).size() - 2; ++idx) {
				int currSum = cache[idx] + triangle.get(prevRow).get(idx);
				int nextSum = cache[idx + 1] + triangle.get(prevRow).get(idx);
				cache[idx] = Math.min(currSum, nextSum);
			}
		}
		return cache[0];
	}

	public static void main(String[] args) {
		List<List<Integer>> triangle = new ArrayList<>();
		triangle.add(Arrays.asList(2));
		triangle.add(Arrays.asList(3, 4));
		triangle.add(Arrays.asList(6, 5, 7));
		triangle.add(Arrays.asList(4, 1, 8, 3));
		System.out.println(new Triangle().minimumTotal(triangle));
	}
}
