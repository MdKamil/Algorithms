package algorithm.graph;

import java.util.HashSet;

public class NumberOfConnectedComponents {

	private static class Node {
		int id;
		Node parent;
		int rank;

		public Node(int id) {
			this.id = id;
			this.rank = 0;
		}

		private void setParent() {
			this.parent = this;
		}
	}

	public static int countComponents(int n, int[][] edges) {
		Node[] nodes = new Node[n];
		// make-set
		for (int idx = 0; idx <= nodes.length - 1; ++idx) {
			nodes[idx] = new Node(idx);
			nodes[idx].setParent();
		}
		for (int[] edge : edges) {
			Node p1 = findSet(nodes[edge[0]]);
			Node p2 = findSet(nodes[edge[1]]);
			if (p1 != p2) {
				union(p1, p2);
			}
		}
		HashSet<Integer> set = new HashSet<>();
		for (Node node : nodes) {
			findSet(node);
			set.add(node.parent.id);
		}
		return set.size();
	}

	private static Node findSet(Node node) {
		if (node != node.parent) {
			node.parent = findSet(node.parent);
		}
		return node.parent;
	}

	private static void union(Node n1, Node n2) {
		if (n1.rank > n2.rank) {
			n2.parent = n1;
		} else {
			n1.parent = n2;
			if (n1.rank == n2.rank) {
				++n2.rank;
			}
		}
	}
}
