package algorithm.array;

import java.util.HashMap;
import java.util.Map;

public class XToZero {
    public int minOps(int[] nums, int x) {
        int[] ps = new int[nums.length];
        int[] ss = new int[nums.length];
        int minOps = Integer.MAX_VALUE;
        int psMaxIdx = -1;
        int ssMaxIdx = -1;
        for (int idx = 0; idx <= nums.length - 1; ++idx) {
            ps[idx] = (idx == 0 ? nums[idx] : ps[idx - 1] + nums[idx]);
            if (ps[idx] == x) {
                minOps = Math.min(minOps, idx + 1);
                psMaxIdx = idx - 1;
            } else if (ps[idx] > x) {
                psMaxIdx = psMaxIdx == -1 ? idx - 1 : psMaxIdx;
            }
        }
        for (int idx = nums.length - 1, ssIdx = 0; idx >= 0; --idx, ++ssIdx) {
            ss[ssIdx] = (idx == nums.length - 1 ? nums[idx] : ss[ssIdx - 1] + nums[idx]);
            if (ss[ssIdx] == x) {
                minOps = Math.min(minOps, ssIdx + 1);
                ssMaxIdx = ssIdx - 1;
            } else if (ss[ssIdx] > x) {
                ssMaxIdx = ssMaxIdx == -1 ? ssIdx - 1 : ssMaxIdx;
            }
        }
        if (psMaxIdx != -1 && ssMaxIdx != -1) {
            for (int idx = 0; idx <= psMaxIdx; ++idx) {
                int length = findTarget(ss, ssMaxIdx, x - ps[idx]);
                if (length != -1) {
                    minOps = Math.min(minOps, (idx + length) + 2);
                }
            }
        }
        return minOps == Integer.MAX_VALUE ? -1 : minOps;
    }

    private static int findTarget(int[] ss, int ssMaxIdx, int target) {
        int maxLength = -1;
        int lo = 0;
        int hi = ssMaxIdx;
        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            if (ss[mid] == target) {
                maxLength = mid;
                break;
            } else if (ss[mid] > target) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return maxLength;
    }

    public int minOps2(int[] nums, int x) {
        int[] ps = new int[nums.length];
        Map<Integer, Integer> map = new HashMap<>();
        int minOps = Integer.MAX_VALUE;
        int val = 0;
        for (int pIdx = 0, sIdx = nums.length - 1; pIdx <= nums.length - 1 && sIdx >= 0; ++pIdx, --sIdx) {
            ps[pIdx] = (pIdx == 0 ? nums[pIdx] : ps[pIdx - 1] + nums[pIdx]);
            val = (sIdx == nums.length - 1 ? nums[sIdx] : val + nums[sIdx]);
            map.put(val, pIdx);
            if (ps[pIdx] == x || val == x) {
                minOps = Math.min(minOps, pIdx + 1);
            }
        }
        for (int idx = 0; idx <= ps.length - 2 && ps[idx] < x; ++idx) {
            int target = x - ps[idx];
            if (map.containsKey(target) && map.get(target) != nums.length - 1) {
                minOps = Math.min(minOps, (idx + map.get(target)) + 2);
            }
        }
        return minOps == Integer.MAX_VALUE ? -1 : minOps;
    }

    public static void main(String[] args) {
        int[] nums = {1, 1};
        int x = 3;
        int minOps = new XToZero().minOps2(nums, x);
        System.out.println(minOps);
    }
}
