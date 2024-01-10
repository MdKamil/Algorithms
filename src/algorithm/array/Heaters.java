package algorithm.array;

import java.util.Arrays;

public class Heaters {

    public int findRadius(int[] houses, int[] heaters) {
        int minRadius = Integer.MIN_VALUE;
        Arrays.sort(houses);
        Arrays.sort(heaters);
        int heaterIdx = 0;
        for (int houseIdx = 0; houseIdx <= houses.length - 1; ++houseIdx) {
            while (heaterIdx + 1 <= heaters.length - 1 && Math.abs(houses[houseIdx] - heaters[heaterIdx + 1]) <= Math.abs(houses[houseIdx] - heaters[heaterIdx])) {
                ++heaterIdx;
            }
            int wr = Math.abs(heaters[heaterIdx] - houses[houseIdx]);
            minRadius = Math.max(minRadius, wr);
        }
        return minRadius;
    }

    public static void main(String[] args) {
        int[] houses = {1, 2, 3, 4, 5, 6, 7, 3};
        int[] heaters = {1, 2, 3, 4, 5, 6, 7, 3};
        int minRadius = new Heaters().findRadius(houses, heaters);
        System.out.println(minRadius);
    }
}
