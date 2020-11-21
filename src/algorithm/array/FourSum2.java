package algorithm.array;

import java.util.HashMap;
import java.util.Map;

public class FourSum2 {

	public static int fourSumCountV1(int[] A, int[] B, int[] C, int[] D) {
		int tupleCount = 0;
		if (A.length > 0 && B.length > 0 && C.length > 0 && D.length > 0) {
			for (int idx1 = 0; idx1 <= A.length - 1; ++idx1) {
				for (int idx2 = 0; idx2 <= B.length - 1; ++idx2) {
					for (int idx3 = 0; idx3 <= C.length - 1; ++idx3) {
						for (int idx4 = 0; idx4 <= D.length - 1; ++idx4) {
							if (A[idx1] + B[idx2] + C[idx3] + D[idx4] == 0) {
								++tupleCount;
							}
						}
					}
				}
			}
		}
		return tupleCount;
	}

	public static int fourSumCountV2(int[] A, int[] B, int[] C, int[] D) {
		int tupleCount = 0;
		if (A.length > 0 && B.length > 0 && C.length > 0 && D.length > 0) {
			Map<Integer, Integer> map = new HashMap<>();
			for (int idx1 = 0; idx1 <= C.length - 1; ++idx1) {
				for (int idx2 = 0; idx2 <= D.length - 1; ++idx2) {
					int sum = C[idx1] + D[idx2];
					map.put(sum, map.getOrDefault(sum, 0) + 1);
				}
			}
			for (int idx1 = 0; idx1 <= A.length - 1; ++idx1) {
				for (int idx2 = 0; idx2 <= B.length - 1; ++idx2) {
					int sum = A[idx1] + B[idx2];
					int remainingSum = 0;
					if (sum > 0) {
						remainingSum = -sum;
					} else {
						remainingSum = sum * (-1);
					}
					tupleCount += map.getOrDefault(remainingSum, 0);
				}
			}
		}
		return tupleCount;
	}

	public static int fourSumCountV3(int[] A, int[] B, int[] C, int[] D) {
		int tupleCount = 0;
		if (A.length > 0 && B.length > 0 && C.length > 0 && D.length > 0) {
			Map<Integer, Integer> map1 = new HashMap<>();
			Map<Integer, Integer> map2 = new HashMap<>();
			int listLength = A.length - 1;
			for (int idx1 = 0; idx1 <= listLength; ++idx1) {
				for (int idx2 = 0; idx2 <= listLength; ++idx2) {
					int sum = A[idx1] + B[idx2];
					map1.put(sum, map1.getOrDefault(sum, 0) + 1);

					sum = C[idx1] + D[idx2];
					map2.put(sum, map2.getOrDefault(sum, 0) + 1);
				}
			}
			for (int key : map1.keySet()) {
				int invertedKey = (key > 0 ? -key : key * (-1));
				if (map2.containsKey(invertedKey)) {
					tupleCount += map1.get(key) * map2.get(invertedKey);
				}
			}
		}
		return tupleCount;
	}

	public static void main(String[] args) {
		int[] A = { 1, 2 };
		int[] B = { -2, -1 };
		int[] C = { -1, 2 };
		int[] D = { 0, 2 };
		int tupleCount = fourSumCountV3(A, B, C, D);
		System.out.println(tupleCount);
	}

}
