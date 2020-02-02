package algorithm.five;

public class MergeBinaryTree {

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

	public static TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
		TreeNode mergedTree = null;
		if (t1 != null || t2 != null) {
			mergedTree = merge(t1, t2);
		}
		return mergedTree;
	}

	private static TreeNode merge(TreeNode t1, TreeNode t2) {
		if (t1 == null && t2 == null) {
			return null;
		}
		TreeNode root = null;
		if (t1 != null && t2 != null) {
			root = new TreeNode(t1.val + t2.val);
		} else if (t1 != null) {
			root = new TreeNode(t1.val);
		} else {
			root = new TreeNode(t2.val);
		}

		if ((t1 != null && t1.left != null) || (t2 != null && t2.left != null)) {
			if ((t1 != null && t1.left != null) && (t2 != null && t2.left != null)) {
				root.left = merge(t1.left, t2.left);
			} else if ((t1 != null && t1.left != null)) {
				root.left = merge(t1.left, null);
			} else if ((t2 != null && t2.left != null)) {
				root.left = merge(null, t2.left);
			}
		}

		if ((t1 != null && t1.right != null) || (t2 != null && t2.right != null)) {
			if ((t1 != null && t1.right != null) && (t2 != null && t2.right != null)) {
				root.right = merge(t1.right, t2.right);
			} else if ((t1 != null && t1.right != null)) {
				root.right = merge(t1.right, null);
			} else if ((t2 != null && t2.right != null)) {
				root.right = merge(null, t2.right);
			}
		}
		return root;
	}

	public static void main(String[] args) {
		TreeNode t1_5 = new TreeNode(5);
		TreeNode t1_3 = new TreeNode(3);
		t1_3.left = t1_5;
		TreeNode t1_2 = new TreeNode(2);
		TreeNode t1_1 = new TreeNode(1);
		t1_1.left = t1_3;
		t1_1.right = t1_2;

		TreeNode t2_4 = new TreeNode(4);
		TreeNode t2_1 = new TreeNode(1);
		t2_1.right = t2_4;
		TreeNode t2_7 = new TreeNode(7);
		TreeNode t2_3 = new TreeNode(3);
		t2_3.right = t2_7;
		TreeNode t2_2 = new TreeNode(2);
		t2_2.left = t2_1;
		t2_2.right = t2_3;

		TreeNode mergedTree = mergeTrees(null, t2_2);
		System.out.println(mergedTree);
	}
}
