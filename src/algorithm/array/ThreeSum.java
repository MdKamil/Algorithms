package algorithm.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {

	public static List<List<Integer>> threeSumNaiveWithDuplicates(int[] nums) {
		List<List<Integer>> list = new ArrayList<>();
		for (int i = 0; i <= nums.length - 3; ++i) {
			int localSum = nums[i];
			int sumBeforej = localSum;
			for (int j = i + 1; j <= nums.length - 2; ++j) {
				localSum += nums[j];
				int sumBeforeK = localSum;
				for (int k = j + 1; k <= nums.length - 1; ++k) {
					localSum += nums[k];
					if (localSum == 0) {
						list.add(Arrays.asList(nums[i], nums[j], nums[k]));
					}
					localSum = sumBeforeK;
				}
				localSum = sumBeforej;
			}
		}
		return list;
	}

	public static List<List<Integer>> threeSumV2(int[] nums) {
		List<List<Integer>> list = new ArrayList<>();
		Arrays.sort(nums);
		if (nums.length == 0 || nums[nums.length - 1] < 0) {
			return list;
		}
		int prevI = nums[0];
		int prevJ = -1;
		int prevK = -1;
		for (int i = 0; i <= nums.length - 3; ++i) {
			if (nums[i] >= 1) {
				break;
			}
			if (i != 0 && nums[i] == prevI) {
				continue;
			}
			int j = i + 1;
			int k = nums.length - 1;
			while (j < k) {
				int sum = nums[j] + nums[k];
				if (sum < 0) {
					++j;
				} else if (sum > -1 * nums[i]) {
					--k;
				} else if (sum < -1 * nums[i]) {
					++j;
				} else {
					// sum == -1 * nums[i]
					if (prevJ == -1 && prevK == -1) {
						list.add(Arrays.asList(nums[i], nums[j], nums[k]));
						prevJ = j;
						prevK = k;
					} else if (nums[j] != nums[prevJ] && nums[k] != nums[prevK]) {
						list.add(Arrays.asList(nums[i], nums[j], nums[k]));
						prevJ = j;
						prevK = k;
					}
					++j;
					--k;
				}
			}
			prevI = nums[i];
			prevJ = -1;
			prevK = -1;
		}
		return list;
	}

	public static List<List<Integer>> threeSumV3(int[] nums) {
		List<List<Integer>> list = new ArrayList<>();
		Arrays.sort(nums);
		if (nums.length == 0 || nums[nums.length - 1] < 0) {
			return list;
		}
		int prevI = nums[0];
		int prevJ = -1;
		int prevK = -1;
		for (int i = 0; i <= nums.length - 3; ++i) {
			if (nums[i] >= 1) {
				break;
			}
			if (i != 0 && nums[i] == prevI) {
				continue;
			}
			int j = i + 1;
			int k = nums.length - 1;
			while (j < k) {
				int sum = nums[i] + nums[j] + nums[k];
				if (sum == 0) {
					if (prevJ == -1 && prevK == -1) {
						list.add(Arrays.asList(nums[i], nums[j], nums[k]));
						prevJ = j;
						prevK = k;
					} else if (nums[j] != nums[prevJ] && nums[k] != nums[prevK]) {
						list.add(Arrays.asList(nums[i], nums[j], nums[k]));
						prevJ = j;
						prevK = k;
					}
					++j;
					--k;
				} else if (sum > 0) {
					--k;
				} else if (sum < 0) {
					++j;
				}
			}
			prevI = nums[i];
			prevJ = -1;
			prevK = -1;
		}
		return list;
	}

	public static void main(String[] args) {
		int[] nums = { 2, 3, 4, 5 };
		List<List<Integer>> bigList = threeSumV2(nums);
		for (List<Integer> list : bigList) {
			System.out.println(list);
		}
	}

}
