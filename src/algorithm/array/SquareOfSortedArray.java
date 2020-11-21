package algorithm.array;

public class SquareOfSortedArray {

	public static int[] sortedSquares(int[] A) {
		int[] result = null;
		if (A == null) {
			return result;
		} else if (A.length == 0) {
			return new int[A.length];
		} else {
			int positiveIdx = -1;
			int negativeIdx = -1;
			int iter = 0;
			for (; iter <= A.length - 1; ++iter) {
				if (A[iter] >= 0) {
					positiveIdx = iter;
					break;
				}
			}
			if (positiveIdx == -1) {
				positiveIdx = A.length;
				negativeIdx = 0;
			} else {
				negativeIdx = positiveIdx - 1;
			}
			iter = 0;
			result = new int[A.length];
			while (positiveIdx <= A.length - 1 && negativeIdx >= 0) {
				if (A[positiveIdx] <= A[negativeIdx] * -1) {
					result[iter] = A[positiveIdx] * A[positiveIdx];
					++iter;
					++positiveIdx;
				} else if (A[negativeIdx] * -1 < A[positiveIdx]) {
					result[iter] = A[negativeIdx] * A[negativeIdx];
					++iter;
					--negativeIdx;
				}
			}
			while (positiveIdx <= A.length - 1) {
				result[iter] = A[positiveIdx] * A[positiveIdx];
				++iter;
				++positiveIdx;
			}
			while (negativeIdx >= 0) {
				result[iter] = A[negativeIdx] * A[negativeIdx];
				++iter;
				--negativeIdx;
			}
			return result;
		}
	}

}
