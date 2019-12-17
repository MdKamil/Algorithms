package algorithm.one;

public class ReverseWords {

	public static String reverseWords(String s) {
		String reverse = null;
		if (s != null) {
			if (s.length() == 0) {
				reverse = s;
			} else {
				StringBuilder builder = new StringBuilder();
				int wordEndIdx = s.length() - 1;
				for (int i = s.length() - 1; i >= 0; --i) {
					if (Character.isWhitespace(s.charAt(i))) {
						formWord(s, builder, i + 1, wordEndIdx);
						wordEndIdx = i - 1;
					}
				}
				// left-over
				formWord(s, builder, 0, wordEndIdx);
				if (builder.length() > 0) {
					reverse = builder.substring(0, builder.length() - 1);
				} else {
					reverse = new String();
				}
			}
		}
		return reverse;
	}

	private static void formWord(String s, StringBuilder builder, int start, int end) {
		for (int from = start; from <= end; ++from) {
			builder.append(s.charAt(from));
			if (from == end) {
				builder.append(' ');
			}
		}
	}

	public static void main(String[] args) {
		String s = "the    sky    is    blue";
		s.trim();
		System.out.println(reverseWords(s));
	}

}
