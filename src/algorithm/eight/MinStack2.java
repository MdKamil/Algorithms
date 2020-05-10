package algorithm.eight;

import java.util.ArrayDeque;
import java.util.Deque;

public class MinStack2 {

	private static class MinCounter {
		int minValue;
		int count;

		public MinCounter(int minValue, int count) {
			this.minValue = minValue;
			this.count = count;
		}
	}

	private Deque<Integer> deque;
	private Deque<MinCounter> minCounterDeque;

	/** initialize your data structure here. */
	public MinStack2() {
		deque = new ArrayDeque<>();
		minCounterDeque = new ArrayDeque<>();
	}

	/** Push element x onto stack. */
	public void push(int x) {
		deque.addLast(x);
		if (minCounterDeque.size() == 0) {
			minCounterDeque.addLast(new MinCounter(x, 1));
		} else {
			if (minCounterDeque.peekLast().minValue <= x) {
				++minCounterDeque.peekLast().count;
			} else {
				minCounterDeque.addLast(new MinCounter(x, 1));
			}
		}
	}

	/** Removes the element on top of the stack. */
	public void pop() {
		deque.pollLast();
		if (minCounterDeque.peekLast().count == 1) {
			minCounterDeque.pollLast();
		} else {
			--minCounterDeque.peekLast().count;
		}
	}

	/** Get the top element. */
	public int top() {
		return deque.peekLast();
	}

	/** Retrieve the minimum element in the stack. */
	public int getMin() {
		return minCounterDeque.peekLast().minValue;
	}

}
