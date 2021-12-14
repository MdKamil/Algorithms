package algorithm.graph;

import java.util.*;

public class CheapestFlightsWithinKStops {

	private static class Node {
		int id;
		List<Edge> edges;
		int minTimeToReach;

		public Node(int id) {
			this.id = id;
			edges = new ArrayList<>();
			minTimeToReach = Integer.MAX_VALUE;
		}
	}

	private static class Edge {
		Node to;
		int price;

		public Edge(Node to, int price) {
			this.to = to;
			this.price = price;
		}
	}

	public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
		int cheapestPrice = Integer.MAX_VALUE;
		if (flights.length > 0) {
			Node[] cities = new Node[n];
			int[][] dp = new int[n][K + 1];
			for (int city = 0; city <= n - 1; ++city) {
				cities[city] = new Node(city);
				Arrays.fill(dp[city], Integer.MAX_VALUE);
			}
			for (int[] flight : flights) {
				cities[flight[0]].edges.add(new Edge(cities[flight[1]], flight[2]));
			}
			Queue<Node> queue = new ArrayDeque<>();
			cities[src].minTimeToReach = 0;
			queue.add(cities[src]);
			int hop = 0;
			while (!queue.isEmpty() && hop <= K) {
				int nodes = queue.size();
				for (int node = 1; node <= nodes; ++node) {
					Node curr = queue.poll();
					for (Edge edge : curr.edges) {
						if (hop == 0 || dp[curr.id][hop - 1] + edge.price < edge.to.minTimeToReach) {
							int val = (hop == 0 ? edge.price : dp[curr.id][hop - 1] + edge.price);
							if (val < dp[edge.to.id][hop]) {
								dp[edge.to.id][hop] = val;
								edge.to.minTimeToReach = (val < edge.to.minTimeToReach ? val : edge.to.minTimeToReach);
								if (edge.to.id == dst) {
									cheapestPrice = Math.min(cheapestPrice, edge.to.minTimeToReach);
								} else {
									queue.add(edge.to);
								}
							}
						}
					}
				}
				++hop;
			}
		}
		return cheapestPrice == Integer.MAX_VALUE ? -1 : cheapestPrice;
	}

	public int findCheapestPriceV2(int n, int[][] flights, int src, int dst, int K) {
		int cheapestPrice = Integer.MAX_VALUE;
		if (flights.length > 0) {
			Node[] cities = new Node[n];
			int[][] dp = new int[n][K + 1];
			for (int city = 0; city <= n - 1; ++city) {
				cities[city] = new Node(city);
				Arrays.fill(dp[city], Integer.MAX_VALUE);
			}
			for (int[] flight : flights) {
				cities[flight[0]].edges.add(new Edge(cities[flight[1]], flight[2]));
			}
			// array format = node.id, hop-count, minTimeToReach
			Queue<int[]> queue = new ArrayDeque<>();
			cities[src].minTimeToReach = 0;
			queue.add(new int[] { src, 0, 0 });
			int hop = 0;
			while (!queue.isEmpty() && hop <= K) {
				int nodes = queue.size();
				for (int node = 1; node <= nodes; ++node) {
					int[] curr = queue.poll();
					for (Edge edge : cities[curr[0]].edges) {
						if (hop == 0 || (curr[2] == dp[cities[curr[0]].id][curr[1]]
								&& dp[cities[curr[0]].id][hop - 1] + edge.price < edge.to.minTimeToReach)) {
							int val = (hop == 0 ? edge.price : dp[cities[curr[0]].id][hop - 1] + edge.price);
							if (val < dp[edge.to.id][hop]) {
								dp[edge.to.id][hop] = val;
								edge.to.minTimeToReach = (val < edge.to.minTimeToReach ? val : edge.to.minTimeToReach);
								if (edge.to.id == dst) {
									cheapestPrice = Math.min(cheapestPrice, edge.to.minTimeToReach);
								} else {
									queue.add(new int[] { edge.to.id, hop, val });
								}
							}
						}
					}
				}
				++hop;
			}
		}
		return cheapestPrice == Integer.MAX_VALUE ? -1 : cheapestPrice;
	}

	public int findCheapestPriceV3(int n, int[][] flights, int src, int dst, int K) {
		int cheapestPrice = Integer.MAX_VALUE;
		if (flights.length > 0) {
			Node[] cities = new Node[n];
			int[][] dp = new int[n][K + 1];
			for (int city = 0; city <= n - 1; ++city) {
				cities[city] = new Node(city);
				Arrays.fill(dp[city], Integer.MAX_VALUE);
			}
			for (int[] flight : flights) {
				cities[flight[0]].edges.add(new Edge(cities[flight[1]], flight[2]));
			}
			// array format = node.id, hop-count, minTimeToReach
			Queue<int[]> queue = new ArrayDeque<>();
			cities[src].minTimeToReach = 0;
			queue.add(new int[] { src, 0, 0 });
			int hop = 0;
			while (!queue.isEmpty() && hop <= K) {
				int nodes = queue.size();
				for (int node = 1; node <= nodes; ++node) {
					int[] curr = queue.poll();
					for (Edge edge : cities[curr[0]].edges) {
						if (hop == 0 || (curr[2] == dp[cities[curr[0]].id][curr[1]]
								&& dp[cities[curr[0]].id][hop - 1] + edge.price < edge.to.minTimeToReach)) {
							int val = (hop == 0 ? edge.price : dp[cities[curr[0]].id][hop - 1] + edge.price);
							if (val < dp[edge.to.id][hop]) {
								dp[edge.to.id][hop] = val;
								if (val < edge.to.minTimeToReach) {
									edge.to.minTimeToReach = val;
									queue.add(new int[] { edge.to.id, hop, val });
								}
							}
						}
					}
				}
				++hop;
			}
			cheapestPrice = cities[dst].minTimeToReach == Integer.MAX_VALUE ? -1 : cities[dst].minTimeToReach;
		}
		return cheapestPrice;
	}
}
