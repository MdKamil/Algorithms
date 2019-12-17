package algorithm.two;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSum {

	public static int[] twoSumSortedInput(int[] numbers, int target) {
		if (numbers == null) {
			return null;
		} else {
			int[] result = null;
			int lo = 0;
			int hi = numbers.length - 1;
			boolean isPresent = false;
			while (true) {
				if (lo >= hi) {
					break;
				}
				int sum = numbers[lo] + numbers[hi];
				if (sum == target) {
					isPresent = true;
					break;
				} else if (sum > target) {
					--hi;
				} else {
					++lo;
				}
			}
			if (isPresent) {
				result = new int[2];
				result[0] = lo + 1;
				result[1] = hi + 1;
			} else {
				result = new int[] {};
			}
			return result;
		}
	}

	public static int[] twoSumUnsortedInput(int[] nums, int target) {
		if (nums == null) {
			return null;
		} else {
			int[] result = null;
			Map<Integer, Integer> map = new HashMap<>();
			int iter = 0;
			boolean isPresent = false;
			for (; iter <= nums.length - 1; ++iter) {
				if (map.containsKey(target - nums[iter])) {
					isPresent = true;
					break;
				} else {
					map.put(nums[iter], iter);
				}
			}
			if (isPresent) {
				result = new int[2];
				result[0] = map.get(target - nums[iter]);
				result[1] = iter;
			} else {
				result = new int[] {};
			}
			return result;
		}
	}

	public static void main(String[] args) {
		int[] nums = { 2, 7, 11, 15 };
		int[] result = twoSumUnsortedInput(nums, 26);
		if (result != null) {
			System.out.println(Arrays.toString(result));
		}
	}

}
