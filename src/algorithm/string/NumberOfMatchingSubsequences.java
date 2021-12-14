package algorithm.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NumberOfMatchingSubsequences {

	public static int numMatchingSubseq(String s, String[] words) {
		int subSeqCount = 0;
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
		HashMap<String, Boolean> wordMatch = new HashMap<>();
		for (String word : words) {
			if (wordMatch.containsKey(word)) {
				if (wordMatch.get(word)) {
					++subSeqCount;
					continue;
				} else {
					continue;
				}
			}
			List<Integer> startIdxs = map.get(word.charAt(0));
			if (startIdxs != null) {
				boolean found = false;
				for (int idx = 0; idx <= startIdxs.size() - 1; ++idx) {
					int strIdx = startIdxs.get(idx);
					int wordIdx = 0;
					while (strIdx <= s.length() - 1 && wordIdx <= word.length() - 1) {
						if (s.charAt(strIdx) == word.charAt(wordIdx)) {
							++wordIdx;
						}
						++strIdx;
					}
					if (wordIdx > word.length() - 1) {
						found = true;
						++subSeqCount;
						break;
					} else {
						wordMatch.put(word, false);
						break;
					}
				}
				wordMatch.put(word, found);
			} else {
				wordMatch.put(word, false);
			}
		}
		return subSeqCount;
	}

	public static int numMatchingSubseqV2(String s, String[] words) {
		int subSeqCount = 0;
		HashMap<Character, List<Integer>> map = new HashMap<>();
		for (int idx = 0; idx <= s.length() - 1; ++idx) {
			Character c = s.charAt(idx);
			List<Integer> idxs = map.get(c);
			if (idxs == null) {
				idxs = new ArrayList<>();
				map.put(c, idxs);
			}
			idxs.add(idx);
		}
		for (String word : words) {
			List<Integer> list = map.get(word.charAt(0));
			if (list != null) {
				int prevPos = list.get(0);
				boolean found = true;
				for (int idx = 1; idx <= word.length() - 1; ++idx) {
					list = map.get(word.charAt(idx));
					if (list == null) {
						found = false;
						break;
					}
					int low = 0;
					int high = list.size() - 1;
					int pos = -1;
					while (low <= high) {
						int mid = (low + high) / 2;
						if (list.get(mid) > prevPos) {
							pos = list.get(mid);
							high = mid - 1;
						} else {
							low = mid + 1;
						}
					}
					if (pos != -1) {
						prevPos = pos;
					} else {
						found = false;
						break;
					}
				}
				if (found) {
					++subSeqCount;
				}
			}
		}
		return subSeqCount;
	}

	public static void main(String[] args) {
		String s = "dsahjpjauf";
		String[] words = { "ahjpjau", "ja", "ahbwzgqnuk", "tnmlanowax" };
		System.out.println(numMatchingSubseqV2(s, words));
	}
}
