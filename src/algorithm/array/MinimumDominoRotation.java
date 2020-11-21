package algorithm.array;

public class MinimumDominoRotation {

	public static int minDominoRotations(int[] A, int[] B) {
		int minRotation = -1;
		if (A != null && A.length != 0 || B != null && B.length != 0) {
			int noOfDominoes = A.length;
			int countOfFirstDomino = 0;
			int countOfFirstDominoOnTop = 0;
			int countOfFirstDominoOnBottom = 0;
			int countOfSecondDomino = 0;
			int countOfSecondDominoOnTop = 0;
			int countOfSecondDominoOnBottom = 0;
			for (int idx = 0; idx <= A.length - 1; ++idx) {
				if (A[0] == A[idx] && A[0] == B[idx]) {
					++countOfFirstDomino;
					++countOfFirstDominoOnTop;
					++countOfFirstDominoOnBottom;
				} else if (A[idx] == A[0]) {
					++countOfFirstDomino;
					++countOfFirstDominoOnTop;
				} else if (B[idx] == A[0]) {
					++countOfFirstDomino;
					++countOfFirstDominoOnBottom;
				}
				if (B[0] == A[idx] && B[0] == B[idx]) {
					++countOfSecondDomino;
					++countOfSecondDominoOnTop;
					++countOfSecondDominoOnBottom;
				} else if (B[idx] == B[0]) {
					++countOfSecondDomino;
					++countOfSecondDominoOnBottom;
				} else if (A[idx] == B[0]) {
					++countOfSecondDomino;
					++countOfSecondDominoOnTop;
				}
			}
			if (countOfFirstDomino == noOfDominoes || countOfSecondDomino == noOfDominoes) {
				if (A[0] == B[0]
						&& (countOfFirstDominoOnTop == noOfDominoes && countOfSecondDominoOnBottom == noOfDominoes)) {
					minRotation = 0;
				} else if (A[0] != B[0]
						&& (countOfFirstDomino == noOfDominoes && countOfSecondDomino == noOfDominoes)) {
					if ((countOfFirstDominoOnTop == noOfDominoes && countOfFirstDominoOnBottom == 0)
							&& (countOfSecondDominoOnBottom == noOfDominoes && countOfSecondDominoOnTop == 0)) {
						minRotation = 0;
					} else {
						minRotation = Math.min(countOfFirstDominoOnTop, countOfFirstDominoOnBottom);
					}
				} else if (countOfFirstDomino == noOfDominoes && countOfSecondDomino < noOfDominoes) {
					minRotation = Math.min(A.length - countOfFirstDominoOnTop, A.length - countOfFirstDominoOnBottom);
				} else {
					minRotation = Math.min(B.length - countOfSecondDominoOnTop, B.length - countOfSecondDominoOnBottom);
				}
			}
		}
		return minRotation;
	}

	public static void main(String[] args) {
		int[] A = { 1, 1, 1, 1, 1, 3, 4 };
		int[] B = { 2, 1, 2, 2, 3, 1, 1 };
		int minRotation = minDominoRotations(A, B);
		System.out.println(minRotation);
	}

}
