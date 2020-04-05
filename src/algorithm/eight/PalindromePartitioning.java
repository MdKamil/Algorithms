package algorithm.eight;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PalindromePartitioning {

	public static List<List<String>> partition(String s) {
		List<List<String>> partitionList = new ArrayList<>();
		if (s != null && s.length() > 0) {
			Map<Integer, List<List<String>>> partitionListMap = new HashMap<>();
			partitionListMap.put(s.length() - 1,
					Arrays.asList(Arrays.asList(Character.toString(s.charAt(s.length() - 1)))));
			for (int idx = s.length() - 2; idx >= 0; --idx) {
				List<List<String>> partitionListAtIdx = partitionListMap.get(idx + 1);
				List<List<String>> partitionListAtCurrentIdx = new ArrayList<>();
				for (List<String> list : partitionListAtIdx) {
					partitionListAtCurrentIdx.add(new ArrayList<>());
					partitionListAtCurrentIdx.get(partitionListAtCurrentIdx.size() - 1)
							.add(Character.toString(s.charAt(idx)));
					partitionListAtCurrentIdx.get(partitionListAtCurrentIdx.size() - 1).addAll(list);
				}
				for (int length = idx + 1; length <= s.length() - 1; ++length) {
					int front = idx;
					int back = length;
					boolean isPalindrome = true;
					while (front <= back) {
						if (s.charAt(front) != s.charAt(back)) {
							isPalindrome = false;
							break;
						}
						++front;
						--back;
					}
					if (isPalindrome) {
						if (length + 1 <= s.length() - 1) {
							partitionListAtIdx = partitionListMap.get(length + 1);
							for (List<String> list : partitionListAtIdx) {
								partitionListAtCurrentIdx.add(new ArrayList<>());
								partitionListAtCurrentIdx.get(partitionListAtCurrentIdx.size() - 1)
										.add(s.substring(idx, length + 1));
								partitionListAtCurrentIdx.get(partitionListAtCurrentIdx.size() - 1).addAll(list);
							}
						} else {
							partitionListAtCurrentIdx.add(Arrays.asList(s.substring(idx, length + 1)));
						}
					}
				}
				partitionListMap.put(idx, partitionListAtCurrentIdx);
			}
			partitionList = partitionListMap.get(0);
		}
		return partitionList;
	}

	public static void main(String[] args) {
		String s = "aab";
		List<List<String>> partitionList = partition(s);
		for (List<String> list : partitionList) {
			System.out.println(list);
		}
	}

}
