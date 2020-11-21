package algorithm.tree;

public class SumTree {
	private static class Node {
		int data;
		Node left;
		Node right;

		Node(int data) {
			this.data = data;
		}
	}

	private static int sumTree(Node root) {
		if (root == null) {
			return 0;
		}
		int leftVal = sumTree(root.left);
		int rightVal = sumTree(root.right);
		if (root.left == null && root.right == null) {
			int rootVal = root.data;
			root.data = 0;
			return rootVal;
		}
		int rootVal = root.data;
		root.data = leftVal + rightVal;
		return (leftVal + rightVal + rootVal);
	}

	public static void main(String[] args) {
	}
}
