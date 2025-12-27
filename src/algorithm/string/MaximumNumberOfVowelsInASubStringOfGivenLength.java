package algorithm.string;

import java.util.HashMap;
import java.util.Set;

public class MaximumNumberOfVowelsInASubStringOfGivenLength {
    public int maxVowels(String s, int k) {
        HashMap<Character, Integer> frequencyMap = new HashMap<>();
        frequencyMap.put('a', 0);
        frequencyMap.put('e', 0);
        frequencyMap.put('i', 0);
        frequencyMap.put('o', 0);
        frequencyMap.put('u', 0);
        Set<Character> vowels = Set.of('a', 'e', 'i', 'o', 'u');
        int maxVowels = 0;
        int currCount = 0;
        for (int idx = 0; idx <= s.length() - 1; ++idx) {
            if (vowels.contains(s.charAt(idx))) {
                frequencyMap.put(s.charAt(idx), frequencyMap.get(s.charAt(idx)) + 1);
                ++currCount;
            }
            if (idx >= k) {
                if (vowels.contains(s.charAt(idx - k))) {
                    frequencyMap.put(s.charAt(idx - k), frequencyMap.get(s.charAt(idx - k)) - 1);
                    --currCount;
                }
            }
            maxVowels = Math.max(maxVowels, currCount);
        }
        return maxVowels;
    }

    public static void main(String[] args) {
        String s = "leetcode";
        int k = 3;
        MaximumNumberOfVowelsInASubStringOfGivenLength vowelsInASubStringOfGivenLength = new MaximumNumberOfVowelsInASubStringOfGivenLength();
        int maxVowels = vowelsInASubStringOfGivenLength.maxVowels(s, k);
        System.out.println(maxVowels);
    }
}
