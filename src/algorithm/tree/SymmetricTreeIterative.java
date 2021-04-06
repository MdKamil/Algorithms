package algorithm.tree;

import java.util.ArrayDeque;
import java.util.Deque;

// https://leetcode.com/problems/symmetric-tree/
public class SymmetricTreeIterative {
	private static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int val) {
			this.val = val;
		}

		@Override
		public String toString() {
			return Integer.toString(val);
		}

	}

	public boolean isSymmetric(TreeNode root) {
		boolean isSymmetric = true;
		Deque<TreeNode> stack1 = new ArrayDeque<>();
		Deque<TreeNode> stack2 = new ArrayDeque<>();
		TreeNode curr1 = root.left;
		TreeNode curr2 = root.right;
		while (true) {
			if (curr1 == null && curr2 == null && stack1.isEmpty() && stack2.isEmpty()) {
				break;
			} else if ((curr1 == null && curr2 != null) || (curr1 != null && curr2 == null)) {
				isSymmetric = false;
				break;
			} else {
				if (curr1 != null && curr2 != null) {
					if (curr1.val != curr2.val) {
						isSymmetric = false;
						break;
					}
					stack1.addLast(curr1);
					stack2.addLast(curr2);
					curr1 = curr1.left;
					curr2 = curr2.right;
				} else {
					curr1 = stack1.pollLast();
					curr2 = stack2.pollLast();
					curr1 = curr1.right;
					curr2 = curr2.left;
				}
			}
		}
		return isSymmetric;
	}
}
