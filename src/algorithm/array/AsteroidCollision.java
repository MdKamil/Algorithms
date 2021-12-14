package algorithm.array;

import java.util.ArrayDeque;
import java.util.Deque;

public class AsteroidCollision {

	public int[] asteroidCollision(int[] asteroids) {
		Deque<Integer> pDeque = new ArrayDeque<>();
		Deque<Integer> nDeque = new ArrayDeque<>();
		for (int idx = 0; idx <= asteroids.length - 1; ++idx) {
			if (asteroids[idx] > 0) {
				pDeque.addLast(idx);
			} else {
				nDeque.addLast(idx);
				checkCollision(asteroids, pDeque, nDeque);
			}
		}
		int[] result = new int[pDeque.size() + nDeque.size()];
		int idx = 0;
		while (!nDeque.isEmpty()) {
			result[idx] = asteroids[nDeque.pollFirst()];
			++idx;
		}
		while (!pDeque.isEmpty()) {
			result[idx] = asteroids[pDeque.pollFirst()];
			++idx;
		}
		return result;
	}

	private void checkCollision(int[] asteroids, Deque<Integer> pDeque, Deque<Integer> nDeque) {
		while (!pDeque.isEmpty()) {
			int pAsteroid = Math.abs(asteroids[pDeque.peekLast()]);
			int nAsteroid = Math.abs(asteroids[nDeque.peekLast()]);
			if (pAsteroid == nAsteroid) {
				pDeque.pollLast();
				nDeque.pollLast();
				break;
			} else if (pAsteroid > nAsteroid) {
				nDeque.pollLast();
				break;
			} else {
				pDeque.pollLast();
			}
		}
	}

}
