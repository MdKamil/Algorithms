package algorithm.seven;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

public class CompletenessOfABinaryTree {

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

	public static boolean isCompleteTree(TreeNode root) {
		boolean isComplete = true;
		if (root != null) {
			Queue<TreeNode> queue = new LinkedList<TreeNode>();
			queue.add(root);
			TreeNode previousNode = root;
			while (!queue.isEmpty()) {
				TreeNode currNode = queue.poll();
				if (currNode != null) {
					if (previousNode == null || (currNode.right != null && currNode.left == null)) {
						isComplete = false;
						break;
					}
					queue.add(currNode.left);
					queue.add(currNode.right);
				}
				previousNode = currNode;
			}
			queue.clear();
		}
		return isComplete;
	}

	public static boolean isCompleteTreeV2(TreeNode root) {
		boolean isComplete = true;
		if (root != null) {
			Queue<TreeNode> queue = new ArrayDeque<TreeNode>();
			queue.add(root);
			TreeNode previousNode = root;
			boolean isLastNodeAdded = false;
			while (!queue.isEmpty()) {
				TreeNode currNode = queue.poll();
				if (currNode != null) {
					if (previousNode == null || (currNode.left == null && currNode.right != null)) {
						isComplete = false;
						break;
					}
					if (currNode.left == null && currNode.right == null) {
						isLastNodeAdded = true;
					} else {
						if (currNode.left != null) {
							if (isLastNodeAdded) {
								isComplete = false;
								break;
							}
							queue.add(currNode.left);
						}
						if (currNode.right != null) {
							queue.add(currNode.right);
						} else {
							isLastNodeAdded = true;
						}
					}
				}
				previousNode = currNode;
			}
			queue.clear();
		}
		return isComplete;
	}

	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		TreeNode n2 = new TreeNode(2);
		TreeNode n3 = new TreeNode(3);
		TreeNode n4 = new TreeNode(4);
		TreeNode n5 = new TreeNode(5);
		TreeNode n6 = new TreeNode(6);

		root.left = n2;
		root.right = n3;
		n2.left = n4;
		n2.right = n5;
		n4.left = n6;

		boolean isComplete = isCompleteTreeV2(root);
		System.out.println(isComplete);
	}
}
