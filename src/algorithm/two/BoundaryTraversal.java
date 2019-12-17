package algorithm.two;

import java.util.ArrayList;
import java.util.List;

public class BoundaryTraversal {
	private static class BinaryTreeNode<T> {
		public T data;
		public BinaryTreeNode<T> left, right;

		public BinaryTreeNode(T data, BinaryTreeNode<T> left, BinaryTreeNode<T> right) {
			this.data = data;
			this.left = left;
			this.right = right;
		}

		@Override
		public String toString() {
			return data.toString();
		}
	}

	public static List<Integer> traverseExterior(BinaryTreeNode<Integer> root) {
		List<Integer> list = new ArrayList<>();
		if (root == null) {
			return list;
		}
		list.add(root.data);
		traverseLeft(root.left, list, true);
		traverseRight(root.right, list, true);
		return list;
	}

	public static void traverseLeft(BinaryTreeNode<Integer> root, List<Integer> list, boolean isBoundary) {
		if (root == null) {
			return;
		}
		if (root.left == null && root.right == null) {
			isBoundary = true;
		}
		if (isBoundary) {
			list.add(root.data);
		}
		traverseLeft(root.left, list, isBoundary);
		traverseLeft(root.right, list, root.left != null ? false : true);
	}

	public static void traverseRight(BinaryTreeNode<Integer> root, List<Integer> list, boolean isBoundary) {
		if (root == null) {
			return;
		}
		traverseRight(root.left, list, root.right != null ? false : true);
		traverseRight(root.right, list, isBoundary);
		if (root.left == null && root.right == null) {
			isBoundary = true;
		}
		if (isBoundary) {
			list.add(root.data);
		}
	}

	public static void main(String[] args) {
		BinaryTreeNode<Integer> n21 = new BinaryTreeNode<>(21, null, null);
		BinaryTreeNode<Integer> n17 = new BinaryTreeNode<>(17, n21, null);
		BinaryTreeNode<Integer> n22 = new BinaryTreeNode<>(22, null, null);
		BinaryTreeNode<Integer> n20 = new BinaryTreeNode<>(20, null, n22);

		BinaryTreeNode<Integer> n18 = new BinaryTreeNode<>(18, null, null);
		BinaryTreeNode<Integer> n19 = new BinaryTreeNode<>(19, null, null);

		BinaryTreeNode<Integer> n15 = new BinaryTreeNode<>(15, n17, n18);
		BinaryTreeNode<Integer> n16 = new BinaryTreeNode<>(16, n19, n20);

		BinaryTreeNode<Integer> n11 = new BinaryTreeNode<>(11, null, null);
		BinaryTreeNode<Integer> n12 = new BinaryTreeNode<>(12, n15, n16);

		BinaryTreeNode<Integer> n13 = new BinaryTreeNode<>(13, null, null);
		BinaryTreeNode<Integer> n14 = new BinaryTreeNode<>(14, null, null);

		BinaryTreeNode<Integer> n8 = new BinaryTreeNode<>(8, n11, n12);
		BinaryTreeNode<Integer> n9 = new BinaryTreeNode<>(9, n13, n14);

		BinaryTreeNode<Integer> root = new BinaryTreeNode<>(10, n8, n9);

		List<Integer> list = traverseExterior(root);
		System.out.println(list);

	}

}
