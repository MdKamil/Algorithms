package algorithm.tree;

import java.util.Objects;

public class BottomLeftTreeValue {
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
        @Override
        public String toString() {
            return Integer.toString(val);
        }

    }

    private static class Pair<T1, T2> {
        T1 first;
        T2 second;
        public Pair() {}
        public Pair(T1 first, T2 second) {
            this.first = first;
            this.second = second;
        }
    }

    public int findBottomLeftValue(TreeNode root) {
        Pair<Integer, Integer> pair = new Pair<>(0, 0);
        dfs(root, 1, pair);
        return pair.second;
    }

    private void dfs(TreeNode root, int row, Pair<Integer, Integer> pair) {
        if (Objects.isNull(root)) {
            return;
        }
        if (row > pair.first) {
            pair.first = row;
            pair.second = root.val;
        }
        dfs(root.left, row + 1, pair);
        dfs(root.right, row + 1, pair);
    }
}
