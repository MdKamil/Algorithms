package algorithm.tree;

public class KSmallestBST {

	private static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x, TreeNode left, TreeNode right) {
			val = x;
			this.left = left;
			this.right = right;
		}

		@Override
		public String toString() {
			return Integer.toString(val);
		}
	}

	private static int cnt = 0;

	public int kthSmallest(TreeNode root, int k) {
		int val = search(root, k);
		return val;
	}

	private static int search(TreeNode root, int k) {
		if (root == null) {
			return 0;
		}
		int left = search(root.left, k);
		if (left != 0 && cnt == k) {
			return left;
		}
		++cnt;
		if (cnt == k) {
			return root.val;
		}
		int right = search(root.right, k);
		if (right != 0 && cnt == k) {
			return right;
		}
		return 0;
	}

	private static class Smallest {
		public int count;
		public TreeNode node;
	}

	public static int getSmallest(TreeNode root, int k) {
		Smallest smallest = new Smallest();
		searchV2(smallest, root, k);
		return smallest.node.val;
	}

	private static void searchV2(Smallest s, TreeNode root, int k) {
		if (root == null) {
			return;
		}
		searchV2(s, root.left, k);
		if (s.count == k) {
			return;
		}
		++s.count;
		if (s.count == k) {
			s.node = root;
			return;
		}
		searchV2(s, root.right, k);
		if (s.count == k) {
			return;
		}
	}

	public static void main(String[] args) {
		TreeNode root = getInput2();
		int val = new KSmallestBST().kthSmallest(root, 3);
		System.out.println(val);
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

	private static TreeNode getInput2() {
		TreeNode n1 = new TreeNode(1, null, null);
		TreeNode n2 = new TreeNode(2, n1, null);
		TreeNode n4 = new TreeNode(4, null, null);

		TreeNode n3 = new TreeNode(3, n2, n4);
		TreeNode n6 = new TreeNode(6, null, null);

		TreeNode root = new TreeNode(5, n3, n6);

		return root;
	}

}
