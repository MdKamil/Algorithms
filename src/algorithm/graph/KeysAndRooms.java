package algorithm.graph;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class KeysAndRooms {
	public boolean canVisitAllRooms(List<List<Integer>> rooms) {
		Queue<Integer> queue = new ArrayDeque<>();
		Set<Integer> seen = new HashSet<>();
		queue.add(0);
		seen.add(0);
		while (!queue.isEmpty()) {
			Integer currKey = queue.poll();
			List<Integer> room = rooms.get(currKey);
			for (Integer key : room) {
				if (!seen.contains(key)) {
					queue.add(key);
					seen.add(key);
				}
			}
		}
		return seen.size() == rooms.size();
	}
}
