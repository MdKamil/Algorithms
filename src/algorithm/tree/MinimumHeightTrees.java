package algorithm.tree;

import java.util.*;

public class MinimumHeightTrees {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        HashMap<Integer, HashMap<Integer, Integer>> adjacentNodes = new HashMap<>();
        for (int[] edge : edges) {
            if (!adjacentNodes.containsKey(edge[0])) {
                HashMap<Integer, Integer> edgeHeight = new HashMap<>();
                edgeHeight.put(edge[1], null);
                adjacentNodes.put(edge[0], edgeHeight);
            } else {
                HashMap<Integer, Integer> edgeHeight = adjacentNodes.get(edge[0]);
                edgeHeight.put(edge[1], null);
            }

            if (!adjacentNodes.containsKey(edge[1])) {
                HashMap<Integer, Integer> edgeHeight = new HashMap<>();
                edgeHeight.put(edge[0], null);
                adjacentNodes.put(edge[1], edgeHeight);
            } else {
                HashMap<Integer, Integer> edgeHeight = adjacentNodes.get(edge[1]);
                edgeHeight.put(edge[0], null);
            }
        }
        Set<Integer> visited = new HashSet<>();
        HashMap<Integer, Integer> heightFromNode = new HashMap<>();
        int minHeight = Integer.MAX_VALUE;
        for (int node = 0; node < n; ++node) {
            int maxHeightFromCurrentNode = dfs(node, adjacentNodes, visited);
            minHeight = Math.min(maxHeightFromCurrentNode, minHeight);
            heightFromNode.put(node, maxHeightFromCurrentNode);
        }
        List<Integer> result = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : heightFromNode.entrySet()) {
            if (entry.getValue() == minHeight) {
                result.add(entry.getKey());
            }
        }
        return result;
    }

    private int dfs(int fromNode, HashMap<Integer, HashMap<Integer, Integer>> adjacentNodes, Set<Integer> visited) {
        HashMap<Integer, Integer> heightFromAdjacentNodes = adjacentNodes.get(fromNode);
        int maxHeightFromAdjacentNodes = 0;
        if (Objects.isNull(heightFromAdjacentNodes)) {
            return maxHeightFromAdjacentNodes;
        }
        visited.add(fromNode);
        for (Map.Entry<Integer, Integer> edge : heightFromAdjacentNodes.entrySet()) {
            if (visited.contains(edge.getKey())) {
                continue;
            }
            if (Objects.isNull(edge.getValue())) {
                int maxHeightFromAdjacentNode = dfs(edge.getKey(), adjacentNodes, visited) + 1;
                heightFromAdjacentNodes.put(edge.getKey(), maxHeightFromAdjacentNode);
                maxHeightFromAdjacentNodes = Math.max(maxHeightFromAdjacentNodes, maxHeightFromAdjacentNode);
            } else {
                maxHeightFromAdjacentNodes = Math.max(maxHeightFromAdjacentNodes, edge.getValue());
            }
        }
        visited.remove(fromNode);
        return maxHeightFromAdjacentNodes;
    }

    public static void main(String[] args) {
        int n = 9;
        int[][] edges = {{0, 5}, {5, 2}, {2, 8}, {5, 3}, {1, 4}, {5, 1}, {1, 7}, {7, 6}};
//        int[][] edges = {{3,0}, {3,1}, {3,2}, {3,4}, {5,4}};
//        int[][] edges = {};
        MinimumHeightTrees minimumHeightTrees = new MinimumHeightTrees();
        List<Integer> result = minimumHeightTrees.findMinHeightTrees(n, edges);
        System.out.println(result);
    }
}
