package algorithm.two;

public class BuySell {

	// Buy and sell once for max profit
	public static int maxProfit(int[] prices) {
		int maxProfit = 0;
		int currLow = Integer.MAX_VALUE;
		for (int i = 0; i <= prices.length - 1; ++i) {
			if (i == 0) {
				currLow = prices[i];
				continue;
			}
			if (prices[i] < currLow) {
				currLow = prices[i];
			} else {
				maxProfit = Math.max(maxProfit, prices[i] - currLow);
			}
		}
		return maxProfit;
	}

	// Buy and sell many times for max profit
	public static int maxProfit2(int[] prices) {
		int maxProfit = 0;
		int currLow = Integer.MAX_VALUE;
		for (int i = 0; i <= prices.length - 1; ++i) {
			if (i == 0) {
				currLow = prices[i];
				continue;
			}
			if (prices[i] < prices[i - 1]) {
				maxProfit = maxProfit + prices[i - 1] - currLow;
				currLow = prices[i];
			}
			if (i == prices.length - 1) {
				maxProfit = maxProfit + prices[i] - currLow;
			}
		}
		return maxProfit;
	}

	public static void main(String[] args) {
		int[] prices = { 1, 2, 4, 2, 5, 7, 2, 4, 9, 0 };
		int maxProfit = maxProfit2(prices);
		System.out.println(maxProfit);
	}

}
