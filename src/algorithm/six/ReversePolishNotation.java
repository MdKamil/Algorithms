package algorithm.six;

import java.util.ArrayDeque;
import java.util.Deque;

public class ReversePolishNotation {

	public static int evalRPN(String[] tokens) {
		int value = 0;
		if (tokens != null && tokens.length > 0) {
			Deque<Integer> stack = new ArrayDeque<>();
			for (String token : tokens) {
				if (token.equals("+")) {
					int a = stack.pollLast();
					int b = stack.pollLast();
					stack.addLast(b + a);
				} else if (token.equals("-")) {
					int a = stack.pollLast();
					int b = stack.pollLast();
					stack.addLast(b - a);
				} else if (token.equals("*")) {
					int a = stack.pollLast();
					int b = stack.pollLast();
					stack.addLast(b * a);
				} else if (token.equals("/")) {
					int a = stack.pollLast();
					int b = stack.pollLast();
					stack.addLast(b / a);
				} else {
					stack.addLast(Integer.valueOf(token));
				}
			}
			value = stack.pollLast();
		}
		return value;
	}

}
