package algorithm.math;

import java.util.Arrays;

public class MaximumSwap {
    public int maximumSwap(int num) {
        int maxNum = num;
        if (num >= 10) {
            char[] unsortedNum = String.valueOf(num).toCharArray();
            char[] sortedNum = String.valueOf(num).toCharArray();
            Arrays.sort(sortedNum);
            for (int idx = sortedNum.length - 1, idx2 = 0; idx >= 0; --idx, ++idx2) {
                if (sortedNum[idx] != unsortedNum[idx2]) {
                    maxNum = getSwappedInt(sortedNum[idx], idx2, unsortedNum);
                    break;
                }
            }
        }
        return maxNum;
    }

    private int getSwappedInt(char bigNum, int smallNumIdx, char[] numStringAsArray) {
        int bigNumIdx = 0;
        for (int idx = smallNumIdx; idx <= numStringAsArray.length - 1; ++idx) {
            if (numStringAsArray[idx] == bigNum) {
                bigNumIdx = idx;
            }
        }
        char temp = numStringAsArray[smallNumIdx];
        numStringAsArray[smallNumIdx] = numStringAsArray[bigNumIdx];
        numStringAsArray[bigNumIdx] = temp;
        return Integer.parseInt(String.valueOf(numStringAsArray));
    }

    public static void main(String[] args) {
        int num = 1993;
        MaximumSwap swap = new MaximumSwap();
        int swappedNum = swap.maximumSwap(num);
        System.out.println(swappedNum);
    }
}
