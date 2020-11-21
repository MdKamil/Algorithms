package algorithm.tree;

public class BinaryTreeColoringGame {
	private class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	public static boolean btreeGameWinningMove(TreeNode root, int n, int x) {
		boolean canWin = false;
		int totalSize = getTotalTreeSize(root);
		if (totalSize > 2) {
			TreeNode xNode = getNodeWithValueX(root, x);
			int leftSubTreeSize = getTotalTreeSize(xNode.left);
			int rightSubTreeSize = getTotalTreeSize(xNode.right);
			int remainingSize = totalSize - (leftSubTreeSize + rightSubTreeSize + 1);
			if (leftSubTreeSize > rightSubTreeSize + 1 + remainingSize) {
				canWin = true;
			} else if (rightSubTreeSize > leftSubTreeSize + 1 + remainingSize) {
				canWin = true;
			} else if (remainingSize > (leftSubTreeSize + rightSubTreeSize + 1)) {
				canWin = true;
			} else {
				canWin = false;
			}
		}
		return canWin;
	}

	private static TreeNode getNodeWithValueX(TreeNode root, int x) {
		if (root == null) {
			return null;
		}
		if (root.val == x) {
			return root;
		}
		TreeNode xNode = null;
		xNode = getNodeWithValueX(root.left, x);
		if (xNode != null) {
			return xNode;
		}
		xNode = getNodeWithValueX(root.right, x);
		return xNode;
	}

	private static int getTotalTreeSize(TreeNode root) {
		if (root == null) {
			return 0;
		}
		int left = getTotalTreeSize(root.left);
		int right = getTotalTreeSize(root.right);
		return left + right + 1;
	}

	public static void main(String[] args) {

	}
}
