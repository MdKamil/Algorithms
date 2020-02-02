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

	public static String minWindowV2(String s, String t) {
		String minWindowString = "";
		if ((s != null && s.trim().length() > 0) && (t != null && t.trim().length() > 0)) {
			int[] T = new int[58];
			int[] S = new int[58];
			for (int idx = 0; idx <= t.length() - 1; ++idx) {
				++T[getCharVal(t, idx)];
			}
			int currStart = 0;
			int start = 0;
			int end = -1;
			int charCountInT = 0;
			int minLength = Integer.MAX_VALUE;
			for (int idx = 0; idx <= s.length() - 1; ++idx) {
				int charVal = getCharVal(s, idx);
				if (T[charVal] > 0) {
					++S[charVal];
					if (S[charVal] <= T[charVal]) {
						++charCountInT;
						if (charCountInT == t.length()) {
							start = currStart;
							end = idx;
							minLength = Math.min(minLength, (end - start) + 1);
						}
					} else {
						while (true) {
							int startCharVal = getCharVal(s, currStart);
							if (S[startCharVal] > T[startCharVal]) {
								--S[startCharVal];
								++currStart;
							} else {
								if (T[startCharVal] == 0) {
									++currStart;
								} else {
									if (charCountInT == t.length() && (idx - currStart) + 1 < minLength) {
										start = currStart;
										end = idx;
										minLength = (end - start) + 1;
									}
									break;
								}
							}
						}
					}
				} else {
					if (charCountInT == 0) {
						++currStart;
					}
				}
			}
			if (start != -1 && end != -1) {
				minWindowString = s.substring(start, end + 1);
			}
		}
		return minWindowString;
	}

	public static void main(String[] args) {
		String s = "acbbaca";
		String t = "aba";
		String minWindowString = minWindow(s, t);
		System.out.println(minWindowString);

		minWindowString = minWindowV2(s, t);
		System.out.println(minWindowString);
	}

}
