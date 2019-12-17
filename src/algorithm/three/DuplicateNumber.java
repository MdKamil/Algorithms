package algorithm.three;

public class DuplicateNumber {
	public static void main(String[] args) {
		int[] input = { 1, 2, 3, 4, 2 };
		int result = 0;
		for (int i = 0; i <= input.length - 1; ++i) {
			result ^= input[i];
		}
		System.out.println(result);
	}
}
