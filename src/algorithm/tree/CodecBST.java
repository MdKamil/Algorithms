package algorithm.tree;

import java.util.ArrayDeque;
import java.util.Deque;

// https://leetcode.com/problems/serialize-and-deserialize-bst
public class CodecBST {
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
		if (root != null) {
			preOrder(root, builder);
			builder.deleteCharAt(builder.length() - 1);
		}
		builder.append("]");
		return builder.toString();
	}

	private static void preOrder(TreeNode root, StringBuilder builder) {
		if (root == null) {
			return;
		}
		builder.append(root.val);
		builder.append(",");
		preOrder(root.left, builder);
		preOrder(root.right, builder);
	}

	// Decodes your encoded data to tree.
	public TreeNode deserialize(String data) {
		TreeNode root = null;
		if (data.length() > 2) {
			Deque<TreeNode> stack = new ArrayDeque<>();
			String withoutBrackets = data.substring(1, data.length() - 1);
			String[] val = withoutBrackets.split(",");
			root = new TreeNode(Integer.parseInt(val[0]));
			stack.addLast(root);
			for (int idx = 1; idx <= val.length - 1; ++idx) {
				TreeNode prev = stack.peekLast();
				TreeNode curr = new TreeNode(Integer.parseInt(val[idx]));
				if (curr.val < prev.val) {
					prev.left = curr;
					stack.addLast(curr);
				} else {
					while (true) {
						if (curr.val > prev.val) {
							stack.pollLast();
							if (stack.peekLast() != null && curr.val > stack.peekLast().val) {
								prev = stack.peekLast();
							} else {
								prev.right = curr;
								stack.addLast(curr);
								break;
							}
						}
					}
				}
			}
		}
		return root;
	}
}
