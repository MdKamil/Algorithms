package algorithm.seven;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class TopKfrequentElements {

	public static List<Integer> topKFrequent(int[] nums, int k) {
		List<Integer> list = null;
		if (nums != null) {
			list = new ArrayList<>(k);
			Map<Integer, Integer> map = new HashMap<>();
			for (int num : nums) {
				Integer count = map.get(num);
				if (count == null) {
					map.put(num, 1);
				} else {
					map.put(num, count + 1);
				}
			}
			Comparator<Integer> c = new Comparator<Integer>() {
				@Override
				public int compare(Integer o1, Integer o2) {
					return map.get(o1).compareTo(map.get(o2));
				}
			};
			PriorityQueue<Integer> queue = new PriorityQueue<>(k, c);
			for (Integer key : map.keySet()) {
				if (queue.size() == k) {
					if (map.get(queue.peek()) < map.get(key)) {
						queue.poll();
						queue.add(key);
					}
				} else {
					queue.add(key);
				}
			}
			while (!queue.isEmpty()) {
				list.add(queue.poll());
			}
			Collections.reverse(list);
		}
		return list;
	}

	public static void main(String[] args) {
		int[] nums = {1};
		int k = 1;
		List<Integer> list = topKFrequent(nums, k);
		System.out.println(list);
	}

}
