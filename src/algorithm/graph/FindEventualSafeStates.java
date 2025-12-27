package algorithm.graph;

import java.util.*;

public class FindEventualSafeStates {
    public List<Integer> eventualSafeNodes(int[][] graph) {
        Set<Integer> terminalAndSafeNodes = new HashSet<>();
        List<Integer> result = new ArrayList<>();
        for (int node = 0; node <= graph.length - 1; ++node) {
            if (graph[node].length == 0) {
                terminalAndSafeNodes.add(node);
            }
        }
        if (!terminalAndSafeNodes.isEmpty()) {
            Set<Integer> seen = new HashSet<>();
            Set<Integer> unsafeNodes = new HashSet<>();
            for (int node = 0; node <= graph.length - 1; ++node) {
                if (!unsafeNodes.contains(node) && !terminalAndSafeNodes.contains(node)) {
                    dfs(graph, node, seen, terminalAndSafeNodes, unsafeNodes);
                }
            }
            result.addAll(terminalAndSafeNodes);
            Collections.sort(result);
        }
        return result;
    }


    private boolean dfs(int[][] graph, int source, Set<Integer> seen, Set<Integer> terminalAndSafeNodes, Set<Integer> unsafeNodes) {
        if (terminalAndSafeNodes.contains(source)) {
            return false;
        }
        if (unsafeNodes.contains(source) || seen.contains(source)) {
            return true;
        }
        boolean cycleExists = false;
        seen.add(source);
        for (int toNode : graph[source]) {
            boolean partOfCycle = dfs(graph, toNode, seen, terminalAndSafeNodes, unsafeNodes);
            if (partOfCycle) {
                cycleExists = true;
            }
        }
        if (cycleExists) {
            unsafeNodes.add(source);
        } else {
            terminalAndSafeNodes.add(source);
        }
        seen.remove(source);
        return cycleExists;
    }


    public static void main(String[] args) {
        //int[][] graph = {{1, 2}, {2, 3}, {5}, {0}, {5}, {}, {}};
        //int[][] graph = {{1, 2, 3, 4}, {1, 2}, {3, 4}, {0, 4}, {}};
        //int[][] graph = {{1}, {2}, {3}, {4}};
        int[][] graph = {{1}, {2}, {3}, {4, 7}, {0, 5}, {6}, {}, {4, 5}};
        FindEventualSafeStates findEventualSafeStates = new FindEventualSafeStates();
        List<Integer> result = findEventualSafeStates.eventualSafeNodes(graph);
        System.out.println(result);
    }
}
