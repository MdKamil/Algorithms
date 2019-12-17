package algorithm.five;

public class LemonadeChange {
	public static boolean lemonadeChange(int[] bills) {
		boolean isPossible = false;
		if (bills != null && bills.length != 0) {
			int fiveDollar = 0;
			int tenDollar = 0;
			for (int idx = 0; idx <= bills.length - 1; ++idx) {
				if (bills[idx] == 5) {
					++fiveDollar;
				} else if (bills[idx] == 10) {
					if (fiveDollar == 0) {
						break;
					} else {
						--fiveDollar;
						++tenDollar;
					}
				} else {
					if (fiveDollar == 0 || (tenDollar == 0 && fiveDollar < 3)) {
						break;
					} else {
						if (tenDollar > 0) {
							--tenDollar;
							--fiveDollar;
						} else {
							fiveDollar -= 3;
						}
					}
				}
				if (idx == bills.length - 1) {
					isPossible = true;
				}
			}
		}
		return isPossible;
	}

	public static void main(String[] args) {
		int[] bills = { 5, 5, 10, 10, 20 };
		boolean isPossible = lemonadeChange(bills);
		System.out.println(isPossible);
	}
}
