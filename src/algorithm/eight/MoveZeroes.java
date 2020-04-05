package algorithm.eight;

import java.util.Arrays;

public class MoveZeroes {

	public static void moveZeroes(int[] nums) {
		if (nums != null && nums.length > 0) {
			int toMoveIdx = 0;
			int idx = 0;
			for (; idx <= nums.length - 1; ++idx) {
				if (nums[idx] == 0) {
					toMoveIdx = idx;
					break;
				}
			}
			idx += 1;
			for (; idx <= nums.length - 1; ++idx) {
				if (nums[idx] != 0) {
					nums[toMoveIdx] = nums[idx];
					nums[idx] = 0;
					++toMoveIdx;
				}
			}
		}
	}

	public static void main(String[] args) {
		int[] nums = { 0, 1 };
		System.out.println(Arrays.toString(nums));
		moveZeroes(nums);
		System.out.println(Arrays.toString(nums));
	}
}
