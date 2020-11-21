package algorithm.string;

public class DecodeWays {

	public static int numDecodings(String s) {
		int noOfWaysToDecode = 0;
		int[] noOfWaysToDecodeFromIdx = new int[s.length()];
		findNoOfWaysToDecode(0, s, noOfWaysToDecodeFromIdx);
		noOfWaysToDecode = noOfWaysToDecodeFromIdx[0];
		return noOfWaysToDecode;
	}

	private static int findNoOfWaysToDecode(int idx, String s, int[] noOfWaysToDecodeFromIdx) {
		int totalNoOfWaysToDecode = 0;
		if (idx > s.length() - 1) {
			return totalNoOfWaysToDecode;
		}
		totalNoOfWaysToDecode = findNoOfWaysToDecode(idx + 1, s, noOfWaysToDecodeFromIdx);
		if (idx + 1 <= s.length() - 1) {
			if (Character.getNumericValue(s.charAt(idx)) != 0) {
				int val = Character.getNumericValue(s.charAt(idx)) * 10 + Character.getNumericValue(s.charAt(idx + 1));
				if (val > 0 && val <= 26) {
					if (Character.getNumericValue(s.charAt(idx + 1)) == 0 && idx + 2 <= s.length() - 1) {
						noOfWaysToDecodeFromIdx[idx] = noOfWaysToDecodeFromIdx[idx + 2];
						totalNoOfWaysToDecode = noOfWaysToDecodeFromIdx[idx];
					} else if (Character.getNumericValue(s.charAt(idx + 2)) == 0 && idx + 2 <= s.length() - 1) {
						noOfWaysToDecodeFromIdx[idx] = noOfWaysToDecodeFromIdx[idx + 1];
						totalNoOfWaysToDecode = noOfWaysToDecodeFromIdx[idx];
					} else if (idx + 2 <= s.length() - 1) {
						int noOfWaysToDecode = noOfWaysToDecodeFromIdx[idx + 2];
						totalNoOfWaysToDecode += noOfWaysToDecode;
						noOfWaysToDecodeFromIdx[idx] = totalNoOfWaysToDecode;
					} else {
						// to handle last but one digit case
						int noOfWaysToDecode = 1;
						totalNoOfWaysToDecode += noOfWaysToDecode;
						noOfWaysToDecodeFromIdx[idx] = totalNoOfWaysToDecode;
					}
				} else {
					noOfWaysToDecodeFromIdx[idx] = noOfWaysToDecodeFromIdx[idx + 1];
					totalNoOfWaysToDecode = noOfWaysToDecodeFromIdx[idx];
				}
			} else {
				noOfWaysToDecodeFromIdx[idx] = 0;
				totalNoOfWaysToDecode = 0;
			}
		} else {
			// last digit
			if (Character.getNumericValue(s.charAt(idx)) != 0) {
				totalNoOfWaysToDecode = 1;
				noOfWaysToDecodeFromIdx[idx] = totalNoOfWaysToDecode;
			}
		}
		return totalNoOfWaysToDecode;
	}

	public static void main(String[] args) {
		String s = "001211001212";
		int noOfWays = numDecodings(s);
		System.out.println(noOfWays);
	}

}
