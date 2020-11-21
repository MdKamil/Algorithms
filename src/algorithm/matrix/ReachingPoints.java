package algorithm.matrix;

import java.util.ArrayDeque;
import java.util.Queue;

public class ReachingPoints {

	public static boolean reachingPoints(int sx, int sy, int tx, int ty) {
		boolean canReach = false;
		if (tx < sx || ty < sy) {
			canReach = false;
		} else {
			Queue<Integer> rowQueue = new ArrayDeque<Integer>();
			Queue<Integer> colQueue = new ArrayDeque<Integer>();
			rowQueue.add(sx);
			colQueue.add(sy);
			while (!(rowQueue.isEmpty() && colQueue.isEmpty())) {
				int currRow = rowQueue.poll();
				int currCol = colQueue.poll();
				if (currRow == tx && currCol == ty) {
					canReach = true;
					break;
				}

				if (currRow + currCol > 1000000000) {
					continue;
				}
				if (currRow <= tx && currRow + currCol <= ty) {
					rowQueue.add(currRow);
					colQueue.add(currRow + currCol);
				}

				if (currRow + currCol <= tx && currCol <= ty) {
					rowQueue.add(currRow + currCol);
					colQueue.add(currCol);
				}
			}
		}
		return canReach;
	}

	public static void main(String[] args) {
		int sx = 3;
		int sy = 3;
		int tx = 12;
		int ty = 9;
		boolean canReach = reachingPoints(sx, sy, tx, ty);
		System.out.println(canReach);
	}

}
