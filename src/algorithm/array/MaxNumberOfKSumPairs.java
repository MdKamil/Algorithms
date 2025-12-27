package algorithm.array;

import java.util.Arrays;

public class MaxNumberOfKSumPairs {
    public int maxOperations(int[] nums, int k) {
        int noOfOperations = 0;
        Arrays.sort(nums);
        int start = 0;
        int end = nums.length - 1;
        while (start < end) {
            int sum = nums[start] + nums[end];
            if (sum == k) {
                ++noOfOperations;
                ++start;
                --end;
            } else if (sum < k) {
                ++start;
            } else {
                --end;
            }
        }
        return noOfOperations;
    }

    public static void main(String[] args) {
        int[] nums = {3, 1, 3, 4, 3};
        int k = 12;
        MaxNumberOfKSumPairs maxNumberOfKSumPairs = new MaxNumberOfKSumPairs();
        int noOfOperations = maxNumberOfKSumPairs.maxOperations(nums, k);
        System.out.println(noOfOperations);
    }
}
