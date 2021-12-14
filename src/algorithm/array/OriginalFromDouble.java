package algorithm.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OriginalFromDouble {

	public int[] findOriginalArray(int[] changed) {
		if (changed.length % 2 != 0) {
			return new int[] {};
		} else {
			int[] counter = new int[200001];
			for (int val : changed) {
				++counter[val];
			}
			List<Integer> originalList = new ArrayList<>();
			for (int val : changed) {
				if (counter[val] > 0) {
					find(val, counter, originalList);
					if (originalList.size() == 0) {
						break;
					}
				}
			}
			int[] original = new int[originalList.size()];
			for (int idx = 0; idx <= originalList.size() - 1; ++idx) {
				original[idx] = originalList.get(idx);
			}
			return original;
		}
	}

	private void find(int val, int[] counter, List<Integer> originalList) {
		if (val == 0) {
			if (counter[val] % 2 != 0) {
				originalList.clear();
			} else {
				for (int no = 1; no <= counter[val] / 2; ++no) {
					originalList.add(val);
				}
				counter[val] = 0;
			}
		} else if (counter[val * 2] > 0) {
			find(val * 2, counter, originalList);
			if (counter[val] > 0) {
				remove(val, counter, originalList);
			}
		} else {
			remove(val, counter, originalList);
		}
	}

	private void remove(int val, int[] counter, List<Integer> originalList) {
		if (val % 2 != 0) {
			originalList.clear();
		} else if (counter[val / 2] >= counter[val]) {
			for (int no = 1; no <= counter[val]; ++no) {
				originalList.add(val / 2);
			}
			counter[val / 2] = counter[val / 2] - counter[val];
			counter[val] = 0;
		} else {
			originalList.clear();
		}
	}

	public static void main(String[] args) {
		int[] changed = { 18, 20, 1, 4, 2, 8, 6, 1, 2, 9 };
		int[] original = new OriginalFromDouble().findOriginalArray(changed);
		System.out.println(Arrays.toString(original));
	}

}
