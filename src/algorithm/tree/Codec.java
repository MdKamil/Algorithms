package algorithm.tree;

import java.util.ArrayDeque;
import java.util.Deque;

//https://leetcode.com/problems/serialize-and-deserialize-binary-tree
public class Codec {
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

	// Encodes a tree to a single string.
	public String serialize(TreeNode root) {
		StringBuilder builder = new StringBuilder();
		builder.append("[");
		Deque<TreeNode> queue = new ArrayDeque<>();
		if (root != null) {
			String nil = "null";
			builder.append(root.val);
			builder.append(",");
			queue.addLast(root);
			while (!queue.isEmpty()) {
				TreeNode curr = queue.pollFirst();
				if (curr.left == null) {
					builder.append(nil);
					builder.append(",");
				} else {
					builder.append(curr.left.val);
					builder.append(",");
					queue.addLast(curr.left);
				}

				if (curr.right == null) {
					builder.append(nil);
					builder.append(",");
				} else {
					builder.append(curr.right.val);
					builder.append(",");
					queue.addLast(curr.right);
				}
			}
			builder.deleteCharAt(builder.length() - 1);
		}
		builder.append("]");
		return builder.toString();
	}

	// Decodes your encoded data to tree.
	public TreeNode deserialize(String data) {
		TreeNode root = null;
		if (data.length() > 2) {
			String withoutBrackets = data.substring(1, data.length() - 1);
			String[] val = withoutBrackets.split(",");
			root = new TreeNode(Integer.parseInt(val[0]));
			Deque<TreeNode> queue = new ArrayDeque<>();
			queue.addLast(root);
			char pos = 'L';
			for (int idx = 1; idx <= val.length - 1; ++idx) {
				TreeNode currRoot = queue.peekFirst();
				TreeNode curr = null;
				if (!val[idx].equals("null")) {
					curr = new TreeNode(Integer.parseInt(val[idx]));
					queue.addLast(curr);
				}
				if (pos == 'L') {
					currRoot.left = curr;
					pos = 'R';
				} else {
					currRoot.right = curr;
					pos = 'L';
					queue.pollFirst();
				}
			}
		}
		return root;
	}
}
