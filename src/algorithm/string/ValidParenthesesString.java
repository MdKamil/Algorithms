package algorithm.string;

import java.util.ArrayDeque;
import java.util.Deque;

public class ValidParenthesesString {
    public boolean checkValidString(String s) {
        boolean isValid = true;
        Deque<Integer> openBracketDeque = new ArrayDeque<>();
        Deque<Integer> asteriskDeque = new ArrayDeque<>();
        for (int idx = 0; idx <= s.length() - 1; ++idx) {
            if (s.charAt(idx) == '(') {
                openBracketDeque.addLast(idx);
            } else if (s.charAt(idx) == ')') {
                if (!openBracketDeque.isEmpty()) {
                    openBracketDeque.pollLast();
                } else if (!asteriskDeque.isEmpty()) {
                    asteriskDeque.pollLast();
                } else {
                    isValid = false;
                    break;
                }
            } else {
                asteriskDeque.addLast(idx);
            }
        }
        if (isValid) {
            if ((!openBracketDeque.isEmpty() && asteriskDeque.isEmpty()) || (openBracketDeque.size() > asteriskDeque.size())) {
                isValid = false;
            } else {
                while (!openBracketDeque.isEmpty() && !asteriskDeque.isEmpty()) {
                    int openBracketIdx = openBracketDeque.pollLast();
                    int asteriskIdx = asteriskDeque.pollLast();
                    if (asteriskIdx < openBracketIdx) {
                        isValid = false;
                        break;
                    }
                }
            }
        }
        return isValid;
    }

    public static void main(String[] args) {
        String s = "(((((*(()((((*((**(((()()*)()()()*((((**)())*)*)))))))(())(()))())((*()()(((()((()*(())*(()**)()(())";
        ValidParenthesesString validParenthesesString = new ValidParenthesesString();
        boolean isValid = validParenthesesString.checkValidString(s);
        System.out.println(isValid);
    }
}
