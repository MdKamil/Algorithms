package algorithm.seven;

import java.util.ArrayDeque;
import java.util.Queue;

public class CousinsInBinaryTree {
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

	public static boolean isCousins(TreeNode root, int x, int y) {
		boolean isCousins = false;
		if (root != null) {
			if (x != root.val && y != root.val) {
				Queue<TreeNode> queue = new ArrayDeque<TreeNode>();
				queue.add(root);
				int level = 0;
				TreeNode xParent = null;
				int xLevel = 0;
				TreeNode yParent = null;
				int yLevel = 0;
				TreeNode nextLevelFirstNode = root;
				while (!queue.isEmpty()) {
					TreeNode currNode = queue.poll();
					if (currNode == nextLevelFirstNode) {
						++level;
						nextLevelFirstNode = null;
					}
					if (currNode.left != null) {
						if (currNode.left.val == x) {
							xParent = currNode;
							xLevel = level + 1;
						} else if (currNode.left.val == y) {
							yParent = currNode;
							yLevel = level + 1;
						}
						queue.add(currNode.left);
						if (nextLevelFirstNode == null) {
							nextLevelFirstNode = currNode.left;
						}
					}
					if (currNode.right != null) {
						if (currNode.right.val == x) {
							xParent = currNode;
							xLevel = level + 1;
						} else if (currNode.right.val == y) {
							yParent = currNode;
							yLevel = level + 1;
						}
						queue.add(currNode.right);
						if (nextLevelFirstNode == null) {
							nextLevelFirstNode = currNode.right;
						}
					}
				}
				if (xParent != yParent && xLevel == yLevel) {
					isCousins = true;
				}
			}
		}
		return isCousins;
	}

	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		TreeNode n2 = new TreeNode(2);
		TreeNode n3 = new TreeNode(3);
		TreeNode n4 = new TreeNode(4);
		TreeNode n5 = new TreeNode(5);
		TreeNode n7 = new TreeNode(7);
		TreeNode n9 = new TreeNode(9);

		root.left = n2;
		root.right = n3;
		n2.left = n4;
		n2.right = n5;
		n3.left = n7;
		n3.right = n9;

		int x = 1;
		int y = 3;
		boolean isCousins = isCousins(root, x, y);
		System.out.println(isCousins);
	}

}
