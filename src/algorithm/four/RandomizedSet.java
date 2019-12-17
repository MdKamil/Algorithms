package algorithm.four;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

// Insert, Delete and get Random 
public class RandomizedSet {

	private Set<Integer> set;
	private Map<Integer, Integer> orderMap;
	private Map<Integer, Integer> keyIdxMap;
	private int size = 0;
	private Random random;

	public RandomizedSet() {
		set = new HashSet<>();
		orderMap = new LinkedHashMap<>();
		keyIdxMap = new HashMap<>();
		random = new Random();
	}

	public boolean insert(int val) {
		boolean isPresent = false;
		if (!set.contains(val)) {
			isPresent = true;
			set.add(val);
			++size;
			orderMap.put(size, val);
			keyIdxMap.put(val, size);
		}
		return isPresent;
	}

	public boolean remove(int val) {
		boolean isPresent = false;
		if (set.contains(val)) {
			isPresent = true;
			set.remove(val);
			if (size == 1) {
				orderMap.remove(size);
				keyIdxMap.remove(val);
			} else {
				Integer valIdx = keyIdxMap.get(val);
				Integer lastVal = orderMap.get(size);
				orderMap.put(valIdx, lastVal);
				orderMap.remove(size);
				keyIdxMap.remove(val);
				keyIdxMap.put(lastVal, valIdx);
			}
			--size;
		}
		return isPresent;
	}

	public int getRandom() {
		int randomVal = orderMap.get(random.nextInt(size) + 1);
		return randomVal;
	}

	public static void main(String[] args) {
		RandomizedSet randomSet = new RandomizedSet();
		System.out.println(randomSet.insert(1));
		System.out.println(randomSet.remove(2));
		System.out.println(randomSet.insert(2));
		System.out.println(randomSet.getRandom());
		System.out.println(randomSet.remove(1));
		System.out.println(randomSet.insert(2));
		System.out.println(randomSet.getRandom());
	}

}
