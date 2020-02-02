package algorithm.seven;

import java.util.LinkedList;
import java.util.Queue;

public class LongestUnivaluePath {
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

	private static class MaxUnivalueCount {
		int count = 0;
	}

	public static int longestUnivaluePath(TreeNode root) {
		int longestUnivaluePath = 0;
		if (root != null) {
			MaxUnivalueCount count = new MaxUnivalueCount();
			performInorderTraversal(root, count);
			longestUnivaluePath = count.count;
		}
		return longestUnivaluePath;
	}

	private static int performInorderTraversal(TreeNode root, MaxUnivalueCount count) {
		if (root == null) {
			return 0;
		}
		int leftVal = performInorderTraversal(root.left, count);
		int rightVal = performInorderTraversal(root.right, count);
		if (root.left == null && root.right == null) {
			return 0;
		}
		if (root.left != null) {
			if (root.val == root.left.val) {
				++leftVal;
			} else {
				leftVal = 0;
			}
		}
		if (root.right != null) {
			if (root.val == root.right.val) {
				++rightVal;
			} else {
				rightVal = 0;
			}
		}
		if (root.left != null && root.right != null) {
			if (root.val == root.left.val && root.val == root.right.val) {
				count.count = Math.max(count.count, leftVal + rightVal);
			}
		}
		count.count = Math.max(count.count, Math.max(leftVal, rightVal));
		return Math.max(leftVal, rightVal);
	}

	public static void main(String[] args) {
		String input = "[1,4,5,4,4,5]";
		TreeNode root = stringToTreeNode(input);
		int longestUnivaluePath = longestUnivaluePath(root);
		System.out.println(longestUnivaluePath);
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

}
