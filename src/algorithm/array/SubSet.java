package algorithm.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubSet {

	public static List<List<Integer>> subsets(int[] nums) {
		List<List<Integer>> list = new ArrayList<>();
		list.add(new ArrayList<>());
		List<Integer> subList = new ArrayList<>();
		recurse(0, nums, subList, list);
		return list;
	}

	private static void recurse(int iter, int[] set, List<Integer> subList, List<List<Integer>> list) {
		for (int i = iter; i <= set.length - 1; ++i) {
			subList.add(set[i]);
			recurse(i + 1, set, subList, list);
			list.add(new ArrayList<>(subList));
			subList.remove(subList.size() - 1);
		}
	}

	public static List<List<Integer>> subsetsWithDup(int[] nums) {
		List<List<Integer>> list = new ArrayList<>();
		list.add(new ArrayList<>());
		List<Integer> subList = new ArrayList<>();
		Arrays.sort(nums);
		recurseWithDup(0, nums, subList, list);
		return list;
	}

	/*
	 * set = [1, 1, 2, 2, 2]
	 * condition 1: i != iter && (set[i] == set[iter]) 
	 * 		for above array if iter = 2, we sub iterate for(i=0 to set.len)
	 * 		the sub-iteration for the above condition when i == 1..., we check 
	 * 		the above condition to avoid duplicate list creation.
	 * 		(Sub-iteration elements are same)
	 * condition 2: i != iter && (set[i] == set[i-1]).
	 * 		for above array when iter == 1, and when we sub-iterate on iter(for(i=0 to set.len))
	 * 		we check for equality for the previous element. If equal , we skip
	 * 		to avod duplicate list creation. As we sub-iterate on diff. iter. we check for the above 
	 * 		condition.
	*/
	private static void recurseWithDup(int iter, int[] set, List<Integer> subList, List<List<Integer>> list) {
		for (int i = iter; i <= set.length - 1; ++i) {
			if (i != iter && (set[i] == set[iter] || set[i] == set[i - 1])) {
				continue;
			}
			subList.add(set[i]);
			recurseWithDup(i + 1, set, subList, list);
			list.add(new ArrayList<>(subList));
			subList.remove(subList.size() - 1);
		}
	}

	public static void main(String... args) {
		int[] nums = { 1, 2, 3, 4, 3 };
		List<List<Integer>> list = subsetsWithDup(nums);
		for (List<Integer> subList : list) {
			System.out.println(subList);
		}
	}

}
