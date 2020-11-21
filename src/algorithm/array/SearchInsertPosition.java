package algorithm.array;

public class SearchInsertPosition {

	public static int searchInsert(int[] nums, int target) {
		int position = -1;
		if (nums != null) {
			if (nums.length == 0) {
				position = 0;
			} else {
				int left = 0;
				int right = nums.length - 1;
				while (left <= right) {
					int mid = (left + right) / 2;
					if (nums[mid] == target) {
						position = mid;
						break;
					} else if (nums[mid] > target) {
						right = mid - 1;
						position = mid;
					} else {
						left = mid + 1;
						position = mid + 1;
					}
				}
			}
		}
		return position;
	}

	public static void main(String[] args) {
		int[] nums = { 1, 3, 5, 7, 9 };
		int target = 20;
		int position = searchInsert(nums, target);
		System.out.println(position);
	}

}
