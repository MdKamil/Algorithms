package algorithm.string;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StringMatchingInArray {

	public static List<String> stringMatching(String[] words) {
		Set<String> set = new HashSet<>();
		for (int idx = 0; idx <= words.length - 2; ++idx) {
			for (int idx1 = idx + 1; idx1 <= words.length - 1; ++idx1) {
				if (words[idx].length() < words[idx1].length()) {
					if (words[idx1].contains(words[idx])) {
						set.add(words[idx]);
					}
				} else if (words[idx].length() > words[idx1].length()) {
					if (words[idx].contains(words[idx1])) {
						set.add(words[idx1]);
					}
				}
			}
		}
		return new ArrayList<>(set);
	}

	public static void main(String[] args) {
		String[] words = { "blue", "green", "bu" };
		List<String> list = stringMatching(words);
		System.out.println(list);
	}

}
