package algorithm.array;

public class MajorityElement {
	
	public static boolean isMajorityElement(int[] nums, int target) {
		boolean isMajority = false;
		int count = 0;
		for (int i = 0; i <= nums.length - 1; ++i) {
			if (nums[i] == target) {
				++count;
			}
		}
		if (count > nums.length / 2) {
			isMajority = true;
		}
		return isMajority;
	}
}
