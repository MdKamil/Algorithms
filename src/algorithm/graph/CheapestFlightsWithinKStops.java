package algorithm.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class CheapestFlightsWithinKStops {

	private static class Edge {
		Node to;
		int price;

		public Edge(Node to, int price) {
			this.to = to;
			this.price = price;
		}

		@Override
		public String toString() {
			return "To: " + to + " : " + price;
		}
	}

	private static class Node implements Comparable<Node> {
		int nodeID;
		Integer costToReach;
		Integer hopToReach;
		List<Edge> edges;

		public Node(int nodeID) {
			this.nodeID = nodeID;
			edges = new ArrayList<Edge>();
		}

		@Override
		public String toString() {
			return Integer.toString(nodeID);
		}

		@Override
		public int compareTo(Node o) {
			return this.costToReach.compareTo(o.costToReach);
		}
	}

	public static int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
		int cheapestFareWithKStop = 0;
		if (n > 1 && flights.length > 1) {
			List<Node> nodeList = new ArrayList<Node>();
			for (int nodeNo = 0; nodeNo < n; ++nodeNo) {
				nodeList.add(new Node(nodeNo));
			}
			for (int[] flight : flights) {
				nodeList.get(flight[0]).edges.add(new Edge(nodeList.get(flight[1]), flight[2]));
			}
			TreeSet<Node> pq = new TreeSet<Node>();
			pq.add(nodeList.get(0));
			while (true) {
				Node currNode = pq.pollFirst();
				for (Edge edge : currNode.edges) {
					pq.add(nodeList.get(edge.to.nodeID));
				}
			}
		}
		return cheapestFareWithKStop;
	}
}
