package algorithm.seven;

import java.util.LinkedList;
import java.util.Queue;

public class ValidateBinarySearchTree {
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

	private static class BooleanHolder {
		boolean isValidBST = true;
	}

	public static boolean isValidBST(TreeNode root) {
		boolean isValidBST = true;
		if (root != null) {
			TreeNode previousNode = null;
			BooleanHolder holder = new BooleanHolder();
			performInorderTraversal(root, previousNode, holder);
			isValidBST = holder.isValidBST;
		}
		return isValidBST;
	}

	private static TreeNode performInorderTraversal(TreeNode root, TreeNode previousNode, BooleanHolder holder) {
		if (root == null) {
			return null;
		}
		TreeNode left = performInorderTraversal(root.left, previousNode, holder);
		TreeNode right = null;
		if (holder.isValidBST) {
			if (left == null) {
				left = previousNode;
			}
			if (left == null || root.val > left.val) {
				previousNode = root;
				right = performInorderTraversal(root.right, previousNode, holder);
				if (right != null) {
					previousNode = right;
				}
			} else {
				holder.isValidBST = false;
			}
		}
		return previousNode;
	}

	public static void main(String[] args) {
		String input = "[5,1,4,null,null,3,6]";
		TreeNode root = stringToTreeNode(input);
		boolean isValidBST = isValidBST(root);
		System.out.println(isValidBST);
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
