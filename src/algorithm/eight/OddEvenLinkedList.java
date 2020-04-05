package algorithm.eight;

public class OddEvenLinkedList {

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

	private static ListNode convertToList(int[] nums) {
		ListNode head = null;
		if (nums != null && nums.length > 0) {
			int idx = 0;
			head = new ListNode(nums[idx++]);
			ListNode temp = head;
			for (; idx <= nums.length - 1; ++idx) {
				temp.next = new ListNode(nums[idx]);
				temp = temp.next;
			}
		}
		return head;
	}

	public static ListNode oddEvenList(ListNode head) {
		if (head != null && head.next != null) {
			ListNode oddIdxListHead = head;
			ListNode oddIdxListEnd = null;
			ListNode evenIdxListHead = head.next;
			ListNode evenIdxListEnd = null;
			head = head.next.next;
			oddIdxListHead.next = evenIdxListHead;
			evenIdxListHead.next = null;
			int idx = 3;
			while (head != null) {
				ListNode currNode = head;
				head = head.next;
				if (idx % 2 != 0) {
					if (oddIdxListEnd == null) {
						oddIdxListEnd = currNode;
						oddIdxListHead.next = oddIdxListEnd;
					} else {
						oddIdxListEnd.next = currNode;
						oddIdxListEnd = oddIdxListEnd.next;
					}
					oddIdxListEnd.next = evenIdxListHead;
				} else {
					if (evenIdxListEnd == null) {
						evenIdxListEnd = currNode;
						evenIdxListHead.next = evenIdxListEnd;
						evenIdxListEnd.next = null;
					} else {
						evenIdxListEnd.next = currNode;
						evenIdxListEnd = evenIdxListEnd.next;
					}
					evenIdxListEnd.next = null;
				}
				++idx;
			}
			head = oddIdxListHead;
		}
		return head;
	}

	public static ListNode oddEvenListV2(ListNode head) {
		if (head != null && head.next != null) {
			ListNode temp = head;
			ListNode oddEnd = head;
			head = head.next;
			while (head != null) {
				if (head.next != null) {
					ListNode tempEvenHead = oddEnd.next;
					oddEnd.next = head.next;
					oddEnd = oddEnd.next;
					head.next = head.next.next;
					oddEnd.next = tempEvenHead;
				}
				head = head.next;
			}
			head = temp;
		}
		return head;
	}

	public static ListNode oddEvenListV3(ListNode head) {
		if (head != null && head.next != null) {
			ListNode temp = head;
			while (head != null) {
				if (head.next != null) {
					ListNode t = head.next;
					head.next = head.next.next;
					if (head.next != null) {
						temp.next = head.next.next;
					}
					head.next.next = t;
				}
			}
			head = temp;
		}
		return head;
	}

	public static void main(String[] args) {
		int[] nums = { 1, 2, 3, 4, 5, 6 };
		ListNode head = convertToList(nums);
		oddEvenListV2(head);
		while (head != null) {
			System.out.println(head);
			head = head.next;
		}

	}
}
