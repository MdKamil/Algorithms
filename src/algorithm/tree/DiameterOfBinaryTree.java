package algorithm.tree;

import java.util.LinkedList;
import java.util.Queue;

public class DiameterOfBinaryTree {

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

	private static TreeNode stringToTreeNode(String input) {
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

	private static class MaxLength {
		int length;
	}

	public static int diameterOfBinaryTree(TreeNode root) {
		int diameter = 0;
		if (root != null) {
			MaxLength maxLength = new MaxLength();
			performInorderTraversal(root, maxLength);
			diameter = maxLength.length;
		}
		return diameter;
	}

	private static int performInorderTraversal(TreeNode root, MaxLength maxLength) {
		int length = 0;
		if (root != null) {
			int leftLength = performInorderTraversal(root.left, maxLength);
			int rightLength = performInorderTraversal(root.right, maxLength);
			if (leftLength + rightLength > maxLength.length) {
				maxLength.length = leftLength + rightLength;
			}
			length = Math.max(leftLength, rightLength) + 1;
		}
		return length;
	}

	public static void main(String[] args) {
		String input = "[1,2,3,4,5]";
		TreeNode root = stringToTreeNode(input);
		int diameter = diameterOfBinaryTree(root);
		System.out.println(diameter);
	}

}
