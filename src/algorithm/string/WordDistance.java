package algorithm.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class WordDistance {

	private Map<String, List<Integer>> stringIdxMap;
	private Map<String, Integer> lastIdxMap;
	private Map<String, Map<String, Integer>> distanceMap;
	private String[] words;

	public WordDistance(String[] words) {
		this.words = words;
		lastIdxMap = new HashMap<>();
		distanceMap = new HashMap<>();
		process(words);

		stringIdxMap = new HashMap<>();
		preProcess(words);

	}

	private void process(String[] words) {
		for (int idx = 0; idx <= words.length - 1; ++idx) {
			Integer lastIdx = lastIdxMap.get(words[idx]);
			if (lastIdx == null) {
				lastIdxMap.put(words[idx], idx);
				Map<String, Integer> fromWordMap = new HashMap<>();
				distanceMap.put(words[idx], fromWordMap);
				updateDistanceBetweenWords(words[idx], idx, fromWordMap);
			} else {
				if (idx - lastIdx > 2) {
					int toIdx = ((idx + lastIdx) / 2) + 1;
					Map<String, Integer> fromWordMap = distanceMap.get(words[idx]);
					for (int fromIdx = idx - 1; fromIdx >= toIdx; --fromIdx) {
						Integer currDistance = fromWordMap.get(words[fromIdx]);
						Integer newDistance = Math.abs(idx - lastIdxMap.get(words[fromIdx]));
						fromWordMap.put(words[fromIdx], Math.min(currDistance, newDistance));

						Map<String, Integer> toWordMap = distanceMap.get(words[fromIdx]);
						currDistance = toWordMap.get(words[idx]);
						toWordMap.put(words[idx], Math.min(currDistance, newDistance));
					}
				}
				lastIdxMap.put(words[idx], idx);
			}
		}
	}

	private void preProcess(String[] words) {
		for (int idx = 0; idx <= words.length - 1; ++idx) {
			List<Integer> list = stringIdxMap.get(words[idx]);
			if (list == null) {
				list = new ArrayList<>();
				list.add(idx);
				stringIdxMap.put(words[idx], list);
			} else {
				list.add(idx);
			}
		}
	}

	private void preProcess2(String[] words) {
		for (int idx = 0; idx <= words.length - 1; ++idx) {
			List<Integer> list = stringIdxMap.get(words[idx]);
			if (list == null) {
				list = new LinkedList<>();
				list.add(idx);
				stringIdxMap.put(words[idx], list);
			} else {
				list.add(idx);
			}
		}
	}

	private int queryDistance(String word1, String word2) {
		int distance = words.length - 1;
		List<Integer> word1IdxList = stringIdxMap.get(word1);
		List<Integer> word2IdxList = stringIdxMap.get(word2);
		int idx1 = 0;
		int idx2 = 0;
		while (idx1 <= word1IdxList.size() - 1 && idx2 <= word2IdxList.size() - 1) {
			int currWord1Idx = word1IdxList.get(idx1);
			int currWord2Idx = word2IdxList.get(idx2);
			distance = Math.min(distance, Math.abs(currWord1Idx - currWord2Idx));
			if (currWord1Idx < currWord2Idx) {
				++idx1;
			} else {
				++idx2;
			}
		}
		return distance;
	}

	private int queryDistance2(String word1, String word2) {
		int distance = words.length - 1;
		List<Integer> word1IdxList = stringIdxMap.get(word1);
		List<Integer> word2IdxList = stringIdxMap.get(word2);
		Iterator<Integer> word1Iter = word1IdxList.iterator();
		Iterator<Integer> word2Iter = word2IdxList.iterator();
		int currWord1Idx = word1Iter.next();
		int currWord2Idx = word2Iter.next();
		while (true) {
			distance = Math.min(distance, Math.abs(currWord1Idx - currWord2Idx));
			if (currWord1Idx < currWord2Idx) {
				if (word1Iter.hasNext()) {
					currWord1Idx = word1Iter.next();
				} else {
					break;
				}
			} else {
				if (word2Iter.hasNext()) {
					currWord2Idx = word2Iter.next();
				} else {
					break;
				}
			}
		}
		return distance;
	}

	private void updateDistanceBetweenWords(String word, int lastIdx, Map<String, Integer> fromWordMap) {
		for (java.util.Map.Entry<String, Integer> entry : lastIdxMap.entrySet()) {
			if (entry.getKey().equals(word)) {
				continue;
			}
			Integer newDistance = Math.abs(lastIdxMap.get(entry.getKey()) - lastIdx);
			fromWordMap.put(entry.getKey(), newDistance);
			Map<String, Integer> toWordMap = distanceMap.get(entry.getKey());
			toWordMap.put(word, newDistance);
		}
	}

	public int shortestDistance(String word1, String word2) {
		int shortestDistance = words.length - 1;
		int word1LastIdx = -1;
		int word2LastIdx = -1;
		for (int idx = 0; idx <= words.length - 1; ++idx) {
			if (words[idx].equals(word1)) {
				word1LastIdx = idx;
				if (word2LastIdx != -1) {
					shortestDistance = Math.min(shortestDistance, idx - word2LastIdx);
				}
			} else if (words[idx].equals(word2)) {
				word2LastIdx = idx;
				if (word1LastIdx != -1) {
					shortestDistance = Math.min(shortestDistance, idx - word1LastIdx);
				}
			}
		}
		return shortestDistance;
	}

	public int shortest(String word1, String word2) {
		int distance = 0;
		// distance = distanceMap.get(word1).get(word2);
		distance = queryDistance(word1, word2);
		return distance;
	}

	public static void main(String[] args) {
		String[] words = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "a", "l", "d", "u", "z", "d" };
		WordDistance wordDistance = new WordDistance(words);
		int distance = wordDistance.shortest("l", "d");
		System.out.println(distance);
	}

}
