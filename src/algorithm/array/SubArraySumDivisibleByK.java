package algorithm.array;

import java.util.HashSet;
import java.util.Set;

public class SubArraySumDivisibleByK {
    public int subarraysDivByK(int[] nums, int k) {
        int subArrayCount = 0;
        Set<Integer> prefixSums = new HashSet<>();
        int prefixSum = 0;
        int minSum = Integer.MAX_VALUE;
        int maxSum = Integer.MIN_VALUE;
        for (int idx = 0; idx <= nums.length - 1; ++idx) {
            prefixSum += nums[idx];
        }
        return 20;
    }
}
