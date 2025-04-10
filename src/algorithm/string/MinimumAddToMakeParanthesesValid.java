package algorithm.string;

public class MinimumAddToMakeParanthesesValid {
    public int minAddToMakeValid(String s) {
        int count = 0;
        int openCount = 0;
        for (char c : s.toCharArray()) {
            if (c == ')' && openCount == 0) {
                ++count;
            } else if (c == ')' && openCount > 0) {
                --openCount;
            } else if (c == '(') {
                ++openCount;
            }
        }
        count += openCount;
        return count;
    }

    public static void main(String[] args) {
        String s = "())";
        int count = new MinimumAddToMakeParanthesesValid().minAddToMakeValid(s);
        System.out.println(count);
    }
}
