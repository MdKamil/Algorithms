package algorithm.string;

import java.util.Objects;

public class MinimumRemoveToMakeValidParentheses {
    public String minRemoveToMakeValid(String s) {
        int openBracketsCnt = 0;
        int closedBracketsCnt = 0;
        int removedCharCnt = 0;
        char[] charArray = s.toCharArray();
        for (int idx = 0; idx <= charArray.length - 1; ++idx) {
            if (Objects.equals(charArray[idx], '(')) {
                ++openBracketsCnt;
            } else if (Objects.equals(charArray[idx], ')')) {
                ++closedBracketsCnt;
            }
            if (closedBracketsCnt > openBracketsCnt) {
                --closedBracketsCnt;
                ++removedCharCnt;
                charArray[idx] = Character.MIN_VALUE;
            }
        }
        if (openBracketsCnt > closedBracketsCnt) {
            for (int idx = charArray.length - 1; idx >= 0 && openBracketsCnt > closedBracketsCnt; --idx) {
                if (Objects.equals('(', charArray[idx])) {
                    charArray[idx] = Character.MIN_VALUE;
                    ++removedCharCnt;
                    --openBracketsCnt;
                }
            }
        }
        char[] result = new char[s.length() - removedCharCnt];
        int idx = 0;
        for (char c : charArray) {
            if (!Objects.equals(c, Character.MIN_VALUE)) {
                result[idx++] = c;
            }
        }
        return new String(result);
    }

    public static void main(String[] args) {
        String s = "(()))";
        String result = new MinimumRemoveToMakeValidParentheses().minRemoveToMakeValid(s);
        System.out.println(result + " " + result.length());
    }
}
