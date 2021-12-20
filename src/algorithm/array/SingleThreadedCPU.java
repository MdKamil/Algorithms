package algorithm.array;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class SingleThreadedCPU {

	public int[] getOrder(int[][] tasks) {
		int[] order = new int[tasks.length];
		Comparator<int[]> etc = new Comparator<int[]>() {
			public int compare(int[] task1, int[] task2) {
				int val = task1[0] - task2[0];
				if (val != 0) {
					return val;
				} else {
					return task1[1] - task2[1];
				}
			};
		};
		PriorityQueue<int[]> etq = new PriorityQueue<int[]>(etc);
		Comparator<int[]> ptc = new Comparator<int[]>() {
			public int compare(int[] task1, int[] task2) {
				int val = task1[1] - task2[1];
				if (val != 0) {
					return val;
				} else {
					return task1[2] - task2[2];
				}
			};
		};
		PriorityQueue<int[]> ptq = new PriorityQueue<int[]>(ptc);
		for (int idx = 0; idx <= tasks.length - 1; ++idx) {
			etq.add(new int[] { tasks[idx][0], tasks[idx][1], idx });
		}
		int time = etq.peek()[0];
		int idx = 0;
		while (!etq.isEmpty() || !ptq.isEmpty()) {
			while (!etq.isEmpty()) {
				if (time >= etq.peek()[0]) {
					ptq.add(etq.poll());
				} else if (time < etq.peek()[0] && ptq.isEmpty()) {
					time = etq.peek()[0];
				} else {
					break;
				}
			}
			if (!ptq.isEmpty()) {
				int[] task = ptq.poll();
				time += task[1];
				order[idx++] = task[2];
			}
		}
		return order;
	}

	public int[] getOrderV2(int[][] tasks) {
		int[] order = new int[tasks.length];
		Comparator<int[]> ptc = new Comparator<int[]>() {
			public int compare(int[] task1, int[] task2) {
				int val = task1[1] - task2[1];
				if (val != 0) {
					return val;
				} else {
					return task1[2] - task2[2];
				}
			};
		};
		PriorityQueue<int[]> ptq = new PriorityQueue<int[]>(ptc);
		Comparator<int[]> etc = new Comparator<int[]>() {
			public int compare(int[] task1, int[] task2) {
				int val = task1[0] - task2[0];
				if (val != 0) {
					return val;
				} else {
					return task1[1] - task2[1];
				}
			};
		};
		int[][] dup = new int[tasks.length][];
		for (int idx = 0; idx <= tasks.length - 1; ++idx) {
			dup[idx] = new int[] { tasks[idx][0], tasks[idx][1], idx };
		}
		Arrays.sort(dup, etc);
		int time = dup[0][0];
		int idx = 0;
		int taskIdx = 0;
		while (taskIdx <= dup.length - 1 || !ptq.isEmpty()) {
			while (taskIdx <= dup.length - 1) {
				if (time >= dup[taskIdx][0]) {
					ptq.add(dup[taskIdx]);
					++taskIdx;
				} else if (time < dup[taskIdx][0] && ptq.isEmpty()) {
					time = dup[taskIdx][0];
				} else {
					break;
				}
			}
			if (!ptq.isEmpty()) {
				int[] task = ptq.poll();
				time += task[1];
				order[idx++] = task[2];
			}
		}
		return order;
	}

	public static void main(String[] args) {
		int[][] tasks = { { 7, 10 }, { 7, 12 }, { 7, 5 }, { 7, 4 }, { 7, 2 } };
		int[] order = new SingleThreadedCPU().getOrderV2(tasks);
		System.out.println(Arrays.toString(order));
	}

}
