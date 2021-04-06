package algorithm.tree;

//https://leetcode.com/problems/flip-equivalent-binary-trees/
public class FlipEquivalentBinaryTrees {
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

	public boolean flipEquiv(TreeNode root1, TreeNode root2) {
		boolean isEquivalent = dfs(root1, root2);
		return isEquivalent;
	}

	private boolean dfs(TreeNode root1, TreeNode root2) {
		boolean isEqual = false;
		if (root1 == null && root2 == null) {
			isEqual = true;
		} else if (root1 != null && root2 != null) {
			if (root1.val == root2.val) {
				if (root1.left == null && root1.right == null && root2.left == null && root2.right == null) {
					isEqual = true;
				} else if (isBothChildEqual(root1, root2)) {
					isEqual = dfs(root1.left, root2.left) && dfs(root1.right, root2.right);
				} else if (isLeftChildEqual(root1, root2)) {
					isEqual = dfs(root1.left, root2.left);
				} else if (isRightChildEqual(root1, root2)) {
					isEqual = dfs(root1.right, root2.right);
				} else {
					// swap
					TreeNode temp = root1.left;
					root1.left = root1.right;
					root1.right = temp;

					if (isBothChildEqual(root1, root2)) {
						isEqual = dfs(root1.left, root2.left) && dfs(root1.right, root2.right);
					} else if (isLeftChildEqual(root1, root2)) {
						isEqual = dfs(root1.left, root2.left);
					} else if (isRightChildEqual(root1, root2)) {
						isEqual = dfs(root1.right, root2.right);
					}
				}
			}
		}
		return isEqual;
	}

	private boolean isBothChildEqual(TreeNode root1, TreeNode root2) {
		return root1.left != null && root2.left != null && root1.right != null && root2.right != null
				&& root1.left.val == root2.left.val && root1.right.val == root2.right.val;
	}

	private boolean isLeftChildEqual(TreeNode root1, TreeNode root2) {
		return root1.left != null && root1.right == null && root2.left != null && root2.right == null
				&& root1.left.val == root2.left.val;
	}

	private boolean isRightChildEqual(TreeNode root1, TreeNode root2) {
		return root1.left == null && root1.right != null && root2.left == null && root2.right != null
				&& root1.right.val == root2.right.val;
	}

}
