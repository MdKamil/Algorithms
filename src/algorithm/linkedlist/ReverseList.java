package algorithm.linkedlist;

public class ReverseList {

	private static class Node {
		int data;
		Node next;

		Node(int data, Node next) {
			this.data = data;
			this.next = next;
		}

		@Override
		public String toString() {
			return Integer.toString(data);
		}
	}

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

	public static ListNode reverseList(ListNode head) {
		ListNode revList = null;
		while (head != null) {
			ListNode curr = head;
			head = head.next;
			curr.next = revList;
			revList = curr;
		}
		return revList;
	}

	// In-Place reverse
	public static ListNode reverseListV2(ListNode head) {
		ListNode reverseHead = head;
		ListNode next = null;
		while (head != null) {
			next = head.next;
			if (next != null) {
				head.next = next.next;
				next.next = reverseHead;
				reverseHead = next;
			} else {
				break;
			}
		}
		return reverseHead;
	}

	public static Node reverseSizeK(Node root, int k) {
		Node revList = null;
		Node currHead = root;
		int count = 0;
		while (root != null && count < k) {
			Node curr = root;
			root = root.next;
			curr.next = revList;
			revList = curr;
			++count;
		}
		Node nextHead = root;
		if (currHead != null) {
			currHead.next = null;
		}
		while (root != null) {
			Node curr = root;
			root = root.next;
			Node temp = currHead.next;
			currHead.next = curr;
			curr.next = temp;
			++count;
			if (count % k == 0) {
				currHead = nextHead;
				nextHead = root;
			}
		}
		return revList;
	}

	public static Node reverseSizeKv2(Node root, int k) {
		Node revList = new Node(Integer.MIN_VALUE, null);
		Node currHead = revList;
		Node nextHead = root;
		int count = 0;
		while (root != null) {
			Node curr = root;
			root = root.next;
			Node temp = currHead.next;
			currHead.next = curr;
			curr.next = temp;
			++count;
			if (count % k == 0) {
				currHead = nextHead;
				nextHead = root;
			}
		}
		revList = revList.next;
		return revList;
	}

	public static void main(String[] args) {
		int[] nums = { 1, 2, 3, 4, 5 };
		ListNode head = convertToList(nums);
		head = reverseList(head);
		printList(head);
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
