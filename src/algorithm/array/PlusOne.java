package algorithm.array;

import java.util.Arrays;

public class PlusOne {

	public static int[] plusOne(int[] digits) {
		int arrayLength = digits.length;
		++arrayLength;
		for (int idx = 0; idx <= digits.length - 1; ++idx) {
			if (digits[idx] != 9) {
				--arrayLength;
				break;
			}
		}
		int[] result = new int[arrayLength];
		int resultIdx = result.length - 1;
		int carry = 1;
		for (int idx = digits.length - 1; idx >= 0; --idx) {
			if (carry == 1) {
				if (digits[idx] + carry > 9) {
					result[resultIdx] = 0;
					carry = 1;
				} else {
					result[resultIdx] = digits[idx] + carry;
					carry = 0;
				}
			} else {
				result[resultIdx] = digits[idx];
			}
			--resultIdx;
		}
		if (carry == 1) {
			result[resultIdx] = 1;
		}
		return result;
	}

	public static void main(String[] args) {
		int[] digits = { 9, 8, 9, 9 };
		int[] result = plusOne(digits);
		System.out.println(Arrays.toString(result));
	}
}
