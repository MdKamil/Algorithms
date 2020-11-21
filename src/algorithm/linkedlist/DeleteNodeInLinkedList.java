package algorithm.linkedlist;

public class DeleteNodeInLinkedList {

	private static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
			next = null;
		}

		@Override
		public String toString() {
			return Integer.toString(val);
		}
	}

	public static void deleteNode(ListNode node) {
		if (node != null) {
			node.val = node.next.val;
			node.next = node.next.next;
		}
	}

	public static void main(String[] args) {
		ListNode n1 = new ListNode(1);
		ListNode n2 = new ListNode(2);
		ListNode n3 = new ListNode(3);
		ListNode n4 = new ListNode(4);
		ListNode n5 = new ListNode(5);

		n1.next = n2;
		n2.next = n3;
		n3.next = n4;
		n4.next = n5;

		System.out.println("Before deleting");
		print(n1);

		deleteNode(n1);

		System.out.println("After deleting");
		print(n1);

	}

	private static void print(ListNode head) {
		ListNode curr = head;
		while (curr != null) {
			System.out.println(curr.val);
			curr = curr.next;
		}
	}

}
