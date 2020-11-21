package algorithm.design;

import java.util.ArrayDeque;
import java.util.Queue;

public class HitCounter {

	private Queue<Integer> queue;

	public HitCounter() {
		queue = new ArrayDeque<>();
	}

	public void hit(int timestamp) {
		queue.add(timestamp);
	}

	public int getHits(int timestamp) {
		int noOfHits = 0;
		if (!queue.isEmpty()) {
			if ((timestamp - queue.peek()) + 1 <= 300) {
				noOfHits = queue.size();
			} else {
				while (!queue.isEmpty()) {
					if ((timestamp - queue.peek()) + 1 <= 300) {
						noOfHits = queue.size();
						break;
					}
					queue.poll();
				}
			}
		}
		return noOfHits;
	}

	public static void main(String[] args) {
		HitCounter hitCounter = new HitCounter();
		hitCounter.hit(1);
		hitCounter.hit(2);
		hitCounter.hit(3);
		System.out.println(hitCounter.getHits(4));
		hitCounter.hit(300);
		System.out.println(hitCounter.getHits(300));
		System.out.println(hitCounter.getHits(301));
		System.out.println(hitCounter.getHits(1000));
		hitCounter.hit(1299);
		hitCounter.hit(1299);
		System.out.println(hitCounter.getHits(1300));
	}
}
