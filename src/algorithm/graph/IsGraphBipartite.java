package algorithm.graph;

import java.util.ArrayDeque;
import java.util.Queue;

public class IsGraphBipartite {
	public boolean isBipartite(int[][] graph) {
		boolean isBipartite = true;
		int[] color = new int[graph.length];
		outer: for (int idx = 0; idx <= graph.length - 1; ++idx) {
			if (color[idx] == 0) {
				Queue<Integer> queue = new ArrayDeque<>();
				queue.add(idx);
				color[idx] = 1;
				while (!queue.isEmpty()) {
					int node = queue.poll();
					for (int adjNode : graph[node]) {
						if (color[adjNode] == 0) {
							queue.add(adjNode);
							color[adjNode] = (color[node] == 1 ? 2 : 1);
						} else if (color[adjNode] == color[node]) {
							isBipartite = false;
							break outer;
						}
					}
				}
			}
		}
		return isBipartite;
	}
}
