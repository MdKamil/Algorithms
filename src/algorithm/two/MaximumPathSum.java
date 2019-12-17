package algorithm.two;

public class MaximumPathSum {
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	private static int maxSum = Integer.MIN_VALUE;

	public static int maxSum(TreeNode root) {
		if (root == null) {
			return 0;
		}
		int lSum = maxSum(root.left);
		int rSum = maxSum(root.right);
		int lrSum = lSum + root.val;
		int rrSum = rSum + root.val;
		int lrrsum = lSum + rSum + root.val;
		int rMAX = Math.max(Math.max(Math.max(lrSum, rrSum), lrrsum), root.val);
		if (rMAX > maxSum) {
			maxSum = rMAX;
		}
		int retVal = Math.max(Math.max(Math.max(lrSum, rrSum), root.val), 0);
		return retVal;
	}

}
