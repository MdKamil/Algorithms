package algorithm.two;

import java.util.ArrayDeque;
import java.util.Deque;

public class LargestRectangle {
	
	
	// Logic: By keeping the deque elements in ascending order we can easily 
	// compute the largest rectangle.
	public static int largestRectangleArea(int[] heights) {
		int maxArea = Integer.MIN_VALUE;
		Deque<Integer> deque = new ArrayDeque<>();
		for (int idx = 0; idx <= heights.length; ++idx) {
			while (!deque.isEmpty() && ((idx == heights.length) || (heights[idx] < heights[deque.peekLast()]))) {
				int currIdx = deque.pollLast();
				int distRight = (idx - currIdx) - 1;
				int prevIdx = deque.peekLast() == null ? -1 : deque.peekLast();
				int distLeft = (currIdx - prevIdx);
				distLeft = (distLeft == 0 ? distLeft : distLeft - 1);
				int currRectArea = (heights[currIdx] * 1) + heights[currIdx] * (distRight + distLeft);
				maxArea = Math.max(maxArea, currRectArea);
			}
			if (idx <= heights.length - 1) {
				deque.add(idx);
			}
		}
		return maxArea;
	}

	public static int calculateLargestRectangleNaive(int[] heights) {
		int maxRect = 0;
		for (int i = 0; i <= heights.length - 1; ++i) {
			int count = 0;
			// traverse left
			for (int j = i - 1; j >= 0; --j) {
				if (heights[i] <= heights[j]) {
					++count;
				} else {
					break;
				}
			}
			// traverse right
			for (int j = i + 1; j <= heights.length - 1; ++j) {
				if (heights[i] <= heights[j]) {
					++count;
				} else {
					break;
				}
			}
			++count;
			maxRect = Math.max(maxRect, count * heights[i]);
		}
		return maxRect;
	}

	public static void main(String[] args) {
		//int[] heights = { 8, 9, 14, 13, 2, 6, 11, 2, 7, 7, 5, 10, 9, 9 };
		int[] heights = {3,4,5,2};
		System.out.println(largestRectangleArea(heights));
	}

}
