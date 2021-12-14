package algorithm.dp;

public class MaximumPointsObtainFromCards {

	public static int maxScore(int[] cardPoints, int k) {
		int maxScore = 0;
		int leftSum = 0;
		int rightSum = 0;
		int idx = cardPoints.length - 1;
		int remove = k;
		while (remove > 0) {
			rightSum += cardPoints[idx--];
			--remove;
		}
		if (k == cardPoints.length) {
			maxScore = rightSum;
		} else {
			maxScore = Math.max(maxScore, leftSum + rightSum);
			for (int count = 0; count <= k - 1; ++count) {
				leftSum += cardPoints[count];
				rightSum -= cardPoints[++idx];
				maxScore = Math.max(maxScore, leftSum + rightSum);
			}
		}
		return maxScore;
	}

	public static void main(String[] args) {
		int[] cardPoints = { 100, 40, 17, 9, 73, 75 };
		int k = 6;
		System.out.println(maxScore(cardPoints, k));
	}

}
