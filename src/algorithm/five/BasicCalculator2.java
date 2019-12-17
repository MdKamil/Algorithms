package algorithm.five;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class BasicCalculator2 {

	public static int calculate(String s) {
		int expressionValue = 0;
		Deque<Integer> numberStack = new ArrayDeque<>();
		Deque<Character> symbolStack = new ArrayDeque<>();
		Map<Character, Integer> symbolWeight = new HashMap<>();
		symbolWeight.put('/', 4);
		symbolWeight.put('*', 3);
		symbolWeight.put('+', 2);
		symbolWeight.put('-', 1);
		int multiplyValue = 1;
		int currentNumber = 0;
		for (int idx = 0; idx <= s.length() - 1; ++idx) {
			if (Character.isWhitespace(s.charAt(idx))) {
				continue;
			} else if (!Character.isDigit(s.charAt(idx))) {
				numberStack.addLast(currentNumber);
				currentNumber = 0;
				multiplyValue = 1;
				if (symbolStack.isEmpty()) {
					symbolStack.addLast(s.charAt(idx));
				} else {
					int value = compare(symbolWeight.get(s.charAt(idx)), symbolWeight.get(symbolStack.peekLast()));
					if (value >= 0) {
						symbolStack.addLast(s.charAt(idx));
					} else {
						while (value < 0) {
							int num1 = numberStack.pollLast();
							int num2 = numberStack.pollLast();
							char symbol = symbolStack.pollLast();
							int result = calculate(num1, num2, symbol);
							numberStack.addLast(result);
							expressionValue += result;
							if (symbolStack.isEmpty()) {
								break;
							}
							value = compare(symbolWeight.get(s.charAt(idx)), symbolWeight.get(symbolStack.peekLast()));
						}
						symbolStack.addLast(s.charAt(idx));
					}
				}
			} else {
				currentNumber = (currentNumber * multiplyValue) + Character.getNumericValue(s.charAt(idx));
				multiplyValue = 10;
			}
		}
		numberStack.addLast(currentNumber);
		while (!symbolStack.isEmpty()) {
			int num1 = numberStack.pollLast();
			int num2 = numberStack.pollLast();
			char symbol = symbolStack.pollLast();
			int result = calculate(num2, num1, symbol);
			numberStack.addLast(result);
		}
		expressionValue = numberStack.pollLast();
		return expressionValue;
	}

	private static int calculate(int num1, int num2, char symbol) {
		int value = 0;
		if (symbol == '+') {
			value = num1 + num2;
		} else if (symbol == '-') {
			value = num1 - num2;
		} else if (symbol == '*') {
			value = num1 * num2;
		} else {
			value = num1 / num2;
		}
		return value;
	}

	private static int compare(int nextSymbolWeight, int currSymbolWeight) {
		return Integer.compare(nextSymbolWeight, currSymbolWeight);
	}

	public static void main(String[] args) {
		String s = "1337+4521";
		int value = calculate(s);
		System.out.println(value);
	}

}
