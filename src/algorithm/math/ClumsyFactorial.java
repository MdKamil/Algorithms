package algorithm.math;

public class ClumsyFactorial {

	public static int clumsy(int N) {
		int result = 0;
		if (N >= 4) {
			result = (((N * (N - 1)) / (N - 2)) + (N - 3));
			N -= 4;
		} else if (N == 3) {
			result = ((N * (N - 1)) / (N - 2));
			N -= 3;
		} else if (N == 2) {
			result = (N * (N - 1));
			N -= 2;
		} else {
			result = N;
			N -= 1;
		}
		while (N > 0) {
			if (N >= 4) {
				result = result - ((N * (N - 1)) / (N - 2));
				N -= 3;
				result = result + N;
				N -= 1;
			} else if (N == 3) {
				result = result - ((N * (N - 1)) / (N - 2));
				N -= 3;
			} else if (N == 2) {
				result = result - (N * (N - 1));
				N -= 2;
			} else {
				result = result - N;
				N -= 1;
			}
		}
		return result;
	}

	public static void main(String[] args) {
		int N = 4;
		int result = clumsy(N);
		System.out.println(result);
	}

}
