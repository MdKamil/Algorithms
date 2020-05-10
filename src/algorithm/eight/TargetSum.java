package algorithm.eight;

public class TargetSum {

	public static int findTargetSumWays(int[] nums, int S) {
		int noOfWays = 0;
		if (nums != null && nums.length > 0) {
			int currentSum = 0;
			int idx = 0;
			noOfWays = getCombinations(nums, idx, S, currentSum);
		}
		return noOfWays;
	}

	private static int getCombinations(int[] nums, int idx, int targetSum, int currentSum) {
		int noOfWays = 0;
		currentSum += nums[idx];
		if (idx == nums.length - 1) {
			if (currentSum == targetSum) {
				++noOfWays;
			}
		} else {
			noOfWays += getCombinations(nums, idx + 1, targetSum, currentSum);
		}
		currentSum -= nums[idx];

		currentSum += -nums[idx];
		if (idx == nums.length - 1) {
			if (currentSum == targetSum) {
				++noOfWays;
			}
		} else {
			noOfWays += getCombinations(nums, idx + 1, targetSum, currentSum);
		}
		currentSum += nums[idx];

		return noOfWays;
	}

	public static void main(String[] args) {
		int[] nums = { 1, 2, 3, 4, 5 };
		int S = 3;
		int noOfWays = findTargetSumWays(nums, S);
		System.out.println(noOfWays);
	}

}
