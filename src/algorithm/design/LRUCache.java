package algorithm.design;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {

	private static class CacheNode {
		Integer key;
		Integer val;
		CacheNode prev;
		CacheNode next;

		public CacheNode(Integer key, Integer val) {
			this.key = key;
			this.val = val;
		}

	}

	private int MAX_SIZE;
	private Map<Integer, CacheNode> cacheMap;
	private CacheNode HEAD;
	private CacheNode TAIL;

	public LRUCache(int capacity) {
		MAX_SIZE = capacity;
		cacheMap = new HashMap<>();
	}

	public int get(int key) {
		int val = -1;
		if (!cacheMap.containsKey(key)) {
			val = -1;
		} else if (HEAD.key == key) {
			val = HEAD.val;
		} else {
			CacheNode newHead = cacheMap.get(key);
			CacheNode newHeadPrev = null;
			if (newHead.prev != null) {
				newHeadPrev = newHead.prev;
			}
			CacheNode newHeadNext = null;
			if (newHead.next != null) {
				newHeadNext = newHead.next;
			}
			if (newHeadPrev != null && newHeadNext != null) {
				// non-tail node;
				newHeadPrev.next = newHeadNext;
				newHeadNext.prev = newHeadPrev;
			} else if (newHeadNext == null && newHeadPrev != null) {
				// tail node
				TAIL = newHeadPrev;
				TAIL.next = null;
				newHead.prev = null;
			}
			newHead.next = HEAD;
			HEAD.prev = newHead;
			HEAD = newHead;

			val = HEAD.val;
		}
		return val;
	}

	public void put(int key, int value) {
		if (cacheMap.containsKey(key)) {
			CacheNode currNode = cacheMap.get(key);
			currNode.val = value;
			cacheMap.put(key, currNode);
			get(key);
			return;
		}
		if (cacheMap.size() == MAX_SIZE) {
			cacheMap.remove(TAIL.key);
			if (cacheMap.isEmpty()) {
				// this if clause is for handling the case where MAX_SIZE = 1
				HEAD = null;
				TAIL = null;
			} else {
				TAIL = TAIL.prev;
				TAIL.next = null;
			}
		}
		CacheNode currNode = new CacheNode(key, value);
		cacheMap.put(key, currNode);
		if (HEAD == null && TAIL == null) {
			// init state of cache and if MAX_SIZE = 1
			HEAD = currNode;
			TAIL = currNode;
		} else {
			currNode.next = HEAD;
			HEAD.prev = currNode;
			HEAD = currNode;
		}
	}

	public static void main(String[] args) {
		// performPutTest();
		// performGetTest();
		validate();
	}

	private static void validate() {
		LRUCache lruCache = new LRUCache(2);
		lruCache.put(2, 1);
		lruCache.put(1, 1);
		lruCache.put(2, 3);
		System.out.println(lruCache.get(1));
		System.out.println(lruCache.get(2));
	}

	private static void performGetTest() {
		LRUCache lruCache = new LRUCache(4);
		lruCache.put(1, 1);
		lruCache.put(2, 2);
		lruCache.put(3, 3);
		lruCache.put(4, 4);
		System.out.println(lruCache.get(4));
		System.out.println("Head: " + lruCache.HEAD);
		System.out.println("Tail: " + lruCache.TAIL);
		System.out.println(lruCache.get(3));
		System.out.println("Head: " + lruCache.HEAD);
		System.out.println("Tail: " + lruCache.TAIL);
		System.out.println(lruCache.get(2));
		System.out.println("Head: " + lruCache.HEAD);
		System.out.println("Tail: " + lruCache.TAIL);
		System.out.println(lruCache.get(1));
		System.out.println("Head: " + lruCache.HEAD);
		System.out.println("Tail: " + lruCache.TAIL);
	}

	private static void performPutTest() {
		LRUCache lruCache = new LRUCache(1);
		lruCache.put(1, 1);
		System.out.println("Head: " + lruCache.HEAD);
		System.out.println("Tail: " + lruCache.TAIL);
		lruCache.put(2, 2);
		System.out.println("Head: " + lruCache.HEAD);
		System.out.println("Tail: " + lruCache.TAIL);
		lruCache.put(3, 3);
		System.out.println("Head: " + lruCache.HEAD);
		System.out.println("Tail: " + lruCache.TAIL);
		lruCache.put(4, 4);
		System.out.println("Head: " + lruCache.HEAD);
		System.out.println("Tail: " + lruCache.TAIL);
		lruCache.put(5, 5);
		System.out.println("Head: " + lruCache.HEAD);
		System.out.println("Tail: " + lruCache.TAIL);
		lruCache.put(6, 6);
		System.out.println("Head: " + lruCache.HEAD);
		System.out.println("Tail: " + lruCache.TAIL);
		lruCache.put(7, 7);
		System.out.println("Head: " + lruCache.HEAD);
		System.out.println("Tail: " + lruCache.TAIL);
		lruCache.put(8, 8);
		System.out.println("Head: " + lruCache.HEAD);
		System.out.println("Tail: " + lruCache.TAIL);

	}
}
