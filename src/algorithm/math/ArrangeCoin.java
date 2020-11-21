package algorithm.math;

public class ArrangeCoin {
	public static int arrangeCoinsV1(int n) {
		int totalNoOfSteps = 0;
		if (n > 0) {
			for (int step = 1;; ++step) {
				n = n - step;
				if (n < 0) {
					totalNoOfSteps = step - 1;
					break;
				} else if (n == 0) {
					totalNoOfSteps = step;
					break;
				}
			}
		}
		return totalNoOfSteps;
	}

	public static void main(String[] args) {
		int n = 1804289383;
		int totalNoOfSteps = arrangeCoinsV1(n);
		System.out.println(totalNoOfSteps);
	}

}
