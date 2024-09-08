package algorithm.array;

// https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/
public class RemoveDuplicatesFromSortedArrayII {
    public int removeDuplicates(int[] nums) {
        int currNumCount = 1;
        int currNum =  nums[0];
        int positionToInsert = 1;
        for (int idx = 1; idx <= nums.length - 1; ++idx) {
            if (nums[idx] == currNum) {
                if (currNumCount < 2) {
                    ++currNumCount;
                    if (positionToInsert < idx)  {
                        nums[positionToInsert] = nums[idx];
                    }
                    ++positionToInsert;
                }
            } else {
                currNumCount = 1;
                currNum = nums[idx];
                if (positionToInsert < idx)  {
                    nums[positionToInsert] = nums[idx];
                }
                ++positionToInsert;
            }
        }
        return positionToInsert;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,2,3,3,3,4};
        int length = new RemoveDuplicatesFromSortedArrayII().removeDuplicates(nums);
        System.out.println(length);
    }
}
