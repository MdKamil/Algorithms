package algorithm.string;

import java.util.Arrays;
import java.util.Comparator;

public class LongestStringChainV2 {

	public static int longestStrChain(String[] words) {
		int maxLength = 1;
		Comparator<String> c = new Comparator<String>() {
			@Override
			public int compare(String s1, String s2) {
				Integer s1Len = s1.length();
				Integer s2Len = s2.length();
				return s1Len == s2Len ? s1.compareTo(s2) : s1Len.compareTo(s2Len);
			}
		};
		Arrays.sort(words, c);
		int[] count = new int[words.length];
		dfs(words, count, 0);
		for (int val : count) {
			maxLength = Math.max(maxLength, val);
		}
		return maxLength;
	}

	private static void dfs(String[] words, int[] count, int fromIdx) {
		for (int idx = fromIdx; idx <= words.length - 1; ++idx) {
			if (count[idx] != 0) {
				continue;
			}
			int maxLength = 1;
			for (int idx1 = idx + 1; idx1 <= words.length - 1; ++idx1) {
				if (words[idx].length() == words[idx1].length()) {
					continue;
				} else if (Math.abs(words[idx].length() - words[idx1].length()) == 1) {
					if (isPredecessor(words[idx], words[idx1])) {
						dfs(words, count, idx1);
						maxLength = Math.max(maxLength, count[idx1] + 1);
					}
				} else {
					break;
				}
			}
			count[idx] = maxLength;
		}
	}

	private static boolean isPredecessor(String curr, String next) {
		boolean isPredecesor = true;
		int misPlacedCount = 0;
		for (int idx = 0, idx1 = 0; idx <= curr.length() - 1;) {
			if (curr.charAt(idx) != next.charAt(idx1)) {
				if (misPlacedCount == 1) {
					isPredecesor = false;
					break;
				} else {
					++misPlacedCount;
					++idx1;
				}
			} else {
				++idx1;
				++idx;
			}
		}
		return isPredecesor;
	}

	public static void main(String[] args) {
		String[] words = { "ksqvsyq", "ks", "kss", "czvh", "zczpzvdhx", "zczpzvh", "zczpzvhx", "zcpzvh", "zczvh", "gr",
				"grukmj", "ksqvsq", "gruj", "kssq", "ksqsq", "grukkmj", "grukj", "zczpzfvdhx", "gru" };
		System.out.println(longestStrChain(words));
	}

}
