package algorithm.array;

import java.util.Arrays;
import java.util.Comparator;

public class LargestNumber {

	public static String largestNumber(int[] nums) {
		String largestNumber = null;
		if (nums != null && nums.length > 0) {
			String[] array = new String[nums.length];
			for (int idx = 0; idx <= nums.length - 1; ++idx) {
				array[idx] = Integer.toString(nums[idx]);
			}
			Comparator<String> c = new Comparator<String>() {
				@Override
				public int compare(String firstNum, String secondNum) {
					return firstNum.concat(secondNum).compareTo(secondNum.concat(firstNum));
				}
			};
			Arrays.sort(array, c);
			largestNumber = array[array.length - 1];
			if (!largestNumber.equals("0")) {
				for (int idx = array.length - 2; idx >= 0; --idx) {
					largestNumber = largestNumber.concat(array[idx]);
				}
			}
		}
		return largestNumber;
	}

	public static void main(String[] args) {
		int[] nums = { 0, 0 };
		String largestNum = largestNumber(nums);
		System.out.println(largestNum);
	}

}
