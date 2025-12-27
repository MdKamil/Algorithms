package algorithm.array;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Objects;
import java.util.Queue;

public class SubarrayProductLessThanK {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int multipliedValue = 1;
        Deque<Integer> deque = new ArrayDeque<>();
        Queue<int[]> subArrayIndexes = new ArrayDeque<>();
        for (int idx = 0; idx <= nums.length - 1; ++idx) {
            multipliedValue *= nums[idx];
            if (multipliedValue >= k) {
                if (!deque.isEmpty()) {
                    subArrayIndexes.add(new int[]{deque.peekFirst(), deque.peekLast()});
                    while (multipliedValue >= k && !deque.isEmpty()) {
                        multipliedValue /= nums[deque.poll()];
                    }
                }
                if (nums[idx] < k) {
                    deque.add(idx);
                } else {
                    multipliedValue /= nums[idx];
                }
            } else {
                deque.add(idx);
            }
        }
        if (!deque.isEmpty()) {
            subArrayIndexes.add(new int[]{deque.peekFirst(), deque.peekLast()});
        }
        int count = 0;
        if (!subArrayIndexes.isEmpty()) {
            int[] prevSubArray = subArrayIndexes.poll();
            int length = (prevSubArray[1] - prevSubArray[0]) + 1;
            count += (length * (length + 1)) / 2;
            while (!subArrayIndexes.isEmpty()) {
                int[] currSubArray = subArrayIndexes.poll();
                length = (currSubArray[1] - currSubArray[0]) + 1;
                count += (length * (length + 1)) / 2;
                if (prevSubArray[1] >= currSubArray[0]) {
                    int overlapLength = (prevSubArray[1] - currSubArray[0]) + 1;
                    count = count - ((overlapLength * (overlapLength + 1)) / 2);
                }
                prevSubArray = currSubArray;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] nums = {10,9,10,4,3,8,3,3,6,2,10,10,9,3};
        int k = 19;
        SubarrayProductLessThanK productLessThanK = new SubarrayProductLessThanK();
        int count = productLessThanK.numSubarrayProductLessThanK(nums, k);
        System.out.println(count);
    }
}
