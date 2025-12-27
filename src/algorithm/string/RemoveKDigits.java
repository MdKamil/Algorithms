package algorithm.string;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class RemoveKDigits {
	public String removeKdigits(String num, int k) {
		if (k == num.length()) {
			return "0";
		} else {
			Deque<Character> stack = new ArrayDeque<>();
			for (int idx = 0; idx <= num.length() - 2; ++idx) {
				if (num.charAt(idx) > num.charAt(idx + 1) && k > 0) {
					--k;
					while (!stack.isEmpty() && k > 0) {
						if (stack.peekLast() > num.charAt(idx +  1)) {
							--k;
							stack.pollLast();
						} else {
							break;
						}
					}
				} else {
					stack.addLast(num.charAt(idx));
				}
			}
			stack.addLast(num.charAt(num.length()-1));
			while (k > 0 && !stack.isEmpty()) {
				--k;
				stack.pollLast();
			}
			while(!stack.isEmpty() && stack.peekFirst() == '0') {
				stack.pollFirst();
			}
			StringBuilder builder = new StringBuilder();
			while(!stack.isEmpty()) {
				builder.append(stack.pollFirst());
			}
			if (builder.isEmpty()) {
				builder.append('0');
			}
			return builder.toString();
		}
	}

	public static void main(String[] args) {
		RemoveKDigits removeKDigits = new RemoveKDigits();
		String num = "10200";
		int k = 3;
		String smallestNum = removeKDigits.removeKdigits(num, k);
		System.out.println(smallestNum);
	}

}
