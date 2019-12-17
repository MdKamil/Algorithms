package algorithm.base;

import java.util.ArrayDeque;
import java.util.Deque;

public class JavaStack {

	private static void dequeAsStack() {
		// Example demonstrating using Deque as Stack;
		Deque<Integer> stack = new ArrayDeque<>();
		// add elements
		stack.addLast(10);
		stack.addLast(20);
		stack.addLast(30);
		stack.addLast(40);
		// peek
		stack.peekLast();
		// poll
		stack.pollLast();
	}

	private static void dequeAsQueue() {
		// Example demonstrating using Deque as Stack;
		Deque<Integer> deque = new ArrayDeque<>();
		// add elements
		deque.add(10);
		deque.add(20);
		deque.add(30);
		deque.add(40);
		// peek
		deque.peek();
		// poll
		deque.poll();
	}

	public static void main(String[] args) {

	}
}
