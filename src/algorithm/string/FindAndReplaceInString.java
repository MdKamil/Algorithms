package algorithm.string;

import java.util.HashMap;

public class FindAndReplaceInString {

	private static class Triplet<T1, T2, T3> {
		T1 first;
		T2 second;
		T3 third;

		public Triplet(T1 first, T2 second, T3 third) {
			this.first = first;
			this.second = second;
			this.third = third;
		}
	}

	public static String findReplaceString(String s, int[] indexes, String[] sources, String[] targets) {
		StringBuilder builder = new StringBuilder();
		HashMap<Integer, Triplet<Integer, String, String>> map = new HashMap<>();
		for (int idx = 0; idx <= indexes.length - 1; ++idx) {
			map.put(indexes[idx], new Triplet<>(indexes[idx], sources[idx], targets[idx]));
		}
		for (int idx = 0; idx <= s.length() - 1;) {
			if (map.containsKey(idx)) {
				Triplet<Integer, String, String> triplet = map.get(idx);
				if (checkIfPresent(triplet.second, s, idx)) {
					builder.append(triplet.third);
					idx += triplet.second.length();
				} else {
					builder.append(s.charAt(idx++));
				}
			} else {
				builder.append(s.charAt(idx++));
			}
		}
		return builder.toString();
	}

	private static boolean checkIfPresent(String source, String s, int idx) {
		int idx1 = 0;
		while (idx1 <= source.length() - 1 && idx <= s.length() - 1) {
			if (source.charAt(idx1++) == s.charAt(idx++)) {
			} else {
				break;
			}
		}
		return idx1 > source.length() - 1 ? true : false;
	}

	public static void main(String[] args) {
		String s = "abcd";
		int[] indexes = { 0, 2 };
		String[] sources = { "ab", "ec" };
		String[] targets = { "eee", "ffff" };
		System.out.println(findReplaceString(s, indexes, sources, targets));
	}

}
