package algorithm.three;

public class MajorityElement {

	// Boyer-Moore Majority voting algorithm

	public static int majorityElement(int[] nums) {
		int majorityCount = 0;
		int majorityElementIdx = -1;
		for (int i = 0; i <= nums.length - 1; ++i) {
			if (majorityCount == 0) {
				++majorityCount;
				majorityElementIdx = i;
			} else if (nums[i] == nums[majorityElementIdx]) {
				++majorityCount;
			} else {
				--majorityCount;
			}
		}
		if (majorityCount > nums.length / 2) {
			return nums[majorityElementIdx];
		} else {
			return majorityElementIdx;
		}
	}

}
