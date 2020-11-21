package algorithm.string;

public class RemoveKDigits {

	public static String removeKdigits(String num, int k) {
		String result = null;
		if (num != null) {
			if (num.length() == 0) {
				result = num;
			} else if (num.length() == k) {
				result = "0";
			} else {
				char[] numArray = num.toCharArray();
				int maxLength = num.length() - k;
				int currentLength = 0;
				StringBuilder smallestNum = new StringBuilder();
				for (int idx = 0; idx <= maxLength - 1; ++idx) {
					smallestNum.append(numArray[idx]);
				}
				StringBuilder currentNum = new StringBuilder();
				getSmallestNumber(numArray, 0, maxLength, currentLength, smallestNum, currentNum);
				result = smallestNum.toString();
			}
		}
		return result;
	}

	private static void getSmallestNumber(char[] numArray, int fromIdx, int maxLength, int currentLength,
			StringBuilder smallestNum, StringBuilder currentNum) {
		if (currentLength > maxLength) {
			return;
		}
		if (fromIdx > numArray.length - 1) {
			return;
		}
		for (int iter = fromIdx; iter <= numArray.length - 1; ++iter) {
			currentNum.append(numArray[iter]);
			++currentLength;
			if (currentLength == maxLength) {
				//System.out.println(currentNum + " " + smallestNum);
				// compare and assign smallest
				int prefixZeroLength = checkForLeadingZeros(currentNum);
				if (prefixZeroLength != -1) {
					if (currentNum.length() - prefixZeroLength < smallestNum.length()) {
						smallestNum.setLength(0);
						if (currentNum.length() - prefixZeroLength == 0) {
							smallestNum.append('0');
						} else {
							smallestNum.append(currentNum.substring(prefixZeroLength));
						}
					} else if (currentNum.length() - prefixZeroLength == smallestNum.length()) {
						int smallNumIdx = 0;
						int currNumIdx = prefixZeroLength;
						while (smallNumIdx <= smallestNum.length() - 1) {
							if (currentNum.charAt(currNumIdx) > smallestNum.charAt(smallNumIdx)) {
								break;
							} else if (currentNum.charAt(currNumIdx) < smallestNum.charAt(smallNumIdx)) {
								smallestNum.setLength(0);
								smallestNum.append(currentNum.substring(prefixZeroLength));
								break;
							}
							++smallNumIdx;
							++currNumIdx;
						}
					}
				} else {
					if (currentNum.length() == smallestNum.length()) {
						int idx = 0;
						while (idx <= currentNum.length() - 1) {
							if (currentNum.charAt(idx) > smallestNum.charAt(idx)) {
								break;
							} else if (currentNum.charAt(idx) < smallestNum.charAt(idx)) {
								smallestNum.setLength(0);
								smallestNum.append(currentNum.toString());
								break;
							}
							++idx;
						}
					}
				}
				currentNum.deleteCharAt(currentNum.length() - 1);
				--currentLength;
				continue;
			}
			getSmallestNumber(numArray, iter + 1, maxLength, currentLength, smallestNum, currentNum);
			currentNum.deleteCharAt(currentNum.length() - 1);
			--currentLength;
		}
	}

	private static int checkForLeadingZeros(StringBuilder currentNum) {
		int zeroLength = -1;
		int idx = 0;
		if (idx <= currentNum.length() - 1 && currentNum.charAt(idx) != '0') {
			return zeroLength;
		}
		while (idx <= currentNum.length() - 1 && currentNum.charAt(idx) == '0') {
			++idx;
		}
		zeroLength = idx;
		return zeroLength;
	}

	public static void main(String[] args) {
		String num = new String("10");
		int k = 1;
		String smallestNum = removeKdigits(num, k);
		System.out.println(smallestNum);
	}

}
