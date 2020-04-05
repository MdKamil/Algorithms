package algorithm.eight;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class WordLadderOptimized {

	public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
		int length = 0;
		HashSet<String> dict = new HashSet<String>(wordList);
		// Distance of every node from the start node
		HashMap<String, Integer> distance = new HashMap<String, Integer>();
		length = bfs(beginWord, endWord, dict, distance);
		return length;
	}

	private static int bfs(String start, String end, Set<String> dict, HashMap<String, Integer> distance) {
		int shortestDistance = Integer.MAX_VALUE;

		Queue<String> queue = new LinkedList<String>();
		queue.offer(start);
		distance.put(start, 0);

		while (!queue.isEmpty()) {
			int count = queue.size();
			boolean foundEnd = false;
			for (int i = 0; i < count; i++) {
				String cur = queue.poll();
				int curDistance = distance.get(cur);
				ArrayList<String> neighbors = getNeighbors(cur, dict);

				for (String neighbor : neighbors) {
					if (!distance.containsKey(neighbor)) {// Check if visited
						distance.put(neighbor, curDistance + 1);
						if (end.equals(neighbor)) {// Found the shortest path
							foundEnd = true;
							shortestDistance = Math.min(shortestDistance, distance.get(neighbor));
						} else {
							queue.offer(neighbor);
						}
					}
				}
			}
			if (foundEnd) {
				break;
			}
		}
		if (shortestDistance == Integer.MAX_VALUE) {
			shortestDistance = 0;
		} else {
			shortestDistance += 1;
		}
		return shortestDistance;
	}

	// Find all next level nodes.
	private static ArrayList<String> getNeighbors(String node, Set<String> dict) {
		ArrayList<String> res = new ArrayList<String>();
		char chs[] = node.toCharArray();

		for (char ch = 'a'; ch <= 'z'; ch++) {
			for (int i = 0; i < chs.length; i++) {
				if (chs[i] == ch)
					continue;
				char old_ch = chs[i];
				chs[i] = ch;
				if (dict.contains(String.valueOf(chs))) {
					res.add(String.valueOf(chs));
				}
				chs[i] = old_ch;
			}

		}
		return res;
	}

	public static void main(String[] args) {
		String beginWord = "hit";
		String endWord = "cog";
		List<String> wordList = Arrays.asList("hot", "dot", "dog", "lot", "log", "cog");
		int shortestDistance = ladderLength(beginWord, endWord, wordList);
		System.out.println(shortestDistance);
	}

}
