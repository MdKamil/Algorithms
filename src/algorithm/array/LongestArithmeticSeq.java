package algorithm.array;

import java.util.HashMap;

public class LongestArithmeticSeq {
    public int longestArithSeqLength(int[] nums) {
        int maxLength = 2;
        HashMap<Integer, HashMap<Integer, Integer>> map = new HashMap<>();
        for (int idx = nums.length - 1; idx >= 0; --idx) {
            map.put(idx, new HashMap<>());
            for (int idx1 = idx + 1; idx1 <= nums.length - 1; ++idx1) {
                int diff = nums[idx] - nums[idx1];
                if (map.get(idx1).containsKey(diff)) {
                    int newDiff = map.get(idx1).get(diff) + 1;
                    if (!map.get(idx).containsKey(diff) || newDiff > map.get(idx).get(diff)) {
                        map.get(idx).put(diff, newDiff);
                    }
                    maxLength = Math.max(maxLength, newDiff);
                } else {
                    map.get(idx).putIfAbsent(diff, 2);
                }
            }
        }
        return maxLength;
    }

    public static void main(String[] args) {
        int[] nums = {20, 1, 15, 3, 10, 5, 8};
        int maxLength = new LongestArithmeticSeq().longestArithSeqLength(nums);
        System.out.println(maxLength);
    }
}
