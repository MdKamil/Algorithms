package algorithm.tree;

import java.util.HashMap;
import java.util.Map;

public class UniqueBinarySearchTrees {
    public int numTrees(int n) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 0);
        map.put(1, 1);
        map.put(2, 2);
        map.put(3, 5);
        if (!map.containsKey(n)) {
            for (int nodeCount = 4; nodeCount <= n; ++nodeCount) {
                int possibilities = 0;
                for (int from = 1; from <= nodeCount; ++from) {
                    int leftSize = (from - 1);
                    int rightSize = nodeCount - from;
                    if (leftSize == 0) {
                        possibilities += map.get(rightSize);
                    } else if (rightSize == 0) {
                        possibilities += map.get(leftSize);
                    } else {
                        possibilities += (map.get(leftSize) * map.get(rightSize));
                    }
                }
                map.put(nodeCount, possibilities);
            }
        }
        return map.get(n);
    }

    public static void main(String[] args) {
        int n = 19;
        int possibilities = new UniqueBinarySearchTrees().numTrees(n);
        System.out.println(possibilities);
    }
}
