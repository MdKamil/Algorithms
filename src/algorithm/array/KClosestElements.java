package algorithm.array;

import java.util.ArrayList;
import java.util.List;

public class KClosestElements {

	public List<Integer> findClosestElements(int[] arr, int k, int x) {
		int idx = getClosestElementIdx(arr, x);
		int left = idx == 0 ? 0 : Math.max(0, idx - (k - 1));
		int right = idx == arr.length - 1 ? arr.length - 1 : Math.min(arr.length - 1, idx + (k - 1));
		while ((right - left) + 1 > k) {
			if (Math.abs(arr[left] - x) <= Math.abs(arr[right] - x)) {
				--right;
			} else {
				++left;
			}
		}
		List<Integer> list = new ArrayList<>();
		for (int curr = left; curr <= right; ++curr) {
			list.add(arr[curr]);
		}
		return list;
	}

	private int getClosestElementIdx(int[] arr, int x) {
		int idx = 0, diff = Integer.MAX_VALUE;
		int left = 0, right = arr.length - 1;
		while (left <= right) {
			int mid = (left + right) / 2;
			if (x == arr[mid]) {
				idx = mid;
				break;
			}
			if (Math.abs(arr[mid] - x) < diff) {
				diff = Math.abs(arr[mid] - x);
				idx = mid;
			}
			if (x > arr[mid]) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		return idx;
	}

	public static void main(String[] args) {
		int[] arr = { 1, 2, 5, 5, 6, 6, 7, 7, 8, 9 };
		int k = 7;
		int x = 7;
		KClosestElements closestElements = new KClosestElements();
		System.out.println(closestElements.getClosestElementIdx(arr, x));
		System.out.println(closestElements.findClosestElements(arr, k, x));
	}

}
