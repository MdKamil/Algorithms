package algorithm.array;

import java.util.Map;
import java.util.TreeMap;

public class MyCalendar1 {

	private TreeMap<Integer, int[]> events;

	public MyCalendar1() {
		events = new TreeMap<>((a, b) -> a.compareTo(b));
	}

	public boolean book(int start, int end) {
		boolean canBook = false;
		Map.Entry<Integer, int[]> nextEvent = events.ceilingEntry(start);
		Map.Entry<Integer, int[]> previousEvent = events.floorEntry(start);
		boolean previousEventIntersection = isIntersecting(previousEvent, start, end - 1);
		boolean nextEventIntersection = isIntersecting(nextEvent, start, end - 1);
		if (!previousEventIntersection && !nextEventIntersection) {
			events.put(start, new int[] { start, end - 1 });
			canBook = true;
		}
		return canBook;
	}

	private boolean isIntersecting(Map.Entry<Integer, int[]> eventHolder, int start, int end) {
		boolean isIntersecting = false;
		if (eventHolder != null) {
			int[] event = eventHolder.getValue();
//			if ((start >= event[0] && start <= event[1]) || (end >= event[0] && end <= event[1])
//					|| (start >= event[0] && end <= event[1]) || (start <= event[0] && end >= event[1])) {
//				isIntersecting = true;
//			}
			if (event[0] <= end && event[1] >= start) {
				isIntersecting = true;
			}
		}
		return isIntersecting;
	}

}
