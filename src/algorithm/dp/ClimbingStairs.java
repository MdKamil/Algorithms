package algorithm.dp;

public class ClimbingStairs {

	public static int climbStairs(int n) {
		int noOfWays = 0;
		noOfWays = recurse(n);
		return noOfWays;
	}

	private static int recurse(int n) {
		if (n == 0) {
			return 0;
		} else if (n == 1) {
			return 1;
		} else if (n == 2) {
			return 2;
		} else {
			return recurse(n - 1) + recurse(n - 2);
		}
	}

	public static int climbStairsV2(int n) {
		int noOfWays = 0;
		int[] stair = new int[n];
		noOfWays = recurseV2(n, stair);
		return noOfWays;
	}

	private static int recurseV2(int n, int[] stair) {
		if (n == 0) {
			return 0;
		} else if (n == 1) {
			return 1;
		} else if (n == 2) {
			return 2;
		} else if (stair[n - 1] != 0) {
			return stair[n - 1];
		} else {
			int noOfSteps = recurseV2(n - 1, stair) + recurseV2(n - 2, stair);
			stair[n - 1] = noOfSteps;
			return stair[n - 1];
		}
	}

	public static void main(String[] args) {
		int n = 10;
		int noOfWays = climbStairsV2(n);
		System.out.println(noOfWays);
	}

}
