package algorithm.three;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

public class IterativeTraversal {

	private static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	public static List<Integer> iterativeInorder(TreeNode root) {
		List<Integer> list = new ArrayList<>();
		if (root != null) {
			Deque<TreeNode> stack = new ArrayDeque<>();
			TreeNode curr = root;
			while (!stack.isEmpty() || curr != null) {
				if (curr != null) {
					stack.addLast(curr);
					curr = curr.left;
				} else {
					TreeNode node = stack.pollLast();
					list.add(node.val);
					curr = node.right;
				}
			}
		}
		return list;
	}

	public static List<Integer> iterativePreorder(TreeNode root) {
		List<Integer> list = new ArrayList<>();
		if (root != null) {
			Deque<TreeNode> queue = new ArrayDeque<>();
			queue.addLast(root);
			while (!queue.isEmpty()) {
				TreeNode curr = queue.pollLast();
				if (curr == null) {
					continue;
				}
				list.add(curr.val);
				if (curr.right != null) {
					queue.addLast(curr.right);
				}
				if (curr.left != null) {
					queue.addLast(curr.left);
				}
			}
		}
		return list;
	}

	public static List<Integer> iterativePostorder(TreeNode root) {
		List<Integer> list = new ArrayList<>();
		if (root != null) {
			Deque<TreeNode> queue = new ArrayDeque<>();
			TreeNode curr = root;
			while (!queue.isEmpty() || curr != null) {
				if (curr != null) {
					list.add(curr.val);
					queue.addLast(curr);
					curr = curr.right;
				} else {
					TreeNode node = queue.pollLast();
					curr = node.left;
				}
			}
		}
		Collections.reverse(list);
		return list;
	}

}
