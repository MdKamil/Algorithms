package algorithm.four;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class MergeIntervals {

	public static int[][] merge(int[][] intervals) {
		int[][] mergedIntervals = null;
		if (intervals != null && intervals.length > 0 && intervals[0].length > 1) {
			Comparator<int[]> c = (o1, o2) -> Integer.valueOf(o1[0]).compareTo(Integer.valueOf(o2[0]));
			PriorityQueue<int[]> queue = new PriorityQueue<>(c);
			for (int[] interval : intervals) {
				queue.add(interval);
			}
			List<int[]> mergedList = new ArrayList<>();
			int[] curr = queue.poll();
			while (!queue.isEmpty()) {
				if (queue.peek()[0] <= curr[1]) {
					int[] interval = queue.poll();
					if (curr[1] > interval[1]) {
						int[] merged = { curr[0], curr[1] };
						curr = merged;
					} else {
						int[] merged = { curr[0], interval[1] };
						curr = merged;
					}
				} else {
					mergedList.add(curr);
					curr = queue.poll();
				}
			}
			mergedList.add(curr);
			mergedIntervals = new int[mergedList.size()][2];
			for (int i = 0; i <= mergedList.size() - 1; ++i) {
				mergedIntervals[i] = mergedList.get(i);
			}
		} else {
			mergedIntervals = new int[intervals.length][];
		}
		return mergedIntervals;
	}

	public static int[][] mergeV2(int[][] intervals) {
		int[][] mergedIntervals = null;
		if (intervals != null && intervals.length > 0 && intervals[0].length > 1) {
			List<int[]> mergedList = new ArrayList<>();
			int[] curr = { intervals[0][0], intervals[0][1] };
			for (int i = 1; i <= intervals.length - 1; ++i) {
				if (intervals[i][0] <= curr[1]) {
					if (curr[1] <= intervals[i][1]) {
						curr[1] = intervals[i][1];
					}
				} else {
					mergedList.add(curr);
					curr = new int[] { intervals[i][0], intervals[i][1] };
				}
			}
			mergedIntervals = new int[mergedList.size()][2];
			for (int i = 0; i <= mergedList.size() - 1; ++i) {
				mergedIntervals[i] = mergedList.get(i);
			}
		} else {
			mergedIntervals = new int[intervals.length][];
		}
		return mergedIntervals;
	}

	public static void main(String[] args) {
		int[][] intervals = { { 0, 2 }, { 1, 8 }, { 3, 6 }, { 7, 9 }, { 11, 12 }, { 14, 17 } };
		int[][] mergedIntervals = merge(intervals);
		for (int[] interval : mergedIntervals) {
			System.out.println(Arrays.toString(interval));
		}
	}

}
