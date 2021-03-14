package algorithm.tree;

//https://leetcode.com/problems/sum-of-nodes-with-even-valued-grandparent/
public class NodeSumWithEvenValuedGrandParent {
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

	private static class MutableInteger {
		int value = 0;
	}

	public int sumEvenGrandparent(TreeNode root) {
		int sum = 0;
		MutableInteger mutableInteger = new MutableInteger();
		sum = performInorderTraversal(root, mutableInteger);
		return sum;
	}

	private int performInorderTraversal(TreeNode root, MutableInteger mutableInteger) {
		if (root == null) {
			return 0;
		}
		int grandChildenSum = 0;
		int childrenSum = 0;
		childrenSum += root.left != null ? root.left.val : 0;
		childrenSum += root.right != null ? root.right.val : 0;
		int leftGrandChildenSum = performInorderTraversal(root.left, mutableInteger);
		int rightGrandChildenSum = performInorderTraversal(root.right, mutableInteger);

		if (root.val % 2 == 0) {
			grandChildenSum = leftGrandChildenSum + rightGrandChildenSum;
			mutableInteger.value += grandChildenSum;
		}

		return childrenSum;
	}

}
