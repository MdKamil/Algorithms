package algorithm.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;

// https://leetcode.com/problems/clone-graph
public class CloneGraph {
	
	private static class Node {
		public int val;
		public List<Node> neighbors;

		public Node() {
			this.val = 0;
			this.neighbors = new ArrayList<Node>();
		}

		public Node(int val) {
			this.val = val;
			this.neighbors = new ArrayList<Node>();
		}

		public Node(int val, ArrayList<Node> neighbors) {
			this.val = val;
			this.neighbors = neighbors;
		}
	}

	public Node cloneGraph(Node node) {
		Node cloneHead = null;
		if (node != null) {
			cloneHead = new Node(node.val);
			HashMap<Integer, Node> nodeClone = new HashMap<>();
			nodeClone.put(node.val, cloneHead);
			Deque<Node> queue = new ArrayDeque<>();
			queue.addLast(node);
			while (!queue.isEmpty()) {
				Node curr = queue.pollLast();
				Node currClone = nodeClone.get(curr.val);
				for (Node neighbour : curr.neighbors) {
					Node clone = nodeClone.get(neighbour.val);
					if (clone == null) {
						clone = new Node(neighbour.val);
						nodeClone.put(neighbour.val, clone);
						queue.addLast(neighbour);
					}
					currClone.neighbors.add(clone);
				}
			}
		}
		return cloneHead;
	}

}
