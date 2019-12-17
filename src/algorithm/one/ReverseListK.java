package algorithm.one;

public class ReverseListK {
	private static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}

	public static ListNode reverseKGroup(ListNode head, int K) {
		ListNode tempHead = head;
		int size = 0;
		while (tempHead != null) {
			++size;
			tempHead = tempHead.next;
		}
		ListNode revList = new ListNode(Integer.MIN_VALUE);
		ListNode currHead = revList;
		ListNode nextHead = head;
		int count = 0;
		while (head != null) {
			ListNode curr = head;
			head = head.next;
			ListNode temp = currHead.next;
			currHead.next = curr;
			curr.next = temp;
			++count;
			if (count % K == 0) {
				currHead = nextHead;
				nextHead = head;
			}
		}
		revList = revList.next;
		return revList;
	}

}
