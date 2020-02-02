package algorithm.five;

import java.util.ArrayDeque;
import java.util.Queue;

public class CBTInserter2 {

	private static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	private TreeNode root;
	private TreeNode[] array;
	int currIdx;

	public CBTInserter2(TreeNode root) {
		this.root = root;
		this.currIdx = 0;
		array = new TreeNode[16384];
		createArray(root);
	}

	private void createArray(TreeNode root) {
		Queue<TreeNode> queue = new ArrayDeque<>();
		queue.add(root);
		while (!queue.isEmpty()) {
			TreeNode node = queue.poll();
			array[currIdx] = node;
			++currIdx;
			if (node.left != null) {
				queue.add(node.left);
			}
			if (node.right != null) {
				queue.add(node.right);
			}
		}
	}

	public int insert(int v) {
		TreeNode node = new TreeNode(v);
		array[currIdx] = node;
		TreeNode parent = array[(currIdx - 1) / 2];
		if (parent.left == null) {
			parent.left = node;

		} else if (parent.right == null) {
			parent.right = node;
		}
		++currIdx;
		return parent.val;
	}

	public TreeNode get_root() {
		return root;
	}

	public static void main(String[] args) {
		TreeNode n4 = new TreeNode(4);
		TreeNode n5 = new TreeNode(5);
		TreeNode n6 = new TreeNode(6);
		TreeNode n2 = new TreeNode(2);
		n2.left = n4;
		n2.right = n5;
		TreeNode n3 = new TreeNode(3);
		n3.left = n6;

		TreeNode n1 = new TreeNode(1);
		n1.left = n2;
		n1.right = n3;

		CBTInserter2 cbtInserter = new CBTInserter2(n1);
		System.out.println(cbtInserter.insert(8));
		System.out.println(cbtInserter.insert(9));
		System.out.println(cbtInserter.insert(10));
		System.out.println(cbtInserter.insert(11));
		System.out.println(cbtInserter.insert(12));
		System.out.println(cbtInserter.insert(13));
		System.out.println(cbtInserter.insert(14));
		System.out.println(cbtInserter.insert(15));
		System.out.println(cbtInserter.insert(16));
		TreeNode root = cbtInserter.get_root();
		System.out.println(root);
	}

}
