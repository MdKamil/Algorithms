package algorithm.tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//https://leetcode.com/problems/delete-nodes-and-return-forest/
public class DeleteNodesReturnForest {
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

	public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
		List<TreeNode> result = new ArrayList<>();
		if (root != null) {
			Set<Integer> set = new HashSet<Integer>();
			if (to_delete != null) {
				for (int num : to_delete) {
					set.add(num);
				}
			}
			Deque<TreeNode> queue = new ArrayDeque<>();
			queue.addLast(root);
			while (!queue.isEmpty()) {
				TreeNode curr = queue.pollFirst();
				if (!set.contains(curr.val)) {
					result.add(curr);
				}
				dfs(curr, queue, set);
			}
		}
		return result;
	}

	private void dfs(TreeNode root, Deque<TreeNode> queue, Set<Integer> set) {
		if (root == null) {
			return;
		} else {
			if (set.contains(root.val)) {
				if (root.left != null) {
					queue.addLast(root.left);
				}
				if (root.right != null) {
					queue.addLast(root.right);
				}
				root.left = null;
				root.right = null;
			}

			if (root.left != null && set.contains(root.left.val)) {
				queue.addLast(root.left);
				root.left = null;
			} else {
				dfs(root.left, queue, set);
			}

			if (root.right != null && set.contains(root.right.val)) {
				queue.addLast(root.right);
				root.right = null;
			} else {
				dfs(root.right, queue, set);
			}
		}
	}

}
