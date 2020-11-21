package algorithm.array;

public class DuplicateNumber {

	public static int findDuplicate(int[] nums) {
		int duplicateNo = -1;
		if (nums.length > 1) {
			int slow = nums[0];
			int fast = nums[nums[0]];
			while (slow != fast) {
				slow = nums[slow];
				fast = nums[nums[fast]];
			}

			fast = 0;
			while (fast != slow) {
				fast = nums[fast];
				slow = nums[slow];
			}
			return slow;
		}
		return duplicateNo;
	}

	public static void main(String[] args) {
		int[] nums = { 5, 2, 1, 3, 5, 7, 6, 4 };
		int duplicateNo = findDuplicate(nums);
		System.out.println(duplicateNo);
	}

}
