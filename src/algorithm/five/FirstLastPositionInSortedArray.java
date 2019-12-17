package algorithm.five;

public class FirstLastPositionInSortedArray {

	public static int[] searchRange(int[] nums, int target) {
		int[] position = new int[] { -1, -1 };
		if (nums != null && nums.length > 0) {
			int left = 0;
			int right = nums.length - 1;
			int mid = (left + right) / 2;
			if (target == nums[mid] && ((nums[left] == nums[mid]) && nums[right] == nums[mid])) {
				position[0] = left;
				position[1] = right;
			} else {
				boolean isFound = false;
				while (left <= right) {
					mid = (left + right) / 2;
					if (nums[mid] == target) {
						isFound = true;
						break;
					}
					if (nums[mid] < target) {
						left = mid + 1;
					} else if (nums[mid] > target) {
						right = mid - 1;
					}
				}
				if (isFound) {
					int firstPosition = mid;
					int lastPosition = mid;
					firstPosition = findLeftPosition(nums, left, mid - 1, target, firstPosition);
					lastPosition = findRightPosition(nums, mid + 1, right, target, lastPosition);
					position[0] = firstPosition;
					position[1] = lastPosition;
				}
			}
		}
		return position;
	}

	private static int findLeftPosition(int[] nums, int left, int right, int target, int currLeftPos) {
		int mid = (left + right) / 2;
		while (left <= right) {
			mid = (left + right) / 2;
			if (nums[mid] == target) {
				currLeftPos = mid;
				right = mid - 1;
			}
			if (nums[mid] < target) {
				left = mid + 1;
			} else if (nums[mid] > target) {
				right = mid - 1;
			}
		}
		return currLeftPos;
	}

	private static int findRightPosition(int[] nums, int left, int right, int target, int currRightPos) {
		int mid = (left + right) / 2;
		while (left <= right) {
			mid = (left + right) / 2;
			if (nums[mid] == target) {
				currRightPos = mid;
				left = mid + 1;
			}
			if (nums[mid] < target) {
				left = mid + 1;
			} else if (nums[mid] > target) {
				right = mid - 1;
			}
		}
		return currRightPos;
	}

	public static void main(String[] args) {
		int[] nums = { 1 };
		int target = 0;
		int[] position = searchRange(nums, target);
		System.out.println("FirstPosition: " + position[0]);
		System.out.println("LastPosition: " + position[1]);
	}

}
