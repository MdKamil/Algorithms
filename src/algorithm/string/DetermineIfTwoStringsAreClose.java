package algorithm.string;

import java.util.*;

public class DetermineIfTwoStringsAreClose {
    public boolean closeStrings(String word1, String word2) {
        if (word1.length() != word2.length()) {
            return false;
        } else {
            int[] word1Frequencies = new int[26];
            int[] word2Frequencies = new int[26];
            for (int idx = 0; idx <= word1.length() -1; ++idx) {
                ++word1Frequencies[(int) word1.charAt(idx) - 97];
            }
            for (int idx = 0; idx <= word2.length() -1; ++idx) {
                ++word2Frequencies[(int) word2.charAt(idx) - 97];
            }
            for (int idx = 0; idx <= 25; ++idx) {
                if ((word1Frequencies[idx] == 0 && word2Frequencies[idx] != 0) || (word1Frequencies[idx] != 0 && word2Frequencies[idx] == 0)) {
                    return false;
                }
            }
            Arrays.sort(word1Frequencies);
            Arrays.sort(word2Frequencies);
            for (int idx = 0; idx <= 25; ++idx) {
                if (word1Frequencies[idx] != word2Frequencies[idx]) {
                    return false;
                }
            }
        }
        return true;
    }


    public static void main(String[] args) {
        String word1 = "cabbba";
        String word2 = "abbccc";
        DetermineIfTwoStringsAreClose determineIfTwoStringsAreClose = new DetermineIfTwoStringsAreClose();
        boolean isClose = determineIfTwoStringsAreClose.closeStrings(word1, word2);
        System.out.println(isClose);
    }
}
