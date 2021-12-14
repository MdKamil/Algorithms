package algorithm.string;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RemoveInvalidParentheses {

	public static List<String> removeInvalidParentheses(String s) {
		Set<String> set = new HashSet<>();
		StringBuilder builder = new StringBuilder();
		Pair<Integer, Integer> pair = new Pair<>();
		recurse(0, s, set, builder, 0, 0, pair, 0);
		return new ArrayList<>(set);
	}

	private static class Pair<T1, T2> {
		T1 first;
		T2 second;

		public Pair() {
		}

		public void copy(T1 first, T2 second) {
			this.first = first;
			this.second = second;
		}
	}

	private static void recurse(int idx, String s, Set<String> set, StringBuilder builder, int open, int closed,
			Pair<Integer, Integer> pair, int skip) {
		if (idx > s.length() - 1) {
			if (open == closed) {
				if (pair.first == null && pair.second == null) {
					pair.copy(builder.length(), s.length() - builder.length());
				}
				String result = builder.toString();
				if (!set.contains(result)) {
					set.add(result);
				}
			}
			return;
		}
		open += s.charAt(idx) == '(' ? 1 : 0;
		closed += s.charAt(idx) == ')' ? 1 : 0;
		if (s.charAt(idx) != '(' && s.charAt(idx) != ')') {
			builder.append(s.charAt(idx));
			recurse(idx + 1, s, set, builder, open, closed, pair, skip);
			builder.deleteCharAt(builder.length() - 1);
		} else {
			if (closed > open) {
				if (pair.second == null || pair.second >= skip + 1) {
					recurse(idx + 1, s, set, builder, open, closed - 1, pair, skip + 1);
				}
			} else if (open >= closed) {
				builder.append(s.charAt(idx));
				recurse(idx + 1, s, set, builder, open, closed, pair, skip);
				builder.deleteCharAt(builder.length() - 1);
				if (pair.second == null || pair.second >= skip + 1) {
					if (s.charAt(idx) == '(') {
						recurse(idx + 1, s, set, builder, open - 1, closed, pair, skip + 1);
					} else if (s.charAt(idx) == ')') {
						recurse(idx + 1, s, set, builder, open, closed - 1, pair, skip + 1);
					}
				}
			}
		}
	}

	public static void main(String[] args) {
		String s = ")(";
		System.out.println(removeInvalidParentheses(s));
	}

}
