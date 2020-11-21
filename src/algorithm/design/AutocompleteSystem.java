package algorithm.design;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

public class AutocompleteSystem {

	private Map<String, Integer> sentenceRank;
	private Map<Character, TreeSet<String>> dictionary;
	private StringBuilder builder;
	private Comparator<String> comparator;

	public AutocompleteSystem(String[] sentences, int[] times) {
		sentenceRank = new HashMap<>();
		dictionary = new HashMap<>();
		builder = new StringBuilder();
		comparator = new Comparator<String>() {
			@Override
			public int compare(String s1, String s2) {
				int val = 0;
				val = sentenceRank.get(s1).compareTo(sentenceRank.get(s2));
				if (val == 0) {
					val = s1.compareTo(s2);
					val *= -1;
				}
				return -val;
			}
		};
		if (sentences != null && times != null) {
			init(sentences, times);
		}
	}

	private void init(String[] sentences, int[] times) {
		for (int idx = 0; idx <= sentences.length - 1; ++idx) {
			insert(sentences[idx], times[idx]);
		}
	}

	private void insert(String sentence, int rank) {
		sentenceRank.put(sentence, rank);
		if (dictionary.containsKey(sentence.charAt(0))) {
			dictionary.get(sentence.charAt(0)).add(sentence);
		} else {
			TreeSet<String> set = new TreeSet<>(comparator);
			set.add(sentence);
			dictionary.put(sentence.charAt(0), set);
		}
	}

	public List<String> input(char c) {
		List<String> wordList = null;
		if (c == '#') {
			wordList = new ArrayList<>();
			String currSentence = builder.toString();
			if (sentenceRank.containsKey(currSentence)) {
				dictionary.get(currSentence.charAt(0)).remove(currSentence);
				sentenceRank.put(currSentence, sentenceRank.get(currSentence) + 1);
				dictionary.get(currSentence.charAt(0)).add(currSentence);
			} else {
				insert(currSentence, 1);
			}
			builder.setLength(0);
		} else {
			builder.append(c);
			wordList = getTopSentences(builder.toString());
		}
		return wordList;
	}

	private List<String> getTopSentences(String searchString) {
		List<String> topSentenceList = new ArrayList<>();
		if (dictionary.containsKey(searchString.charAt(0))) {
			TreeSet<String> wordSet = dictionary.get(searchString.charAt(0));
			int count = 0;
			for (String sentence : wordSet) {
				if (sentence.startsWith(searchString)) {
					topSentenceList.add(sentence);
					++count;
					if (count == 3) {
						break;
					}
				}
			}
		}
		return topSentenceList;
	}

	public static void main(String[] args) {
		String[] sentences = { "i love you", "island", "ironman", "i love leetcode" };
		int[] times = { 5, 3, 2, 2 };
		AutocompleteSystem autocompleteSystem = new AutocompleteSystem(sentences, times);
		List<String> topSentences = autocompleteSystem.input('i');
		print(topSentences);
		topSentences = autocompleteSystem.input('s');
		print(topSentences);
		topSentences = autocompleteSystem.input('l');
		print(topSentences);
		topSentences = autocompleteSystem.input('a');
		print(topSentences);
		topSentences = autocompleteSystem.input('n');
		print(topSentences);
		topSentences = autocompleteSystem.input('d');
		print(topSentences);
		topSentences = autocompleteSystem.input('#');
		print(topSentences);
		System.out.println(autocompleteSystem.sentenceRank);
		System.out.println(autocompleteSystem.dictionary);
	}

	private static void print(List<String> topSentences) {
		System.out.println("Printing top sentences");
		System.out.println("======================");
		for (String sentence : topSentences) {
			System.out.println(sentence);
		}
		System.out.println();
	}

}
