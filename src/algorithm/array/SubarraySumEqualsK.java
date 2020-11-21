package algorithm.array;

import java.util.HashMap;
import java.util.Map;

public class SubarraySumEqualsK {

	public static int subarraySum(int[] nums, int k) {
		int count = 0;
		if (nums != null && nums.length > 0) {
			Map<Integer, Integer> map = new HashMap<>();
			int prefixSum = 0;
			for (int idx = 0; idx <= nums.length - 1; ++idx) {
				prefixSum += nums[idx];
				Integer expected = map.get(prefixSum - k);
				if (expected != null) {
					count += expected;
				}
				if (prefixSum == k) {
					++count;
				}
				Integer val = map.get(prefixSum);
				if (val == null) {
					map.put(prefixSum, 1);
				} else {
					map.put(prefixSum, 1 + val);
				}
			}
		}
		return count;
	}

	public static void main(String[] args) {
		int[] nums = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		int k = 0;
		int count = subarraySum(nums, k);
		System.out.println(count);
	}

}
