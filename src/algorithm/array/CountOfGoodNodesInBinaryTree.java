package algorithm.array;

import com.sun.source.tree.Tree;

import java.util.Objects;

public class CountOfGoodNodesInBinaryTree {

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

    public int goodNodes(TreeNode root) {
        int count = 0;
        return dfs(root, root.val, count);
    }

    private int dfs(TreeNode root, int maxVal, int count) {
        if (Objects.isNull(root)) {
            return count;
        }
        if (root.val >= maxVal) {
            ++count;
            maxVal = root.val;
        }
        count = dfs(root.left, maxVal, count);
        count = dfs(root.right, maxVal, count);
        return count;
    }
}
