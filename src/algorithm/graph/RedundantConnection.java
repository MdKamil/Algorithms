package algorithm.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

//https://leetcode.com/problems/serialize-and-deserialize-binary-tree
public class RedundantConnection {

	private static class Node {
		int val;
		List<Edge> edges;
		boolean visited;

		public Node(int val) {
			this.val = val;
			this.visited = false;
			this.edges = new ArrayList<>();
		}
	}

	private static class Edge {
		Node to;
		int rank;

		public Edge(int rank, Node to) {
			this.rank = rank;
			this.to = to;
		}
	}

	public int[] findRedundantConnection(int[][] edges) {
		int[] edgeToRemove = null;
		Node[] nodes = new Node[edges.length + 1];
		for (int idx = 1; idx <= edges.length; ++idx) {
			nodes[idx] = new Node(idx);
		}
		for (int idx = 0; idx <= edges.length - 1; ++idx) {
			Edge edge = new Edge(idx, nodes[edges[idx][1]]);
			nodes[edges[idx][0]].edges.add(edge);

			edge = new Edge(idx, nodes[edges[idx][0]]);
			nodes[edges[idx][1]].edges.add(edge);
		}
		Deque<int[]> queue = new ArrayDeque<int[]>();
		edgeToRemove = dfs(nodes[1], null, 0, queue);
		Arrays.sort(edgeToRemove);
		return edgeToRemove;
	}

	private int[] dfs(Node child, Node parent, int rank, Deque<int[]> queue) {
		child.visited = true;
		int[] edgeToRemove = null;
		for (Edge edge : child.edges) {
			if (parent != null && edge.to.val == parent.val) {
				continue;
			} else if (edge.to.visited) {
				// cycle detected
				queue.addLast(new int[] { child.val, edge.to.val, edge.rank });
				break;
			} else {
				edgeToRemove = dfs(edge.to, child, edge.rank, queue);
				if (edgeToRemove == null) {
					int[] peek = queue.peekFirst();
					if (peek != null) {
						if (child.val == peek[1]) {
							// start of the cycle
							int[] edgeWithHighRank = { child.val, edge.to.val, edge.rank };
							while (!queue.isEmpty()) {
								int[] curr = queue.pollFirst();
								if (curr[2] > edgeWithHighRank[2]) {
									edgeWithHighRank = curr;
								}
							}
							edgeToRemove = new int[] { edgeWithHighRank[0], edgeWithHighRank[1] };
							break;
						} else {
							queue.addLast(new int[] { child.val, edge.to.val, edge.rank });
							break;
						}
					}
				} else {
					// we found out the edge that needs to be removed. Return it.
					break;
				}
			}
		}
		child.visited = false;
		return edgeToRemove;
	}

	public static void main(String[] args) {
		int[][] edges = { { 16, 25 }, { 7, 9 }, { 3, 24 }, { 10, 20 }, { 15, 24 }, { 2, 8 }, { 19, 21 }, { 2, 15 },
				{ 13, 20 }, { 5, 21 }, { 7, 11 }, { 6, 23 }, { 7, 16 }, { 1, 8 }, { 17, 20 }, { 4, 19 }, { 11, 22 },
				{ 5, 11 }, { 1, 16 }, { 14, 20 }, { 1, 4 }, { 22, 23 }, { 12, 20 }, { 15, 18 }, { 12, 16 } };
		RedundantConnection connection = new RedundantConnection();
		int[] edge = connection.findRedundantConnection(edges);
		System.out.println(edge[0] + " " + edge[1]);
	}

}
