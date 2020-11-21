package algorithm.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CourseSchedule2 {

	public static int[] findOrder(int numCourses, int[][] prerequisites) {
		int[] order = new int[0];
		Map<Integer, Integer> rankMap = new HashMap<>();
		boolean canFinish = canFinish(numCourses, prerequisites, rankMap);
		if (canFinish) {
			List<java.util.Map.Entry<Integer, Integer>> rankList = new ArrayList<>(rankMap.entrySet());
			rankList.sort(java.util.Map.Entry.comparingByValue());
			order = new int[numCourses];
			int idx = 0;
			for (java.util.Map.Entry<Integer, Integer> entry : rankList) {
				order[idx] = entry.getKey();
				++idx;
			}
		}
		return order;
	}

	private static boolean canFinish(int numCourses, int[][] prerequisites, Map<Integer, Integer> rankMap) {
		boolean canFinish = true;
		List<List<Integer>> nodeList = new ArrayList<>();
		for (int i = 0; i <= numCourses - 1; ++i) {
			nodeList.add(new ArrayList<>());
		}
		for (int edge = 0; edge <= prerequisites.length - 1; ++edge) {
			nodeList.get(prerequisites[edge][0]).add(prerequisites[edge][1]);
		}
		Set<Integer> visited = new HashSet<>();
		Set<Integer> completed = new HashSet<>();
		for (int i = 0; i <= nodeList.size() - 1; ++i) {
			if (!completed.contains(i)) {
				if (nodeList.get(i).size() == 0) {
					completed.add(i);
					if (!rankMap.containsKey(i)) {
						rankMap.put(i, 0);
					}
					continue;
				}
				visited.add(i);
				canFinish = visit(i, nodeList, visited, completed, rankMap);
				visited.remove(i);
				if (!canFinish) {
					break;
				}
			}
		}
		return canFinish;
	}

	private static boolean visit(int from, List<List<Integer>> nodeList, Set<Integer> visited, Set<Integer> completed,
			Map<Integer, Integer> rankMap) {
		boolean canVisit = true;
		int maxRank = Integer.MIN_VALUE;
		if (nodeList.get(from).size() == 0) {
			rankMap.put(from, 0);
			maxRank = 0;
		} else {
			List<Integer> edges = nodeList.get(from);
			for (int i = 0; i <= edges.size() - 1; ++i) {
				Integer to = edges.get(i);
				if (visited.contains(to)) {
					canVisit = false;
					break;
				}
				if (completed.contains(to)) {
					int rank = rankMap.get(to);
					maxRank = Math.max(maxRank, rank + 1);
					if (i == edges.size() - 1) {
						completed.add(from);
						rankMap.put(from, maxRank);
					}
					continue;
				}
				visited.add(to);
				canVisit = visit(to, nodeList, visited, completed, rankMap);
				if (!canVisit) {
					return canVisit;
				}
				int rank = rankMap.get(to);
				maxRank = Math.max(maxRank, rank + 1);
				visited.remove(to);
				if (i == edges.size() - 1) {
					completed.add(from);
					rankMap.put(from, maxRank);
				}
			}
		}
		return canVisit;
	}

	public static void main(String[] args) {
		int[][] preRequisites = { { 1, 0 }, { 0, 1 } };
		int numCourses = 2;
		int[] order = findOrder(numCourses, preRequisites);
		System.out.println(Arrays.toString(order));
	}
}
