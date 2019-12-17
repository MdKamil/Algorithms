package algorithm.two;

public class TwoSumBST {

	private static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int val) {
			this.val = val;
		}
	}

	public static boolean findTarget(TreeNode root, int k) {
		if (root == null) {
			return false;
		}
		int lo = getLowest(root);
		int hi = getHighest(root);
		if (lo == hi) {
			return false;
		}
		while (true) {
			if (lo >= hi) {
				break;
			}
			int sum = lo + hi;
			if (sum > k) {
				--hi;
			} else if (sum < k) {
				++lo;
			} else {
				boolean val1 = checkInBST(root, lo);
				boolean val2 = checkInBST(root, hi);
				if (val1 && val2) {
					return true;
				} else if (!val1 && !val2) {
					++lo;
					--hi;
				} else if (!val1) {
					++lo;
				} else {
					--hi;
				}
			}
		}
		return false;
	}

	private static boolean checkInBST(TreeNode root, int a) {
		while (root != null) {
			if (a == root.val) {
				return true;
			}
			if (a > root.val) {
				root = root.right;
			} else {
				root = root.left;
			}
		}
		return false;
	}

	private static int getLowest(TreeNode root) {
		while (true) {
			if (root.left == null) {
				return root.val;
			} else {
				root = root.left;
			}
		}
	}

	private static int getHighest(TreeNode root) {
		while (true) {
			if (root.right == null) {
				return root.val;
			} else {
				root = root.right;
			}
		}
	}

	public static void main(String[] args) {
		TreeNode root = new TreeNode(0);
		TreeNode n3 = new TreeNode(-3);
		TreeNode n4 = new TreeNode(-4);
		TreeNode p2 = new TreeNode(2);
		TreeNode p1 = new TreeNode(1);

		root.left = n3;
		root.right = p2;
		n3.left = n4;
		p2.left = p1;

		boolean result = findTarget(root, 1);
		System.out.println(result);
	}

}
