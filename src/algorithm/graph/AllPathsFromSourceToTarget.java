package algorithm.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AllPathsFromSourceToTarget {
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> pathLists = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        path.add(0);
        int startNode = 0;
        int endNode = graph.length - 1;
        dfs(graph, startNode, endNode, path, pathLists);
        return pathLists;
    }

    private void dfs(int[][] graph, int startNode, int endNode, List<Integer> path, List<List<Integer>> pathLists) {
        if (Objects.equals(startNode, endNode)) {
            pathLists.add(new ArrayList<>(path));
            return;
        }
        for (int toNode : graph[startNode]) {
            path.add(toNode);
            dfs(graph, toNode, endNode, path, pathLists);
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[][] graph = {{4,3,1}, {3,2,4}, {3}, {4}, {}};
        List<List<Integer>> pathLists = new AllPathsFromSourceToTarget().allPathsSourceTarget(graph);
        for (List<Integer> path : pathLists) {
            System.out.println(path);
        }
    }
}
