package algorithm.two;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class NodesAtDistanceKBinaryTree {

	private static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int val) {
			this.val = val;
		}
	}

	public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
		List<Integer> list = new ArrayList<Integer>();
		Deque<TreeNode> stack = new ArrayDeque<>();
		getAncestors(root, target, stack);
		if (stack.isEmpty()) {
			return list;
		}
		TreeNode prevNode = null;
		int red = 0;
		while (!stack.isEmpty()) {
			TreeNode currNode = stack.pop();
			check(currNode, prevNode, list, red, K);
			prevNode = currNode;
			++red;
		}
		return list;
	}

	private static void check(TreeNode root, TreeNode parent, List<Integer> list, int dist, int k) {
		if (root == null) {
			return;
		}
		if (dist > k) {
			return;
		}
		if (dist == k) {
			list.add(root.val);
			return;
		}
		if (root.left != parent) {
			check(root.left, root, list, dist + 1, k);
		}
		if (root.right != parent) {
			check(root.right, root, list, dist + 1, k);
		}
	}

	private static void getAncestors(TreeNode root, TreeNode target, Deque<TreeNode> stack) {
		if (root == null) {
			return;
		}
		stack.push(root);
		if (stack.peek().val == target.val) {
			return;
		}
		getAncestors(root.left, target, stack);
		if (stack.peek().val == target.val) {
			return;
		}
		getAncestors(root.right, target, stack);
		if (stack.peek().val == target.val) {
			return;
		}
		stack.pop();
	}
}
