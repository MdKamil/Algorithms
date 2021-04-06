package algorithm.tree;

//https://leetcode.com/problems/maximum-difference-between-node-and-ancestor/
public class MaxDiffBetweenNodeAndAncestor {
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

	public int maxAncestorDiff(TreeNode root) {
		int maxDiff = 0;
		if (root != null) {
			maxDiff = dfs(root, root.val, root.val);
		}
		return maxDiff;
	}

	private int dfs(TreeNode root, int min, int max) {
		if (root == null) {
			return Math.abs(min - max);
		}

		if (root.val < min) {
			min = root.val;
		} else if (root.val > max) {
			max = root.val;
		}

		int maxDiffFromLeft = dfs(root.left, min, max);
		int maxDiffFromRight = dfs(root.right, min, max);

		int difference = Math.abs(min - max);
		difference = Math.max(Math.max(maxDiffFromLeft, difference), maxDiffFromRight);

		return difference;
	}

}
