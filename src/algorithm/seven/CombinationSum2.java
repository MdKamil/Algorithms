package algorithm.seven;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSum2 {

	public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
		List<List<Integer>> combinationList = new ArrayList<List<Integer>>();
		if (candidates != null && candidates.length > 0) {
			List<Integer> subList = new ArrayList<>();
			Arrays.sort(candidates);
			int idx = 0;
			int sum = 0;
			recurse(candidates, idx, target, sum, combinationList, subList);
		}
		return combinationList;
	}

	private static void recurse(int[] candidates, int idx, int target, int sum, List<List<Integer>> combinationList,
			List<Integer> subList) {
		for (int iter = idx; iter <= candidates.length - 1; ++iter) {
			if (iter != idx && (candidates[iter] == candidates[idx] || candidates[iter] == candidates[iter - 1])) {
				continue;
			}
			sum += candidates[iter];
			if (sum <= target) {
				subList.add(candidates[iter]);
				if (sum == target) {
					combinationList.add(new ArrayList<>(subList));
				} else {
					recurse(candidates, iter + 1, target, sum, combinationList, subList);
				}
				subList.remove(subList.size() - 1);
				sum -= candidates[iter];
			} else {
				break;
			}
		}
	}

	public static void main(String[] args) {
		int[] candidates = { 3, 1, 3, 5, 1, 1 };
		int target = 8;
		List<List<Integer>> combinationList = combinationSum2(candidates, target);
		for (List<Integer> subList : combinationList) {
			System.out.println(subList);
		}
	}

}
