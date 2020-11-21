package algorithm.array;

public class RotatedSortedSearchDuplicates {

	public static boolean search(int[] nums, int target) {
		boolean isFound = false;
		if (nums != null && nums.length > 0) {
			int left = 0;
			int right = nums.length - 1;
			int mid = (left + right) / 2;
			if (nums[left] == nums[mid] && nums[mid] == nums[right]) {
				boolean inLeft = false;
				for (int i = mid - 1; i >= 0; --i) {
					if (nums[i] != nums[mid]) {
						inLeft = true;
						right = i;
						break;
					}
				}
				if (!inLeft) {
					for (int i = mid + 1; i <= nums.length - 1; ++i) {
						if (nums[i] != nums[mid]) {
							left = i;
							break;
						}
					}
				}
			}
			while (left <= right) {
				mid = (left + right) / 2;
				if (nums[mid] == target) {
					isFound = true;
					break;
				}
				if (nums[left] > nums[mid] && target >= nums[left]) {
					right = mid - 1;
				} else if (nums[right] < nums[mid] && target <= nums[right]) {
					left = mid + 1;
				} else if (target > nums[mid]) {
					left = mid + 1;
				} else if (target < nums[mid]) {
					right = mid - 1;
				}
			}
		}
		return isFound;
	}

	public static void main(String[] args) {
		int[] nums = { 3, 1 };
		int target = 3;
		boolean isFound = search(nums, target);
		if (isFound) {
			System.out.println("Present");
		} else {
			System.out.println("Not present");
		}
	}

}
