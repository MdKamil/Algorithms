package algorithm.dp;

public class BuySellAtmost2 {
	
	public static int maxProfitAtmost2(int[] prices) {
		int b1 = Integer.MIN_VALUE, s1 = 0, b2 = Integer.MIN_VALUE, s2 = 0;
		for (int p : prices) {
			b1 = Math.max(b1, -p);
			s1 = Math.max(s1, b1 + p);
			b2 = Math.max(b2, s1 - p);
			s2 = Math.max(s2, b2 + p);
		}
		return s2;
	}

	public static void main(String[] args) {
		int[] prices = { 1, 2, 4, 2, 5, 7, 2, 4, 9 };
		System.out.println(maxProfitAtmost2(prices));
	}

}
