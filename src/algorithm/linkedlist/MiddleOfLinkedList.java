package algorithm.linkedlist;

public class MiddleOfLinkedList {

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

	public static ListNode middleNode(ListNode head) {
		ListNode middleNode = null;
		if (head != null) {
			ListNode slow = head;
			ListNode fast = head;
			while (true) {
				if (fast.next != null) {
					fast = fast.next.next;
					slow = slow.next;
				} else {
					break;
				}
				if (fast == null) {
					break;
				}
			}
			middleNode = slow;
		}
		return middleNode;
	}

	public static void main(String[] args) {
		int[] nums = { 1 };
		ListNode head = convertToList(nums);
		ListNode middleNode = middleNode(head);
		System.out.println(middleNode);
	}

}
