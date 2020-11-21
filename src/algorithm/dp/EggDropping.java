package algorithm.dp;

public class EggDropping {

	public static int eggDrop(int noOfEgg, int maxFloor) {
		// If there are no floors, then
		// no trials needed. OR if there
		// is one currFloor, one trial needed.
		if (maxFloor == 1 || maxFloor == 0)
			return maxFloor;

		// We need currFloor trials for one egg
		// and currFloor floors
		if (noOfEgg == 1)
			return maxFloor;

		int minDrops = Integer.MAX_VALUE;

		// Consider all droppings from
		// 1st currFloor to kth currFloor and
		// return the minimum of these
		// values plus 1.
		for (int currFloor = 1; currFloor <= maxFloor; ++currFloor) {
			int left = eggDrop(noOfEgg - 1, currFloor - 1);
			int right = eggDrop(noOfEgg, maxFloor - currFloor);
			minDrops = Math.min(minDrops, Math.max(left, right));
		}

		return minDrops + 1;
	}

	public static int eggDropV2(int noOfEgg, int maxFloor) {
		int[][] dp = new int[noOfEgg + 1][maxFloor + 1];
		int minDroppings = getMinDrops(noOfEgg, maxFloor, dp);
		return minDroppings;
	}

	private static int getMinDrops(int noOfEgg, int maxFloor, int[][] dp) {
		if (maxFloor <= 1) {
			return maxFloor;
		}
		if (noOfEgg == 1) {
			return maxFloor;
		}
		if (dp[noOfEgg][maxFloor] > 0) {
			return dp[noOfEgg][maxFloor];
		}
		int minDrops = maxFloor;
		for (int currFloor = 1; currFloor <= maxFloor; currFloor++) {
			int left = getMinDrops(noOfEgg - 1, currFloor - 1, dp);
			int right = getMinDrops(noOfEgg, maxFloor - currFloor, dp);
			minDrops = Math.min(minDrops, Math.max(left, right) + 1);
		}
		dp[noOfEgg][maxFloor] = minDrops;
		return minDrops;
	}

	public static void main(String[] args) {
		int noOfEgg = 2;
		int maxFloor = 6;
		//System.out.println(eggDrop(noOfEgg, maxFloor));
		System.out.println(eggDropV2(noOfEgg, maxFloor));
	}

}
