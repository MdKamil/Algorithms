package algorithm.string;

import java.util.*;

public class ReorganizeString {
    private static class Pair<T1, T2> {
        T1 first;
        T2 second;

        public Pair(T1 first, T2 second) {
            this.first = first;
            this.second = second;
        }
    }

    public String reorganizeString(String s) {
        int maxCharCount = 0;
        HashMap<Character, Integer> frequencyMap = new HashMap<>();
        for (int idx = 0; idx <= s.length() - 1; ++idx) {
            int val = frequencyMap.getOrDefault(s.charAt(idx), 0) + 1;
            maxCharCount = Math.max(maxCharCount, val);
            frequencyMap.put(s.charAt(idx), val);
        }
        if (maxCharCount - (s.length() - maxCharCount) >= 2) {
            return "";
        } else {
            PriorityQueue<Pair<Character, Integer>> pq = new PriorityQueue<>((p1, p2) -> -p1.second.compareTo(p2.second));
            for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
                pq.add(new Pair<>(entry.getKey(), entry.getValue()));
            }
            char[] reorganisedString = new char[s.length()];
            int currIdx = 0;
            Deque<Integer> prevUnusedIndexes = new ArrayDeque<>();
            while (!pq.isEmpty()) {
                Pair<Character, Integer> pair = pq.poll();
                if (currIdx > 0 && reorganisedString[currIdx - 1] == pair.first) {
                    prevUnusedIndexes.addLast(currIdx);
                    ++currIdx;
                    reorganisedString[currIdx] = pair.first;
                    ++currIdx;
                } else if (!prevUnusedIndexes.isEmpty()) {
                    reorganisedString[prevUnusedIndexes.pollFirst()] = pair.first;
                } else {
                    reorganisedString[currIdx] = pair.first;
                    ++currIdx;
                }
                --pair.second;
                if (pair.second > 0) {
                    pq.add(pair);
                }
            }
            return String.valueOf(reorganisedString);
        }
    }

    public static void main(String[] args) {
        String s = "aaaaaaaabbbccd";
        ReorganizeString reorganizeString = new ReorganizeString();
        String reorganisedString = reorganizeString.reorganizeString(s);
        System.out.println(reorganisedString);
    }

}
