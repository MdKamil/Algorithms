package algorithm.array;

public class RotatedSortedSearch {

	public static int search(int[] nums, int target) {
		int index = -1;
		if (nums != null && nums.length > 0) {
			int left = 0;
			int right = nums.length - 1;
			while (left <= right) {
				int mid = (left + right) / 2;
				if (nums[mid] == target) {
					index = mid;
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
		return index;
	}

	public static void main(String[] args) {
		int[] nums = { 3, 1 };
		int target = 3;
		int index = search(nums, target);
		if (index != -1) {
			System.out.println(index);
		} else {
			System.out.println("Not found");
		}
	}

}
