package algorithm.five;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

public class AnagramsInString {

	private static int getCharVal(String string, int idx) {
		int charVal = (int) string.charAt(idx) - 97;
		return charVal;
	}

	public static List<Integer> findAnagramsV1(String s, String p) {
		List<Integer> list = new ArrayList<>();
		if ((s != null && s.trim().length() > 0) && (p != null && p.trim().length() > 0)) {
			int[] charCountInP = new int[26];
			int[] charCountInS = new int[26];
			Queue<Integer> queue = new ArrayDeque<>();
			for (int idx = 0; idx <= p.length() - 1; ++idx) {
				++charCountInP[getCharVal(p, idx)];
			}
			int currCharCount = 0;
			for (int idx = 0; idx <= s.length() - 1; ++idx) {
				int charVal = getCharVal(s, idx);
				if (charCountInP[charVal] > 0) {
					++charCountInS[charVal];
					queue.add(idx);
					if (charCountInS[charVal] <= charCountInP[charVal]) {
						++currCharCount;
						if (currCharCount == p.length()) {
							list.add(queue.peek());
						}
					} else {
						while (!queue.isEmpty()) {
							if (s.charAt(queue.peek()) == s.charAt(idx) && queue.peek() != idx) {
								int charIdx = queue.poll();
								--charCountInS[getCharVal(s, charIdx)];
								--currCharCount;
								break;
							} else {
								int charIdx = queue.poll();
								--charCountInS[getCharVal(s, charIdx)];
								--currCharCount;
							}
						}
						++currCharCount;
						if (currCharCount == p.length()) {
							list.add(queue.peek());
						}
					}
				} else {
					while (!queue.isEmpty()) {
						int charIdx = queue.poll();
						--charCountInS[getCharVal(s, charIdx)];
					}
					currCharCount = 0;
				}
			}
			while (!queue.isEmpty()) {
				queue.poll();
			}
		}
		return list;
	}

	public static List<Integer> findAnagramsV2(String s, String p) {
		List<Integer> list = new ArrayList<>();
		if ((s != null && s.trim().length() > 0) && (p != null && p.trim().length() > 0)) {
			int[] charCountInP = new int[26];
			int[] charCountInS = new int[26];
			for (int idx = 0; idx <= p.length() - 1; ++idx) {
				++charCountInP[getCharVal(p, idx)];
			}
			int start = 0;
			int currCharCount = 0;
			for (int idx = 0; idx <= s.length() - 1; ++idx) {
				int charVal = getCharVal(s, idx);
				if (charCountInP[charVal] > 0) {
					++charCountInS[charVal];
					if (charCountInS[charVal] <= charCountInP[charVal]) {
						++currCharCount;
						if (currCharCount == p.length()) {
							list.add(start);
						}
					} else {
						for (int from = start; from <= idx; ++from) {
							int startCharVal = getCharVal(s, start);
							--charCountInS[startCharVal];
							--currCharCount;
							++start;
							if (s.charAt(from) == s.charAt(idx)) {
								break;
							}
						}
						++currCharCount;
						if (currCharCount == p.length()) {
							list.add(start);
						}
					}
				} else {
					Arrays.fill(charCountInS, 0);
					currCharCount = 0;
					start = idx + 1;
				}
			}
		}
		return list;
	}

	public static void main(String[] args) {
		String s = "cbaebabacd";
		String p = "abc";
		List<Integer> list = findAnagramsV2(s, p);
		for (Integer startIdx : list) {
			System.out.println(startIdx);
		}
	}

}
