package algorithm.string;

public class LongestCommonPrefix {
	public static String longestCommonPrefix(String[] strs) {
		String commonPrefixString = "";
		if (strs != null && strs.length > 0) {
			int currPrefixLength = strs[0].length();
			for (int idx = 1; idx <= strs.length - 1; ++idx) {
				int length = 1;
				for (; length <= Math.min(currPrefixLength, strs[idx].length()); ++length) {
					if (strs[0].charAt(length - 1) != strs[idx].charAt(length - 1)) {
						break;
					}
				}
				currPrefixLength = length - 1;
				if (currPrefixLength == 0) {
					break;
				}
			}
			commonPrefixString = strs[0].substring(0, currPrefixLength);
		}
		return commonPrefixString;
	}

	public static void main(String[] args) {
		String[] strs = { "flower", "flow", "flight", "fana", "a" };
		String commonPrefixString = longestCommonPrefix(strs);
		System.out.println(commonPrefixString);
	}

}
