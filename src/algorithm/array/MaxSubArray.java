package algorithm.array;

// Also known as Kadane's algorithm

public class MaxSubArray {

	public static void maxSumSubArrayv1(int[] a) {
		int overAllMaximum = Integer.MIN_VALUE;
		int maxUntilNow = 0;
		for (int i = 0; i <= a.length - 1; ++i) {
			maxUntilNow += a[i];
			if (overAllMaximum < maxUntilNow) {
				overAllMaximum = maxUntilNow;
			}
			if (maxUntilNow < 0) {
				maxUntilNow = 0;
			}
		}
		System.out.println(overAllMaximum);
	}

	public static void maxSumSubArray(int[] a) {
		int overAllMaximum = Integer.MIN_VALUE;
		int maxUntilNow = 0;
		int start = 0;
		int end = 0;
		int s = 0;
		for (int i = 0; i <= a.length - 1; ++i) {
			maxUntilNow += a[i];
			if (overAllMaximum < maxUntilNow) {
				overAllMaximum = maxUntilNow;
				start = s;
				end = i;
			}
			if (maxUntilNow < 0) {
				maxUntilNow = 0;
				s = i + 1;
			}
		}
		System.out.println(overAllMaximum);
		System.out.println("Start: " + Integer.toString(start));
		System.out.println("End: " + Integer.toString(end));
	}

	public static void main(String[] args) {
		int[] a = { 13, -3, -25, 20, -3, -16, -23, 18, 20, -7, 12, -5, -22, 15, -4, 7 };
		maxSumSubArrayv1(a);
	}
}
