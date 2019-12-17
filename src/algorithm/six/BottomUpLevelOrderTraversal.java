package algorithm.six;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;

public class BottomUpLevelOrderTraversal {

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

	public static List<List<Integer>> levelOrder(TreeNode root) {
		List<List<Integer>> list = new ArrayList<>();
		if (root != null) {
			TreeNode nextLevelFirstNode = null;
			Queue<TreeNode> queue = new ArrayDeque<>();
			queue.add(root);
			List<Integer> levelNodeList = new ArrayList<>();
			while (!queue.isEmpty()) {
				TreeNode currNode = queue.poll();
				if (currNode == nextLevelFirstNode) {
					list.add(levelNodeList);
					levelNodeList = new ArrayList<>();
					nextLevelFirstNode = null;
				}
				levelNodeList.add(currNode.val);
				if (currNode.left != null) {
					queue.add(currNode.left);
				}
				if (currNode.right != null) {
					queue.add(currNode.right);
				}
				if (nextLevelFirstNode == null) {
					nextLevelFirstNode = (currNode.left != null ? currNode.left : currNode.right);
				}
			}
			list.add(levelNodeList);
			Collections.reverse(list);
		}
		return list;
	}
}
