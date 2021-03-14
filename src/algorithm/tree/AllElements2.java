package algorithm.tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class AllElements2 {
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

	public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
		Deque<TreeNode> stack1 = new ArrayDeque<>();
		Deque<TreeNode> stack2 = new ArrayDeque<>();
		List<Integer> result = new ArrayList<>();

		traverseLeft(root1, stack1);
		traverseLeft(root2, stack2);
		TreeNode curr1 = stack1.pollLast();
		TreeNode curr2 = stack2.pollLast();
		while (curr1 != null || curr2 != null) {
			if (curr1 != null && curr2 != null) {
				if (curr1.val <= curr2.val) {
					curr1 = addToResult(curr1, result, stack1);
				} else {
					curr2 = addToResult(curr2, result, stack2);
				}
			} else if (curr1 != null) {
				curr1 = addToResult(curr1, result, stack1);
			} else if (curr2 != null) {
				curr2 = addToResult(curr2, result, stack2);
			}
		}
		return result;
	}

	private TreeNode addToResult(TreeNode curr, List<Integer> result, Deque<TreeNode> stack) {
		result.add(curr.val);
		if (curr.right != null) {
			stack.addLast(curr.right);
			traverseLeft(curr.right.left, stack);
		}
		curr = stack.pollLast();
		return curr;
	}

	private void traverseLeft(TreeNode root, Deque<TreeNode> stack) {
		if (root == null) {
			return;
		}
		stack.addLast(root);
		traverseLeft(root.left, stack);
	}
}
