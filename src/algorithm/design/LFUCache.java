package algorithm.design;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LFUCache {

	private static class CacheNode {
		public Integer key;
		public Integer val;
		public Integer count;
		public Long timeStamp;

		public CacheNode(Integer key, Integer val) {
			this.key = key;
			this.val = val;
			count = Integer.valueOf(1);
			timeStamp = System.nanoTime();
		}

		@Override
		public String toString() {
			return key.toString();
		}

	}

	private static class MinHeap {
		// underlying dynamic array of MinHeap
		private List<CacheNode> list;
		// comparator used while adding and updating
		private Comparator<CacheNode> comparator;
		// store the index of each key in the dynamic array
		private Map<Integer, Integer> keyIdxMap;
		private int size;

		public MinHeap(Comparator<CacheNode> comparator) {
			list = new ArrayList<>();
			this.comparator = comparator;
			keyIdxMap = new HashMap<>();
		}

		private int parent(int idx) {
			return (idx - 1) / 2;
		}

		private int left(int idx) {
			return 2 * (idx) + 1;
		}

		private int right(int idx) {
			return 2 * (idx) + 2;
		}

		public CacheNode min() {
			return list.get(0);
		}

		private void shiftUp(int idx, CacheNode node) {
			while (idx > 0 && compare(list.get(idx), list.get(parent(idx)))) {
				swap(parent(idx), idx);
				// parent node's index changed. Updating it in keyIdxMap
				keyIdxMap.put(list.get(idx).key, idx);
				idx = parent(idx);
			}
			keyIdxMap.put(node.key, idx);
		}

		private void minHeapify(int idx) {
			int leftIdx = left(idx);
			int rightIdx = right(idx);
			int smallestIdx = idx;
			if (leftIdx <= size - 1 && compare(list.get(leftIdx), list.get(idx))) {
				smallestIdx = leftIdx;
			}
			if (rightIdx <= size - 1 && compare(list.get(rightIdx), list.get(smallestIdx))) {
				smallestIdx = rightIdx;
			}
			if (smallestIdx != idx) {
				swap(smallestIdx, idx);
				keyIdxMap.put(list.get(idx).key, idx);
				keyIdxMap.put(list.get(smallestIdx).key, smallestIdx);
				minHeapify(smallestIdx);
			}
		}

		private void swap(int i, int j) {
			CacheNode temp = list.get(i);
			list.set(i, list.get(j));
			list.set(j, temp);
		}

		private boolean compare(CacheNode node1, CacheNode node2) {
			int result = this.comparator.compare(node1, node2);
			if (result == -1) {
				return true;
			} else {
				return false;
			}
		}

		public void add(CacheNode node) {
			++size;
			list.add(node);
			shiftUp(size - 1, node);
		}

		public void updateKey(int key, Integer count, Long recentAccessTime) {
			int idx = keyIdxMap.get(key);
			CacheNode node = list.get(idx);
			node.count = count;
			node.timeStamp = recentAccessTime;
			minHeapify(idx);
		}

		public Integer removeMin() {
			Integer key = -1;
			if (size > 0) {
				key = min().key;
				keyIdxMap.remove(key);
				list.set(0, list.get(size - 1));
				list.remove(size - 1);
				--size;
				minHeapify(0);
			}
			return key;
		}

	}

	private int MAX_SIZE;
	private Map<Integer, CacheNode> cacheMap;
	private Comparator<CacheNode> comparator = new Comparator<CacheNode>() {
		@Override
		public int compare(CacheNode o1, CacheNode o2) {
			int result = o1.count.compareTo(o2.count);
			if (result != 0) {
				return result;
			}
			result = o1.timeStamp.compareTo(o2.timeStamp);
			if (result != 0) {
				return result;
			}
			return result;
		}
	};
	private MinHeap minHeap = new MinHeap(comparator);

	public LFUCache(int capacity) {
		this.MAX_SIZE = capacity;
		cacheMap = new HashMap<>();
	}

	public int get(int key) {
		int val = -1;
		if (!cacheMap.containsKey(key)) {
			val = -1;
		} else {
			CacheNode node = cacheMap.get(key);
			node.count += 1;
			node.timeStamp = System.nanoTime();
			cacheMap.put(key, node);
			val = node.val;
			minHeap.updateKey(key, node.count, node.timeStamp);
		}
		return val;
	}

	public void put(int key, int value) {
		if (cacheMap.containsKey(key)) {
			CacheNode currNode = cacheMap.get(key);
			currNode.val = value;
			currNode.count += 1;
			currNode.timeStamp = System.nanoTime();
			cacheMap.put(key, currNode);
			minHeap.updateKey(key, currNode.count, currNode.timeStamp);
		} else {
			if (MAX_SIZE > 0) {
				if (cacheMap.size() == MAX_SIZE) {
					Integer minKey = minHeap.removeMin();
					cacheMap.remove(minKey);
				}
				CacheNode node = new CacheNode(key, value);
				cacheMap.put(key, node);
				minHeap.add(node);
			}
		}
	}

	public static void main(String[] args) {
		// testMinHeap();
		testCache();
	}

	private static void testCache() {
		LFUCache cache = new LFUCache(2);
		cache.put(1, 1);
		cache.put(2, 2);
		System.out.println(cache.get(1));
		cache.put(3, 3);
		System.out.println(cache.get(2));
		System.out.println(cache.get(3));
		cache.put(4, 4);
		System.out.println(cache.get(1));
		System.out.println(cache.get(3));
		System.out.println(cache.get(4));
	}

	private static void testMinHeap() {
		Comparator<CacheNode> c = new Comparator<CacheNode>() {
			@Override
			public int compare(CacheNode o1, CacheNode o2) {
				int result = o1.count.compareTo(o2.count);
				if (result != 0) {
					return result;
				}
				result = o1.timeStamp.compareTo(o2.timeStamp);
				if (result != 0) {
					return result;
				}
				return result;
			}
		};

		MinHeap minHeap = new MinHeap(c);
		CacheNode n1 = new CacheNode(10, 1);
		n1.count = 2;
		n1.timeStamp = System.nanoTime();
		CacheNode n2 = new CacheNode(11, 2);
		n2.count = 3;
		n2.timeStamp = System.nanoTime();
		CacheNode n3 = new CacheNode(12, 3);
		n3.count = 3;
		n3.timeStamp = System.nanoTime();
		CacheNode n4 = new CacheNode(13, 4);
		n4.count = 4;
		n4.timeStamp = System.nanoTime();
		CacheNode n5 = new CacheNode(14, 5);
		n5.count = 4;
		n5.timeStamp = System.nanoTime();
		CacheNode n6 = new CacheNode(15, 6);
		n6.count = 5;
		n6.timeStamp = System.nanoTime();
		CacheNode n7 = new CacheNode(16, 7);
		n7.count = 5;
		n7.timeStamp = System.nanoTime();

		minHeap.add(n1);
		minHeap.add(n2);
		minHeap.add(n3);
		minHeap.add(n4);
		minHeap.add(n5);
		minHeap.add(n6);
		minHeap.add(n7);

		System.out.println(minHeap);

		minHeap.removeMin();
		minHeap.removeMin();
		minHeap.removeMin();
		minHeap.removeMin();
		minHeap.removeMin();
		minHeap.removeMin();
		minHeap.removeMin();

		System.out.println(minHeap);

		// minHeap.updateKey(n2.key, 4, System.nanoTime());
		// System.out.println(minHeap);
		//
		// CacheNode n8 = new CacheNode(17, 8);
		// n8.count = 1;
		// n8.timeStamp = System.nanoTime();
		//
		// minHeap.add(n8);
		// System.out.println(minHeap);
		//
		// minHeap.removeMin();
		// minHeap.removeMin();
		// minHeap.removeMin();
		// minHeap.removeMin();
		// minHeap.removeMin();
		// minHeap.removeMin();
		// minHeap.removeMin();
		// minHeap.removeMin();
		// System.out.println(minHeap);
		//
		// minHeap.add(n1);
		// minHeap.add(n2);
		// minHeap.add(n3);
		// minHeap.add(n4);
		// minHeap.add(n5);
		// minHeap.add(n6);
		// minHeap.add(n7);
		// minHeap.add(n8);
		//
		// System.out.println(minHeap);
	}

}
