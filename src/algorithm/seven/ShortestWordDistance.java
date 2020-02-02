package algorithm.seven;

public class ShortestWordDistance {

	public static int shortestDistance(String[] words, String word1, String word2) {
		int shortestDistance = words.length - 1;
		int word1LastIdx = -1;
		int word2LastIdx = -1;
		for (int idx = 0; idx <= words.length - 1; ++idx) {
			if (words[idx].equals(word1)) {
				word1LastIdx = idx;
				if (word2LastIdx != -1) {
					shortestDistance = Math.min(shortestDistance, idx - word2LastIdx);
				}
			} else if (words[idx].equals(word2)) {
				word2LastIdx = idx;
				if (word1LastIdx != -1) {
					shortestDistance = Math.min(shortestDistance, idx - word1LastIdx);
				}
			}
		}
		return shortestDistance;
	}

	public static void main(String[] args) {
		String[] words = { "practice", "makes", "perfect", "makes", "coding", "perfect", "makes", "practice", "makes",
				"coding", "practice" };
		String word1 = "perfect";
		String word2 = "coding";
		int shortestDistance = shortestDistance(words, word1, word2);
		System.out.println(shortestDistance);
	}

}
