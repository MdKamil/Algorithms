package algorithm.graph;

import java.util.ArrayList;
import java.util.List;

public class ParallelCourses {

	private static class Course {
		int courseId;
		List<Course> preRequisites;
		boolean visited;
		int preReqCount = 0;

		public Course(int courseId) {
			this.courseId = courseId;
			this.preRequisites = new ArrayList<>();
		}
	}

	public int minimumSemesters(int n, int[][] relations) {
		Course[] courses = new Course[n + 1];
		for (int[] relation : relations) {
			if (courses[relation[0]] == null) {
				courses[relation[0]] = new Course(relation[0]);
			}
			if (courses[relation[1]] == null) {
				courses[relation[1]] = new Course(relation[1]);
			}
			courses[relation[1]].preRequisites.add(courses[relation[0]]);
		}
		int minSemesters = -1;
		for (int idx = 1; idx <= courses.length - 1; ++idx) {
			if (courses[idx] == null) {
				continue;
			} else if (dfs(courses[idx])) {
				minSemesters = Math.max(minSemesters, courses[idx].preReqCount);
			} else {
				minSemesters = -1;
				break;
			}
		}
		return minSemesters;
	}

	private static boolean dfs(Course course) {
		boolean canFinish = true;
		course.visited = true;
		if (course.preRequisites.size() != 0) {
			for (Course preReqCourse : course.preRequisites) {
				if (preReqCourse.visited) {
					canFinish = false;
					break;
				} else {
					if (preReqCourse.preReqCount == 0) {
						canFinish = dfs(preReqCourse);
					}
					if (canFinish) {
						course.preReqCount = Math.max(preReqCourse.preReqCount + 1, course.preReqCount);
					} else {
						break;
					}
				}
			}
		} else {
			course.preReqCount = 1;
		}
		course.visited = false;
		return canFinish;
	}
}
