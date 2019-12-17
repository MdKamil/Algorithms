package algorithm.five;

public class LongestMountain {

	public static int longestMountain(int[] A) {
		int maxLength = 0;
		if (A != null && A.length > 2) {
			int currMountainStartIdx = -1;
			for (int idx = 1; idx <= A.length - 1; ++idx) {
				if (A[idx] > A[idx - 1]) {
					if (currMountainStartIdx == -1 || A[idx - 1] < A[idx - 2]) {
						currMountainStartIdx = idx - 1;
					}
				} else if (A[idx] < A[idx - 1]) {
					if (currMountainStartIdx != -1) {
						int currMountainLength = (idx - currMountainStartIdx) + 1;
						maxLength = Math.max(maxLength, currMountainLength);
					}
				} else {
					currMountainStartIdx = -1;
				}
			}
		}
		return maxLength;
	}

	public static void main(String[] args) {
		int[] A = { 5, 10, 5, 10, 5 };
		int maxLength = longestMountain(A);
		System.out.println(maxLength);
	}
}
