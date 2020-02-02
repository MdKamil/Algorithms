package algorithm.two;

import java.util.ArrayDeque;
import java.util.Deque;

public class TwoSumBST2 {

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

	public static boolean findTarget(TreeNode root, int k) {
		boolean isPresent = false;
		Deque<TreeNode> stack = new ArrayDeque<>();
		if (root != null) {
			parseLeftSideOfTree(root, stack);
			isPresent = performReverseInorderTraversal(root, stack, k);
		}
		return isPresent;
	}

	private static void parseLeftSideOfTree(TreeNode root, Deque<TreeNode> stack) {
		if (root == null) {
			return;
		}
		stack.addLast(root);
		parseLeftSideOfTree(root.left, stack);
	}

	private static boolean performReverseInorderTraversal(TreeNode root, Deque<TreeNode> stack, int target) {
		boolean isPresent = false;
		if (root == null) {
			return isPresent;
		}
		isPresent = performReverseInorderTraversal(root.right, stack, target);
		if (!isPresent) {
			while (!stack.isEmpty()) {
				if (root == stack.peekLast()) {
					stack.pollLast();
					continue;
				}
				int sum = root.val + stack.peekLast().val;
				if (sum == target) {
					isPresent = true;
					break;
				} else {
					if (sum < target) {
						TreeNode lastNode = stack.pollLast();
						if (lastNode.right != null) {
							parseLeftSideOfTree(lastNode.right, stack);
						}
					} else {
						break;
					}
				}
			}
			if (!isPresent && !stack.isEmpty()) {
				isPresent = performReverseInorderTraversal(root.left, stack, target);
			}
		}
		return isPresent;
	}

	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		TreeNode n0 = new TreeNode(0);
		TreeNode n4 = new TreeNode(4);
		TreeNode nn2 = new TreeNode(-2);
		TreeNode n3 = new TreeNode(3);

		root.left = n0;
		root.right = n4;
		n0.left = nn2;
		n4.left = n3;

		int k = 7;
		boolean isPresent = findTarget(root, k);
		System.out.println(isPresent);
	}

}
