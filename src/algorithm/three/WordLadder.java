package algorithm.three;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class WordLadder {

	// Naive approach that works
	public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
		int length = 0;
		Map<String, Integer> transformLength = new HashMap<>();
		length = getSmallestTransformation(beginWord, endWord, wordList, transformLength);
		return length;
	}

	private static int getSmallestTransformation(String beginWord, String endWord, List<String> wordList,
			Map<String, Integer> transformLength) {
		Queue<String> queue = new ArrayDeque<String>();
		queue.add(beginWord);
		transformLength.put(beginWord, 1);
		while (!queue.isEmpty()) {
			String source = queue.poll();
			for (String to : wordList) {
				if (!transformLength.containsKey(to)) {
					boolean isSingleEdit = checkTwoWordsDifferBySingleEditV2(source, to);
					if (isSingleEdit) {
						if (to.equals(endWord)) {
							transformLength.put(to, transformLength.get(source) + 1);
							break;
						}
						queue.add(to);
						transformLength.put(to, transformLength.get(source) + 1);
					}
				}
			}
		}
		if (!transformLength.containsKey(endWord)) {
			transformLength.put(endWord, 0);
		}
		return transformLength.get(endWord);
	}

	public static boolean checkTwoWordsDifferBySingleEditV2(String word1, String word2) {
		int diffCnt = 0;
		for (int i = 0; i <= word1.length() - 1; ++i) {
			if (word1.charAt(i) != word2.charAt(i)) {
				++diffCnt;
			}
		}
		return diffCnt == 1 ? true : false;
	}

	public static int ladderLength(String beginWord, String endWord, Set<String> wordDict) {
		Set<String> reached = new HashSet<String>();
		reached.add(beginWord);
		wordDict.add(endWord);
		int distance = 1;
		while (!reached.contains(endWord)) {
			Set<String> toAdd = new HashSet<String>();
			for (String each : reached) {
				for (int i = 0; i < each.length(); i++) {
					char[] chars = each.toCharArray();
					for (char ch = 'a'; ch <= 'z'; ch++) {
						chars[i] = ch;
						String word = new String(chars);
						if (wordDict.contains(word)) {
							toAdd.add(word);
							wordDict.remove(word);
						}
					}
				}
			}
			distance++;
			if (toAdd.size() == 0)
				return 0;
			reached = toAdd;
		}
		return distance;
	}

	public static void main(String[] args) {
		String beginWord = "hot";
		String endWord = "cog";
		List<String> wordList = Arrays.asList("hot", "dot", "dog", "lot", "log", "cog");
		int length = ladderLength(beginWord, endWord, new HashSet<>(wordList));
		System.out.println(length);
	}

}
