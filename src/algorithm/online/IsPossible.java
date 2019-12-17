package algorithm.online;

import java.util.ArrayDeque;
import java.util.Queue;

public class IsPossible {
	public static String isPossible(int a, int b, int c, int d) {
		boolean isPossible = false;
		String result = "No";
		if (d < b) {
			result = "No";
		} else {
			Queue<Integer> rowQueue = new ArrayDeque<Integer>();
			Queue<Integer> colQueue = new ArrayDeque<Integer>();
			rowQueue.add(a);
			colQueue.add(b);
			while (!(rowQueue.isEmpty() && colQueue.isEmpty())) {
				int currRow = rowQueue.poll();
				int currCol = colQueue.poll();
				if (currRow == c && currCol == d) {
					isPossible = true;
					break;
				}
				if (currRow + currCol > 1000) {
					break;
				}
				rowQueue.add(currRow);
				colQueue.add(currRow + currCol);

				rowQueue.add(currRow + currCol);
				colQueue.add(currCol);
			}
			if (isPossible) {
				result = "Yes";
			}
		}
		return result;
	}

	public static void main(String[] args) {
		int a = 1;
		int b = 3;
		int c = 4;
		int d = 3;
		String result = isPossible(a, b, c, d);
		System.out.println(result);
	}

}
