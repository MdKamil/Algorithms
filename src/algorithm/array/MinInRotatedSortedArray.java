package algorithm.array;
public class MinInRotatedSortedArray {
    public int findMin(int[] nums) {
        int low = 0;
        int high = nums.length - 1;
        while (high - low > 1) {
            int mid = (low + high) / 2;
            if (nums[mid] < nums[low]) {
                high = mid;
            } else if (nums[mid] > nums[high]) {
                low = mid;
            } else {
                break;
            }
        }
        return Math.min(nums[low], nums[high]);
    }

    public static void main(String[] args) {
        int[] nums = {7};
        int low = new MinInRotatedSortedArray().findMin(nums);
        System.out.println(low);
    }
}