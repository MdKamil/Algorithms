package algorithm.four;

import java.util.ArrayList;
import java.util.List;

public class BalancedParantheses {
	public static List<String> generateBalancedParentheses(int numPairs) {
		List<String> result = new ArrayList<>();
		directedGenerateBalancedParentheses(numPairs, numPairs, "", result);
		return result;
	}

	private static void directedGenerateBalancedParentheses(int numLeftParensNeeded, int numRightParensNeeded,
			String validPrefix, List<String> result) {
		if (numLeftParensNeeded == 0 && numRightParensNeeded == 0) {
			result.add(validPrefix);
			return;
		}
		if (numLeftParensNeeded > 0) { // Able to insert
			directedGenerateBalancedParentheses(numLeftParensNeeded - 1, numRightParensNeeded, validPrefix + "(",
					result);
		}
		if (numLeftParensNeeded < numRightParensNeeded) { // Able to insert
			directedGenerateBalancedParentheses(numLeftParensNeeded, numRightParensNeeded - 1, validPrefix + ")",
					result);
		}
	}
}
