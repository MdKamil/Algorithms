package algorithm.string;

public class BullsCows {

	public String getHint(String secret, String guess) {
		StringBuilder builder = new StringBuilder();
		int[] unmatchedSecret = new int[10];
		int[] unmatchedGuess = new int[10];
		int bulls = 0;
		int cows = 0;
		for (int idx = 0; idx <= secret.length() - 1; ++idx) {
			char sc = secret.charAt(idx);
			char gc = guess.charAt(idx);
			if (sc == gc) {
				++bulls;
			} else {
				++unmatchedSecret[Character.digit(sc, 10)];
				++unmatchedGuess[Character.digit(sc, 10)];
			}
		}
		for (int idx = 0; idx <= 9; ++idx) {
			cows += (Math.min(unmatchedSecret[idx], unmatchedGuess[idx]));
		}
		builder.append(bulls).append('A').append(cows).append('B');
		return builder.toString();
	}
}
	