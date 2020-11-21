package algorithm.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TopKFrequentElements2 {

	public static List<Integer> topKFrequent(int[] nums, int k) {
		List<Integer> list = null;
		if (nums != null && nums.length > 0) {
			list = new ArrayList<>();
			Map<Integer, Integer> frequencyMap = new HashMap<>();
			int highestFrequency = 1;
			for (int num : nums) {
				Integer frequency = frequencyMap.get(num);
				if (frequency == null) {
					frequencyMap.put(num, 1);
				} else {
					frequencyMap.put(num, frequency + 1);
					highestFrequency = Math.max(highestFrequency, frequency + 1);
				}
			}
			ArrayList<ArrayList<Integer>> bucket = new ArrayList<>(highestFrequency);
			for (int idx = 0; idx <= highestFrequency; ++idx) {
				bucket.add(new ArrayList<>());
			}
			for (int num : frequencyMap.keySet()) {
				bucket.get(frequencyMap.get(num)).add(num);
			}
			int retrieved = 0;
			for (int idx = highestFrequency; idx >= 0 && retrieved < k; --idx) {
				if (bucket.get(idx).size() > 0) {
					ArrayList<Integer> subList = bucket.get(idx);
					for (int subIdx = 0; subIdx <= subList.size() - 1 && retrieved < k; ++subIdx) {
						list.add(subList.get(subIdx));
						++retrieved;
					}
				}
			}
		}
		return list;
	}

	public static void main(String[] args) {
		int[] nums = { 1, 1, 1, 2, 2, 3 };
		int k = 2;
		List<Integer> list = topKFrequent(nums, k);
		System.out.println(list);
	}

}
