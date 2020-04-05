package algorithm.eight;

import java.util.LinkedList;
import java.util.Queue;

public class PathSum3 {

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

	public static int pathSum(TreeNode root, int sum) {
		int noOfPathsWithSum = 0;
		if (root != null) {
			noOfPathsWithSum += performPreorderTraversal(root, sum);
		}
		return noOfPathsWithSum;
	}

	private static int performPreorderTraversal(TreeNode root, int sum) {
		if (root == null) {
			return 0;
		}
		int count = travelDown(root, 0, sum);
		count += performPreorderTraversal(root.left, sum);
		count += performPreorderTraversal(root.right, sum);
		return count;
	}

	private static int travelDown(TreeNode root, int currSum, int sum) {
		if (root == null) {
			return 0;
		}
		int count = 0;
		currSum += root.val;
		if (currSum == sum) {
			++count;
		}
		count += travelDown(root.left, currSum, sum);
		count += travelDown(root.right, currSum, sum);
		return count;
	}
	
	public static int pathSumV2(TreeNode root, int sum) {
		int noOfPathsWithSum = 0;
		if (root != null) {
			
		}
		return noOfPathsWithSum;
	}
	
	public static void main(String[] args) {
		String input = "[10,5,-3,3,2,null,11,3,-2,null,1]";
		TreeNode root = stringToTreeNode(input);
		int sum = 8;
		int noOfPathsWithSum = pathSum(root, sum);
		System.out.println(noOfPathsWithSum);
	}

}
