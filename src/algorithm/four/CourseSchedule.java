package algorithm.four;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CourseSchedule {

	public static boolean canFinish(int numCourses, int[][] prerequisites) {
		boolean canFinish = true;
		if (numCourses >= 2) {
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
						continue;
					}
					visited.add(i);
					canFinish = visit(i, nodeList, visited, completed);
					visited.remove(i);
					if (!canFinish) {
						break;
					}
				}
			}
		}
		return canFinish;
	}

	private static boolean visit(int from, List<List<Integer>> nodeList, Set<Integer> visited, Set<Integer> completed) {
		boolean canVisit = true;
		for (Integer to : nodeList.get(from)) {
			if (visited.contains(to)) {
				canVisit = false;
				break;
			}
			if (completed.contains(to)) {
				continue;
			}
			visited.add(to);
			canVisit = visit(to, nodeList, visited, completed);
			if (!canVisit) {
				return canVisit;
			}
			visited.remove(to);
		}
		if (canVisit) {
			completed.add(from);
		}
		return canVisit;
	}

	public static void main(String[] args) {
		int[][] preRequisites = { { 1, 0 }, { 2, 1 } };
		int numCourses = 3;
		boolean canFinish = canFinish(numCourses, preRequisites);
		System.out.println(canFinish);
	}
}
