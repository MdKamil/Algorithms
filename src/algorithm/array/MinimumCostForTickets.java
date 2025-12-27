package algorithm.array;

import java.util.*;

public class MinimumCostForTickets {
    public int mincostTickets(int[] days, int[] costs) {
        HashSet<Integer> travelDays = new HashSet<>();
        for (int day : days) {
            travelDays.add(day);
        }
        int lastDay = days[days.length - 1];
        int firstDay = days[0];
        int[] result = new int[lastDay + 1];
        result[lastDay] = Math.min(costs[0], Math.min(costs[1], costs[2]));
        for (int day = lastDay - 1; day >= firstDay; --day) {
            if (travelDays.contains(day)) {
                result[day] = costs[0] + result[day + 1];

                int futureDay = day + 7;
                if (futureDay > lastDay) {
                    result[day] = Math.min(result[day], costs[1]);
                } else {
                    result[day] = Math.min(result[day], costs[1] + result[futureDay]);
                }

                futureDay = day + 30;
                if (futureDay > lastDay) {
                    result[day] = Math.min(result[day], costs[2]);
                } else {
                    result[day] = Math.min(result[day], costs[2] + result[futureDay]);
                }
            } else {
                result[day] = result[day + 1];
            }
        }
        return result[firstDay];
    }

    public static void main(String[] args) {
        int[] days = {10, 19, 23, 27, 28, 29, 30, 31, 32, 33, 38, 46, 49, 57, 65};
        int[] costs = {2, 7, 12};
        MinimumCostForTickets minimumCostForTickets = new MinimumCostForTickets();
        int minCost = minimumCostForTickets.mincostTickets(days, costs);
        System.out.println(minCost);
    }
}
