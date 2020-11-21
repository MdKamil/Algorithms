package algorithm.matrix;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class FriendCircles {

	public static int findCircleNumV1(int[][] M) {
		int noOfFriendCircles = 0;
		Map<Integer, Set<Integer>> studentFriendsList = new HashMap<>();
		int[] parent = new int[M.length];
		for (int student = 0; student <= M.length - 1; ++student) {
			Set<Integer> friends = new HashSet<>();
			friends.add(student);
			studentFriendsList.put(student, friends);
			parent[student] = student;
		}
		for (int student = 0; student <= M.length - 1; ++student) {
			for (int otherStudent = 0; otherStudent <= M[student].length - 1; ++otherStudent) {
				if (student != otherStudent) {
					if (M[student][otherStudent] == 1) {
						int studentParent = parent[student];
						int otherStudentParent = parent[otherStudent];
						if (studentParent != otherStudentParent) {
							Set<Integer> studentFriendList = studentFriendsList.get(studentParent);
							Set<Integer> otherStudentFriendList = studentFriendsList.get(otherStudentParent);
							for (Integer friend : otherStudentFriendList) {
								studentFriendList.add(friend);
								parent[friend] = studentParent;
							}
							studentFriendsList.remove(otherStudentParent);
						}
					}
				}
			}
		}
		noOfFriendCircles = studentFriendsList.size();
		return noOfFriendCircles;
	}

	public static void main(String[] args) {
		int[][] M = { { 1, 0, 0, 1 }, { 0, 1, 1, 0 }, { 0, 1, 1, 1 }, { 1, 0, 1, 1 } };
		int noOfFriendCircles = findCircleNumV1(M);
		System.out.println(noOfFriendCircles);
	}

}
