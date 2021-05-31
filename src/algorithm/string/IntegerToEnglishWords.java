package algorithm.string;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

// https://leetcode.com/problems/integer-to-english-words
public class IntegerToEnglishWords {

	private static void init(HashMap<Integer, String> map) {
		map.put(0, "Zero");
		map.put(1, "One");
		map.put(2, "Two");
		map.put(3, "Three");
		map.put(4, "Four");
		map.put(5, "Five");
		map.put(6, "Six");
		map.put(7, "Seven");
		map.put(8, "Eight");
		map.put(9, "Nine");
		map.put(10, "Ten");
		map.put(11, "Eleven");
		map.put(12, "Twelve");
		map.put(13, "Thirteen");
		map.put(14, "Fourteen");
		map.put(15, "Fifteen");
		map.put(16, "Sixteen");
		map.put(17, "Seventeen");
		map.put(18, "Eighteen");
		map.put(19, "Nineteen");
		map.put(20, "Twenty");
		map.put(30, "Thirty");
		map.put(40, "Forty");
		map.put(50, "Fifty");
		map.put(60, "Sixty");
		map.put(70, "Seventy");
		map.put(80, "Eighty");
		map.put(90, "Ninety");
	}

	private static void append(int pos, List<String> list) {
		if (pos == 1000) {
			list.add("Thousand");
		} else if (pos == 1000000) {
			list.add("Million");
		} else if (pos == 1000000000) {
			list.add("Billion");
		}
	}

	private static void split(List<String> list, HashMap<Integer, String> map, int rem) {
		if (map.containsKey(rem)) {
			if (rem != 0) {
				list.add(map.get(rem));
			}
		} else {
			if (rem % 10 != 0) {
				list.add(map.get(rem % 10));
			}
			list.add(map.get((rem / 10) * 10));
		}
	}

	// Add numbers in reverse order
	public static String numberToWords(int num) {
		List<String> list = new ArrayList<>();
		HashMap<Integer, String> map = new HashMap<Integer, String>();
		init(map);
		if (num == 0) {
			list.add(map.get(0));
		}
		int pos = 1;
		while (num > 0) {
			int rem = num % 1000;
			if (rem > 99) {
				append(pos, list);
				int rem2 = rem % 100;
				split(list, map, rem2);
				list.add("Hundred");
				list.add(map.get(rem / 100));
			} else if (rem > 9) {
				append(pos, list);
				split(list, map, rem);
			} else if (rem > 0) {
				append(pos, list);
				list.add(map.get(rem));
			}
			num /= 1000;
			pos *= 1000;
		}
		Collections.reverse(list);
		StringBuilder intWords = new StringBuilder();
		for (String word : list) {
			intWords.append(word);
			intWords.append(" ");
		}
		intWords.deleteCharAt(intWords.length() - 1);
		return intWords.toString();
	}

}
