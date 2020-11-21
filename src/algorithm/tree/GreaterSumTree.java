package algorithm.tree;

public class GreaterSumTree {

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

	public static TreeNode bstToGst(TreeNode root) {
		if (root != null) {
			int sum = 0;
			doReverseInorderTraversal(root, sum);
		}
		return root;
	}

	private static int doReverseInorderTraversal(TreeNode root, int sum) {
		if (root == null) {
			return 0;
		}
		int rightVal = doReverseInorderTraversal(root.right, sum);
		if (root.right != null) {
			root.val = root.val + rightVal;
		} else {
			root.val = root.val + sum;
		}
		int leftVal = doReverseInorderTraversal(root.left, root.val);
		if (root.left != null) {
			return leftVal;
		} else {
			return root.val + leftVal;
		}
	}

	public static void main(String[] args) {
		TreeNode root = createBSTree();
		bstToGst(root);
		System.out.println(root);
	}

	private static TreeNode createBSTree() {
		TreeNode root = new TreeNode(4);
		TreeNode r1 = new TreeNode(1);
		TreeNode r6 = new TreeNode(6);
		TreeNode r0 = new TreeNode(0);
		TreeNode r2 = new TreeNode(2);
		TreeNode r5 = new TreeNode(5);
		TreeNode r7 = new TreeNode(7);
		TreeNode r3 = new TreeNode(3);
		TreeNode r8 = new TreeNode(8);

		root.left = r1;
		root.right = r6;
		r1.left = r0;
		r1.right = r2;
		r2.right = r3;
		r6.left = r5;
		r6.right = r7;
		r7.right = r8;

		return root;
	}

}
