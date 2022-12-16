package algorithm.linkedlist;

public class ReverseList2 {
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

	public ListNode reverseBetween(ListNode head, int left, int right) {
		if (left == right) {
			return head;
		}
		ListNode reverseHead = null;
		ListNode reverseTail = null;
		ListNode tempHead = head;
		ListNode prev = null;
		int count = 0;
		while (head != null) {
			++count;
			ListNode curr = head;
			head = head.next;
			if (count >= left && count <= right) {
				curr.next = reverseHead;
				reverseHead = curr;
				if (count == left) {
					reverseTail = reverseHead;
				} else if (count == right) {
					if (prev == null) {
						tempHead = reverseHead;
					} else {
						prev.next = reverseHead;
					}
					reverseTail.next = head;
					break;
				}
			} else {
				prev = curr;
			}
		}
		head = tempHead;
		return head;
	}

	public static void main(String[] args) {
		int[] nums = { 5 };
		ListNode head = convertToList(nums);
		ListNode reversedList = new ReverseList2().reverseBetween(head, 1, 1);
		printList(reversedList);
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

	private static void printList(ListNode head) {
		while (head != null) {
			System.out.print(head.val + " -> ");
			head = head.next;
		}
		System.out.print("null");
	}
}
