package algorithm.string;

// https://leetcode.com/problems/decode-string/
public class DecodeString {
	public String decodeString(String s) {
		Pair<String, Integer> pair = new Pair<>();
		decode(s, 0, pair);
		return pair.first;
	}

	private static class Pair<T1, T2> {
		T1 first;
		T2 second;

		public Pair() {
		}

		public Pair(T1 first, T2 second) {
			this.first = first;
			this.second = second;
		}
	}

	private void decode(String s, int fromIdx, Pair<String, Integer> pair) {
		StringBuilder builder = new StringBuilder();
		int idx = fromIdx;
		for (; idx <= s.length() - 1;) {
			if (Character.isDigit(s.charAt(idx))) {
				int times = 0;
				while (Character.isDigit(s.charAt(idx))) {
					times = (times * 10) + Character.digit(s.charAt(idx), 10);
					++idx;
				}
				decode(s, idx, pair);
				for (int count = 1; count <= times; ++count) {
					builder.append(pair.first);
				}
				idx = pair.second;
			} else if (s.charAt(idx) == ']') {
				++idx;
				break;
			} else {
				builder.append(s.charAt(idx));
				++idx;
			}
		}
		pair.first = builder.toString();
		pair.second = idx;
	}

	public static void main(String[] args) {
		String encoded = "100[abcdefghijklmnopqrstuvwxy]";
		DecodeString decodeString = new DecodeString();
		System.out.println(decodeString.decodeString(encoded));
	}

}
