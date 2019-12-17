package algorithm.online;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class IfExists {
	public static int solution(int[] A) {
		if (A.length == 1) {
			return -2;
		}
		Set<Integer> set = new HashSet<>();
		for (int idx = 0; idx <= A.length - 1; ++idx) {
			set.add(A[idx]);
		}
		Integer[] noDuplicate = set.toArray(new Integer[set.size()]);
		Arrays.sort(noDuplicate);
		Map<Integer, Integer> elementIdxInArray = new HashMap<>();
		for (int idx = 0; idx <= noDuplicate.length - 1; ++idx) {
			elementIdxInArray.put(noDuplicate[idx], idx);
		}
		int minDistance = 1000000001;
		for (int from = 0; from <= A.length - 2; ++from) {
			for (int to = from + 1; to <= A.length - 1; ++to) {
				int fromElementIdx = elementIdxInArray.get(A[from]);
				int toElementIdx = elementIdxInArray.get(A[to]);
				if (Math.abs(fromElementIdx - toElementIdx) <= 1) {
					int distance = Math.abs(A[from] - A[to]);
					minDistance = Math.min(minDistance, distance);
				}
			}
		}
		if (minDistance < 1000000001) {
			return minDistance;
		} else if (minDistance == 1000000001) {
			return -1;
		} else {
			return -2;
		}
	}

	public static void main(String[] args) {
		int[] T = { 3, 5, 11 };
		int result = solution(T);
		System.out.println(result);
	}

}
