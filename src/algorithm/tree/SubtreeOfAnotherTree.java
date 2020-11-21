package algorithm.tree;

public class SubtreeOfAnotherTree {

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

	public static boolean isSubtree(TreeNode s, TreeNode t) {
		boolean isSubTree = false;
		if (s != null && t != null) {
			isSubTree = doInorderTraversal(s, t);
		}
		return isSubTree;
	}

	private static boolean doInorderTraversal(TreeNode s, TreeNode t) {
		boolean isSubTree = false;
		if (s.val == t.val) {
			isSubTree = compareTree(s, t);
		}
		if (!isSubTree && s.left != null) {
			isSubTree = doInorderTraversal(s.left, t);
		}
		if (!isSubTree && s.right != null) {
			isSubTree = doInorderTraversal(s.right, t);
		}
		return isSubTree;
	}

	private static boolean compareTree(TreeNode s, TreeNode t) {
		boolean isSubTree = true;
		if (s.val == t.val) {
			if (s.left != null && t.left != null) {
				isSubTree = compareTree(s.left, t.left);
			} else if (s.left == null && t.left == null) {
				isSubTree = true;
			} else {
				isSubTree = false;
			}

			if (isSubTree) {
				if (s.right != null && t.right != null) {
					isSubTree = compareTree(s.right, t.right);
				} else if (s.right == null && t.right == null) {
					isSubTree = true;
				} else {
					isSubTree = false;
				}
			}
		} else {
			isSubTree = false;
		}
		return isSubTree;
	}

	public static void main(String[] args) {
		TreeNode s = new TreeNode(3);
		TreeNode s4 = new TreeNode(4);
		TreeNode s5 = new TreeNode(5);
		TreeNode s1 = new TreeNode(1);
		TreeNode s2 = new TreeNode(2);
		TreeNode s0 = new TreeNode(0);

		s.left = s4;
		s.right = s5;
		s4.left = s1;
		s4.right = s2;
		// s2.left = s0;

		TreeNode t = new TreeNode(4);
		TreeNode t1 = new TreeNode(1);
		TreeNode t2 = new TreeNode(2);

		t.left = t1;
		t.right = t2;

		boolean isSubTree = isSubtree(s, t);
		System.out.println(isSubTree);
	}

}
