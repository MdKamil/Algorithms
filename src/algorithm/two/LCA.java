package algorithm.two;

public class LCA {

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

	private static class LCAContainer {
		private int count;
		private TreeNode lcaNode;
	}

	public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		LCAContainer container = getLCABinaryTree(root, p, q);
		if (container.count == 2 && container.lcaNode != null) {
			return container.lcaNode;
		} else {
			return null;
		}
	}

	private static LCAContainer getLCABinaryTree(TreeNode root, TreeNode p, TreeNode q) {
		if (root == null) {
			return null;
		}

		LCAContainer fromRoot = null;
		if (root == p || root == q) {
			fromRoot = new LCAContainer();
			fromRoot.count = 1;
		}

		LCAContainer fromLeft = getLCABinaryTree(root.left, p, q);
		if (fromLeft != null && fromLeft.count == 2 && fromLeft.lcaNode != null) {
			return fromLeft;
		}
		LCAContainer fromRight = getLCABinaryTree(root.right, p, q);
		if (fromRight != null && fromRight.count == 2 && fromRight.lcaNode != null) {
			return fromRight;
		}
		if (fromLeft != null && fromRight != null) {
			LCAContainer container = new LCAContainer();
			container.count = 2;
			container.lcaNode = root;
			return container;
		} else if (fromLeft != null) {
			if (fromRoot != null) {
				LCAContainer container = new LCAContainer();
				container.count = 2;
				container.lcaNode = root;
				return container;
			}
			return fromLeft;
		} else if (fromRight != null) {
			if (fromRoot != null) {
				LCAContainer container = new LCAContainer();
				container.count = 2;
				container.lcaNode = root;
				return container;
			}
			return fromRight;
		} else if (fromRoot != null) {
			return fromRoot;
		} else {
			return null;
		}
	}
}