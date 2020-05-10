package algorithm.eight;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PartitionEqualSubsetSum {

	public static boolean canPartition(int[] nums) {
		boolean isPossible = false;
		if (nums != null && nums.length > 1) {
			int sum = 0;
			for (int num : nums) {
				sum += num;
			}
			if (sum % 2 == 0) {
				Arrays.sort(nums);
				int halfSum = sum / 2;
				int currSum = 0;
				int fromIdx = 0;
				isPossible = permute(fromIdx, nums, halfSum, currSum);
			}
		}
		return isPossible;
	}

	// TLE
	private static boolean permuteV1(int fromIdx, int[] nums, int expectedSum, int currSum) {
		boolean isPossible = false;
		for (int idx = fromIdx; idx <= nums.length - 1; ++idx) {
			currSum += nums[idx];
			if (currSum == expectedSum) {
				isPossible = true;
			} else if (currSum < expectedSum) {
				isPossible = permute(idx + 1, nums, expectedSum, currSum);
				if (isPossible) {
					break;
				}
				currSum -= nums[idx];
			} else {
				break;
			}
		}
		return isPossible;
	}

	private static boolean permute(int fromIdx, int[] nums, int expectedSum, int currSum) {
		boolean isPossible = false;
		for (int idx = fromIdx; idx <= nums.length - 1; ++idx) {
			if (idx != fromIdx && nums[idx] == nums[fromIdx]) {
				continue;
			}
			currSum += nums[idx];
			if (currSum == expectedSum) {
				isPossible = true;
			} else if (currSum < expectedSum) {
				isPossible = permute(idx + 1, nums, expectedSum, currSum);
				if (isPossible) {
					break;
				}
				currSum -= nums[idx];
			} else {
				break;
			}
		}
		return isPossible;
	}

	public static void main(String[] args) {
		int[] nums = { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
				1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
				1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 100 };
		boolean isPossible = canPartition(nums);
		System.out.println(isPossible);
	}

	private static int[] getRandom(int arraySize, int maxLimit) {
		int[] nums = new int[arraySize];
		java.util.Random random = new java.util.Random();
		for (int i = 0; i <= nums.length - 1; ++i) {
			nums[i] = 1 + random.nextInt(maxLimit);
		}
		return nums;
	}

}
