package algorithm.two;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class SlidingWindowMax {

	private static int[] maxSlidingWindow(int[] nums, int k) {
		int[] result = new int[nums.length - (k - 1)];
		int j = 0;
		Deque<Integer> deque = new ArrayDeque<Integer>();
		for (int i = 0; i <= nums.length - 1; ++i) {
			if (deque.isEmpty()) {
				deque.addLast(i);
			} else {
				while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
					deque.pollLast();
				}
				deque.addLast(i);
			}
			if (i + 1 >= k) {
				int idx = deque.peek();
				result[j] = nums[idx];
				++j;
				if (idx + (k - 1) == i) {
					deque.pollFirst();
				}
			}
		}
		return result;
	}

	public static void main(String[] args) {
		int[] nums = { 15, 14, 13, 12, 11, 16, 10, 9, 8, 7, 19, 6, 5, 4, 3, 20, 2, 25 };
		int k = 5;
		int[] result = maxSlidingWindow(nums, k);
		System.out.println(Arrays.toString(result));
	}

}
