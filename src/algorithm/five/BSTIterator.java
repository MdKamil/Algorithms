package algorithm.five;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.NoSuchElementException;

public class BSTIterator {

	private static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	Deque<TreeNode> stack;

	public BSTIterator(TreeNode root) {
		stack = new ArrayDeque<>();
		if (root != null) {
			parseLeftSideOfTree(root);
		}
	}

	private void parseLeftSideOfTree(TreeNode root) {
		if (root == null) {
			return;
		}
		stack.addLast(root);
		parseLeftSideOfTree(root.left);
	}

	/** @return the next smallest number */
	public int next() {
		if (hasNext()) {
			TreeNode currentNode = stack.pollLast();
			int val = currentNode.val;
			if (currentNode.right != null) {
				parseLeftSideOfTree(currentNode.right);
			}
			return val;
		} else {
			throw new NoSuchElementException();
		}
	}

	/** @return whether we have a next smallest number */
	public boolean hasNext() {
		if (stack.size() == 0) {
			return false;
		} else {
			return true;
		}
	}

	public static void main(String[] args) {
		TreeNode n35 = new TreeNode(35);
		TreeNode n45 = new TreeNode(45);
		TreeNode n40 = new TreeNode(40);
		n40.left = n35;
		n40.right = n45;

		TreeNode n30 = new TreeNode(30);
		n30.right = n40;

		TreeNode n20 = new TreeNode(20);
		n20.right = n30;

		BSTIterator iterator = new BSTIterator(n20);
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
	}

}
