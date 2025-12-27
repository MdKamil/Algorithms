package algorithm.string;

import java.util.ArrayDeque;
import java.util.Deque;

public class RemoveStarsFromString {
    public String removeStars(String s) {
        Deque<Integer> deque = new ArrayDeque<>();
        int starCount = 0;
        for (int idx = 0; idx <= s.length() -1; ++idx) {
            if (s.charAt(idx) != '*') {
                deque.addLast(idx);
            } else {
                ++starCount;
                deque.pollLast();
            }
        }
        char[] result = new char[s.length() - (2 * starCount)];
        for (int idx = 0; idx <= result.length -1 && !deque.isEmpty(); ++idx) {
            result[idx] = s.charAt(deque.pollFirst());
        }
        return new String(result);
    }

    public static void main(String[] args) {
        String s = "erase*****";
        RemoveStarsFromString removeStarsFromString = new RemoveStarsFromString();
        String result = removeStarsFromString.removeStars(s);
        System.out.println(result);
    }
}
