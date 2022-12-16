package algorithm.array;

import java.util.HashMap;

public class PairsDivisibleBy60 {

	public int numPairsDivisibleBy60(int[] time) {
		int[] sixtyMultiples = { 60, 120, 180, 240, 300, 360, 420, 480, 540, 600, 660, 720, 780, 840, 900, 960 };
		int pairs = 0;
		if (time.length > 1) {
			HashMap<Integer, Integer> map = new HashMap<>();
			for (int idx = 0; idx <= time.length - 1; ++idx) {
				map.put(time[idx], map.getOrDefault(time[idx], 0) + 1);
			}
			for (int idx = 0; idx <= time.length - 1; ++idx) {
				if (!map.containsKey(time[idx])) {
					continue;
				}
				int val = time[idx];
				int fromIdx = nextIdx(val, sixtyMultiples);
				for (; fromIdx <= sixtyMultiples.length - 1; ++fromIdx) {
					int diff = sixtyMultiples[fromIdx] - val;
					if (map.containsKey(diff)) {
						if (val != diff) {
							pairs += (map.get(val) * map.get(diff));
						} else {
							long count = map.get(val);
							pairs += ((count * (count - 1)) / 2);
						}
					}
				}
				map.remove(val);
			}
		}
		return pairs;
	}

	private int nextIdx(int val, int[] sixtyMultiples) {
		int nextGreaterIdx = 0;
		for (int idx = 0; idx <= sixtyMultiples.length - 1; ++idx) {
			if (sixtyMultiples[idx] > val) {
				nextGreaterIdx = idx;
				break;
			}
		}
		return nextGreaterIdx;
	}

	public static void main(String[] args) {
		int[] time = { 269, 230, 318, 468, 171, 158, 350, 60, 287, 27, 11, 384, 332, 267, 412, 478, 280, 303, 242, 378,
				129, 131, 164, 467, 345, 146, 264, 332, 276, 479, 284, 433, 117, 197, 430, 203, 100, 280, 145, 287, 91,
				157, 5, 475, 288, 146, 370, 199, 81, 428, 278, 2, 400, 23, 470, 242, 411, 470, 330, 144, 189, 204, 62,
				318, 475, 24, 457, 83, 204, 322, 250, 478, 186, 467, 350, 171, 119, 245, 399, 112, 252, 201, 324, 317,
				293, 44, 295, 14, 379, 382, 137, 280, 265, 78, 38, 323, 347, 499, 238, 110, 18, 224, 473, 289, 198, 106,
				256, 279, 275, 349, 210, 498, 201, 175, 472, 461, 116, 144, 9, 221, 473 };
		int pairs = new PairsDivisibleBy60().numPairsDivisibleBy60(time);
		System.out.println(pairs);
	}
}
