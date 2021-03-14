package algorithm.tree;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

//https://leetcode.com/problems/all-elements-in-two-binary-search-trees/
public class AllElements {
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

	public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
		List<Integer> result = new ArrayList<>();
		Comparator<Integer> c = new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o1.compareTo(o2);
			}
		};
		TreeMap<Integer, Integer> map = new TreeMap<>(c);

		performInorderTraversal(root1, map);
		performInorderTraversal(root2, map);

		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
			Integer key = entry.getKey();
			Integer value = entry.getValue();
			for (int from = 1; from <= value; ++from) {
				result.add(key);
			}
		}

		return result;
	}

	private void performInorderTraversal(TreeNode root, TreeMap<Integer, Integer> map) {
		if (root == null) {
			return;
		}
		Integer val = map.get(root.val);
		if (val == null) {
			map.put(root.val, 1);
		} else {
			map.put(root.val, val + 1);
		}
		performInorderTraversal(root.left, map);
		performInorderTraversal(root.right, map);
	}

}
