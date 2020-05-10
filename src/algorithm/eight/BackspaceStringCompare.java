package algorithm.eight;

public class BackspaceStringCompare {

	private static int getIdxAfterBackspacing(String str, int currIdx) {
		int hashCount = 0;
		while (currIdx >= 0) {
			if (str.charAt(currIdx) == '#') {
				++hashCount;
				--currIdx;
			} else {
				if (hashCount > 0) {
					while (hashCount > 0 && (currIdx >= 0 && str.charAt(currIdx) != '#')) {
						--hashCount;
						--currIdx;
					}
				} else {
					break;
				}
			}
		}
		return currIdx;
	}

	public static boolean backspaceCompare(String S, String T) {
		boolean isSame = true;
		if (S != null && T != null) {
			int idx1 = S.length() - 1;
			int idx2 = T.length() - 1;
			while (true) {
				idx1 = getIdxAfterBackspacing(S, idx1);
				idx2 = getIdxAfterBackspacing(T, idx2);
				if (idx1 < 0 && idx2 < 0) {
					isSame = true;
					break;
				} else if ((idx1 < 0 && idx2 >= 0) || (idx1 >= 0 && idx2 < 0) || (S.charAt(idx1) != T.charAt(idx2))) {
					isSame = false;
					break;
				} else {
					--idx1;
					--idx2;
				}
			}
		}
		return isSame;
	}

	public static void main(String[] args) {
		String S = "####";
		String T = "da##";
		boolean isSame = backspaceCompare(S, T);
		System.out.println(isSame);
	}

}
