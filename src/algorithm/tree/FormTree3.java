package algorithm.tree;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

// Build BinaryTree from InOrder-Level order traversal sequence
public class FormTree3 {

	private static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		public TreeNode(int x) {
			val = x;
		}

		@Override
		public String toString() {
			return Integer.toString(val);
		}
	}

	private static class Pair {
		int l;
		int r;

		public Pair(int l, int r) {
			this.l = l;
			this.r = r;
		}
	}

	public static TreeNode buildTree(int[] inorder, int[] levelorder) {
		return buildTreeNaive(inorder, levelorder);
	}

	public static TreeNode buildTreeNaive(int[] inorder, int[] levelorder) {
		TreeNode root = null;
		if (inorder.length != 0 && levelorder.length != 0) {
			Map<Integer, Integer> inOrderIdx = new HashMap<>();
			for (int i = 0; i <= inorder.length - 1; ++i) {
				inOrderIdx.put(inorder[i], i);
			}
			Deque<Pair> pairQueue = new ArrayDeque<>();
			Deque<TreeNode> nodeQueue = new ArrayDeque<>();
			int count = 1;
			pairQueue.addLast(new Pair(0, inorder.length - 1));
			root = new TreeNode(levelorder[count - 1]);
			nodeQueue.addLast(root);
			while (!pairQueue.isEmpty()) {
				Pair p = pairQueue.pop();
				TreeNode node = nodeQueue.pop();
				int idx = inOrderIdx.get(node.val);
				if (idx - p.l > 0) {
					++count;
					node.left = new TreeNode(levelorder[count - 1]);
					nodeQueue.addLast(node.left);
					pairQueue.addLast(new Pair(p.l, idx - 1));
				}
				if (p.r - idx > 0) {
					++count;
					node.right = new TreeNode(levelorder[count - 1]);
					nodeQueue.addLast(node.right);
					pairQueue.addLast(new Pair(idx + 1, p.r));
				}
			}
		}
		return root;
	}

	public static void main(String[] args) {
		// int[] inorder = { 17, 13, 18, 11, 19, 14, 20, 10, 21, 15, 22, 12, 23,
		// 16, 24 };
		// int[] levelorder = { 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21,
		// 22, 23, 24 };
		int[] inorder = { 13, 18, 11, 10 };
		int[] levelorder = { 10, 11, 13, 18 };
		TreeNode root = buildTree(inorder, levelorder);
		System.out.println(root);
	}

}
