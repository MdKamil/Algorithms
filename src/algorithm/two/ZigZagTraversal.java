package algorithm.two;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

public class ZigZagTraversal {

	private static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x, TreeNode left, TreeNode right) {
			val = x;
			this.left = left;
			this.right = right;
		}
	}

	public static List<List<Integer>> zigzagLevelOrderRecursion(TreeNode root) {
		List<List<Integer>> list = new ArrayList<>();
		int height = getHeight(root);
		if (height >= 0) {
			list.add(Arrays.asList(root.val));
			List<Integer> result = null;
			char dir = 'r';
			for (int level = 1; level <= height; ++level) {
				result = new ArrayList<>();
				getNodes(root, 0, level, result, dir);
				dir = (dir == 'r') ? 'l' : 'r';
				list.add(result);
			}
		}
		return list;
	}

	private static void getNodes(TreeNode root, int iter, int level, List<Integer> result, char dir) {
		if (root == null) {
			return;
		}
		if (iter + 1 == level) {
			if (dir == 'r') {
				addToResult(result, root.right);
				addToResult(result, root.left);
			} else {
				addToResult(result, root.left);
				addToResult(result, root.right);
			}
			return;
		}
		if (dir == 'r') {
			getNodes(root.right, iter + 1, level, result, dir);
			getNodes(root.left, iter + 1, level, result, dir);
		} else {
			getNodes(root.left, iter + 1, level, result, dir);
			getNodes(root.right, iter + 1, level, result, dir);
		}
	}

	private static void addToResult(List<Integer> result, TreeNode node) {
		if (node != null) {
			result.add(node.val);
		}
	}

	private static int getHeight(TreeNode root) {
		if (root == null) {
			return -1;
		}
		int left = 1 + getHeight(root.left);
		int right = 1 + getHeight(root.right);
		return Math.max(left, right);
	}

	public static List<List<Integer>> zigzagLevelOrderIterative(TreeNode root) {
		List<List<Integer>> list = new ArrayList<>();
		if (root != null) {
			Deque<TreeNode> stack1 = new ArrayDeque<>();
			Deque<TreeNode> stack2 = new ArrayDeque<>();
			stack1.push(root);
			TreeNode node = null;
			List<Integer> subList = null;
			while (!stack1.isEmpty() || !stack2.isEmpty()) {
				if (!stack1.isEmpty()) {
					subList = new ArrayList<>();
					while (!stack1.isEmpty()) {
						node = stack1.pop();
						subList.add(node.val);
						addTo(stack2, node.left);
						addTo(stack2, node.right);
					}
					list.add(subList);
				}
				if (!stack2.isEmpty()) {
					subList = new ArrayList<>();
					while (!stack2.isEmpty()) {
						node = stack2.pop();
						subList.add(node.val);
						addTo(stack1, node.right);
						addTo(stack1, node.left);
					}
					list.add(subList);
				}
			}
		}
		return list;
	}

	private static void addTo(Deque<TreeNode> stack, TreeNode node) {
		if (node != null) {
			stack.push(node);
		}
	}

	public static void main(String[] args) {
		TreeNode root = getInput1();
		List<List<Integer>> list = new ArrayList<>();
		list = zigzagLevelOrderRecursion(root);
		for (List<Integer> subList : list) {
			System.out.print(subList + " ");
		}
		System.out.println();
		list = zigzagLevelOrderIterative(root);
		for (List<Integer> subList : list) {
			System.out.print(subList + " ");
		}
	}

	private static TreeNode getInput1() {
		TreeNode n23 = new TreeNode(23, null, null);
		TreeNode n24 = new TreeNode(24, null, null);
		TreeNode n35 = new TreeNode(35, null, null);
		TreeNode n25 = new TreeNode(25, null, null);
		TreeNode n26 = new TreeNode(26, null, null);

		TreeNode n15 = new TreeNode(15, n23, n24);
		TreeNode n16 = new TreeNode(16, null, n35);
		TreeNode n17 = new TreeNode(17, n25, n26);
		TreeNode n18 = new TreeNode(18, null, null);
		TreeNode n19 = new TreeNode(19, null, null);
		TreeNode n20 = new TreeNode(20, null, null);
		TreeNode n21 = new TreeNode(21, null, null);
		TreeNode n22 = new TreeNode(22, null, null);

		TreeNode n11 = new TreeNode(11, n15, n16);
		TreeNode n12 = new TreeNode(12, n17, n18);
		TreeNode n13 = new TreeNode(13, n19, n20);
		TreeNode n14 = new TreeNode(14, n21, n22);

		TreeNode n5 = new TreeNode(5, n11, n12);
		TreeNode n2 = new TreeNode(2, n13, n14);

		TreeNode root = new TreeNode(10, n5, n2);

		return root;
	}

}
