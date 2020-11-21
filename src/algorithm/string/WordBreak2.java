package algorithm.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WordBreak2 {

	public static List<String> wordBreak(String s, List<String> wordDict) {
		Set<String> set = new HashSet<>(wordDict);
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		List<String> list = wordBreak(s, set, map);
		return list;
	}

	public static List<String> wordBreak(String s, Set<String> wordDict, Map<String, List<String>> map) {
		System.out.println(s);
		List<String> res = new ArrayList<String>();
		if (s == null || s.length() == 0) {
			return res;
		}
		if (map.containsKey(s)) {
			return map.get(s);
		}
		if (wordDict.contains(s)) {
			res.add(s);
		}
		for (int i = 1; i < s.length(); i++) {
			String t = s.substring(i);
			if (wordDict.contains(t)) {
				List<String> temp = wordBreak(s.substring(0, i), wordDict, map);
				if (temp.size() != 0) {
					for (int j = 0; j < temp.size(); j++) {
						res.add(temp.get(j) + " " + t);
					}
				}
			}
		}
		map.put(s, res);
		return res;
	}

	public static void main(String[] args) {
		String s = "catsanddog";
		List<String> wordDict = Arrays.asList("cat", "cats", "and", "sand", "dog");
		List<String> list = wordBreak(s, wordDict);
		for (String word : list) {
			//System.out.println(word);
		}
	}

}
