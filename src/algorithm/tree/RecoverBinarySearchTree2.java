package algorithm.tree;

// https://leetcode.com/problems/recover-binary-search-tree/
public class RecoverBinarySearchTree2 {

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

	private static class Pair {
		TreeNode node1;
		TreeNode node2;
	}

	// We find the nodes that are swapped, and swap their values
	public void recoverTree(TreeNode root) {
		Pair pair = new Pair();
		dfs(root, null, pair);
		int val = pair.node1.val;
		pair.node1.val = pair.node2.val;
		pair.node2.val = val;
	}

	private TreeNode dfs(TreeNode root, TreeNode predecessor, Pair pair) {
		if (root == null) {
			return predecessor;
		}
		predecessor = dfs(root.left, predecessor, pair);
		if (predecessor != null && root.val < predecessor.val) {
			if (pair.node1 == null && pair.node2 == null) {
				pair.node1 = predecessor;
				pair.node2 = root;
			} else {
				pair.node2 = root;
			}
		}
		predecessor = dfs(root.right, root, pair);
		predecessor = predecessor == null ? root : predecessor;
		return predecessor;
	}

}
