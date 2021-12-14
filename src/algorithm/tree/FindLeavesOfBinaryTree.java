package algorithm.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FindLeavesOfBinaryTree {

	private static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode() {
		}

		TreeNode(int val) {
			this.val = val;
		}

		TreeNode(int val, TreeNode left, TreeNode right) {
			this.val = val;
			this.left = left;
			this.right = right;
		}

		@Override
		public String toString() {
			return Integer.toString(val);
		}

	}

	public List<List<Integer>> findLeaves(TreeNode root) {
		HashMap<Integer, List<Integer>> levelMap = new HashMap<>();
		List<List<Integer>> resultList = new ArrayList<>();
		inorder(root, levelMap);
		for (int level = 1;; ++level) {
			List<Integer> list = levelMap.get(level);
			if (list == null) {
				break;
			} else {
				resultList.add(list);
			}
		}
		return resultList;
	}

	private int inorder(TreeNode root, HashMap<Integer, List<Integer>> levelMap) {
		if (root == null) {
			return 0;
		}
		int leftLevel = inorder(root.left, levelMap);
		int rightLevel = inorder(root.right, levelMap);
		int maxLevel = Math.max(leftLevel, rightLevel) + 1;
		List<Integer> list = levelMap.getOrDefault(maxLevel, new ArrayList<Integer>());
		list.add(root.val);
		levelMap.putIfAbsent(maxLevel, list);
		return maxLevel;
	}
}
