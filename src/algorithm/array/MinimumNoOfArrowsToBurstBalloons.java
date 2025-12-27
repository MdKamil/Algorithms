package algorithm.array;

import java.util.Arrays;
import java.util.Comparator;

public class MinimumNoOfArrowsToBurstBalloons {
    public int findMinArrowShots(int[][] points) {
        Arrays.sort(points, Comparator.comparing(o -> o[0]));
        int minArrows = 1;
        int[] currDiameter = points[0];
        for (int idx = 1; idx <= points.length - 1; ++idx) {
            if (isOverlap(currDiameter, points[idx])) {
                currDiameter[1] = Math.min(currDiameter[1], points[idx][1]);
            } else {
                ++minArrows;
                currDiameter = points[idx];
            }
        }
        return minArrows;
    }

    private boolean isOverlap(int[] balloon1Diameter, int[] balloon2Diameter) {
        return balloon1Diameter[0] <= balloon2Diameter[1] && balloon1Diameter[1] >= balloon2Diameter[0];
    }

    public static void main(String[] args) {
        int[][] points = {{10, 16}, {2, 8}, {1,6}, {7,12}};
        MinimumNoOfArrowsToBurstBalloons noOfArrowsToBurstBalloons = new MinimumNoOfArrowsToBurstBalloons();
        int minArrows = noOfArrowsToBurstBalloons.findMinArrowShots(points);
        System.out.println(minArrows);
    }
}
