package algorithm.six;

import java.util.HashMap;
import java.util.Map;

public class RemoveNthNode {

	public static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}

		@Override
		public String toString() {
			return Integer.toString(val);
		}

	}

	public static ListNode removeNthFromEnd(ListNode head, int n) {
		if (head != null) {
			ListNode temp = head;
			int listLength = 0;
			while (head != null) {
				++listLength;
				head = head.next;
			}
			head = temp;
			if (listLength == n) {
				head = head.next;
			} else {
				temp = head;
				while (true) {
					if (listLength - 1 == n) {
						head.next = head.next.next;
						break;
					} else {
						head = head.next;
						--listLength;
					}
				}
				head = temp;
			}
		}
		return head;
	}

	public static ListNode removeNthFromEndV2(ListNode head, int n) {
		if (head != null) {
			Map<Integer, ListNode> map = new HashMap<>();
			ListNode temp = head;
			int listLength = 0;
			while (head != null) {
				++listLength;
				map.put(listLength, head);
				head = head.next;
			}
			map.put(listLength + 1, null);
			head = temp;
			if (listLength == n) {
				head = head.next;
			} else {
				int idx = listLength - n;
				ListNode nodeAtIdx = map.get(idx);
				nodeAtIdx.next = map.get(idx + 2);
			}
		}
		return head;
	}

	public static void main(String[] args) {
		ListNode head = createList();
		int n = 10;
		ListNode temp = head;
		System.out.println("Before Removing..");
		while (head != null) {
			System.out.print(head + "->");
			head = head.next;
		}
		head = temp;
		System.out.print("NULL");
		head = removeNthFromEndV2(head, n);
		System.out.println();
		System.out.println("After removing");
		while (head != null) {
			System.out.print(head + "->");
			head = head.next;
		}
		System.out.print("NULL");
	}

	private static ListNode createList() {
		ListNode n10 = new ListNode(10);
		ListNode n9 = new ListNode(9);
		n9.next = n10;
		ListNode n8 = new ListNode(8);
		n8.next = n9;
		ListNode n7 = new ListNode(7);
		n7.next = n8;
		ListNode n6 = new ListNode(6);
		n6.next = n7;
		ListNode n5 = new ListNode(5);
		n5.next = n6;
		ListNode n4 = new ListNode(4);
		n4.next = n5;
		ListNode n3 = new ListNode(3);
		n3.next = n4;
		ListNode n2 = new ListNode(2);
		n2.next = n3;
		ListNode n1 = new ListNode(1);
		n1.next = n2;

		return n1;
	}

}
