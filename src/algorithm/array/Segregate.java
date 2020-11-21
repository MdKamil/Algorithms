package algorithm.array;

import java.util.Arrays;

public class Segregate {

	private static void shiftV2(int[] nums) {
		int i = 0;
		int j = 0;
		while (j <= nums.length - 1) {
			if (nums[j] == 0) {
				swap(nums, i, j);
				++i;
				++j;
			} else {
				++j;
			}
		}
	}

	private static void swap(int[] a, int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

	public static void main(String[] args) {
		int[] nums = getInput1();
		System.out.println(Arrays.toString(nums) + "\n");
		shiftV2(nums);
		System.out.println(Arrays.toString(nums));
	}

	private static int[] getInput1() {
		java.util.Random rnd = new java.util.Random();
		int size = 20;
		int[] nums = new int[size];
		for (int i = 0; i <= nums.length - 1; ++i) {
			nums[i] = rnd.nextInt(2);
		}
		return nums;
	}
}
