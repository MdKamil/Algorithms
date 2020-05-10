package algorithm.base;

import java.util.*;

public class Driver {

	public static void main(String[] args) {
		int[] matrix = { 0 };
		int left = 0;
		int right = matrix.length - 1;
		int maxLeft = -1;
		while (left <= right) {
			int mid = (left + right) / 2;
			if (matrix[mid] == 1) {
				maxLeft = mid;
				right = mid - 1;
			} else if (matrix[mid] == 0) {
				left = mid + 1;
			}
		}
		System.out.println(maxLeft);
	}
}
