package algorithm.five;

import java.util.ArrayDeque;
import java.util.Queue;

public class MinimumWindowSubstring {

	private static int getCharVal(String string, int idx) {
		int charVal = (int) string.charAt(idx) - 65;
		return charVal;
	}

	public static String minWindow(String s, String t) {
		String minWindowString = "";
		if ((s != null && s.trim().length() > 0) && (t != null && t.trim().length() > 0)) {
			Queue<Integer> queue = new ArrayDeque<>();
			int[] T = new int[58];
			int[] S = new int[58];
			for (int idx = 0; idx <= t.length() - 1; ++idx) {
				++T[getCharVal(t, idx)];
			}
			int start = -1;
			int end = -1;
			int minLength = Integer.MAX_VALUE;
			int charCountInT = 0;
			for (int idx = 0; idx <= s.length() - 1; ++idx) {
				int charVal = getCharVal(s, idx);
				if (T[charVal] > 0) {
					++S[charVal];
					queue.add(idx);
					if (S[charVal] <= T[charVal]) {
						++charCountInT;
						if (charCountInT == t.length()) {
							minLength = (idx - queue.peek()) + 1;
							start = queue.peek();
							end = idx;
						}
					} else {
						while (!queue.isEmpty()) {
							int queuePeekCharVal = getCharVal(s, queue.peek());
							if (S[queuePeekCharVal] > T[queuePeekCharVal]) {
								queue.poll();
								if (charCountInT == t.length()) {
									if ((idx - queue.peek()) + 1 < minLength) {
										minLength = (idx - queue.peek()) + 1;
										start = queue.peek();
										end = idx;
									}
								}
								--S[queuePeekCharVal];
							} else {
								break;
							}
						}
					}
				}
			}
			if (start != -1 && end != -1) {
				minWindowString = s.substring(start, end + 1);
				queue.clear();
			}
		}
		return minWindowString;
	}

	public static void main(String[] args) {
		String s = "acbbaca";
		String t = "aba";
		String minWindowString = minWindow(s, t);
		System.out.println(minWindowString);
	}

}
