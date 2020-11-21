package algorithm.tree;

public class FlattenBinaryTreeToList {

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

	public static void flatten(TreeNode root) {
		if (root != null) {
			TreeNode joinNode = null;
			flattenTree(root, joinNode);
		}
	}

	private static TreeNode flattenTree(TreeNode root, TreeNode joinNode) {
		if (root == null) {
			return null;
		}
		TreeNode rightNode = flattenTree(root.right, joinNode);
		TreeNode leftNode = null;
		if (rightNode == null) {
			leftNode = flattenTree(root.left, joinNode);
		} else {
			leftNode = flattenTree(root.left, rightNode);
		}
		if (rightNode == null && leftNode == null) {
			root.right = joinNode;
		} else if (leftNode != null) {
			root.right = root.left;
			root.left = null;
		}
		return root;
	}

	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		TreeNode n2 = new TreeNode(2);
		TreeNode n5 = new TreeNode(5);
		TreeNode n3 = new TreeNode(3);
		TreeNode n4 = new TreeNode(4);
		TreeNode n6 = new TreeNode(6);

		root.left = n2;
		root.right = n5;
		//n2.left = n3;
		n2.right = n4;
		n5.left = n6;

		flatten(root);
		while (root != null) {
			System.out.println(root.val);
			System.out.println(root.left);
			root = root.right;
		}
	}

}
