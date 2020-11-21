package algorithm.array;

import java.util.Arrays;

public class RotateArray {

	public static void rotate(int[] nums, int k) {
		if (nums != null && nums.length > 1) {
			if (k % nums.length != 0) {
				if (k > nums.length) {
					k = k % nums.length;
				}
				int leftStart = 0;
				int leftEnd = (nums.length - 1) - k;
				int rightStart = leftEnd + 1;
				int rightEnd = nums.length - 1;
				while (true) {
					int leftSize = (leftEnd - leftStart) + 1;
					int rightSize = (rightEnd - rightStart) + 1;
					if (leftSize == rightSize) {
						// Base condition for coming out of loop
						for (int idx = rightStart; idx <= rightEnd; ++idx, ++rightStart, ++leftStart) {
							int temp = nums[leftStart];
							nums[leftStart] = nums[rightStart];
							nums[rightStart] = temp;
						}
						break;
					} else if (leftSize > rightSize) {
						for (int idx = rightEnd; idx >= rightStart; --idx, --rightEnd, --leftEnd) {
							int temp = nums[leftEnd];
							nums[leftEnd] = nums[rightEnd];
							nums[rightEnd] = temp;
						}
						rightStart -= rightSize;
					} else {
						for (int idx = leftStart; idx <= leftEnd; ++idx, ++rightStart, ++leftStart) {
							int temp = nums[leftStart];
							nums[leftStart] = nums[rightStart];
							nums[rightStart] = temp;
						}
						leftEnd += leftSize;
					}
				}
			}
		}
	}

	public static void main(String[] args) {
		int[] nums = { 1, 2 };
		int k = 3;
		rotate(nums, k);
		System.out.println(Arrays.toString(nums));
	}

}
