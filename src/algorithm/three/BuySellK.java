package algorithm.three;

public class BuySellK {

	public static int maxProfit(int K, int[] prices) {
		if (prices == null || prices.length == 0) {
			return 0;
		}
		if (K >= prices.length / 2) {
			int ans = 0;
			for (int day = 1; day < prices.length; day++) {
				ans += Math.max(0, prices[day] - prices[day - 1]);
			}
			return ans;
		}
		int totalTxns = K + 1;
		int noOfDays = prices.length;
		int[][] dp = new int[totalTxns][noOfDays];
		for (int noOfTxn = 0; noOfTxn < totalTxns; ++noOfTxn) {
			if (noOfTxn == 0) {
				// No transaction on that day.
				continue;
			}
			for (int day = 0; day < noOfDays; ++day) {
				if (day == 0) {
					// No profit on day 0
					continue;
				}
				// goal is to ensure whether selling the stock would be
				// appropriate or holding it will be good on any single day
				int tmpMax = 0;
				// finding the good day for selling the stock. i.e the day on
				// which more profit will be there, prices[day] -
				// prices[pastDay]
				for (int pastDay = 0; pastDay < day; ++pastDay) {
					tmpMax = Math.max(tmpMax,
							(prices[day] - prices[pastDay]) + (pastDay == 0 ? 0 : dp[noOfTxn - 1][pastDay - 1]));
				}
				// on a particular day when not planning to sell, carry over
				// the previous day profit i.e. dp[noOfTxn][day - 1]
				dp[noOfTxn][day] = Math.max(dp[noOfTxn][day - 1], tmpMax);
			}
		}
		return dp[K][noOfDays - 1];
	}

	public static int maxProfitV2(int K, int[] prices) {
		if (prices == null || prices.length == 0) {
			return 0;
		}
		if (K >= prices.length / 2) {
			int ans = 0;
			for (int day = 1; day < prices.length; day++) {
				ans += Math.max(0, prices[day] - prices[day - 1]);
			}
			return ans;
		}
		int totalTxns = K + 1;
		int noOfDays = prices.length;
		int[][] dp = new int[totalTxns][noOfDays];
		for (int noOfTxn = 0; noOfTxn < totalTxns; ++noOfTxn) {
			if (noOfTxn == 0) {
				// No transaction on that day.
				continue;
			}
			// On day-1 we wont have any profit as we have purchased the stock, hence the profit at that time is negative
			int tmpMax = -prices[0];
			for (int day = 0; day < noOfDays; ++day) {
				if (day == 0) {
					// No profit on day 0
					continue;
				}
				// which is appropriate? retaining (or) Selling the stock
				dp[noOfTxn][day] = Math.max(dp[noOfTxn][day - 1], prices[day] + tmpMax);
				// when is right time to buy the stock
				tmpMax = Math.max(tmpMax, dp[noOfTxn - 1][day - 1] - prices[day]);
			}
		}
		return dp[K][noOfDays - 1];
	}

	public static void main(String[] args) {
		int[] prices = { 1, 2, 4, 2, 5, 7, 2, 4, 9 };
		System.out.println(maxProfit(3, prices));
		System.out.println(maxProfitV2(3, prices));
	}

}
