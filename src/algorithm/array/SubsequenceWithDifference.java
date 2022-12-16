package algorithm.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SubsequenceWithDifference {

	public int longestSubsequence(int[] arr, int difference) {
		int maxLength = 1;
		HashMap<Integer, List<Integer>> idxMap = new HashMap<>();
		HashMap<Integer, Integer> cache = new HashMap<>();
		for (int idx = 0; idx <= arr.length - 1; ++idx) {
			if (idxMap.containsKey(arr[idx])) {
				idxMap.get(arr[idx]).add(idx);
			} else {
				List<Integer> list = new ArrayList<>();
				list.add(idx);
				idxMap.put(arr[idx], list);
			}
		}
		for (int idx = 0; idx <= arr.length - 1; ++idx) {
			if (!cache.containsKey(arr[idx])) {
				int length = dfs(arr[idx], idx, difference, idxMap, cache);
				maxLength = Math.max(maxLength, length);
			}
		}
		return maxLength;
	}

	private int dfs(int val, int valIdx, int difference, HashMap<Integer, List<Integer>> idxMap,
			HashMap<Integer, Integer> cache) {
		int nextVal = val + difference;
		int length = 1;
		if (idxMap.containsKey(nextVal)) {
			List<Integer> idxList = idxMap.get(nextVal);
			for (int idx : idxList) {
				if (idx > valIdx) {
					length += dfs(nextVal, idx, difference, idxMap, cache);
					break;
				}
			}
		}
		cache.putIfAbsent(val, length);
		return length;
	}

	public static void main(String[] args) {
		int[] arr = { 1, 3, 5, 7 };
		int difference = 1;
		int max = new SubsequenceWithDifference().longestSubsequence(arr, difference);
		System.out.println(max);
	}
}
