package algorithm.eight;

import java.util.ArrayDeque;
import java.util.Deque;

// In-Complete
public class ValidParenthesesString {

	public static boolean checkValidString(String s) {
		boolean isValid = true;
		Deque<Character> deque = new ArrayDeque<>();
		for (int idx = 0; idx <= s.length() - 1; ++idx) {
			if (s.charAt(idx) == '(' || s.charAt(idx) == '*') {
				deque.addLast(s.charAt(idx));
			} else if (s.charAt(idx) == ')') {
				if (!deque.isEmpty() && deque.peekLast() == '(') {
					deque.pollLast();
				} else {
					deque.addLast(s.charAt(idx));
				}
			}
		}
		if (!deque.isEmpty()) {
			if (deque.peekLast() == '(' || deque.peekFirst() == ')') {
				isValid = false;
			} else {
				int openCount = 0;
				int closedCount = 0;
				int starCount = 0;
				while (!deque.isEmpty()) {
					char c = deque.pollLast();
					if (c == ')') {
						++closedCount;
					} else if (c == '(') {
						++openCount;
					} else if (c == '*') {
						++starCount;
					}
					if (openCount > closedCount + starCount) {
						isValid = false;
						break;
					} else if (openCount != 0 && closedCount + starCount > openCount) {
						if (closedCount != 0) {
							--openCount;
							--closedCount;
						} else {
							--openCount;
							--starCount;
						}
					} else if (closedCount + starCount == openCount) {
						openCount = closedCount = starCount = 0;
					}
				}
				if (isValid) {
					if (closedCount > starCount) {
						isValid = false;
					}
				}
			}
		}
		return isValid;
	}
}
