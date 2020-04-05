package algorithm.eight;

import java.util.LinkedList;
import java.util.Queue;

public class DeepestLeavesSum {

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

	public static TreeNode stringToTreeNode(String input) {
		input = input.trim();
		input = input.substring(1, input.length() - 1);
		if (input.length() == 0) {
			return null;
		}

		String[] parts = input.split(",");
		String item = parts[0];
		TreeNode root = new TreeNode(Integer.parseInt(item));
		Queue<TreeNode> nodeQueue = new LinkedList<>();
		nodeQueue.add(root);

		int index = 1;
		while (!nodeQueue.isEmpty()) {
			TreeNode node = nodeQueue.remove();

			if (index == parts.length) {
				break;
			}

			item = parts[index++];
			item = item.trim();
			if (!item.equals("null")) {
				int leftNumber = Integer.parseInt(item);
				node.left = new TreeNode(leftNumber);
				nodeQueue.add(node.left);
			}

			if (index == parts.length) {
				break;
			}

			item = parts[index++];
			item = item.trim();
			if (!item.equals("null")) {
				int rightNumber = Integer.parseInt(item);
				node.right = new TreeNode(rightNumber);
				nodeQueue.add(node.right);
			}
		}
		return root;
	}

	private static class DeepestLevel {
		int level;
		int levelSum;
	}

	public static int deepestLeavesSum(TreeNode root) {
		int sum = 0;
		if (root != null) {
			DeepestLevel deepestLevel = new DeepestLevel();
			int level = 0;
			performInorderTraversal(root, deepestLevel, level);
			sum = deepestLevel.levelSum;
		}
		return sum;
	}

	private static void performInorderTraversal(TreeNode root, DeepestLevel deepestLevel, int level) {
		if (root == null) {
			return;
		}
		performInorderTraversal(root.left, deepestLevel, level + 1);
		if (level > deepestLevel.level) {
			deepestLevel.level = level;
			deepestLevel.levelSum = root.val;
		} else if (level == deepestLevel.level) {
			deepestLevel.levelSum += root.val;
		}
		performInorderTraversal(root.right, deepestLevel, level + 1);
	}

	public static void main(String[] args) {
		String input = "[1,2,3]";
		TreeNode root = stringToTreeNode(input);
		int sum = deepestLeavesSum(root);
		System.out.println(sum);
	}

}
