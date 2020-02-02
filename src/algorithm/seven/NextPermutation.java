package algorithm.seven;

import java.util.Arrays;

public class NextPermutation {

	public static void nextPermutation(int[] nums) {
		if (nums != null && nums.length > 1) {
			int idx = nums.length - 2;
			for (; idx >= 0; --idx) {
				if (nums[idx] < nums[idx + 1]) {
					break;
				}
			}
			int front = idx + 1;
			int back = nums.length - 1;
			while (front < back) {
				int temp = nums[back];
				nums[back] = nums[front];
				nums[front] = temp;
				++front;
				--back;
			}
			if (idx >= 0) {
				front = idx + 1;
				back = nums.length - 1;
				int targetIdx = -1;
				int mid = -1;
				while (front <= back) {
					mid = (front + back) / 2;
					if (nums[mid] > nums[idx]) {
						targetIdx = mid;
						back = mid - 1;
					} else if (nums[mid] <= nums[idx]) {
						front = mid + 1;
					}
				}
				int temp = nums[idx];
				nums[idx] = nums[targetIdx];
				nums[targetIdx] = temp;
			}
		}
	}

	public static void main(String[] args) {
		int[] nums = { 1, 5, 6 };
		nextPermutation(nums);
		System.out.println(Arrays.toString(nums));
	}
}
