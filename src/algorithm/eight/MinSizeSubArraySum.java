package algorithm.eight;

public class MinSizeSubArraySum {

	public static int minSubArrayLen(int s, int[] nums) {
		int minLength = 0;
		if (nums != null && nums.length > 0) {
			minLength = Integer.MAX_VALUE;
			int sumUntilNow = 0;
			int start = 0;
			for (int idx = 0; idx <= nums.length - 1; ++idx) {
				sumUntilNow += nums[idx];
				if (sumUntilNow == s) {
					minLength = Math.min(minLength, (idx - start) + 1);
					sumUntilNow -= nums[start];
					++start;
				} else if (sumUntilNow > s) {
					minLength = Math.min(minLength, (idx - start) + 1);
					while (true) {
						sumUntilNow -= nums[start];
						++start;
						if (sumUntilNow == s) {
							minLength = Math.min(minLength, (idx - start) + 1);
							sumUntilNow -= nums[start];
							++start;
							break;
						} else if (sumUntilNow < s) {
							break;
						} else {
							minLength = Math.min(minLength, (idx - start) + 1);
						}
					}
				}
			}
			if (minLength == Integer.MAX_VALUE) {
				minLength = 0;
			}
		}
		return minLength;
	}

	public static void main(String[] args) {
		int[] nums = { 2, 3, 1, 2, 4, 3 };
		int s = 7;
		int minLength = minSubArrayLen(s, nums);
		System.out.println(minLength);
	}

}
