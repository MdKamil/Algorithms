package algorithm.array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

public class CountSmall {

	public static List<Integer> countSmaller(int[] nums) {
		List<Integer> list = new ArrayList<>();
		if (nums != null && nums.length > 0) {
			for (int idx = 0; idx <= nums.length - 2; ++idx) {
				int count = 0;
				for (int from = idx + 1; from <= nums.length - 1; ++from) {
					if (nums[idx] > nums[from]) {
						++count;
					}
				}
				list.add(count);
			}
			list.add(0);
		}
		return list;
	}

	public static List<Integer> countSmallerV2(int[] nums) {
		List<Integer> list = new ArrayList<>();
		if (nums != null && nums.length > 0) {
			TreeSet<Integer> set = new TreeSet<>();
			set.add(nums[nums.length - 1]);
			list.add(0);
			for (int idx = nums.length - 2; idx >= 0; --idx) {
				set.add(nums[idx]);
				list.add(set.headSet(nums[idx]).size());
			}
			Collections.reverse(list);
		}
		return list;
	}

}
