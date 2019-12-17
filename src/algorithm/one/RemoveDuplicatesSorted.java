package algorithm.one;

import java.util.Arrays;

public class RemoveDuplicatesSorted {

	private static void removeDuplicate(int[] nums) {
		int i = 0;
		for (int j = i + 1; j <= nums.length - 1; ++j) {
			if (nums[j] == nums[i]) {
				nums[j] = 0;
			} else {
				if (j - i > 1) {
					nums[i + 1] = nums[j];
					nums[j] = 0;
				}
				++i;
			}
		}
		System.out.println(i + 1);
	}

	public static void main(String[] args) {
		int[] nums = { 0, 0, 1, 1, 1, 2, 2, 3, 3, 4 };
		System.out.println("Before removing");
		System.out.println(Arrays.toString(nums));
		removeDuplicate(nums);
		System.out.println("After removing");
		System.out.println(Arrays.toString(nums));
	}
}
