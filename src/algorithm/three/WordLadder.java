package algorithm.three;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

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

	public static void main(String[] args) {
		String beginWord = "talk";
		String endWord = "tail";
		List<String> wordList = Arrays.asList("talk", "tons", "fall", "tail", "gale", "hall", "negs");
		int length = ladderLength(beginWord, endWord, wordList);
		System.out.println(length);
	}

}
