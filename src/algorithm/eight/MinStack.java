package algorithm.eight;

import java.util.ArrayDeque;
import java.util.Deque;

public class MinStack {

	private Deque<Integer> deque;
	private Deque<Integer> minElement;

	/** initialize your data structure here. */
	public MinStack() {
		deque = new ArrayDeque<>();
		minElement = new ArrayDeque<>();
	}

	public void push(int x) {
		deque.addLast(x);
		if (minElement.size() == 0) {
			minElement.addLast(x);
		} else {
			int min = minElement.peekLast();
			if (min <= x) {
				minElement.addLast(min);
			} else {
				minElement.addLast(x);
			}
		}
	}

	public void pop() {
		deque.pollLast();
		minElement.pollLast();
	}

	public int top() {
		return deque.peekLast();
	}

	public int getMin() {
		return minElement.peekLast();
	}
}
