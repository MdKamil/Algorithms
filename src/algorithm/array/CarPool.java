package algorithm.array;

import java.util.Arrays;

public class CarPool {

	public boolean carPooling(int[][] trips, int capacity) {
		Arrays.sort(trips, (a, b) -> a[1] - b[1]);
		int[] to = new int[1001];
		int passengers = 0;
		boolean possible = true;
		int loc = 0;
		int trip = 0;
		while (loc <= 1000 && trip <= trips.length - 1) {
			if (to[loc] > 0) {
				passengers -= to[loc];
				to[loc] = 0;
			}
			if (loc == trips[trip][1]) {
				if (passengers + trips[trip][0] <= capacity) {
					passengers += trips[trip][0];
					to[trips[trip][2]] += trips[trip][0];
					++trip;
				} else {
					possible = false;
					break;
				}
			} else {
				++loc;
			}
		}
		return possible;
	}

	public static void main(String[] args) {
		int capacity = 12;
		int[][] trips = { { 4, 1, 3 }, { 8, 2, 3 }, { 1, 3, 6 }, { 8, 4, 6 }, { 4, 4, 8 } };
		System.out.println(new CarPool().carPooling(trips, capacity));
	}
}
