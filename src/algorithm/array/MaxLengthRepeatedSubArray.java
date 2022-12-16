package algorithm.array;

public class MaxLengthRepeatedSubArray {
	public int findLength(int[] nums1, int[] nums2) {
		int maxLength = 0;
		int[][] dp = new int[nums1.length][nums2.length];
		for (int idx1 = 0; idx1 <= nums1.length - 1; ++idx1) {
			for (int idx2 = 0; idx2 <= nums2.length - 1; ++idx2) {
				if (nums1[idx1] == nums2[idx2]) {
					if (idx1 - 1 >= 0 && idx2 - 1 >= 0) {
						dp[idx1][idx2] = dp[idx1 - 1][idx2 - 1] + 1;
					} else {
						dp[idx1][idx2] = 1;
					}
					maxLength = Math.max(maxLength, dp[idx1][idx2]);
				}
			}
		}
		return maxLength;
	}

	public static void main(String[] args) {
		int[] nums1 = { 0, 0, 0, 0, 0, 1, 1, 1, 0, 1, 0, 1, 1, 0, 0, 0, 1, 0, 0, 1, 1, 0, 1, 0, 1, 1, 1, 0, 0, 1, 0, 1,
				1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 0, 1,
				1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 0, 0, 0, 1, 0, 0, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 0, 1, 0, 0, 0, 1 };
		int[] nums2 = { 1, 0, 1, 1, 0, 1, 1, 1, 0, 0, 0, 0, 0, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 0, 1,
				1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 0, 0, 0, 1, 0, 0, 1, 1, 0, 1, 0, 1, 0, 1, 1, 0, 1, 1, 0, 1, 0, 0, 0, 1, 0,
				1, 0, 1, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 0, 0, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0 };
		System.out.println(new MaxLengthRepeatedSubArray().findLength(nums1, nums2));
	}
}
