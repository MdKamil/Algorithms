package algorithm.two;

public class FirstMissingPositive {

	public static int firstMissingPositive(int[] nums) {
		int missing = 0;
		int i = 0;
		int j = nums.length - 1;
		// Re-order
		while (i <= j) {
			if (nums[j] <= 0) {
				--j;
			} else if (nums[i] <= 0) {
				swap(nums, i, j);
				++i;
				--j;
			} else {
				++i;
			}
		}
		int MAX = i;
		for (int iter = 0; iter <= MAX - 1; ++iter) {
			int target = nums[iter];
			while (target <= MAX && target != nums[target - 1]) {
				int temp = nums[iter];
				nums[iter] = nums[target - 1];
				nums[target - 1] = temp;
				target = nums[iter];
			}
		}
		for (int iter = 0; iter <= MAX - 1; ++iter) {
			if (nums[iter] != iter + 1) {
				missing = iter + 1;
				break;
			}
		}
		if (missing == 0) {
			missing = MAX + 1;
		}
		return missing;
	}

	public static int firstMissingPositiveV2(int[] nums) {
		int missing = 0;
		for (int iter = 0; iter <= nums.length - 1; ++iter) {
			int target = nums[iter];
			while (target <= nums.length && target > 0 && target != nums[target - 1]) {
				int temp = nums[iter];
				nums[iter] = nums[target - 1];
				nums[target - 1] = temp;
				target = nums[iter];
			}
		}
		for (int iter = 0; iter <= nums.length - 1; ++iter) {
			if (nums[iter] != iter + 1) {
				missing = iter + 1;
				break;
			}
		}
		if (missing == 0) {
			missing = nums.length + 1;
		}
		return missing;
	}

	private static void swap(int[] nums, int i, int j) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}

	public static void main(String[] args) {
		int[] nums = { 3, 4, -1, 7, 11 };
		int missing = firstMissingPositiveV2(nums);
		System.out.println("Mising element: " + Integer.toString(missing));
	}
}
