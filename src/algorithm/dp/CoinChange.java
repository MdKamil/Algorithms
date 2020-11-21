package algorithm.dp;

public class CoinChange {

	public static int coinChange(int[] coins, int amount) {
		return coinChangeNaive(0, coins, amount);
	}

	private static int coinChangeNaive(int idxCoin, int[] coins, int amount) {
		if (amount == 0)
			return 0;
		if (idxCoin < coins.length && amount > 0) {
			int maxVal = amount / coins[idxCoin];
			int minCost = Integer.MAX_VALUE;
			for (int x = 0; x <= maxVal; x++) {
				if (amount >= x * coins[idxCoin]) {
					int res = coinChangeNaive(idxCoin + 1, coins, amount - x * coins[idxCoin]);
					if (res != -1)
						minCost = Math.min(minCost, res + x);
				}
			}
			return (minCost == Integer.MAX_VALUE) ? -1 : minCost;
		}
		return -1;
	}

	public static int coinChangeV2(int[] coins, int amount) {
		if (amount < 1)
			return 0;
		int[] cntArray = new int[amount];
		int min = coinChange(coins, amount, cntArray);
		return min;
	}

	private static int coinChange(int[] coins, int remAmount, int[] count) {
		if (remAmount < 0)
			return -1;
		if (remAmount == 0)
			return 0;
		if (count[remAmount - 1] != 0)
			return count[remAmount - 1];
		int min = Integer.MAX_VALUE;
		for (int coin : coins) {
			int res = coinChange(coins, remAmount - coin, count);
			if (res >= 0 && res < min)
				min = 1 + res;
		}
		count[remAmount - 1] = (min == Integer.MAX_VALUE) ? -1 : min;
		return count[remAmount - 1];
	}

	public static int countNoOfWays(int[] coins, int amount) {
		return getCount(coins, coins.length, amount);
	}

	private static int getCount(int[] coins, int maxCoinIdx, int amount) {
		if (amount == 0) {
			return 1;
		}
		if (amount < 0) {
			return 0;
		}
		if (maxCoinIdx <= 0 && amount >= 1) {
			return 0;
		}
		return getCount(coins, maxCoinIdx - 1, amount) + getCount(coins, maxCoinIdx, amount - coins[maxCoinIdx - 1]);
	}

	public static int countNoOfWaysV2(int[] coins, int amount) {
		int[] combinations = new int[amount + 1];
		combinations[0] = 1;
		for (int coin : coins) {
			for (int currAmount = 1; currAmount <= amount; ++currAmount) {
				if (currAmount >= coin) {
					combinations[currAmount] += combinations[currAmount - coin];
				}
			}
		}
		return combinations[amount];
	}

	public static void main(String[] args) {
		int[] coins = { 9, 3, 5 };
		int amount = 17;
		// System.out.println(coinChangeV2(coins, amount));
		System.out.println(countNoOfWaysV2(coins, amount));
	}

}
