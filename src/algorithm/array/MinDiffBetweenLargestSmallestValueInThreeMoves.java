package algorithm.array;

import java.util.Arrays;

public class MinDiffBetweenLargestSmallestValueInThreeMoves {

	public static int minDifference(int[] nums) {
		int n = nums.length, res = Integer.MAX_VALUE;
		if (n < 5)
			return 0;
		Arrays.sort(nums);
		for (int i = 0; i < 4; ++i) {
			res = Math.min(res, nums[n - 4 + i] - nums[i]);
		}
		return res;
	}

	public static void main(String[] args) {
		int[] nums = { 1, 5, 6, 14, 15 };
		System.out.println(minDifference(nums));
	}

}
