package algorithm.base;

import java.util.*;

public class Driver {

	public static int maxSubArray(int[] nums) {
		int maxSum = Integer.MIN_VALUE;
		if (nums != null && nums.length > 0) {
			int maxUntilNow = 0;
			for (int num : nums) {
				maxUntilNow += num;
				maxSum = Math.max(maxSum, maxUntilNow);
				if (maxUntilNow < 0) {
					maxUntilNow = 0;
				}
			}
		}
		return maxSum;
	}

	public static void main(String[] args) {
		int[] nums = { -2, 1, -3, 4, -1, 2, 1, -5, 4 };
		int maxSum = maxSubArray(nums);
		System.out.println(maxSum);
	}
}
