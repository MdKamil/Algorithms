package algorithm.tree;

public class CountUnivalueSubtree {
	private class TreeNode {
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

	private static class Counter {
		int count;
	}

	public static int countUnivalSubtrees(TreeNode root) {
		int uniValueSubTreeCount = 0;
		if (root != null) {
			Counter counter = new Counter();
			doInOrderTraversal(root, counter);
			uniValueSubTreeCount = counter.count;
		}
		return uniValueSubTreeCount;
	}

	private static boolean doInOrderTraversal(TreeNode root, Counter counter) {
		if (root.left == null && root.right == null) {
			++counter.count;
			return true;
		}
		boolean rootUnique = false;
		boolean leftUnique = false;
		boolean rightUnique = false;
		if (root.left != null) {
			leftUnique = doInOrderTraversal(root.left, counter);
		}
		if (root.right != null) {
			rightUnique = doInOrderTraversal(root.right, counter);
		}
		if (leftUnique && rightUnique) {
			if (root.val == root.left.val && root.val == root.right.val) {
				++counter.count;
				rootUnique = true;
			}
		} else if (leftUnique) {
			if (root.val == root.left.val) {
				if (root.right == null) {
					rootUnique = true;
					++counter.count;
				}
			}
		} else if (rightUnique) {
			if (root.val == root.right.val) {
				if (root.left == null) {
					rootUnique = true;
					++counter.count;
				}
			}
		}
		return rootUnique;
	}

}
