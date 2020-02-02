package algorithm.seven;

public class HouseRobber {
	public static int rob(int[] nums) {
		int maxAmount = 0;
		if (nums != null && nums.length > 0) {
			if (nums.length == 1) {
				maxAmount = nums[0];
			} else if (nums.length == 2) {
				maxAmount = Math.max(nums[0], nums[1]);
			} else if (nums.length == 3) {
				maxAmount = Math.max(nums[0] + nums[2], nums[1]);
			} else {
				int[] amount = new int[nums.length];
				amount[nums.length - 1] = nums[nums.length - 1];
				amount[nums.length - 2] = nums[nums.length - 2];
				amount[nums.length - 3] = nums[nums.length - 3] + nums[nums.length - 1];
				maxAmount = Math.max(amount[nums.length - 3], amount[nums.length - 2]);
				for (int idx = nums.length - 4; idx >= 0; --idx) {
					amount[idx] = Math.max(nums[idx] + amount[idx + 2], nums[idx] + amount[idx + 3]);
					maxAmount = Math.max(maxAmount, amount[idx]);
				}
			}
		}
		return maxAmount;
	}

	public static void main(String[] args) {
		int[] nums = { 1, 2, 1, 1 };
		int maxAmount = rob(nums);
		System.out.println(maxAmount);
	}
}
