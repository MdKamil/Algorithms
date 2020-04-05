package algorithm.base;

import java.util.HashSet;
import java.util.Set;

public class HappyNumber {

	public static boolean isHappy(int n) {
		boolean isHappy = true;
		Set<Integer> set = new HashSet<>();
		set.add(n);
		int nextNumber = 0;
		if (n > 0) {
			while (true) {
				while (n > 0) {
					int remainder = n % 10;
					remainder = remainder * remainder;
					nextNumber += remainder;
					n /= 10;
				}
				n = nextNumber;
				nextNumber = 0;
				if (n == 1) {
					break;
				}
				if (set.contains(n)) {
					isHappy = false;
					break;
				} else {
					set.add(n);
				}
			}
		} else {
			isHappy = false;
		}
		return isHappy;
	}

	public static void main(String[] args) {
		int n = 100;
		boolean isHappy = isHappy(n);
		System.out.println(isHappy);
	}

}
