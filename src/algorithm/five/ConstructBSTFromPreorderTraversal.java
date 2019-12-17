package algorithm.five;

import java.util.Arrays;

public class ConstructBSTFromPreorderTraversal {

	public static class TreeNode {
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

	public static TreeNode bstFromPreorder(int[] preorder) {
		TreeNode root = null;
		int[] inorder = new int[preorder.length];
		for (int idx = 0; idx <= preorder.length - 1; ++idx) {
			inorder[idx] = preorder[idx];
		}
		Arrays.sort(inorder);
		root = buildTreeInPr(preorder, inorder);
		return root;
	}

	private static int idxCounter = 0;

	public static TreeNode bstFromPreorderV2(int[] preorder) {
		idxCounter = 0;
		TreeNode root = new TreeNode(preorder[idxCounter]);
		TreeNode tempRoot = root;
		TreeNode parent = null;
		++idxCounter;
		constructBSTFromPreorder(preorder, tempRoot, parent);
		return root;
	}

	private static void constructBSTFromPreorder(int[] preorder, TreeNode root, TreeNode parent) {
		if (idxCounter > preorder.length - 1) {
			return;
		}
		if (idxCounter <= preorder.length - 1 && preorder[idxCounter] < root.val) {
			root.left = new TreeNode(preorder[idxCounter]);
			++idxCounter;
			constructBSTFromPreorder(preorder, root.left, root);
		}
		if (idxCounter <= preorder.length - 1
				&& (preorder[idxCounter] > root.val && (parent == null || preorder[idxCounter] < parent.val))) {
			root.right = new TreeNode(preorder[idxCounter]);
			++idxCounter;
			constructBSTFromPreorder(preorder, root.right, parent);
		}
	}

	private static TreeNode buildTreeInPr(int[] preorder, int[] inorder) {
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
		int[] preorder = { 100, 80, 60, 70, 120, 140, 135, 160 };
		TreeNode root = bstFromPreorderV2(preorder);
		System.out.println(root);
	}

}
