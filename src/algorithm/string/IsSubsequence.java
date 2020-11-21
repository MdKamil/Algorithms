package algorithm.string;

public class IsSubsequence {
	public static boolean isSubsequence(String s, String t) {
		boolean isSubsequence = false;
		if (s.length() == 0 && t.length() == 0) {
			isSubsequence = true;
		} else if (s.length() == 0 && t.length() > 0) {
			isSubsequence = true;
		} else if (s.length() > 0 && t.length() == 0) {
			isSubsequence = false;
		} else {
			int sIdx = 0;
			int tIdx = 0;
			while (tIdx <= t.length() - 1 && sIdx <= s.length() - 1) {
				if (s.charAt(sIdx) == t.charAt(tIdx)) {
					++sIdx;
				}
				++tIdx;
			}
			if (sIdx > s.length() - 1) {
				isSubsequence = true;
			}
		}
		return isSubsequence;
	}

	public static void main(String[] args) {
		String s = "s";
		String t = "ahbgdc";
		boolean isSubsequence = isSubsequence(s, t);
		System.out.println(isSubsequence);
	}

}
