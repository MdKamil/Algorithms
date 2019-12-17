package algorithm.three;

import java.util.Arrays;

public class SortColors {

	public static void sortColors(int[] nums) {
		if (nums == null) {
			return;
		}
		int i = 0;
		int j = 0;
		int n = nums.length - 1;
		int midElement = 1;
		while (j <= n) {
			if (nums[j] < midElement) {
				swap(i, j, nums);
				++i;
				++j;
			} else if (nums[j] == midElement) {
				++j;
			} else {
				swap(j, n, nums);
				--n;
			}
		}
	}

	private static void swap(int i, int j, int[] nums) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}

	public static void main(String[] args) {
		int[] nums = { 0, 1, 2, 2, 1, 0, 1, 2, 1, 0, 1, 2, 0, 1, 0, 2, 0, 1, 2, 0 };
		sortColors(nums);
		System.out.println(Arrays.toString(nums));
	}

}
