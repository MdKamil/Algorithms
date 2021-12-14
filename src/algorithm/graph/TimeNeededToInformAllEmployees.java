package algorithm.graph;

import java.util.ArrayList;
import java.util.List;

public class TimeNeededToInformAllEmployees {

	private static class Node {
		int val;
		List<Node> children;

		public Node(int val) {
			this.val = val;
			children = new ArrayList<>();
		}
	}

	public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
		int minTimeNeeded = 0;
		Node[] nodes = new Node[n];
		for (int idx = 0; idx <= nodes.length - 1; ++idx) {
			if (nodes[idx] == null) {
				nodes[idx] = new Node(idx);
			}
			if (manager[idx] != -1) {
				if (nodes[manager[idx]] == null) {
					nodes[manager[idx]] = new Node(manager[idx]);
				}
				nodes[manager[idx]].children.add(nodes[idx]);
			}
		}
		minTimeNeeded = dfs(nodes[headID], informTime, 0);
		return minTimeNeeded;
	}

	private static int dfs(Node root, int[] informTime, int time) {
		int timeNeeded = time;
		for (Node child : root.children) {
			timeNeeded = Math.max(timeNeeded, dfs(child, informTime, time + informTime[root.val]));
		}
		return timeNeeded;
	}

}
