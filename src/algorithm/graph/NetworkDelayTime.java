package algorithm.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class NetworkDelayTime {

	private static class Node {
		int val;
		List<Edge> edges;
		int minTimeToReach;
		boolean visited;

		public Node(int val) {
			this.val = val;
			this.edges = new ArrayList<>();
			minTimeToReach = Integer.MAX_VALUE;
		}
	}

	private static class Edge {
		int to;
		int time;

		public Edge(int to, int time) {
			this.to = to;
			this.time = time;
		}
	}

	public int networkDelayTime(int[][] times, int n, int k) {
		int timeTaken = -1;
		Node[] nodes = new Node[n + 1];
		for (int val = 1; val <= n; ++val) {
			nodes[val] = new Node(val);
		}
		for (int[] time : times) {
			nodes[time[0]].edges.add(new Edge(time[1], time[2]));
		}
		nodes[k].minTimeToReach = 0;
		nodes[k].visited = true;
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
		pq.add(new int[] { k, nodes[k].minTimeToReach });
		while (!pq.isEmpty()) {
			int[] val = pq.poll();
			Node node = nodes[val[0]];
			for (Edge edge : node.edges) {
				if (val[1] + edge.time < nodes[edge.to].minTimeToReach) {
					nodes[edge.to].minTimeToReach = val[1] + edge.time;
					pq.add(new int[] { edge.to, nodes[edge.to].minTimeToReach });
					nodes[edge.to].visited = true;
				}
			}
		}
		for (int idx = 1; idx <= nodes.length - 1; ++idx) {
			if (!nodes[idx].visited) {
				timeTaken = -1;
				break;
			}
			timeTaken = Math.max(timeTaken, nodes[idx].minTimeToReach);
		}
		return timeTaken;
	}
}
