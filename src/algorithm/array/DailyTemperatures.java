package algorithm.array;

import java.util.ArrayDeque;
import java.util.Deque;

public class DailyTemperatures {
	public int[] dailyTemperatures(int[] temperatures) {
		Deque<Integer> stack = new ArrayDeque<>();
		int[] result = new int[temperatures.length];
		for (int idx = 0; idx <= temperatures.length - 2; ++idx) {
			if (temperatures[idx] < temperatures[idx + 1]) {
				result[idx] = 1;
				while (!stack.isEmpty() && temperatures[stack.peek()] < temperatures[idx + 1]) {
					int prevIdx = stack.pollFirst();
					result[prevIdx] = (idx + 1) - prevIdx;
				}
			} else {
				stack.addFirst(idx);
			}
		}
		stack.clear();
		return result;
	}
}
