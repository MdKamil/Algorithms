package algorithm.array;

public class SingleNumber {

	public static int singleNumber(int[] nums) {
		int singleNo = -1;
		if (nums != null && nums.length > 0) {
			singleNo = nums[0];
			for (int idx = 1; idx <= nums.length - 1; ++idx) {
				singleNo ^= nums[idx];
			}
		}
		return singleNo;
	}

	public static void main(String[] args) {
		int[] nums = { 4, 1, 2, 1, 2 };
		int singleNo = singleNumber(nums);
		System.out.println(singleNo);
	}

}
