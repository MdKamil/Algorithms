package algorithm.array;

import java.util.ArrayDeque;
import java.util.Deque;

public class BasicCalculator2 {
    public int calculate(String s) {
        Deque<Integer> nD = new ArrayDeque<>();
        Deque<Character> sD = new ArrayDeque<>();
        int num = 0;
        for (int idx = 0; idx <= s.length() - 1; ++idx) {
            if (Character.isDigit(s.charAt(idx))) {
                num = ((num * 10) + Character.getNumericValue(s.charAt(idx)));
            } else if (!Character.isDigit(s.charAt(idx)) && !Character.isWhitespace(s.charAt(idx))) {
                nD.push(num);
                num = 0;
                if (nD.size() > 1 && !sD.isEmpty() && (sD.peek() == '/' || sD.peek() == '*')) {
                    int num2 = nD.poll();
                    int num1 = nD.poll();
                    int val = calculate(num1, num2, sD.poll());
                    nD.push(val);
                }
                sD.push(s.charAt(idx));
            }
        }
        nD.push(num);
        if (nD.size() > 1 && !sD.isEmpty() && (sD.peek() == '/' || sD.peek() == '*')) {
            int num2 = nD.poll();
            int num1 = nD.poll();
            int val = calculate(num1, num2, sD.poll());
            nD.push(val);
        }
        while (nD.size() > 1 && !sD.isEmpty()) {
            int num1 = nD.pollLast();
            int num2 = nD.pollLast();
            int val = calculate(num1, num2, sD.pollLast());
            nD.addLast(val);
        }
        return nD.poll();
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

    public static void main(String[] args) {
        String s = " 3/2 ";
        int val = new BasicCalculator2().calculate(s);
        System.out.println(val);
    }
}
