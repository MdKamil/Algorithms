package algorithm.eight;

import java.util.Arrays;

public class WiggleSort {

	public static void wiggleSort(int[] nums) {
		if (nums != null && nums.length > 1) {
			Arrays.sort(nums);
			if (nums.length > 2) {
				int idxToBeReplaced = 1;
				int idx = 2;
				while (idxToBeReplaced < nums.length - 1) {
					if (nums[idxToBeReplaced] < nums[idx]) {
						int temp = nums[idxToBeReplaced];
						nums[idxToBeReplaced] = nums[idx];
						nums[idx] = temp;
						idxToBeReplaced += 2;
					}
					++idx;
				}
			}
		}
	}

	public static void main(String[] args) {
		int[] nums = { 2, 1, 1 };
		wiggleSort(nums);
		System.out.println(Arrays.toString(nums));
	}

}
