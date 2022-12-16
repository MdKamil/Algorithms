package algorithm.array;

import java.util.ArrayList;
import java.util.List;

public class IntervalListIntersection {

	public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
		int idx1 = 0;
		int idx2 = 0;
		List<int[]> list = new ArrayList<>();
		while (idx1 <= firstList.length - 1 && idx2 <= secondList.length - 1) {
			if (isOverlap(firstList[idx1], secondList[idx2])) {
				list.add(new int[] { Math.max(firstList[idx1][0], secondList[idx2][0]),
						Math.min(firstList[idx1][1], secondList[idx2][1]) });
			}
			if (firstList[idx1][1] > secondList[idx2][1]) {
				++idx2;
			} else if (firstList[idx1][1] < secondList[idx2][1]) {
				++idx1;
			} else {
				++idx1;
				++idx2;
			}
		}
		int[][] intersection = new int[list.size()][];
		for (int idx = 0; idx <= list.size() - 1; ++idx) {
			intersection[idx] = list.get(0);
		}
		return intersection;
	}

	private static boolean isOverlap(int[] first, int[] second) {
		return first[0] <= second[1] && first[1] >= second[0];
	}
}
