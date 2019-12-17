package algorithm.six;

public class SwapNodesInPairs {

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

	public static ListNode swapPairs(ListNode head) {
		ListNode newHead = null;
		if (head != null) {
			if (head.next == null) {
				newHead = head;
			} else {
				newHead = head.next;
				ListNode temp = null;
				ListNode previous = null;
				while (head != null) {
					temp = head.next;
					if (head.next != null) {
						head.next = head.next.next;
					}
					if (temp != null) {
						temp.next = head;
					}
					if (previous != null) {
						if (temp != null) {
							previous.next = temp;
						} else {
							previous.next = head;
						}
					}
					previous = head;
					head = head.next;
				}
			}
		}
		return newHead;
	}

	public static void main(String[] args) {
		ListNode head = createList();
		ListNode temp = head;
		System.out.println("Before Removing..");
		while (head != null) {
			System.out.print(head + "->");
			head = head.next;
		}
		head = temp;
		System.out.print("NULL");
		head = swapPairs(head);
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
		ListNode n8 = new ListNode(8);
		ListNode n7 = new ListNode(7);
		ListNode n6 = new ListNode(6);
		ListNode n5 = new ListNode(5);
		ListNode n4 = new ListNode(4);
		ListNode n3 = new ListNode(3);
		ListNode n2 = new ListNode(2);
		ListNode n1 = new ListNode(1);
		n9.next = n10;
		n8.next = n9;
		n7.next = n8;
		n6.next = n7;
		n5.next = n6;
		n4.next = n5;
		//n3.next = n4;
		n2.next = n3;
		n1.next = n2;
		return n1;
	}

}
