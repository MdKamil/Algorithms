package algorithm.array;

import java.util.Objects;

public class ContinousSubArraySum {
    public boolean checkSubarraySum(int[] nums, int k) {
        boolean sumExists = false;
        if (nums.length > 1) {
            int[] suffixSum = new int[nums.length];
            outerLoop:
            for (int outerIdx = nums.length - 2; outerIdx >= 0; --outerIdx) {
                suffixSum[outerIdx + 1] = nums[outerIdx + 1];
                for (int innerIdx = outerIdx + 1; innerIdx <= nums.length - 1; ++innerIdx) {
                    suffixSum[innerIdx] = nums[outerIdx] + suffixSum[innerIdx];
                    if (Objects.equals(suffixSum[innerIdx], 0) || Objects.equals(suffixSum[innerIdx] % k, 0)) {
                        sumExists = true;
                        break outerLoop;
                    }
                }
            }
        }
        return sumExists;
    }

    public static void main(String[] args) {
        int[] nums = {23, 2, 6, 4, 7};
        int k = 13;
        boolean sumExists = new ContinousSubArraySum().checkSubarraySum(nums, k);
        System.out.println(sumExists);
    }
}
