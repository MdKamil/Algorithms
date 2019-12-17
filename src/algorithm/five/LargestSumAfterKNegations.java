package algorithm.five;

import java.util.Arrays;

public class LargestSumAfterKNegations {

	public static int largestSumAfterKNegations(int[] A, int K) {
		int maxSum = 0;
		Arrays.sort(A);
		int positiveValIdx = 0;
		for (int idx = 0; idx <= A.length - 1; ++idx) {
			if (A[idx] >= 0) {
				positiveValIdx = idx;
				break;
			}
		}
		for (int idx = 0; idx < positiveValIdx && K > 0; ++idx) {
			A[idx] = Math.abs(A[idx]);
			--K;
		}
		if (K > 0) {
			int idxToModify = -1;
			if (positiveValIdx == 0) {
				idxToModify = positiveValIdx;
			} else if (A[positiveValIdx] < A[positiveValIdx - 1]) {
				idxToModify = positiveValIdx;
			} else {
				idxToModify = positiveValIdx - 1;
			}
			if (K % 2 != 0) {
				A[idxToModify] = -A[idxToModify];
			}
		}
		for (int idx = 0; idx <= A.length - 1; ++idx) {
			maxSum += A[idx];
		}
		return maxSum;
	}

	public static int largestSumAfterKNegationsV2(int[] A, int K) {
		int maxSum = 0;
		Arrays.sort(A);
		int positiveValIdx = 0;
		for (int idx = 0; idx <= A.length - 1 && K > 0; ++idx) {
			if (A[idx] < 0) {
				A[idx] = Math.abs(A[idx]);
				--K;
				if (idx == A.length - 1) {
					positiveValIdx = idx;
				}
			} else {
				positiveValIdx = idx;
				break;
			}
		}
		if (K > 0) {
			int idxToModify = -1;
			if (positiveValIdx == 0) {
				idxToModify = positiveValIdx;
			} else if (A[positiveValIdx] < A[positiveValIdx - 1]) {
				idxToModify = positiveValIdx;
			} else {
				idxToModify = positiveValIdx - 1;
			}
			if (K % 2 != 0) {
				A[idxToModify] = -A[idxToModify];
			}
		}
		for (int idx = 0; idx <= A.length - 1; ++idx) {
			maxSum += A[idx];
		}
		return maxSum;
	}

	public static int largestSumAfterKNegationsV3(int[] A, int K) {
		int maxSum = 0;
		Arrays.sort(A);
		int minValue = Integer.MAX_VALUE;
		for (int idx = 0; idx <= A.length - 1; ++idx) {
			if (A[idx] < 0) {
				if (K > 0) {
					A[idx] = Math.abs(A[idx]);
					--K;
				}
				maxSum += A[idx];
			} else {
				if (idx == 0) {
					minValue = A[idx];
				} else {
					minValue = Math.min(minValue, Math.min(A[idx - 1], A[idx]));
				}
				maxSum += A[idx];
			}
		}
		if (K > 0) {
			if (K % 2 != 0) {
				maxSum -= minValue;
				maxSum -= minValue;
			}
		}
		return maxSum;
	}

	public static void main(String[] args) {
		int[] A = { -4, -3, 1, 2 };
		int K = 1;
		int maxSum = largestSumAfterKNegationsV3(A, K);
		System.out.println(maxSum);
	}

}
