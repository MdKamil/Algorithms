package algorithm.array;

import java.util.ArrayList;
import java.util.List;

public class Combinations {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> combinations = new ArrayList<>();
        recurse(combinations, new ArrayList<>(), n, k, 1);
        return combinations;
    }

    private void recurse(List<List<Integer>> combinations, List<Integer> list, int n, int k, int start) {
        if (list.size() == k) {
            combinations.add(new ArrayList<>(list));
            return;
        }
        for (int from = start; from <= n && (list.size() + (n - from) + 1) >= k; ++from) {
            list.add(from);
            recurse(combinations, list, n, k, from + 1);
            list.remove(list.size() - 1);
        }
    }

    public static void main(String[] args) {
        int n = 10;
        int k = 1;
        List<List<Integer>> combinations = new Combinations().combine(n, k);
        for (List<Integer> list : combinations) {
            System.out.println(list);
        }
    }
}
