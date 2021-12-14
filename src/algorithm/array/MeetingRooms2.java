package algorithm.array;

import java.util.Arrays;
import java.util.PriorityQueue;

public class MeetingRooms2 {

	public int minMeetingRooms(int[][] intervals) {
		int minRooms = 1;
		Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		pq.add(intervals[0][1]);
		for (int idx = 1; idx <= intervals.length - 1; ++idx) {
			if (intervals[idx][0] < pq.peek()) {
				pq.add(intervals[idx][1]);
				minRooms = Math.max(minRooms, pq.size());
			} else {
				while (!pq.isEmpty()) {
					if (intervals[idx][0] >= pq.peek()) {
						pq.poll();
					} else {
						break;
					}
				}
				pq.add(intervals[idx][1]);
			}
		}
		return minRooms;
	}
}
