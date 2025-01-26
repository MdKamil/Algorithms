package algorithm.array;

import java.util.*;

public class PartitionLabels {
    public List<Integer> partitionLabels(String s) {
        Map<Character, int[]> occurrences = new HashMap<>();
        for (int idx = 0; idx <= s.length() - 1; ++idx) {
            if (occurrences.containsKey(s.charAt(idx))) {
                occurrences.get(s.charAt(idx))[1] = idx + 1;
            } else {
                occurrences.put(s.charAt(idx), new int[] {idx+1, idx+1});
            }
        }
        List<int[]> intervalsList = new ArrayList<>(occurrences.values());
        intervalsList.sort(Comparator.comparing(o -> o[0]));
        List<Integer> intervalSize = new ArrayList<>();
        int[] currentInterval = intervalsList.get(0);
        for (int idx = 1; idx <= intervalsList.size() - 1; ++idx) {
            int[] interval = intervalsList.get(idx);
            if (isOverlap(currentInterval, interval)) {
                currentInterval[1] = Math.max(currentInterval[1], interval[1]);
            } else {
                intervalSize.add((currentInterval[1] - currentInterval[0]) + 1);
                currentInterval = interval;
            }
        }
        intervalSize.add((currentInterval[1] - currentInterval[0]) + 1);
        return intervalSize;
    }

    private boolean isOverlap(int[] interval1, int[] interval2) {
        return interval1[0] < interval2[1] && interval1[1] > interval2[0];
    }

    public static void main(String[] args) {
        String s = "ab";
        List<Integer> intervalSize = new PartitionLabels().partitionLabels(s);
        System.out.println(intervalSize);
    }
}
