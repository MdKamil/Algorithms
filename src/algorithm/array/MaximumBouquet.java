package algorithm.array;

public class MaximumBouquet {
    public int minDays(int[] bloomDay, int m, int k) {
        if ((long) m * k > bloomDay.length) {
            return -1;
        }
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int day : bloomDay) {
            min = Math.min(min, day);
            max = Math.max(max, day);
        }
        int minDays = Integer.MAX_VALUE;
        while (min <= max) {
            int mid = (min + max) / 2;
            int bouquets = 0;
            int count = 0;
            for (int idx = 0; idx <= bloomDay.length - 1; ++idx) {
                if (mid >= bloomDay[idx]) {
                    ++count;
                    if (count == k) {
                        ++bouquets;
                        count = 0;
                    }
                } else {
                    count = 0;
                }
            }
            if (bouquets >= m) {
                minDays = Math.min(minDays, mid);
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return minDays;
    }

    public static void main(String[] args) {
        int[] bloomDay = {7, 7, 150, 7, 7, 900, 7, 7, 700, 100, 7, 7, 7, 800, 1, 1, 1, 1};
        int m = 2;
        int k = 2;
        int minDays = new MaximumBouquet().minDays(bloomDay, m, k);
        System.out.println(minDays);
    }
}
