package algorithm.five;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class TopKFrequentWords {

	public static List<String> topKFrequent(String[] words, int k) {
		List<String> wordList = new ArrayList<>();
		if (words != null && words.length > 0) {
			Map<String, Integer> map = new HashMap<String, Integer>();
			for (String word : words) {
				if (map.containsKey(word)) {
					map.put(word, map.get(word) + 1);
				} else {
					map.put(word, 1);
				}
			}
			List<java.util.Map.Entry<String, Integer>> entryList = new ArrayList<>(map.entrySet());
			Comparator<java.util.Map.Entry<String, Integer>> c = new Comparator<java.util.Map.Entry<String, Integer>>() {
				@Override
				public int compare(java.util.Map.Entry<String, Integer> o1, java.util.Map.Entry<String, Integer> o2) {
					int value = o1.getValue().compareTo(o2.getValue());
					if (value == 0) {
						return o1.getKey().compareTo(o2.getKey());
					}
					return value;
				}
			};
			entryList.sort(c);
			int count = 0;
			for (java.util.Map.Entry<String, Integer> entry : entryList) {
				++count;
				if (count <= k) {
					wordList.add(entry.getKey());
				}
				if (count > k) {
					break;
				}
			}
		}
		return wordList;
	}

	public static List<String> topKFrequentV2(String[] words, int k) {
		List<String> wordList = new ArrayList<>();
		if (words != null && words.length > 0) {
			Map<String, Integer> map = new HashMap<String, Integer>();
			for (String word : words) {
				if (map.containsKey(word)) {
					map.put(word, map.get(word) + 1);
				} else {
					map.put(word, 1);
				}
			}
			Comparator<java.util.Map.Entry<String, Integer>> c = new Comparator<java.util.Map.Entry<String, Integer>>() {
				@Override
				public int compare(java.util.Map.Entry<String, Integer> o1, java.util.Map.Entry<String, Integer> o2) {
					int value = o1.getValue().compareTo(o2.getValue());
					if (value == 0) {
						return -o1.getKey().compareTo(o2.getKey());
					}
					return value;
				}
			};
			PriorityQueue<java.util.Map.Entry<String, Integer>> queue = new PriorityQueue<>(c);
			for (java.util.Map.Entry<String, Integer> entry : map.entrySet()) {
				if (queue.size() < k) {
					queue.add(entry);
				} else {
					java.util.Map.Entry<String, Integer> minEntry = queue.peek();
					if (entry.getValue() > minEntry.getValue()) {
						queue.poll();
						queue.add(entry);
					} else if (entry.getValue() == minEntry.getValue()) {
						int value = entry.getKey().compareTo(minEntry.getKey());
						if (value < 0) {
							queue.poll();
							queue.add(entry);
						}
					}
				}
			}
			while (!queue.isEmpty()) {
				wordList.add(queue.poll().getKey());
			}
			Collections.reverse(wordList);
		}
		return wordList;
	}

	public static void main(String[] args) {
		String[] wordList = { "a", "a", "a", "aa", "aa", "aa", "aa", "aaa", "aaa", "aaa", "aaa", "aaa", "aaaa", "aaaa",
				"aaaa", "aaaa", "aaaa", "aaaa", "aaaa" };
		int k = 4;
		List<String> result = topKFrequentV2(wordList, k);
		for (String word : result) {
			System.out.println(word);
		}
	}

}
