package algorithm.tree;

//https://leetcode.com/problems/symmetric-tree
public class SymmetricTree {
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

	public boolean isSymmetric(TreeNode root) {
		boolean isSymmetric = dfs(root.left, root.right);
		return isSymmetric;
	}

	private boolean dfs(TreeNode root1, TreeNode root2) {
		boolean isSymmetric = false;
		if (root1 == null && root2 == null) {
			isSymmetric = true;
		} else if (root1 != null && root2 != null) {
			if (root1.val == root2.val) {
				isSymmetric = dfs(root1.left, root2.right) && dfs(root1.right, root2.left);
			}
		}
		return isSymmetric;
	}
}
