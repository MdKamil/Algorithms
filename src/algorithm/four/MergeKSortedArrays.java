package algorithm.four;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

public class MergeKSortedArrays {

	private static class ArrayEntry {
		public Integer value;
		public Integer arrayld;

		public ArrayEntry(Integer value, Integer arrayld) {
			this.value = value;
			this.arrayld = arrayld;
		}
	}

	public static List<Integer> mergeSortedArrays(List<List<Integer>> sortedArrays) {
		List<Iterator<Integer>> iters = new ArrayList<>(sortedArrays.size());
		for (List<Integer> array : sortedArrays) {
			iters.add(array.iterator());
		}
		PriorityQueue<ArrayEntry> minHeap = new PriorityQueue<>(((int) sortedArrays.size()),
				new Comparator<ArrayEntry>() {
					@Override
					public int compare(ArrayEntry ol, ArrayEntry o2) {
						return Integer.compare(ol.value, o2.value);
					}
				});
		for (int i = 0; i < iters.size(); ++i) {
			if (iters.get(i).hasNext()) {
				minHeap.add(new ArrayEntry(iters.get(i).next(), i));
			}
		}
		List<Integer> result = new ArrayList<>();
		while (!minHeap.isEmpty()) {
			ArrayEntry headEntry = minHeap.poll();
			result.add(headEntry.value);
			if (iters.get(headEntry.arrayld).hasNext()) {
				minHeap.add(new ArrayEntry(iters.get(headEntry.arrayld).next(), headEntry.arrayld));
			}
		}
		return result;
	}

}
