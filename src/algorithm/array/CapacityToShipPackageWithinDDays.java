package algorithm.array;

import java.util.TreeSet;

public class CapacityToShipPackageWithinDDays {
    public int shipWithinDays(int[] weights, int days) {
        TreeSet<Integer> prefixSumSet = new TreeSet<>();
        int prefixSum = 0;
        for (int weight : weights) {
            prefixSum += weight;
            prefixSumSet.add(prefixSum);
        }
        int minimumCapacity = prefixSum;
        int lowestCapacity = weights[0];
        int highestCapacity = prefixSum;
        while (lowestCapacity <= highestCapacity) {
            int currentCapacity = (lowestCapacity + highestCapacity) / 2;
            Integer currWeight = 0;
            int day = 0;
            while (day <= days && currWeight < prefixSum) {
                currWeight += currentCapacity;
                currWeight = prefixSumSet.floor(currWeight);
                ++day;
            }
            if (day <= days) {
                highestCapacity = currentCapacity - 1;
                minimumCapacity = Math.min(minimumCapacity, currentCapacity);
            } else {
                lowestCapacity = currentCapacity + 1;
            }
        }
        return minimumCapacity;
    }

    public static void main(String[] args) {
        int[] weights = {1,2,3,4,5,6,7,8,9,10};
        int days = 5;
        CapacityToShipPackageWithinDDays capacityToShipPackageWithinDDays = new CapacityToShipPackageWithinDDays();
        int minimumCapacity = capacityToShipPackageWithinDDays.shipWithinDays(weights, days);
        System.out.println(minimumCapacity);
    }
}

