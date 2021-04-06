package algorithm.tree;

public class RecoverBinarySearchTree {
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
		TreeNode parent;
		TreeNode node;

		public Pair(TreeNode parent, TreeNode node) {
			this.parent = parent;
			this.node = node;
		}
	}

	private static class MisplacedNodes {
		Pair pair1;
		Pair pair2;
	}
	
	public void recoverTree(TreeNode root) {
		MisplacedNodes nodes = new MisplacedNodes();
		dfs(root, null, null, nodes);
		Pair pair1 = nodes.pair1;
		Pair pair2 = nodes.pair2;
		int val = pair1.node.val;
		pair1.node.val = pair2.node.val;
		pair2.node.val = val;
	}

	private Pair dfs(TreeNode root, TreeNode parent, Pair predecessor, MisplacedNodes misplacedNodes) {
		if (root == null) {
			return predecessor;
		}
		Pair curr = new Pair(parent, root);
		predecessor = dfs(root.left, root, predecessor, misplacedNodes);
		if (predecessor != null && root.val < predecessor.node.val) {
			if (misplacedNodes.pair1 == null && misplacedNodes.pair2 == null) {
				misplacedNodes.pair1 = predecessor;
				misplacedNodes.pair2 = curr;
			} else {
				misplacedNodes.pair2 = curr;
			}
		}
		predecessor = dfs(root.right, root, curr, misplacedNodes);
		predecessor = predecessor == null ? curr : predecessor;
		return predecessor;
	}

}
