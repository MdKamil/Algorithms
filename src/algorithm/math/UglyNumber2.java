package algorithm.math;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UglyNumber2 {

	public static int nthUglyNumberV1(int n) {
		int nthUglyNumber = -1;
		if (n <= 5) {
			nthUglyNumber = n;
		} else {
			Map<Integer, Boolean> isUgly = new HashMap<>();
			isUgly.put(1, true);
			isUgly.put(2, true);
			isUgly.put(3, true);
			isUgly.put(4, true);
			isUgly.put(5, true);
			int uglyNoCount = 5;
			for (int num = 6;; ++num) {
				int from = num / 2;
				boolean isPrime = true;
				for (; from >= 2; --from) {
					if (num % from == 0) {
						isPrime = false;
						if (!isUgly.get(from)) {
							isUgly.put(num, false);
						} else {
							isUgly.put(num, true);
							++uglyNoCount;
						}
						break;
					}
				}
				if (isPrime) {
					isUgly.put(num, false);
				}
				if (uglyNoCount == n) {
					nthUglyNumber = num;
					break;
				}
			}
		}
		return nthUglyNumber;
	}

	public static int nthUglyNumberV2(int n) {
		int nthUglyNumber = -1;
		if (n <= 6) {
			nthUglyNumber = n;
		} else {
			List<Integer> uglyPrimeFactorList = Arrays.asList(2, 3, 5);
			int uglyNoCount = 6;
			for (int num = 7;; ++num) {
				int currNum = num;
				for (int idx = 0; idx <= uglyPrimeFactorList.size() - 1; ++idx) {
					while (currNum > 1) {
						if (currNum % uglyPrimeFactorList.get(idx) != 0) {
							break;
						} else {
							currNum = currNum / uglyPrimeFactorList.get(idx);
						}
					}
				}
				if (currNum == 1) {
					++uglyNoCount;
					if (uglyNoCount == n) {
						nthUglyNumber = num;
						break;
					}
				}
			}
		}
		return nthUglyNumber;
	}

	public static void main(String[] args) {
		int n = 1000;
		int nthUglyNumber = nthUglyNumberV2(n);
		System.out.println(nthUglyNumber);
	}

}
