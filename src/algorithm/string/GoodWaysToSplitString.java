package algorithm.string;

public class GoodWaysToSplitString {

	public static int numSplits(String s) {
		int numSplits = 0;
		if (s.length() > 1) {
			int[] front = new int[26];
			int[] back = new int[26];
			int backCount = 0;
			int frontCount = 0;
			for (int idx = s.length() - 1; idx >= 1; --idx) {
				if (back[s.charAt(idx) - 'a'] == 0) {
					++backCount;
				}
				++back[s.charAt(idx) - 'a'];
			}
			for (int idx = 0; idx <= s.length() - 2; ++idx) {
				if (front[s.charAt(idx) - 'a'] == 0) {
					++frontCount;
				}
				++front[s.charAt(idx) - 'a'];
				if (frontCount == backCount) {
					++numSplits;
				}
				--back[s.charAt(idx + 1) - 'a'];
				if (back[s.charAt(idx + 1) - 'a'] == 0) {
					--backCount;
				}
			}
		}
		return numSplits;
	}

	public static void main(String[] args) {
		String s = "acbadbaada";
		System.out.println(numSplits(s));
	}
}
