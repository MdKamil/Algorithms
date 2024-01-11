package algorithm.array;

// https://leetcode.com/problems/longest-subarray-of-1s-after-deleting-one-element/
public class LongestSubArrayOfOnesAfterDelete {
    public int longestSubarray(int[] nums) {
        int maxCount = 0;
        int currentCount = 0;
        int countAfterLastZero = 0;
        int lastZeroIdx = -1;
        for (int idx = 0; idx <= nums.length-1; ++idx) {
            if (nums[idx] == 1) {
                ++ currentCount;
                if (lastZeroIdx != -1) {
                    ++countAfterLastZero;
                }
                maxCount = Math.max(maxCount, currentCount);
            } else if (nums[idx] == 0) {
                if (lastZeroIdx == -1 ) {
                    lastZeroIdx = idx;
                } else {
                    maxCount = Math.max(maxCount, currentCount);
                    currentCount = countAfterLastZero;
                    lastZeroIdx = idx;
                    countAfterLastZero = 0;
                }
            }
        }
        maxCount = lastZeroIdx == -1 ? maxCount -1 : maxCount;
        return maxCount;
    }

    public static void main(String[] args) {
        int[] nums = {0,1,1,0,1,1,1,0,1,1,1,1,0,1,0,1,0};
        LongestSubArrayOfOnesAfterDelete afterDelete = new LongestSubArrayOfOnesAfterDelete();
        int maxCount = afterDelete.longestSubarray(nums);
        System.out.println(maxCount);
    }
}
