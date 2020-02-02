package algorithm.seven;

import java.util.ArrayList;
import java.util.List;

public class CombinationSum3 {
	public static List<List<Integer>> combinationSum3(int k, int n) {
		List<List<Integer>> combinationList = new ArrayList<List<Integer>>();
		if (k > 0 && n > 0) {
			List<Integer> subList = new ArrayList<>();
			int from = 1;
			int length = k;
			int sum = 0;
			recurse(from, length, n, sum, combinationList, subList);
		}
		return combinationList;
	}

	private static void recurse(int from, int length, int target, int sum, List<List<Integer>> combinationList,
			List<Integer> subList) {
		for (; from <= (from + length) - 1 && from <= 9; ++from) {
			sum += from;
			if (sum <= target) {
				subList.add(from);
				if (sum == target && length == 1) {
					combinationList.add(new ArrayList<>(subList));
				} else {
					recurse(from + 1, length - 1, target, sum, combinationList, subList);
				}
				subList.remove(subList.size() - 1);
				sum -= from;
			} else {
				break;
			}
		}
	}

	public static void main(String[] args) {
		int k = 2;
		int n = 18;
		List<List<Integer>> combinationList = combinationSum3(k, n);
		for (List<Integer> subList : combinationList) {
			System.out.println(subList);
		}
	}

}
