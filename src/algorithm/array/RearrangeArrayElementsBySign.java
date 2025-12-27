package algorithm.array;

import java.util.Arrays;

public class RearrangeArrayElementsBySign {
    public int[] rearrangeArray(int[] nums) {
        int[] rearrangedArray = new int[nums.length];
        int idxToInsertPositiveNo = 0;
        int idxToInsertNegativeNo = 1;
        for (int num : nums) {
            if (num < 0) {
                rearrangedArray[idxToInsertNegativeNo] = num;
                idxToInsertNegativeNo += 2;
            } else {
                rearrangedArray[idxToInsertPositiveNo] = num;
                idxToInsertPositiveNo += 2;
            }
        }
        return rearrangedArray;
    }

    public static void main(String[] args) {
        int[] nums = {-3, -4, -4, -6, 1,2,3,4};
        RearrangeArrayElementsBySign rearrangeArrayElementsBySign = new RearrangeArrayElementsBySign();
        int[] rearrangedArray = rearrangeArrayElementsBySign.rearrangeArray(nums);
        System.out.println(Arrays.toString(rearrangedArray));
    }
}
