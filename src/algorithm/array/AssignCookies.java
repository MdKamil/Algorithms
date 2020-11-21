package algorithm.array;

import java.util.Arrays;

public class AssignCookies {

	public static int findContentChildren(int[] g, int[] s) {
		int maxContent = 0;
		if (g != null && g.length > 0 && s != null && s.length > 0) {
			Arrays.sort(g);
			Arrays.sort(s);
			int greedIdx = 0;
			int sizeIdx = 0;
			while (greedIdx <= g.length - 1 && sizeIdx <= s.length - 1) {
				if (s[sizeIdx] >= g[greedIdx]) {
					++sizeIdx;
					++greedIdx;
					++maxContent;
				} else {
					++sizeIdx;
				}
			}
		}
		return maxContent;
	}

	public static void main(String[] args) {
		int[] g = { 1, 2, 3 };
		int[] s = { 1, 1 };
		int maxContent = findContentChildren(g, s);
		System.out.println(maxContent);
	}

}
