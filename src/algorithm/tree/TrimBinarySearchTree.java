package algorithm.tree;

// https://leetcode.com/problems/trim-a-binary-search-tree/
public class TrimBinarySearchTree {
	private static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int val) {
			this.val = val;
		}

		@Override
		public String toString() {
			return Integer.toString(val);
		}

	}

	public TreeNode trimBST(TreeNode root, int low, int high) {
		return dfs(root, low, high);
	}

	private TreeNode dfs(TreeNode root, int low, int high) {
		if (root == null) {
			return null;
		}
		if (root.val < low) {
			root = dfs(root.right, low, high);
		} else if (root.val > high) {
			root = dfs(root.left, low, high);
		} else {
			root.left = dfs(root.left, low, high);
			root.right = dfs(root.right, low, high);
		}
		return root;
	}
}
