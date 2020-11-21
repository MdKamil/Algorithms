package algorithm.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FourSum {
	public static List<List<Integer>> fourSum(int[] nums, int target) {
		List<List<Integer>> masterSumList = new ArrayList<>();
		if (nums != null && nums.length > 3) {
			Arrays.sort(nums);
			for (int idx1 = 0; idx1 <= nums.length - 4; ++idx1) {
				if (idx1 > 0 && nums[idx1] == nums[idx1 - 1]) {
					continue;
				}
				for (int idx2 = idx1 + 1; idx2 <= nums.length - 3; ++idx2) {
					if (idx2 > idx1 + 1 && nums[idx2] == nums[idx2 - 1]) {
						continue;
					}
					int front = idx2 + 1;
					int back = nums.length - 1;
					while (front < back) {
						int sum = nums[idx1] + nums[idx2] + nums[front] + nums[back];
						if (sum == target) {
							if (front == idx2 + 1
									|| (nums[front] != nums[front - 1] && nums[front] != nums[front - 1])) {
								masterSumList.add(Arrays.asList(nums[idx1], nums[idx2], nums[front], nums[back]));
							}
							++front;
							--back;
						} else if (sum > target) {
							--back;
						} else {
							++front;
						}
					}
				}
			}
		}
		return masterSumList;
	}

	public static void main(String[] args) {
		int[] nums = { 1, 0, -1, 0, -2, 2 };
		int target = 0;
		List<List<Integer>> masterSumList = fourSum(nums, target);
		for (List<Integer> list : masterSumList) {
			System.out.println(list);
		}
	}

}
