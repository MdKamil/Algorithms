package algorithm.string;

import java.util.ArrayDeque;
import java.util.Deque;

public class ValidParantheses {

	public static boolean isValid(String s) {
		boolean isValid = false;
		if (s != null) {
			isValid = true;
			Deque<Character> stack = new ArrayDeque<>();
			for (char c : s.toCharArray()) {
				if (stack.isEmpty() && (c == ')' || c == '}' || c == ']')) {
					isValid = false;
					break;
				}
				if (!stack.isEmpty() && ((stack.peekLast() == '(' && (c == '}' || c == ']'))
						|| (stack.peekLast() == '{' && (c == ')' || c == ']'))
						|| (stack.peekLast() == '[' && (c == '}' || c == ')')))) {
					isValid = false;
					break;
				}
				if ((c == ')' && stack.peekLast() == '(') || (c == '}' && stack.peekLast() == '{')
						|| (c == ']' && stack.peekLast() == '[')) {
					stack.pollLast();
					continue;
				}
				stack.addLast(c);
			}
			if (stack.size() > 0) {
				isValid = false;
			}
		}
		return isValid;
	}

	public static void main(String[] args) {
		String s = "())";
		boolean isValid = isValid(s);
		System.out.println(isValid);
	}

}
