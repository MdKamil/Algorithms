package algorithm.tree;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class MaxLevelSum {

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

	private static class LevelWithMaxSum {
		int level;
		int levelSum = Integer.MIN_VALUE;
	}

	public int maxLevelSum(TreeNode root) {
		int smallestLevelWithMaxSum = 0;
		if (root != null) {
			int currLevel = 1;
			Map<Integer, Integer> levelSum = new HashMap<>();
			LevelWithMaxSum levelWithMaxSum = new LevelWithMaxSum();
			performDFS(root, levelSum, currLevel);
			for (java.util.Map.Entry<Integer, Integer> entry : levelSum.entrySet()) {
				if (entry.getValue() > levelWithMaxSum.levelSum) {
					levelWithMaxSum.level = entry.getKey();
					levelWithMaxSum.levelSum = entry.getValue();
				} else if (entry.getValue() == levelWithMaxSum.levelSum) {
					if (entry.getKey() < levelWithMaxSum.level) {
						levelWithMaxSum.level = entry.getKey();
						levelWithMaxSum.levelSum = entry.getValue();
					}
				}
			}
			smallestLevelWithMaxSum = levelWithMaxSum.level;
		}
		return smallestLevelWithMaxSum;
	}

	private static void performDFS(TreeNode root, Map<Integer, Integer> levelSum, int currLevel) {
		if (root == null) {
			return;
		}
		if (!levelSum.containsKey(currLevel)) {
			levelSum.put(currLevel, root.val);
		} else {
			levelSum.put(currLevel, levelSum.get(currLevel) + root.val);
		}
		performDFS(root.left, levelSum, currLevel + 1);
		performDFS(root.right, levelSum, currLevel + 1);
	}

	public int maxLevelSumV2(TreeNode root) {
		int smallestLevelWithMaxSum = 0;
		if (root != null) {
			int currLevel = 1;
			int currLevelSum = 0;
			smallestLevelWithMaxSum = 1;
			int maxSum = root.val;
			TreeNode nextLevelFirstNode = null;
			Queue<TreeNode> queue = new ArrayDeque<>();
			queue.add(root);
			while (!queue.isEmpty()) {
				TreeNode currNode = queue.poll();
				if (currNode == nextLevelFirstNode) {
					if (currLevelSum > maxSum) {
						maxSum = currLevelSum;
						smallestLevelWithMaxSum = currLevel;
					}
					nextLevelFirstNode = null;
					currLevelSum = 0;
					++currLevel;
				}
				currLevelSum += currNode.val;
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
		}
		return smallestLevelWithMaxSum;
	}

	public static void main(String[] args) {

	}

}
