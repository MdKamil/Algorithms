package algorithm.tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/most-frequent-subtree-sum/
public class MostFrequentSubTreeSum {
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

	public int[] findFrequentTreeSum(TreeNode root) {
		HashMap<Integer, Integer> map = new HashMap<>();
		dfs(root, map);
		int maxFrequency = 0;
		int maxUnique = 0;
		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
			if (entry.getValue() > maxFrequency) {
				maxFrequency = entry.getValue();
				maxUnique = 0;
				++maxUnique;
			} else if (entry.getValue() == maxFrequency) {
				++maxUnique;
			}
		}
		int[] result = new int[maxUnique];
		int idx = 0;
		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
			if (entry.getValue() == maxFrequency) {
				result[idx] = entry.getKey();
				++idx;
			}
		}
		return result;
	}

	public int[] findFrequentTreeSumV2(TreeNode root) {
		HashMap<Integer, Integer> map = new HashMap<>();
		dfs(root, map);
		int maxFrequency = 0;
		Deque<Integer> stack = new ArrayDeque<>();
		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
			if (entry.getValue() > maxFrequency) {
				maxFrequency = entry.getValue();
				stack.clear();
				stack.addLast(entry.getKey());
			} else if (entry.getValue() == maxFrequency) {
				stack.addLast(entry.getKey());
			}
		}
		int[] result = new int[stack.size()];
		int idx = 0;
		while (!stack.isEmpty()) {
			result[idx] = stack.pollLast();
			++idx;
		}
		return result;
	}

	private int dfs(TreeNode root, HashMap<Integer, Integer> map) {
		if (root == null) {
			return 0;
		}
		int sumLeft = dfs(root.left, map);
		int sumRight = dfs(root.right, map);

		int sumAtRoot = root.val + sumLeft + sumRight;
		Integer value = map.get(sumAtRoot);
		if (value == null) {
			map.put(sumAtRoot, 1);
		} else {
			map.put(sumAtRoot, value + 1);
		}

		return sumAtRoot;
	}
}
