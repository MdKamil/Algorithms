package algorithm.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupAnagrams {

	public static List<List<String>> groupAnagrams(String[] strs) {
		List<List<String>> masterList = new ArrayList<>();
		if (strs != null && strs.length > 0) {
			Map<String, List<String>> map = new HashMap<>();
			int[] count = new int[26];
			StringBuilder builder = new StringBuilder();
			for (String word : strs) {
				for (int idx = 0; idx <= word.length() - 1; ++idx) {
					++count[((int) word.charAt(idx)) - 97];
				}
				for (int idx = 0; idx <= count.length - 1; ++idx) {
					if (count[idx] == 0) {
						builder.append('0');
					} else {
						builder.append(count[idx]);
					}
					count[idx] = 0;
				}
				String key = builder.toString();
				List<String> value = map.get(key);
				if (value == null) {
					List<String> list = new ArrayList<>();
					list.add(word);
					masterList.add(list);
					map.put(key, list);
				} else {
					value.add(word);
				}
				builder.setLength(0);
			}
		}
		return masterList;
	}

	public static void main(String[] args) {
		String[] strs = { "eat", "tea", "tan", "ate", "nat", "bat", "song" };
		List<List<String>> masterList = groupAnagrams(strs);
		for (List<String> list : masterList) {
			System.out.println(list);
		}
	}
}
