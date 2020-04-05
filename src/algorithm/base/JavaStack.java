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

}
