package algorithm.array;

public class SingleElementInSortedArray {

	public int singleNonDuplicate(int[] nums) {
		int start = 0;
		int end = nums.length - 1;
		int mid = (start + end) / 2;
		while (start <= end) {
			mid = (start + end) / 2;
			if (start == end) {
				break;
			} else if (nums[mid] == nums[mid - 1]) {
				int length = (mid - start) + 1;
				if (length % 2 != 0) {
					end = mid - 2;
				} else {
					start = mid + 1;
				}
			} else if (nums[mid] == nums[mid + 1]) {
				int length = (end - mid) + 1;
				if (length % 2 != 0) {
					start = mid + 2;
				} else {
					end = mid - 1;
				}
			} else {
				break;
			}
		}
		return nums[mid];
	}

	public static void main(String[] args) {
		int[] nums = { 1, 2, 2, 3, 3, 4, 4, 5, 5 };
		System.out.println(new SingleElementInSortedArray().singleNonDuplicate(nums));
	}
}
