package algorithm.tree;

public class RangeSumBST {
	private static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}

		@Override
		public String toString() {
			return Integer.toString(val);
		}

	}

	public int rangeSumBST(TreeNode root, int low, int high) {
		int rangeSum = 0;
		rangeSum = performInorderTraversal(root, low, high);
		return rangeSum;
	}

	private int performInorderTraversal(TreeNode root, int low, int high) {
		if (root == null) {
			return 0;
		}
		int leftSum = 0, rightSum = 0;
		if (low >= root.val) {
			rightSum = performInorderTraversal(root.right, low, high);
		} else if (high <= root.val) {
			leftSum = performInorderTraversal(root.left, low, high);
		} else {
			leftSum = performInorderTraversal(root.left, low, high);
			rightSum = performInorderTraversal(root.right, low, high);
		}
		return leftSum + rightSum + (low <= root.val && root.val <= high ? root.val : 0);
	}

}
