package algorithm.sorting;

import java.util.Arrays;
import java.util.Random;

public class Sorting {

	public static void performQuickSort(int[] nums) {
		int l = 0;
		int r = nums.length - 1;
		quickSort(nums, l, r);
	}

	private static void quickSort(int[] nums, int l, int r) {
		if (l > r) {
			return;
		}
		int p = partition(nums, l, r);
		quickSort(nums, l, p - 1);
		quickSort(nums, p + 1, r);
	}

	private static int partition(int[] nums, int l, int r) {
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
		System.out.println("Before sorting");
		System.out.println(Arrays.toString(nums));
		performQuickSort(nums);
		System.out.println("Before sorting");
		System.out.println(Arrays.toString(nums));
	}

	private static int[] getRandomInput() {
		int[] nums = new int[3];
		Random random = new Random();
		for (int i = 0; i <= nums.length - 1; ++i) {
			nums[i] = random.nextInt(10);
		}
		return nums;
	}

}
