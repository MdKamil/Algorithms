package algorithm.tree;

import java.util.LinkedList;
import java.util.Queue;

public class HouseRobber3 {

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

	private static class MaxAtEachLevel {
		int withRoot;
		int withoutRoot;
	}

	private static class MaxAmountRobbed {
		int amount;
	}

	public static int rob(TreeNode root) {
		int maxAmount = 0;
		if (root != null) {
			MaxAmountRobbed maxAmountRobbed = new MaxAmountRobbed();
			performInorderTraversal(root, maxAmountRobbed);
			maxAmount = maxAmountRobbed.amount;
		}
		return maxAmount;
	}

	private static MaxAtEachLevel performInorderTraversal(TreeNode root, MaxAmountRobbed maxAmountRobbed) {
		if (root == null) {
			return new MaxAtEachLevel();
		}
		MaxAtEachLevel currNode = new MaxAtEachLevel();
		MaxAtEachLevel left = performInorderTraversal(root.left, maxAmountRobbed);
		MaxAtEachLevel right = performInorderTraversal(root.right, maxAmountRobbed);
		currNode.withRoot = root.val + left.withoutRoot + right.withoutRoot;
		currNode.withoutRoot = Math.max(left.withRoot + right.withRoot,
				Math.max(left.withRoot + right.withoutRoot, left.withoutRoot + right.withRoot));
		System.out.println(currNode.withRoot + " " + currNode.withoutRoot);
		maxAmountRobbed.amount = Math.max(maxAmountRobbed.amount, Math.max(currNode.withRoot, currNode.withoutRoot));
		return currNode;
	}

	public static void main(String[] args) {
		String input = "[10,2,500,3,null,null,null,2,null,3,null,4,null]";
		TreeNode root = stringToTreeNode(input);
		int maxAmount = rob(root);
		System.out.println(maxAmount);
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
