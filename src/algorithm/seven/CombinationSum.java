package algorithm.seven;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSum {

	public static List<List<Integer>> combinationSum(int[] candidates, int target) {
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
			int totalNoOfTimes = (target - sum) / candidates[iter];
			if (totalNoOfTimes > 0) {
				int times = 1;
				for (; times <= totalNoOfTimes; ++times) {
					sum += candidates[iter];
					subList.add(candidates[iter]);
					if (sum == target) {
						combinationList.add(new ArrayList<>(subList));
					} else if (sum < target) {
						recurse(candidates, iter + 1, target, sum, combinationList, subList);
					}
				}
				times = 1;
				for (; times <= totalNoOfTimes; ++times) {
					sum -= candidates[iter];
					subList.remove(subList.size() - 1);
				}
			} else {
				break;
			}
		}
	}

	public static void main(String[] args) {
		int[] candidates = { 12, 13, 16, 77 };
		int target = 10;
		List<List<Integer>> combinationList = combinationSum(candidates, target);
		for (List<Integer> subList : combinationList) {
			System.out.println(subList);
		}
	}

}
