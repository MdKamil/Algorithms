package algorithm.two;

import java.util.Map;
import java.util.HashMap;

public class FirstDistinctCharacter {

	public static int firstUniqChar(String s) {
		int index = -1;
		int[] count = new int[255];
		for (int i = 0; i <= s.length() - 1; ++i) {
			++count[(int) s.charAt(i)];
		}
		for (int i = 0; i <= s.length() - 1; ++i) {
			if (count[(int) s.charAt(i)] == 1) {
				index = i;
				break;
			}
		}
		return index;
	}

	public static int firstUniqCharV2(String s) {
		int defaultIdx = -1;
		int[] count = new int[255];
		Map<Character, Integer> map = new HashMap<>();
		for (int i = 0; i <= s.length() - 1; ++i) {
			++count[(int) s.charAt(i)];
			map.put(s.charAt(i), i);
		}
		int index = Integer.MAX_VALUE;
		boolean hasDistinct = false;
		for (int i = 0; i <= count.length - 1; ++i) {
			if (count[i] == 1) {
				hasDistinct = true;
				int idx = map.get((char) i);
				index = Math.min(idx, index);
			}
		}
		return hasDistinct ? index : defaultIdx;
	}

	public static void main(String[] args) {
		String s = "leetcode";
		int index = firstUniqChar(s);
		System.out.println(s.charAt(index));
		index = firstUniqCharV2(s);
		System.out.println(s.charAt(index));
	}

}
