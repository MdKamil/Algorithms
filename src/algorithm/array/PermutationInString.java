package algorithm.array;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Objects;

public class PermutationInString {
    public boolean checkInclusion(String s1, String s2) {
        boolean isPresent = false;
        Deque<Character> deque = new ArrayDeque<>();
        HashMap<Character, Integer> s1FrequencyMap = new HashMap<>();
        for (Character c : s1.toCharArray()) {
            s1FrequencyMap.put(c, s1FrequencyMap.getOrDefault(c, 0) + 1);
        }
        HashMap<Character, Integer> subStringFrequencyMap = new HashMap<>();
        for (Character c : s2.toCharArray()) {
            if (s1FrequencyMap.containsKey(c)) {
                if (subStringFrequencyMap.containsKey(c) && subStringFrequencyMap.get(c) + 1 > s1FrequencyMap.get(c)) {
                    while (!deque.isEmpty()) {
                        char currChar = deque.pollFirst();
                        subStringFrequencyMap.put(currChar, subStringFrequencyMap.get(currChar) - 1);
                        if (subStringFrequencyMap.get(currChar) == 0) {
                            subStringFrequencyMap.remove(currChar);
                        }
                        if (Objects.equals(currChar, c)) {
                            break;
                        }
                    }
                }
                deque.addLast(c);
                subStringFrequencyMap.put(c, subStringFrequencyMap.getOrDefault(c, 0) + 1);
                if (Objects.equals(deque.size(), s1.length())) {
                    isPresent = true;
                    break;
                }
            } else {
                subStringFrequencyMap.clear();
                deque.clear();
            }
        }
        return isPresent;
    }

    public static void main(String[] args) {
        String s1 = "abc";
        String s2 = "bbbbbaaaaacccccbbaaccbbbaacccdabc";
        boolean isPresent = new PermutationInString().checkInclusion(s1, s2);
        System.out.println(isPresent);
    }
}
