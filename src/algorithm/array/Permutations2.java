package algorithm.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Permutations2 {

    // if input contain no duplicates
    public List<List<Integer>> permuteNonUnique(int[] nums) {
        List<List<Integer>> permutationList = new ArrayList<>();
        permuteNonUniqueInner(permutationList, 0, nums, new ArrayList<>());
        return permutationList;
    }

    public void permuteNonUniqueInner(List<List<Integer>> permutationList, int idx, int[] nums, List<Integer> list) {
        if (idx == nums.length) {
            permutationList.add(new ArrayList<>(list));
            return;
        }
        for (int innerIdx = idx; innerIdx <= nums.length - 1; ++innerIdx) {
            swap(nums, idx, innerIdx);
            list.add(nums[idx]);
            permuteNonUniqueInner(permutationList, idx + 1, nums, list);
            list.remove(list.size() - 1);
            swap(nums, idx, innerIdx);
        }
    }

    // if input contains duplicates
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> permutationList = new ArrayList<>();
        permuteUniqueInner(permutationList, 0, nums, new ArrayList<>());
        return permutationList;
    }

    public void permuteUniqueInner(List<List<Integer>> permutationList, int idx, int[] nums, List<Integer> list) {
        if (idx == nums.length) {
            permutationList.add(new ArrayList<>(list));
            return;
        }
        for (int innerIdx = idx; innerIdx <= nums.length - 1; ++innerIdx) {
            if (idx != innerIdx) {
                boolean present = false;
                for (int from = innerIdx - 1; from >= idx; --from) {
                    if (nums[innerIdx] == nums[from]) {
                        present = true;
                        break;
                    }
                }
                if (present) {
                    continue;
                }
            }
            swap(nums, idx, innerIdx);
            list.add(nums[idx]);
            permuteUniqueInner(permutationList, idx + 1, nums, list);
            list.remove(list.size() - 1);
            swap(nums, idx, innerIdx);
        }
    }

    private void swap(int[] nums, int idx, int innerIdx) {
        int temp = nums[idx];
        nums[idx] = nums[innerIdx];
        nums[innerIdx] = temp;
    }

    public static void main(String[] args) {
        int[] nums = {0, 2, 1, -1, -1, 1, 0};
        List<List<Integer>> permutationList = new Permutations2().permuteUnique(nums);
        for (List<Integer> list : permutationList) {
            System.out.println(list);
        }
    }
}
