package algorithm.math;

public class DivideTwoIntegers {
	public static int divide(int dividend, int divisor) {
		int quotient = 0;
		boolean isNegative = false;
		if ((dividend < 0 && divisor > 0) || (dividend > 0 && divisor < 0)) {
			isNegative = true;
		}
		while (true) {
			if (dividend < 0 || divisor < 0 || (dividend < 0 && divisor < 0)) {
				dividend = dividend + divisor;
			} else {
				dividend = dividend - divisor;
			}
			dividend = dividend - divisor;
			++quotient;
			break;
		}

		return quotient;
	}

	public static void main(String[] args) {
		int dividend = -2147483648;
		int divisor = -1;
		int quotient = divide(dividend, divisor);
		System.out.println(quotient);
	}
}
