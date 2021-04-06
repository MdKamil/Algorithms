package algorithm.tree;

// https://leetcode.com/problems/distribute-coins-in-binary-tree/
public class DistributeCoinsBinaryTree {
	private static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int val) {
			this.val = val;
		}

		@Override
		public String toString() {
			return Integer.toString(val);
		}

	}

	private static class MutableInteger {
		int moves;
	}

	public int distributeCoins(TreeNode root) {
		MutableInteger mutInt = new MutableInteger();
		dfs(root, mutInt);
		return mutInt.moves;
	}

	private int dfs(TreeNode root, MutableInteger mutInt) {
		if (root == null) {
			return 0;
		}
		int leftVal = dfs(root.left, mutInt);
		int rightVal = dfs(root.right, mutInt);
		root.val += (leftVal + rightVal);
		int coinsToReturn = 0;
		if (root.val >= 1 && root.left != null && root.left.val == 0) {
			root.val = distribute(root.left, 1, root.val, mutInt);
		}
		if (root.val >= 1 && root.right != null && root.right.val == 0) {
			root.val = distribute(root.right, 1, root.val, mutInt);
		}
		if (root.val > 1) {
			coinsToReturn = root.val - 1;
			mutInt.moves += coinsToReturn;
			root.val = 1;
		}
		return coinsToReturn;
	}

	private int distribute(TreeNode root, int dist, int coins, MutableInteger mutInt) {
		if (root == null || root.val == 1) {
			return coins;
		}
		coins = distribute(root.left, dist + 1, coins, mutInt);
		coins = distribute(root.right, dist + 1, coins, mutInt);
		if (coins != 0 && root.val == 0) {
			root.val = 1;
			mutInt.moves += dist;
			--coins;
		}
		return coins;
	}

}