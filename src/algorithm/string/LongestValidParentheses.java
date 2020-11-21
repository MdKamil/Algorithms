package algorithm.string;

public class LongestValidParentheses {

	public static int longestValidParentheses(String s) {
		int maxLength = 0;
		if (s != null && s.length() > 0) {
			int openCount = 0;
			int closedCount = 0;
			int currentLength = 0;
			for (int idx = 0; idx <= s.length() - 1; ++idx) {
				if (s.charAt(idx) == '(') {
					++openCount;
				} else if (s.charAt(idx) == ')') {
					++closedCount;
					if (closedCount > openCount) {
						maxLength = Math.max(maxLength, currentLength);
						currentLength = 0;
						openCount = 0;
						closedCount = 0;
					} else {
						currentLength += 2;
						--openCount;
						--closedCount;
						if (closedCount == 0 && openCount == 0) {
							maxLength = Math.max(maxLength, currentLength);
						}
					}
				}
			}
			openCount = 0;
			closedCount = 0;
			currentLength = 0;
			for (int idx = s.length() - 1; idx >= 0; --idx) {
				if (s.charAt(idx) == ')') {
					++closedCount;
				} else if (s.charAt(idx) == '(') {
					++openCount;
					if (openCount > closedCount) {
						maxLength = Math.max(maxLength, currentLength);
						currentLength = 0;
						openCount = 0;
						closedCount = 0;
					} else {
						currentLength += 2;
						--openCount;
						--closedCount;
						if (closedCount == 0 && openCount == 0) {
							maxLength = Math.max(maxLength, currentLength);
						}
					}
				}
			}
		}
		return maxLength;
	}

	public static int longestValidParenthesesV2(String s) {
		int maxLength = 0;
		if (s != null && s.length() > 0) {
			int openCountFromFront = 0;
			int closedCountFromFront = 0;
			int currentLengthFromFront = 0;
			int openCountFromBack = 0;
			int closedCountFromBack = 0;
			int currentLengthFromBack = 0;
			for (int front = 0, back = s.length() - 1; front <= s.length() - 1 && back >= 0; ++front, --back) {
				if (s.charAt(front) == '(') {
					++openCountFromFront;
				} else if (s.charAt(front) == ')') {
					++closedCountFromFront;
					if (closedCountFromFront > openCountFromFront) {
						maxLength = Math.max(maxLength, currentLengthFromFront);
						currentLengthFromFront = 0;
						openCountFromFront = 0;
						closedCountFromFront = 0;
					} else {
						currentLengthFromFront += 2;
						--openCountFromFront;
						--closedCountFromFront;
						if (closedCountFromFront == 0 && openCountFromFront == 0) {
							maxLength = Math.max(maxLength, currentLengthFromFront);
						}
					}
				}

				if (s.charAt(back) == ')') {
					++closedCountFromBack;
				} else if (s.charAt(back) == '(') {
					++openCountFromBack;
					if (openCountFromBack > closedCountFromBack) {
						maxLength = Math.max(maxLength, currentLengthFromBack);
						currentLengthFromBack = 0;
						openCountFromBack = 0;
						closedCountFromBack = 0;
					} else {
						currentLengthFromBack += 2;
						--openCountFromBack;
						--closedCountFromBack;
						if (closedCountFromBack == 0 && openCountFromBack == 0) {
							maxLength = Math.max(maxLength, currentLengthFromBack);
						}
					}
				}

			}
		}
		return maxLength;
	}

	public static void main(String[] args) {
		String s = ")(((())))(";
		int maxLength = longestValidParentheses(s);
		System.out.println(maxLength);
	}

}
