package algorithm.five;

public class RotateList {

	private static class ListNode {
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

	public static ListNode rotateRight(ListNode head, int k) {
		ListNode newHead = null;
		if (head != null) {
			ListNode iterHead = head;
			int size = 0;
			while (iterHead != null) {
				++size;
				iterHead = iterHead.next;
			}
			if (size > 1 && k % size != 0) {
				if (k > size) {
					k = k % size;
				}
				iterHead = head;
				int lengthToTraverse = size - k;
				int count = 0;
				while (head != null) {
					++count;
					if (count == lengthToTraverse) {
						break;
					}
					head = head.next;
				}
				newHead = head.next;
				head.next = null;
				head = iterHead;

				iterHead = newHead;
				while (newHead != null) {
					if (newHead.next == null) {
						newHead.next = head;
						break;
					}
					newHead = newHead.next;
				}
				newHead = iterHead;
			} else {
				newHead = head;
			}
		}
		return newHead;
	}

	public static void main(String[] args) {
		ListNode head = createList();
		int k = 0;
		ListNode newhead = rotateRight(head, k);
		while (newhead != null) {
			System.out.print(newhead + "->");
			newhead = newhead.next;
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
