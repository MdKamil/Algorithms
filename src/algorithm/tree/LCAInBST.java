package algorithm.tree;

public class LCAInBST {
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

	public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		TreeNode lcaNode = null;
		if (root != null) {
			while (true) {
				if (root == p || root == q || (root.val > p.val && root.val < q.val)
						|| (root.val > q.val && root.val < p.val)) {
					lcaNode = root;
					break;
				} else if (root.val < p.val && root.val < q.val) {
					root = root.right;
				} else if (root.val > p.val && root.val > q.val) {
					root = root.left;
				}
			}
		}
		return lcaNode;
	}

}
