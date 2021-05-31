package algorithm.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NumberOfMatchingSubsequences {

	public static int numMatchingSubseq(String s, String[] words) {
		int count = 0;
		HashMap<Character, List<Integer>> map = new HashMap<>();
		for (int idx = 0; idx <= s.length() - 1; ++idx) {
			Character c = s.charAt(idx);
			List<Integer> startIdxs = map.get(c);
			if (startIdxs == null) {
				startIdxs = new ArrayList<>();
				map.put(c, startIdxs);
			}
			startIdxs.add(idx);
		}
		for (String word : words) {
			List<Integer> startIdxs = map.get(word.charAt(0));
			if (startIdxs == null) {
				continue;
			}
			for (Integer startIdx : startIdxs) {
				int strIdx = startIdx;
				int wordIdx = 0;
				while (strIdx <= s.length() - 1 && wordIdx <= word.length() - 1) {
					if (s.charAt(strIdx) == word.charAt(wordIdx)) {
						++wordIdx;
					}
					++strIdx;
				}
				if (wordIdx > word.length() - 1) {
					++count;
					break;
				}
			}
		}
		return count;
	}

	public static void main(String[] args) {
		String s = "dsahjpjauf";
		String[] words = { "ahjpjau", "ja", "ahbwzgqnuk", "tnmlanowax" };
		System.out.println(numMatchingSubseq(s, words));
	}
}
