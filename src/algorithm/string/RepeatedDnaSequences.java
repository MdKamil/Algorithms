package algorithm.string;

import java.util.*;

public class RepeatedDnaSequences {
    public List<String> findRepeatedDnaSequences(String s) {
        Set<String> result = new HashSet<>();
        if (s.length() > 9) {
            Set<String> dnaSequences = new HashSet<>();
            StringBuilder builder = new StringBuilder(10);
            builder.append(s, 0, 10);
            dnaSequences.add(builder.toString());
            for (int idx = 10; idx <= s.length() -1 ; ++idx) {
                builder.deleteCharAt(0);
                builder.append(s.charAt(idx));
                String subString = builder.toString();
                if (dnaSequences.contains(subString)) {
                    result.add(subString);
                } else {
                    dnaSequences.add(subString);
                }
            }
        }
        return List.copyOf(result);
    }

    public static void main(String[] args) {
        String s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT";
        RepeatedDnaSequences repeatedDnaSequences = new RepeatedDnaSequences();
        List<String> list = repeatedDnaSequences.findRepeatedDnaSequences(s);
        System.out.println(list);
    }
}
