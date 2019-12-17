package algorithm.two;

import java.util.HashMap;
import java.util.Map;

public class FormTree2 {

	private static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		public TreeNode(int val) {
			this.val = val;
		}

		@Override
		public String toString() {
			return Integer.toString(val);
		}
	}

	public static TreeNode buildTree(int[] inorder, int[] postorder) {
		TreeNode root = null;
		int postStart = postorder.length - 1;
		int inStart = 0;
		int inEnd = inorder.length - 1;
		root = buildTreeFromInPo(postorder, postStart, inorder, inStart, inEnd);
		return root;
	}

	private static TreeNode buildTreeFromInPo(int[] postorder, int postStart, int[] inorder, int inStart, int inEnd) {
		if (inStart > inEnd) {
			return null;
		}
		int iter = inStart;
		for (; iter <= inEnd; ++iter) {
			if (inorder[iter] == postorder[postStart]) {
				break;
			}
		}
		TreeNode root = new TreeNode(inorder[iter]);
		root.left = buildTreeFromInPo(postorder, postStart - ((inEnd - iter) + 1), inorder, inStart, iter - 1);
		root.right = buildTreeFromInPo(postorder, postStart - 1, inorder, iter + 1, inEnd);
		return root;
	}

	public static TreeNode buildTreeV2(int[] inorder, int[] postorder) {
		TreeNode root = null;
		int postStart = postorder.length - 1;
		int inStart = 0;
		int inEnd = inorder.length - 1;
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i <= inorder.length - 1; ++i) {
			map.put(inorder[i], i);
		}
		root = buildTreeFromInPoV2(postorder, postStart, inorder, inStart, inEnd, map);
		return root;
	}

	private static TreeNode buildTreeFromInPoV2(int[] postorder, int postStart, int[] inorder, int inStart, int inEnd,
			Map<Integer, Integer> map) {
		if (inStart > inEnd) {
			return null;
		}
		int iter = map.get(postorder[postStart]);
		TreeNode root = new TreeNode(inorder[iter]);
		root.left = buildTreeFromInPoV2(postorder, postStart - ((inEnd - iter) + 1), inorder, inStart, iter - 1, map);
		root.right = buildTreeFromInPoV2(postorder, postStart - 1, inorder, iter + 1, inEnd, map);
		return root;
	}

	public static void main(String[] args) {
		int[] inorder = { 9, 3, 15, 20, 7 };
		int[] postorder = { 9, 15, 7, 20, 3 };
		TreeNode root = buildTreeV2(inorder, postorder);
		System.out.println(root);
	}

}
