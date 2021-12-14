package algorithm.array;

import java.util.Arrays;

public class MeetingRooms {

	public boolean canAttendMeetings(int[][] intervals) {
		boolean canAttend = true;
		Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
		for (int idx = 1; idx <= intervals.length - 1; ++idx) {
			if (intervals[idx][0] < intervals[idx - 1][1]) {
				canAttend = false;
				break;
			}
		}
		return canAttend;
	}
}
