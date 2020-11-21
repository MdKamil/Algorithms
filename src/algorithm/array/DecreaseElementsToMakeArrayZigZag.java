package algorithm.array;

public class DecreaseElementsToMakeArrayZigZag {

	public static int movesToMakeZigzag(int[] nums) {
		int minMoves = Integer.MAX_VALUE;
		if (nums != null && nums.length > 1) {
			int currLeft = nums[0] - 1;
			int currRight = nums[1];
			int minMoveEvenIdx = 0;
			int minMoveOddIdx = 0;
			int decrease = 0;
			for (int i = 0; i <= nums.length - 1; i += 2) {
				currRight = (i + 1 <= nums.length - 1) ? nums[i + 1] : nums[i] - 1;
				if (nums[i] <= currLeft) {
					decrease = (currLeft - nums[i]) + 1;
					currLeft = currLeft - decrease;
					minMoveEvenIdx += decrease;
				}
				if (i + 1 <= nums.length - 1 && nums[i] <= currRight) {
					decrease = (currRight - nums[i]) + 1;
					currRight = currRight - decrease;
					minMoveEvenIdx += decrease;
				}
				currLeft = currRight;
			}
			minMoves = Math.min(minMoves, minMoveEvenIdx);

			currLeft = nums[0];
			for (int i = 1; i <= nums.length - 1; i += 2) {
				currRight = (i + 1 <= nums.length - 1) ? nums[i + 1] : nums[i] - 1;
				if (nums[i] <= currLeft) {
					decrease = (currLeft - nums[i]) + 1;
					currLeft = currLeft - decrease;
					minMoveOddIdx += decrease;
				}
				if (i + 1 <= nums.length - 1 && nums[i] <= currRight) {
					decrease = (currRight - nums[i]) + 1;
					currRight = currRight - decrease;
					minMoveOddIdx += decrease;
				}
				currLeft = currRight;
			}
			minMoves = Math.min(minMoveEvenIdx, minMoveOddIdx);
		}
		return minMoves;
	}

	public static void main(String[] args) {
		int[] nums = { 1, 2, 3, 4, 5, 6, 7 };
		int minMOves = movesToMakeZigzag(nums);
		System.out.println(minMOves);
	}

}
