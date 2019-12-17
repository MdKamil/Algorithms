package algorithm.six;

public class WildcardPatternMatching {

	public static boolean isMatch(String s, String p) {
		boolean isMatch = false;
		if (s != null && p != null) {
			if (p.length() == 0 && s.length() == 0) {
				isMatch = true;
			} else if (p.length() == 0 && s.length() != 0) {
				isMatch = false;
			} else {
				int starCount = 0;
				int questionMarkCount = 0;
				int lastStarIdx = -1;
				int currStarIdx = -1;
				int patternIdxToResort = -1;
				int patternIdx = 0;
				int stringLength = s.length() == 0 ? 0 : s.length() - 1;
				for (int idx = 0; idx <= stringLength; ++idx) {
					if (patternIdx > p.length() - 1 && idx <= s.length() - 1) {
						if (lastStarIdx != -1) {
							patternIdx = patternIdxToResort;
						} else {
							isMatch = false;
							break;
						}
					}
					if (p.charAt(patternIdx) == '*' || p.charAt(patternIdx) == '?') {
						starCount = 0;
						questionMarkCount = 0;
						while (true) {
							if (p.charAt(patternIdx) == '*') {
								if (currStarIdx == -1) {
									currStarIdx = patternIdx;
								}
								++starCount;
							} else if (p.charAt(patternIdx) == '?') {
								++questionMarkCount;
							} else {
								break;
							}
							++patternIdx;
							if (patternIdx > p.length() - 1) {
								break;
							}
						}
						if (currStarIdx != -1) {
							lastStarIdx = currStarIdx;
						}
						currStarIdx = -1;
						if (starCount > 0) {
							patternIdxToResort = patternIdx;
						}
					}
					if (patternIdx > p.length() - 1) {
						if (questionMarkCount > 0 && s.length() > 0) {
							if (((s.length() - 1) - idx) + 1 == questionMarkCount) {
								isMatch = true;
								break;
							} else if (((s.length() - 1) - idx) + 1 > questionMarkCount) {
								if (starCount > 0) {
									isMatch = true;
									break;
								}
							} else {
								isMatch = false;
								break;
							}
						} else if (starCount > 0) {
							isMatch = true;
							break;
						}

					}
					if (questionMarkCount > 0) {
						idx += (questionMarkCount - 1);
						questionMarkCount = 0;
					} else if (s.length() == 0) {
						isMatch = false;
						break;
					} else if (p.charAt(patternIdx) == s.charAt(idx)) {
						++patternIdx;
						if (patternIdx > p.length() - 1 && idx + 1 > s.length() - 1) {
							isMatch = true;
							break;
						}
					} else {
						if (lastStarIdx == -1) {
							isMatch = false;
							break;
						} else {
							if (p.charAt(patternIdxToResort) == s.charAt(idx)) {
								idx -= 1;
							}
							patternIdx = patternIdxToResort;
						}
					}
				}
				if (patternIdx <= p.length() - 1) {
					isMatch = true;
					for (int idx = patternIdx; idx <= p.length() - 1; ++idx) {
						if (p.charAt(idx) != '*') {
							isMatch = false;
							break;
						}
					}
				}
			}
		}
		return isMatch;
	}

	public static void main(String[] args) {
		String s = "aa";
		String p = "a";
		boolean isMatch = isMatch(s, p);
		System.out.println(isMatch);
	}
}
