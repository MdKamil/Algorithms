package algorithm.tree;

public class ConvertSortedArrayToBST {

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

	public static TreeNode sortedArrayToBST(int[] nums) {
		TreeNode root = null;
		if (nums != null && nums.length > 0) {
			int left = 0;
			int right = nums.length - 1;
			root = formBSTFromArray(nums, left, right);
		}
		return root;
	}

	private static TreeNode formBSTFromArray(int[] nums, int left, int right) {
		TreeNode root = null;
		if (left <= right) {
			int mid = (left + right) / 2;
			root = new TreeNode(nums[mid]);
			root.left = formBSTFromArray(nums, left, mid - 1);
			root.right = formBSTFromArray(nums, mid + 1, right);
		}
		return root;
	}

	public static void main(String[] args) {
		int[] nums = { -10, -3, 0, 5, 9 };
		TreeNode root = sortedArrayToBST(nums);
		System.out.println(root);
	}

}
