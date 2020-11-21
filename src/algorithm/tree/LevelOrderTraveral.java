package algorithm.tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class LevelOrderTraveral {
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
		}
		return list;
	}

	public static void main(String[] args) {
		TreeNode n3 = new TreeNode(3);
		TreeNode n9 = new TreeNode(9);
		TreeNode n20 = new TreeNode(20);
		TreeNode n15 = new TreeNode(15);
		TreeNode n7 = new TreeNode(7);

		n3.left = n9;
		n3.right = n20;
		n20.left = n15;
		n20.right = n7;

		List<List<Integer>> list = levelOrder(n3);
		for (List<Integer> levelList : list) {
			System.out.println(levelList);
		}

	}

}
