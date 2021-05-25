package algorithm.tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

// https://leetcode.com/problems/n-ary-tree-level-order-traversal/
public class NAryLevelOrder {

	private static class Node {
		public int val;
		public List<Node> children;

		public Node() {
		}

		public Node(int val) {
			this.val = val;
		}

		public Node(int val, List<Node> children) {
			this.val = val;
			this.children = children;
		}
	}

	public List<List<Integer>> levelOrderIterative(Node root) {
		List<List<Integer>> result = new ArrayList<>();
		if (root != null) {
			Deque<Node> queue = new ArrayDeque<>();
			int noOfNodesAtCurrLevel = 1;
			int nodesVisitedAtCurrLevel = 0;
			int noOfNodesAtNextLevel = 0;
			List<Integer> nodesAtCurrLevel = new ArrayList<>();
			queue.addLast(root);
			while (!queue.isEmpty()) {
				Node curr = queue.pollFirst();
				nodesAtCurrLevel.add(curr.val);
				queue.addAll(curr.children);
				noOfNodesAtNextLevel += curr.children.size();
				++nodesVisitedAtCurrLevel;
				if (nodesVisitedAtCurrLevel == noOfNodesAtCurrLevel) {
					result.add(nodesAtCurrLevel);
					nodesAtCurrLevel = new ArrayList<>();
					noOfNodesAtCurrLevel = noOfNodesAtNextLevel;
					noOfNodesAtNextLevel = 0;
					nodesVisitedAtCurrLevel = 0;
				}
			}
		}
		return result;
	}

	public List<List<Integer>> levelOrderRecursive(Node root) {
		List<List<Integer>> result = new ArrayList<>();
		dfs(root, 1, result);
		return result;
	}

	private void dfs(Node root, int level, List<List<Integer>> result) {
		if (root == null) {
			return;
		}
		if (level > result.size()) {
			result.add(level - 1, new ArrayList<>());
		}
		result.get(level - 1).add(root.val);
		for (Node child : root.children) {
			dfs(child, level + 1, result);
		}
	}

}
