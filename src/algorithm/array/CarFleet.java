package algorithm.array;

import java.util.Arrays;

public class CarFleet {

	private static class Car implements Comparable<Car> {
		Integer speed;
		Integer position;
		float timeToReachDest;

		public Car(Integer speed, Integer position, float timeToReachDest) {
			this.speed = speed;
			this.position = position;
			this.timeToReachDest = timeToReachDest;
		}

		@Override
		public int compareTo(Car other) {
			return -this.position.compareTo(other.position);
		}
	}

	public static int carFleet(int target, int[] position, int[] speed) {
		int carFleets = 0;
		if (position != null && position.length > 0) {
			Car[] cars = new Car[position.length];
			for (int idx = 0; idx <= position.length - 1; ++idx) {
				float timeToReachDest = (float) (target - position[idx]) / speed[idx];
				cars[idx] = new Car(speed[idx], position[idx], timeToReachDest);
			}
			Arrays.sort(cars);
			++carFleets;
			Car firstCarInPrevFleet = cars[0];
			for (int idx = 1; idx <= cars.length - 1; ++idx) {
				if (cars[idx].timeToReachDest > firstCarInPrevFleet.timeToReachDest) {
					firstCarInPrevFleet = cars[idx];
					++carFleets;
				}
			}
		}
		return carFleets;
	}

	public static void main(String[] args) {
		int target = 12;
		int[] position = { 10, 8, 0, 5, 3 };
		int[] speed = { 2, 4, 1, 1, 3 };
		int result = carFleet(target, position, speed);
		System.out.println(result);
	}

}
