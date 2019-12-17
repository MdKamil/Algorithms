package algorithm.four;

public class JumpGame {

	public static boolean canJump(int[] nums) {
		boolean canJump = false;
		if (nums != null) {
			if (nums.length == 1) {
				canJump = true;
			} else {
				int currLargeIdx = 0;
				while (currLargeIdx < nums.length - 1) {
					int nextLargeIdx = currLargeIdx;
					if (nums[currLargeIdx] == 0) {
						canJump = false;
						break;
					}
					if (currLargeIdx + nums[currLargeIdx] >= nums.length - 1) {
						canJump = true;
						break;
					}
					int maxDistance = 0;
					for (int idx = currLargeIdx; idx <= nums.length - 1
							&& idx <= currLargeIdx + nums[currLargeIdx]; ++idx) {
						if (nums[idx] == 0) {
							continue;
						}
						int distance = idx + nums[idx];
						if (distance > maxDistance) {
							maxDistance = distance;
							nextLargeIdx = idx;
						}
					}
					if (nextLargeIdx == currLargeIdx) {
						canJump = false;
						break;
					}
					currLargeIdx = nextLargeIdx;
				}
			}
		}
		return canJump;
	}

	public static void main(String[] args) {
		int[] nums = { 1, 1, 2, 2, 0, 1, 1 };
		boolean canJump = canJump(nums);
		System.out.println(canJump);
	}

}
