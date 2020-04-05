package algorithm.eight;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class PathSum2 {

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

	public static List<List<Integer>> pathSum(TreeNode root, int sum) {
		List<List<Integer>> masterList = new ArrayList<>();
		if (root != null) {
			List<Integer> subList = new ArrayList<>();
			int currSum = 0;
			performPreOrderTraversal(root, sum, currSum, subList, masterList);
		}
		return masterList;
	}

	private static void performPreOrderTraversal(TreeNode root, int sum, int currSum, List<Integer> subList,
			List<List<Integer>> masterList) {
		if (root == null) {
			return;
		}
		currSum += root.val;
		if (currSum == sum && root.left == null && root.right == null) {
			subList.add(root.val);
			masterList.add(new ArrayList<>(subList));
			subList.remove(subList.size() - 1);
		} else {
			subList.add(root.val);
			performPreOrderTraversal(root.left, sum, currSum, subList, masterList);
			performPreOrderTraversal(root.right, sum, currSum, subList, masterList);
			subList.remove(subList.size() - 1);
		}
	}

	public static void main(String[] args) {
		String input = "[1,2]";
		TreeNode root = stringToTreeNode(input);
		int sum = 1;
		List<List<Integer>> masterList = pathSum(root, sum);
		for (List<Integer> list : masterList) {
			System.out.println(list);
		}
	}

}
