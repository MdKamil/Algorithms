package algorithm.array;

import java.util.ArrayList;
import java.util.List;

public class LargestDivisibleSubset {

	public static List<Integer> largestDivisibleSubset(int[] nums) {
		List<Integer> largestDivisibleSubset = new ArrayList<>();
		if (nums != null && nums.length > 0) {
			int start = nums.length - 1;
			int end = nums.length - 1;
			int maxLength = 1;
			int to = nums.length - 1;
			int from = nums.length - 2;
			while (from >= 0) {
				for (int curr = from + 1; curr <= to; ++curr) {
					if (nums[from] % nums[curr] != 0 && nums[curr] % nums[from] != 0) {
						to = curr - 1;
						break;
					}
				}
				if ((to - from) + 1 > maxLength) {
					maxLength = (to - from) + 1;
					start = from;
					end = to;
				}
				--from;
			}
			for (int idx = start; idx <= end; ++idx) {
				largestDivisibleSubset.add(nums[idx]);
			}
		}
		return largestDivisibleSubset;
	}

	public static void main(String[] args) {
		int[] nums = { 1, 2, 1, 2, 1, 2, 3 };
		List<Integer> largestDivisibleSubset = largestDivisibleSubset(nums);
		System.out.println(largestDivisibleSubset);
	}

}
