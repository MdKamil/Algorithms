package algorithm.string;

public class RemoveAdjacentDuplicatesString {

	public String removeDuplicates(String s) {
		StringBuilder builder = new StringBuilder();
		for (int idx = 0; idx <= s.length() - 1;) {
			if (idx + 1 <= s.length() - 1 && s.charAt(idx) == s.charAt(idx + 1)) {
				idx += 2;
			} else {
				if (builder.length() > 0 && builder.charAt(builder.length() - 1) == s.charAt(idx))
					builder.deleteCharAt(builder.length() - 1);
				else {
					builder.append(s.charAt(idx));
				}
				idx += 1;
			}
		}
		return builder.toString();
	}

	public static void main(String[] args) {
		String s = "azxxzy";
		System.out.println(new RemoveAdjacentDuplicatesString().removeDuplicates(s));
	}
}
