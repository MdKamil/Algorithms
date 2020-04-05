package algorithm.eight;

public class PalindromeLinkedList {

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

	public static boolean isPalindrome(ListNode head) {
		boolean isPalindrome = true;
		if (head != null) {
			ListNode slow = head;
			ListNode fast = head;
			ListNode mid = null;
			while (true) {
				if (fast.next != null) {
					fast = fast.next.next;
				} else {
					fast = fast.next;
				}
				mid = slow;
				slow = slow.next;
				if (fast == null) {
					break;
				}
			}
			if (slow != null) {
				ListNode reversedList = null;
				while (slow != null) {
					ListNode temp = slow;
					slow = slow.next;
					temp.next = reversedList;
					reversedList = temp;
				}
				mid.next = reversedList;
				while (reversedList != null) {
					if (head.val != reversedList.val) {
						isPalindrome = false;
						break;
					} else {
						head = head.next;
						reversedList = reversedList.next;
					}
				}
			}
		}
		return isPalindrome;
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

	public static void main(String[] args) {
		int[] nums = { 1, 2, 3 };
		ListNode head = convertToList(nums);
		boolean isPalindrome = isPalindrome(head);
		System.out.println(isPalindrome);
	}

}
