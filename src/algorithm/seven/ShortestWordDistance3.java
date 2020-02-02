package algorithm.seven;

public class ShortestWordDistance3 {

	public static int shortestWordDistance(String[] words, String word1, String word2) {
		int shortestDistance = words.length - 1;
		int word1LastIdx = -1;
		int word2LastIdx = -1;
		for (int idx = 0; idx <= words.length - 1; ++idx) {
			if (words[idx].equals(word1)) {
				if (words[idx].equals(word2)) {
					if (word1LastIdx != -1) {
						shortestDistance = Math.min(shortestDistance, idx - word1LastIdx);
					}
				} else {
					if (word2LastIdx != -1) {
						shortestDistance = Math.min(shortestDistance, idx - word2LastIdx);
					}
				}
				word1LastIdx = idx;
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
		String[] words = { "a", "b", "c", "a", "e", "a", "e", "e", "d" };
		String word1 = "e";
		String word2 = "e";
		int shortestDistance = shortestWordDistance(words, word1, word2);
		System.out.println(shortestDistance);
	}

}
