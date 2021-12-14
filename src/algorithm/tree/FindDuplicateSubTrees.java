package algorithm.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class FindDuplicateSubTrees {
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

	public static List<TreeNode> findDuplicateSubtrees(TreeNode root) {
		List<TreeNode> list = new ArrayList<>();
		HashMap<String, Integer> map = new HashMap<>();
		dfs(root, map, list);
		return list;
	}

	private static String dfs(TreeNode root, HashMap<String, Integer> map, List<TreeNode> list) {
		if (root == null) {
			return "l";
		}
		StringBuilder builder = new StringBuilder();
		String left = dfs(root.left, map, list);
		String right = dfs(root.right, map, list);
		builder.append(root.val);
		if (left != "l" || right != "l") {
			builder.append("nl");
		}
		builder.append(left);
		builder.append(right);
		String s = builder.toString();
		Integer val = map.get(s);
		if (val == null) {
			map.put(s, 1);
		} else {
			if (val == 1) {
				map.put(s, 2);
				list.add(root);
			}
		}
		return s;
	}

	public static void main(String[] args) {
		String s = "[2,1,11,11,null,1]";
		TreeNode root = stringToTreeNode(s);
		List<TreeNode> list = findDuplicateSubtrees(root);
		System.out.println(list);
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

}
