package algorithm.five;

public class SubArrayMaxOneDeletion {

	public static int maximumSum(int[] arr) {
		int currMaxSum = arr[0];
		int maxSum = currMaxSum;
		if (arr.length > 1) {
			int valTodelete = Math.min(arr[0], arr[1]);
			currMaxSum = Math.max(arr[0], arr[1]);
			maxSum = Math.max(currMaxSum, maxSum);
			for (int idx = 2; idx <= arr.length - 1; ++idx) {
				if (arr[idx] < valTodelete) {
					currMaxSum += valTodelete;
					maxSum = Math.max(currMaxSum, maxSum);
					valTodelete = arr[idx];
				} else if (currMaxSum + arr[idx] <= arr[idx]) {
					currMaxSum = arr[idx];
					maxSum = Math.max(currMaxSum, maxSum);
					valTodelete = 0;
				} else {
					currMaxSum += arr[idx];
					maxSum = Math.max(currMaxSum, maxSum);
				}
			}
		}
		return maxSum;
	}

	public static void main(String[] args) {
		int[] arr = { 7, -8, -1, -2, 10 };
		int maxSum = maximumSum(arr);
		System.out.println(maxSum);
	}

}
