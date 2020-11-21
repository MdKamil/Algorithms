package algorithm.tree;

public class FormTree1 {

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

	public static TreeNode buildTreeInPr(int[] preorder, int[] inorder) {
		if (preorder.length == 0 || inorder.length == 0) {
			return null;
		}
		TreeNode root = null;
		int preStart = 0;
		int inStart = 0;
		int inEnd = inorder.length - 1;
		root = buildTreeFromInPr(preorder, preStart, inorder, inStart, inEnd);
		return root;
	}

	private static TreeNode buildTreeFromInPr(int[] preorder, int preStart, int[] inorder, int inStart, int inEnd) {
		if (inEnd < inStart) {
			return null;
		}
		int iter = inStart;
		for (; iter <= inEnd; ++iter) {
			if (inorder[iter] == preorder[preStart]) {
				break;
			}
		}
		TreeNode root = new TreeNode(inorder[iter]);
		root.left = buildTreeFromInPr(preorder, preStart + 1, inorder, inStart, iter - 1);
		root.right = buildTreeFromInPr(preorder, preStart + (iter - inStart) + 1, inorder, iter + 1, inEnd);
		return root;
	}

	public static void main(String[] args) {
		int[] preorder = { 10 };
		int[] inorder = { 10 };
		TreeNode root = buildTreeInPr(preorder, inorder);
		System.out.println(root);
	}

}
