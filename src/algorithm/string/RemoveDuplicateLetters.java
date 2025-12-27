package algorithm.string;

import java.util.*;

public class RemoveDuplicateLetters {
    public String removeDuplicateLetters(String s) {
        int[] nextIdx = new int[s.length()];
        Arrays.fill(nextIdx, -1);
        HashMap<Character, Integer> map = new HashMap<>();
        for (int idx = s.length() - 1; idx >= 0; --idx) {
            if (!map.containsKey(s.charAt(idx))) {
                map.put(s.charAt(idx), idx);
            } else {
                nextIdx[idx] = map.get(s.charAt(idx));
                map.put(s.charAt(idx), idx);
            }
        }
        HashSet<Character> seen = new HashSet<>();
        HashMap<Character, Integer> currNextIdx = new HashMap<>();
        Deque<Character> charDeque = new ArrayDeque<>();
        for (int idx = 0; idx <= s.length() - 1; ++idx) {
            currNextIdx.put(s.charAt(idx), nextIdx[idx]);
            while (!charDeque.isEmpty()) {
                if (seen.contains(s.charAt(idx))) {
                    break;
                } else if (s.charAt(idx) < charDeque.peekLast() && currNextIdx.get(charDeque.peekLast()) != -1 && currNextIdx.get(charDeque.peekLast()) > idx) {
                    seen.remove(charDeque.pollLast());
                } else {
                    break;
                }
            }
            if (!seen.contains(s.charAt(idx))) {
                charDeque.addLast(s.charAt(idx));
                seen.add(s.charAt(idx));
            }
        }
        StringBuilder distinctCharacterString = new StringBuilder(charDeque.size());
        while (!charDeque.isEmpty()) {
            distinctCharacterString.append(charDeque.pollFirst());
        }
        return distinctCharacterString.toString();
    }

    public static void main(String[] args) {
        String s = "ccacbaba";
        RemoveDuplicateLetters duplicateLetters = new RemoveDuplicateLetters();
        String smallestString = duplicateLetters.removeDuplicateLetters(s);
        System.out.println(smallestString);
    }
}
