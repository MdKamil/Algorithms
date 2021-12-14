package algorithm.array;

import java.util.Arrays;

public class InsertInterval {

	public static int[][] insert(int[][] intervals, int[] newInterval) {
		int overlappingCount = 0;
		for (int[] interval : intervals) {
			if (isOverlapping(interval, newInterval)) {
				newInterval[0] = Math.min(interval[0], newInterval[0]);
				newInterval[1] = Math.max(interval[1], newInterval[1]);
				++overlappingCount;
			}
		}
		int[][] output = new int[Math.abs(intervals.length - overlappingCount) + 1][2];
		for (int idx = 0, idx1 = 0; idx <= intervals.length - 1; ++idx) {
			if (isOverlapping(intervals[idx], newInterval)) {
				continue;
			} else {

				output[idx1++] = intervals[idx];
			}
		}
		output[output.length - 1] = newInterval;
		for (int idx = output.length - 2; idx >= 0; --idx) {
			if (output[idx][0] > output[idx + 1][0]) {
				int[] temp = output[idx];
				output[idx] = output[idx + 1];
				output[idx + 1] = temp;
			} else {
				break;
			}
		}
		return output;
	}

	public static int[][] insertV2(int[][] intervals, int[] newInterval) {
		int overlappingCount = 0;
		for (int[] interval : intervals) {
			if (isOverlapping(interval, newInterval)) {
				newInterval[0] = Math.min(interval[0], newInterval[0]);
				newInterval[1] = Math.max(interval[1], newInterval[1]);
				++overlappingCount;
			}
		}
		int[][] output = new int[Math.abs(intervals.length - overlappingCount) + 1][2];
		int idx1 = 0;
		output[idx1++] = newInterval;
		for (int idx = 0; idx <= intervals.length - 1; ++idx) {
			if (isOverlapping(intervals[idx], newInterval)) {
				continue;
			} else {
				output[idx1++] = intervals[idx];
				if (output[idx1 - 1][0] < output[idx1 - 2][0]) {
					int[] temp = output[idx1 - 1];
					output[idx1 - 1] = output[idx1 - 2];
					output[idx1 - 2] = temp;
				}
			}
		}
		return output;
	}

	private static boolean isOverlapping(int[] interval1, int[] interval2) {
		return interval1[0] <= interval2[1] && interval1[1] >= interval2[0];
	}

	public static void main(String[] args) {
		int[][] intervals = { { 1, 2 }, { 3, 5 }, { 6, 7 }, { 8, 10 }, { 12, 16 } };
		int[] newInterval = { 4, 8 };
		int[][] result = insertV2(intervals, newInterval);
		for (int[] interval : result) {
			System.out.println(Arrays.toString(interval));
		}
	}

}
