package algorithm.tree;

// https://leetcode.com/problems/smallest-subtree-with-all-the-deepest-nodes/
// https://leetcode.com/problems/lowest-common-ancestor-of-deepest-leaves/
public class SmallestSubTreeWithDeepestNodes {
	private static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode() {
		}

		TreeNode(int val) {
			this.val = val;
		}

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

	private static class Pair {
		TreeNode node;
		int depth;
	}

	public TreeNode subtreeWithAllDeepest(TreeNode root) {
		Pair deepestNode = new Pair();
		inorder(root, deepestNode, 0);
		return deepestNode.node;
	}

	private int inorder(TreeNode root, Pair deepestNode, int dist) {
		if (root == null) {
			return 0;
		}
		int leftHeight = inorder(root.left, deepestNode, dist + 1);
		int rightHeight = inorder(root.right, deepestNode, dist + 1);
		if (leftHeight == rightHeight) {
			int depth = dist + leftHeight;
			if (depth >= deepestNode.depth) {
				deepestNode.node = root;
				deepestNode.depth = depth;
			}
		}
		return leftHeight >= rightHeight ? leftHeight + 1 : rightHeight + 1;
	}

}
