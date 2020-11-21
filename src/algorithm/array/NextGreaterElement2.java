package algorithm.array;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class NextGreaterElement2 {

	public static int[] nextGreaterElements(int[] nums) {
		int[] result = null;
		if (nums != null) {
			if (nums.length == 0) {
				result = new int[0];
			} else {
				result = new int[nums.length];
				Deque<Integer> stack = new ArrayDeque<>();
				int currLowIdx = 0;
				if (nums.length > 1) {
					for (int idx = 1; idx <= nums.length - 1; ++idx) {
						if (nums[idx] > nums[currLowIdx]) {
							result[currLowIdx] = nums[idx];
							currLowIdx = idx;
							while (!stack.isEmpty() && nums[stack.peekLast()] < nums[currLowIdx]) {
								int prevIdx = stack.pollLast();
								result[prevIdx] = nums[currLowIdx];
							}
						} else {
							stack.addLast(currLowIdx);
							currLowIdx = idx;
						}
					}
					stack.addLast(currLowIdx);
				} else {
					stack.addLast(currLowIdx);
				}
				for (int idx = 0; idx <= stack.peekFirst();) {
					if (nums[idx] > nums[stack.peekLast()]) {
						result[stack.pollLast()] = nums[idx];
					} else {
						++idx;
					}
				}
				while (!stack.isEmpty()) {
					result[stack.pollLast()] = -1;
				}
			}
		}
		return result;
	}

	public static void main(String[] args) {
		int[] nums = { 100, 1, 11, 1, 120, 111, 123, 1, -1, -100 };
		int[] result = nextGreaterElements(nums);
		System.out.println(Arrays.toString(result));
	}

}
