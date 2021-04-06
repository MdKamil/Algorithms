package algorithm.tree;

//https://leetcode.com/problems/sum-root-to-leaf-numbers
public class SumRootToLeafNumbers {

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

	public int sumNumbers(TreeNode root) {
		int sum = dfs(root, 0);
		return sum;
	}

	private int dfs(TreeNode root, int sumFromRoot) {
		if (root == null) {
			return 0;
		}
		sumFromRoot = (sumFromRoot * 10) + root.val;
		int returnSum = 0;
		if (root.left == null && root.right == null) {
			returnSum = sumFromRoot;
		} else {
			int leftSum = dfs(root.left, sumFromRoot);
			int rightSum = dfs(root.right, sumFromRoot);
			returnSum = leftSum + rightSum;
		}
		return returnSum;
	}
}
