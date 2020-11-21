package algorithm.array;

public class LIS {

	public static int computeLIS(int[] a) {
		if (a.length == 0) {
			return 0;
		}
		int[] maxAtPoint = new int[a.length];
		maxAtPoint[a.length - 1] = 1;
		int maxLenght = 1;
		for (int i = a.length - 2; i >= 0; --i) {
			int j = i + 1;
			int currMax = 1;
			for (; j <= a.length - 1; ++j) {
				if (a[j] > a[i]) {
					int val = 1 + maxAtPoint[j];
					currMax = Math.max(val, currMax);
				}
			}
			maxAtPoint[i] = currMax;
			maxLenght = Math.max(maxLenght, maxAtPoint[i]);
		}
		return maxLenght;
	}

	public static void main(String[] args) {
		// computeLIS(new int[] { 0, 8, 4, 12, 25, 14, 15, 16, 17, 35, 19, 21,
		// 24, 45, 36, 38, 39, 40, 55 });
		System.out.println(computeLIS(new int[] { 1, 3, 6, 7, 9, 4, 10, 5, 6 }));
	}
}
