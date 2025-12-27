package algorithm.backtracking;

import java.util.ArrayList;
import java.util.List;

public class GenerateParentheses {
    private static final Character OPEN_BRACKET = '(';
    private static final Character CLOSED_BRACKET = ')';

    public List<String> generateParenthesis(int n) {
        StringBuilder builder = new StringBuilder();
        List<String> list = new ArrayList<>();
        recurse(n, 1, 1, builder, list);
        return list;
    }

    private void recurse(int n, int currOpen, int currClose, StringBuilder builder, List<String> list) {
        if (currOpen == n + 1 && currClose == n + 1) {
            list.add(builder.toString());
        }
        if (currOpen <= n) {
            builder.append(OPEN_BRACKET);
            recurse(n, currOpen + 1, currClose, builder, list);
            builder.deleteCharAt(builder.length() - 1);
        }
        if (currClose <= n && currClose + 1 <= currOpen) {
            builder.append(CLOSED_BRACKET);
            recurse(n, currOpen, currClose + 1, builder, list);
            builder.deleteCharAt(builder.length() - 1);
        }
    }

    public static void main(String[] args) {
        int n = 1;
        GenerateParentheses generate = new GenerateParentheses();
        List<String> parentheses = generate.generateParenthesis(n);
        parentheses.forEach(System.out::println);
    }
}
