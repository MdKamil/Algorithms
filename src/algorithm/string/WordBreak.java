package algorithm.string;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordBreak {

	// Naive approach - TLE
	public static boolean wordBreakV1(String s, List<String> wordDict) {
		Set<String> wordSet = new HashSet<>(wordDict);
		StringBuilder newWord = new StringBuilder();
		return check(s, newWord, wordSet, 0);
	}

	// DP based implementation - 2D matrix space used
	public static boolean wordBreakV2(String s, List<String> wordDict) {
		Set<String> wordSet = new HashSet<>(wordDict);
		StringBuilder newWord = new StringBuilder();
		boolean[][] notPossible = new boolean[s.length()][s.length()];
		return checkV2(s, newWord, wordSet, 0, notPossible);
	}

	// DP based implementation - linear space used
	public static boolean wordBreakV3(String s, List<String> wordDict) {
		Set<String> wordSet = new HashSet<>(wordDict);
		StringBuilder newWord = new StringBuilder();
		boolean[] notPossible = new boolean[s.length()];
		return checkV3(s, newWord, wordSet, 0, notPossible);
	}

	private static boolean check(String input, StringBuilder newWord, Set<String> wordSet, int idx) {
		boolean result = false;
		if (idx > input.length() - 1) {
			if (newWord.toString().equals("")) {
				return true;
			} else {
				return false;
			}
		}
		newWord.append(input.charAt(idx));
		if (wordSet.contains(newWord.toString())) {
			result = check(input, new StringBuilder(), wordSet, idx + 1);
			if (!result) {
				result = check(input, newWord, wordSet, idx + 1);
			}
		} else {
			result = check(input, newWord, wordSet, idx + 1);
		}
		return result;
	}

	private static boolean checkV2(String input, StringBuilder newWord, Set<String> wordSet, int idx,
			boolean[][] notPossible) {
		boolean result = true;
		if (idx > input.length() - 1) {
			if (newWord.toString().equals("")) {
				return true;
			} else {
				return false;
			}
		}
		newWord.append(input.charAt(idx));
		if (wordSet.contains(newWord.toString())) {
			if (idx + 1 <= input.length() - 1 && notPossible[idx + 1][input.length() - 1]) {
				return checkV2(input, newWord, wordSet, idx + 1, notPossible);
			}
			result = checkV2(input, new StringBuilder(), wordSet, idx + 1, notPossible);
			if (!result) {
				notPossible[idx + 1][input.length() - 1] = !result;
				result = checkV2(input, newWord, wordSet, idx + 1, notPossible);
			}
		} else {
			result = checkV2(input, newWord, wordSet, idx + 1, notPossible);
		}
		return result;
	}

	private static boolean checkV3(String input, StringBuilder newWord, Set<String> wordSet, int idx,
			boolean[] notPossible) {
		boolean result = true;
		if (idx > input.length() - 1) {
			if (newWord.toString().equals("")) {
				return true;
			} else {
				return false;
			}
		}
		newWord.append(input.charAt(idx));
		if (wordSet.contains(newWord.toString())) {
			// This if case improves runtime by not splitting here. If the
			// following if case turns out to be true,
			// it means, from the next character, till end there is no way to
			// split the word
			if (idx + 1 <= input.length() - 1 && notPossible[idx + 1]) {
				return checkV3(input, newWord, wordSet, idx + 1, notPossible);
			}

			result = checkV3(input, new StringBuilder(), wordSet, idx + 1, notPossible);
			if (!result) {
				notPossible[idx + 1] = !result;
				result = checkV3(input, newWord, wordSet, idx + 1, notPossible);
			}
		} else {
			result = checkV3(input, newWord, wordSet, idx + 1, notPossible);
		}
		return result;
	}

	public static void main(String[] args) {
		List<String> wordDict = java.util.Arrays.asList("acdef", "a", "b", "c", "d", "e", "f");
		String s = "abcdefg";
		boolean canBreak = wordBreakV2(s, wordDict);
		System.out.println(canBreak);
	}

}
