package algorithm.four;

public class IncreasingTripletSequence {

	public static boolean increasingTriplet(int[] nums) {
		boolean isPresent = false;
		int currLowIdx = 0;
		int currMidIdx = -1;
		for (int i = 1; i <= nums.length - 1; ++i) {
			if (currMidIdx != -1 && nums[i] > nums[currMidIdx]) {
				isPresent = true;
				break;
			} else if (nums[i] < nums[currLowIdx]) {
				currLowIdx = i;
			} else if (nums[i] > nums[currLowIdx]) {
				currMidIdx = i;
			}
		}
		return isPresent;
	}

}
