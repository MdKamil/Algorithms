package algorithm.array;

import java.util.*;

public class NonOverlappingIntervals {
    public int eraseOverlapIntervals(int[][] intervals) {
        HashMap<int[], Integer> hMap = new HashMap<>();
        for (int idx = 0; idx <= intervals.length - 1; ++idx) {
            hMap.put(intervals[idx], 0);
            for (int idx1 = 0; idx1 <= intervals.length - 1; ++idx1) {
                if (idx != idx1 && !isOverlap(intervals[idx], intervals[idx1])) {
                    hMap.put(intervals[idx], hMap.get(intervals[idx]) + 1);
                }
            }
        }
        Comparator<Integer> c = (o1, o2) -> -o1.compareTo(o2);
        TreeMap<Integer, List<int[]>> tMap = new TreeMap<>(c);
        for (Map.Entry<int[], Integer> entry : hMap.entrySet()) {
            if (tMap.containsKey(entry.getValue())) {
                tMap.get(entry.getValue()).add(entry.getKey());
            } else {
                List<int[]> list = new ArrayList<>();
                list.add(entry.getKey());
                tMap.put(entry.getValue(), list);
            }
        }
        Comparator<int[]> tsc = Comparator.comparingInt(o -> o[0]);
        Comparator<int[]> c1 = (o1, o2) -> {
            int val = Integer.compare(o1[0], o2[0]);
            int o1length = o1[1] - o1[0];
            int o2length = o2[1] - o2[0];
            return Integer.compare(o1length, o2length);
        };
        TreeSet<int[]> set = new TreeSet<>(tsc);
        int minIntervalsToRemove = 0;
        for (Map.Entry<Integer, List<int[]>> entry : tMap.entrySet()) {
            entry.getValue().sort(c1);
            for (int[] interval : entry.getValue()) {
                int[] lowInterval = set.lower(interval);
                int[] highInterval = set.ceiling(interval);
                if ((Objects.nonNull(lowInterval) && isOverlap(lowInterval, interval)) || (Objects.nonNull(highInterval) && isOverlap(highInterval, interval))) {
                    ++minIntervalsToRemove;
                } else {
                    set.add(interval);
                }
            }
        }
        return minIntervalsToRemove;
    }

    private boolean isOverlap(int[] interval1, int[] interval2) {
        return interval1[0] < interval2[1] && interval1[1] > interval2[0];
    }

    public static void main(String[] args) {
        int[][] intervals = {{-73, -26},{-65, -11},{-63, 2},{-62, -49},{-52, 31},{-40, -26},{-31, 49},{30, 47},{58, 95},{66, 98},{82, 97},{95, 99}};
        int minIntervalsToRemove = new NonOverlappingIntervals().eraseOverlapIntervals(intervals);
        System.out.println(minIntervalsToRemove);
    }
}
