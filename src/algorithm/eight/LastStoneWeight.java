package algorithm.eight;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

public class LastStoneWeight {

	public static int lastStoneWeightV1(int[] stones) {
		Queue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
		for (int stone : stones) {
			pq.add(stone);
		}
		while (pq.size() > 1) {
			int first = pq.poll();
			int second = pq.poll();
			if (first - second > 0) {
				pq.add(first - second);
			}
		}
		if (pq.size() == 0) {
			return 0;
		} else {
			return pq.poll();
		}
	}

	public static void main(String[] args) {
		int[] stones = { 2, 7, 4, 1, 8, 1 };
		int lastStoneWeight = lastStoneWeightV1(stones);
		System.out.println(lastStoneWeight);
	}

}
