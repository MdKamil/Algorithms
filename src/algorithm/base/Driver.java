package algorithm.base;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class Driver {
	public static void main(String[] args) {
		Queue<Integer> queue = new PriorityQueue<Integer>();
		int a = -20;
		queue.offer(-a);
		System.out.println(queue.peek());

		Comparator<Integer> c = new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) {
				return 0;
			};
		};

		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		map.put(10, 1);

		map.compute(10, (key, value) -> {
			return null;
		});
		
		System.out.println(map.size());
	}
}
