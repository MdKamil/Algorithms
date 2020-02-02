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

	// Assuming both nodes are there in tree;
	public static TreeNode lowestCommonAncestorV2(TreeNode root, TreeNode p, TreeNode q) {
		TreeNode lcaNode = null;
		if (root != null) {
			lcaNode = performInorderTraversal(root, p, q);
		}
		return lcaNode;
	}

	private static TreeNode performInorderTraversal(TreeNode root, TreeNode p, TreeNode q) {
		if (root == null) {
			return null;
		}
		if (root == p || root == q) {
			return root;
		}
		TreeNode left = performInorderTraversal(root.left, p, q);
		TreeNode right = performInorderTraversal(root.right, p, q);
		if (left != null && right != null) {
			return root;
		} else if (left != null) {
			return left;
		} else if (right != null) {
			return right;
		} else {
			return null;
		}
	}

	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		TreeNode n2 = new TreeNode(2);
		TreeNode n3 = new TreeNode(3);
		TreeNode n4 = new TreeNode(4);
		TreeNode n5 = new TreeNode(5);
		TreeNode n7 = new TreeNode(7);
		TreeNode n9 = new TreeNode(9);

		root.left = n2;
		root.right = n3;
		n2.left = n4;
		n2.right = n5;
		n3.left = n7;
		n3.right = n9;

		TreeNode p = n4;
		TreeNode q = n2;
		TreeNode lcaNode = lowestCommonAncestorV2(root, p, q);
		System.out.println(lcaNode);
	}

}