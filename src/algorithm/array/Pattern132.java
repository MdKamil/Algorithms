package algorithm.array;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Pattern132 {
    public boolean find132pattern(int[] nums) {
        boolean present = false;
        int[] smallest = new int[nums.length];
        int[] greatest = new int[nums.length];
        Arrays.fill(greatest, -1);
        Deque<Integer> stack = new ArrayDeque<>();
        int small = 0;
        for (int idx = 0; idx <= nums.length - 1; ++idx) {
            if (nums[idx] < nums[small]) {
                small = idx;
            }
            smallest[idx] = small;
        }
        for (int idx = nums.length - 1; idx >= 1; --idx) {
            if (nums[idx - 1] > nums[idx]) {
                greatest[idx] = idx - 1;
                while (!stack.isEmpty()) {
                    if (nums[idx - 1] > nums[stack.peek()]) {
                        greatest[stack.poll()] = idx - 1;
                    } else {
                        break;
                    }
                }
            } else {
                stack.push(idx);
            }
        }
        for (int idx = nums.length - 1; idx >= 2; --idx) {
            if (greatest[idx] != -1 && greatest[idx] - 1 >= 0 && nums[idx] > nums[smallest[greatest[idx] - 1]]) {
                present = true;
                break;
            }
        }
        return present;
    }

    public static void main(String[] args) {
        int[] nums = {3,1,2};
        boolean isPresent = new Pattern132().find132pattern(nums);
        System.out.println(isPresent);
    }
}
