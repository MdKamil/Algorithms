package algorithm.string;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

public class CustomSortString {
    public String customSortString(String order, String s) {
        HashMap<Character, Integer> charRank = new HashMap<>();
        Character[] charArray = new Character[s.length()];
        for (int idx = 0; idx <= order.length() - 1; ++idx) {
            charRank.put(order.charAt(idx), idx);
        }
        for (int idx = 0; idx <= s.length() - 1; ++idx) {
            if (!charRank.containsKey(s.charAt(idx))) {
                charRank.put(s.charAt(idx), order.length());
            }
            charArray[idx] = s.charAt(idx);
        }
        Arrays.sort(charArray, Comparator.comparing(charRank::get));
        char[] sortedString = new char[s.length()];
        for (int idx = 0; idx <= charArray.length -1; ++idx) {
            sortedString[idx] = charArray[idx];
        }
        return String.valueOf(sortedString);
    }

    public static void main(String[] args) {
        String order = "bcafg";
        String s = "abcd";
        CustomSortString sortString = new CustomSortString();
        String sortedString = sortString.customSortString(order, s);
        System.out.println(sortedString);
    }
}
