package algorithm.linkedlist;

public class FlattenDoublyLinkedList {

	private static class Node {
		public int val;
		public Node prev;
		public Node next;
		public Node child;
	}

	public static Node flatten(Node head) {
		Node initHead = head;
		flattenList(head);
		return initHead;
	}

	private static Node flattenList(Node head) {
		Node currHead = head;
		while (head != null) {
			if (head.child != null) {
				Node childHead = flattenList(head.child);
				Node headNext = head.next;
				head.next = childHead;
				childHead.prev = head;
				head.child = null;
				while (true) {
					if (childHead.next == null) {
						childHead.next = headNext;
						if (headNext != null) {
							headNext.prev = childHead;
						}
						head = childHead;
						break;
					}
					childHead = childHead.next;
				}
			}
			head = head.next;
		}
		head = currHead;
		return head;
	}

}
