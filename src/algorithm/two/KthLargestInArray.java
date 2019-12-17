package algorithm.two;

import java.util.Arrays;
import java.util.Random;

public class KthLargestInArray {

	public static int findKthLargestNaive(int[] nums, int k) {
		Arrays.sort(nums);
		return nums[nums.length - k];
	}

	public static int findKthLargest(int[] nums, int k) {
		int kthLargest = quickSort(nums, (nums.length - k) + 1, 0, nums.length - 1);
		return kthLargest;
	}

	public static int findKthLargestIterative(int[] nums, int k) {
		int kthLargest = quickSortIterative(nums, (nums.length - k) + 1, 0, nums.length - 1);
		return kthLargest;
	}

	private static int quickSort(int[] nums, int k, int l, int r) {
		if (l > r) {
			return -1;
		}
		int p = partition(nums, l, r, k);
		if (p + 1 == k) {
			return nums[p];
		}
		if (k < p + 1) {
			return quickSort(nums, k, l, p - 1);
		} else {
			return quickSort(nums, k, p + 1, r);
		}
	}

	private static int quickSortIterative(int[] nums, int k, int l, int r) {
		int val = Integer.MAX_VALUE;
		while (l <= r) {
			int p = partition(nums, l, r, k);
			if (p + 1 == k) {
				val = nums[p];
				break;
			}
			if (k < p + 1) {
				r = p - 1;
			} else {
				l = p + 1;
			}
		}
		return val;
	}

	private static int partition(int[] nums, int l, int r, int k) {
		int swapIndex = l - 1;
		for (int i = l; i <= r - 1; ++i) {
			if (nums[i] < nums[r]) {
				++swapIndex;
				swap(nums, swapIndex, i);
			}
		}
		++swapIndex;
		swap(nums, swapIndex, r);
		return swapIndex;
	}

	private static void swap(int[] nums, int swapIndex, int i) {
		int temp = nums[swapIndex];
		nums[swapIndex] = nums[i];
		nums[i] = temp;
	}

	public static void main(String[] args) {
		int[] nums = getRandomInput();
		int k = 5;
		// System.out.println(Arrays.toString(nums));
		// System.out.println(Arrays.toString(nums));
		System.out.println(findKthLargest(nums, k));
		System.out.println(findKthLargestIterative(nums, k));
	}

	private static int[] getRandomInput() {
		int[] nums = new int[30];
		Random random = new Random();
		for (int i = 0; i <= nums.length - 1; ++i) {
			nums[i] = random.nextInt(10) + 1;
		}
		return nums;
	}

}