package algorithm.array;

public class BuySellStockWIthTransactionFee {
    public int maxProfit(int[] prices, int fee) {
        int maxProfit = Integer.MIN_VALUE;
        int[] maxProfitFromDay = new int[prices.length];
        int[] overallProfit = new int[prices.length];
        maxProfitFromDay[maxProfitFromDay.length - 1] = 0;
        for (int from = prices.length - 2; from >= 0; --from) {
            for (int to = from + 1; to <= prices.length - 1; ++to) {
                int profit = (prices[to] - prices[from]) - fee;
                int otherTransactionsProfit = 0;
                if (to + 1 <= prices.length - 1) {
                    otherTransactionsProfit = overallProfit[to + 1];
                }
                maxProfitFromDay[from] = Math.max(maxProfitFromDay[from], profit + otherTransactionsProfit);
                maxProfit = Math.max(maxProfit, maxProfitFromDay[from]);
            }
            overallProfit[from] = Math.max(maxProfitFromDay[from], overallProfit[from + 1]);
        }
        return maxProfit;
    }

    public static void main(String[] args) {
//        int[] prices = {10, 14, 13, 18, 12, 20, 19, 30, 29, 100};
        int[] prices = {1,3,9,1,9,7,6,2,1,8,3,15};
        int fee = 0;
        BuySellStockWIthTransactionFee buySellStockWIthTransactionFee = new BuySellStockWIthTransactionFee();
        int maxProfit = buySellStockWIthTransactionFee.maxProfit(prices, fee);
        System.out.println(maxProfit);
    }
}
