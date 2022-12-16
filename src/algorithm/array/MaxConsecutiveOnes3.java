package algorithm.array;

import java.util.ArrayDeque;
import java.util.Deque;

public class MaxConsecutiveOnes3 {

	// with Deque
	public int longestOnes(int[] nums, int k) {
		int maxLength = 0;
		Deque<Integer> deque = new ArrayDeque<>();
		int count = 0;
		for (int num : nums) {
			if (num == 0) {
				++count;
			}
			if (count == k + 1) {
				while (!deque.isEmpty()) {
					if (deque.pollFirst() == 0) {
						break;
					}
				}
				count = (k == 0 ? 0 : count - 1);
			}
			if (num == 1 || (num == 0 && k != 0)) {
				deque.addLast(num);
			}
			maxLength = Math.max(maxLength, deque.size());
		}
		deque.clear();
		return maxLength;
	}

	// without Deque
	public int longestOnesV2(int[] nums, int k) {
		int maxLength = 0;
		int count = 0;
		int start = 0;
		for (int idx = 0; idx <= nums.length - 1; ++idx) {
			if (nums[idx] == 0) {
				++count;
			}
			if (count == k + 1) {
				while (nums[start++] != 0);
				count = (k == 0 ? 0 : count - 1);
			}
			maxLength = Math.max(maxLength, (idx - start) + 1);
		}
		return maxLength;
	}

	public static void main(String[] args) {
		int k = 0;
		int[] nums = { 1, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 1, 1, 0, 0 };
		System.out.println(new MaxConsecutiveOnes3().longestOnes(nums, k));
	}
}
