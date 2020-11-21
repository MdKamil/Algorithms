package algorithm.array;

public class FindPeakElement {

	public static int findPeakElement(int[] nums) {
		int peakIdx = -1;
		if (nums != null && nums.length > 0) {
			if (nums.length == 1) {
				peakIdx = 0;
			} else if (nums.length == 2) {
				peakIdx = nums[0] > nums[1] ? 0 : 1;
			} else {
				int front = 0;
				int back = nums.length - 1;
				if (nums[front] > nums[front + 1]) {
					peakIdx = front;
				} else if (nums[back] > nums[back - 1]) {
					peakIdx = back;
				} else {
					front = 1;
					back = nums.length - 2;
					while (front <= back) {
						if (nums[front] > nums[front - 1] && nums[front] > nums[front + 1]) {
							peakIdx = front;
							break;
						}
						if (nums[back] > nums[back - 1] && nums[back] > nums[back + 1]) {
							peakIdx = back;
							break;
						}
						++front;
						--back;
					}
				}
			}
		}
		return peakIdx;
	}

	public static void main(String[] args) {
		int[] nums = {2};
		int peakIdx = findPeakElement(nums);
		System.out.println(peakIdx);
	}

}
