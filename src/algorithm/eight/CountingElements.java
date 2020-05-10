package algorithm.eight;

import java.util.Arrays;

public class CountingElements {

	public static int countElementsV2(int[] arr) {
		int count = 0;
		if (arr != null && arr.length > 1) {
			Arrays.sort(arr);
			int currNoCount = 1;
			for (int idx = 1; idx <= arr.length - 1; ++idx) {
				if (arr[idx] == arr[idx - 1]) {
					++currNoCount;
				} else {
					if (arr[idx] - arr[idx - 1] == 1) {
						count += currNoCount;
					}
					currNoCount = 1;
				}
			}
		}
		return count;
	}

	public static void main(String[] args) {
		int[] arr = { 1, 3, 2, 3, 5, 0 };
		int count = countElementsV2(arr);
		System.out.println(count);
	}

}
