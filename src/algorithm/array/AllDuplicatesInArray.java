package algorithm.array;

import java.util.ArrayList;
import java.util.List;

public class AllDuplicatesInArray {
	public List<Integer> findDuplicates(int[] nums) {
		List<Integer> dupList = new ArrayList<>();
		for (int idx = 0; idx <= nums.length - 1; ++idx) {
			while (nums[idx] != idx + 1 && nums[idx] != nums[nums[idx] - 1]) {
				int mismatchNo = nums[idx];
				nums[idx] = nums[nums[idx] - 1];
				nums[mismatchNo - 1] = mismatchNo;
			}
		}
		for (int idx = 0; idx <= nums.length - 1; ++idx) {
			if (nums[idx] != idx + 1) {
				dupList.add(nums[idx]);
			}
		}
		return dupList;
	}

	public static void main(String[] args) {
		int[] nums = {1};
		System.out.println(new AllDuplicatesInArray().findDuplicates(nums));
	}
}
