package algorithm.array;

import java.util.ArrayDeque;
import java.util.Queue;

// https://leetcode.com/problems/longest-repeating-character-replacement
public class LongestRepeatingCharacterReplacement {
    public int characterReplacement(String s, int k) {
        int maxLength = 1;
        Queue<Integer> queue = new ArrayDeque<>();
        for (int charVal = 65; charVal <= 90; ++charVal) {
            char val = (char) charVal;
            int currentStartIdx = 1;
            for (int idx = 0; idx <= s.length()-1; ++idx) {
                if (val != s.charAt(idx)) {
                    queue.add(idx + 1);
                    if (queue.size() > k) {
                        int nonValIdx = queue.poll();
                        maxLength = Math.max(maxLength, ((idx+1) - nonValIdx));
                        currentStartIdx = nonValIdx + 1;
                    }
                }
                maxLength = Math.max(maxLength, ((idx+1) - currentStartIdx) + 1);
            }
            queue.clear();
        }
        return maxLength;
    }

    public static void main(String[] args) {
        String s = "AABABBA";
        int k = 1;
        int maxLength = new LongestRepeatingCharacterReplacement().characterReplacement(s, k);
        System.out.println(maxLength);
    }
}
