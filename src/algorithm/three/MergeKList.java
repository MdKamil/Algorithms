package algorithm.three;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MergeKList {

	private static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
		
		@Override
		public String toString() {
			return String.valueOf(val);
		}
	}

	public static ListNode mergeKLists(ListNode[] lists) {
		Comparator<ListNode> c = new Comparator<ListNode>() {
			@Override
			public int compare(ListNode n1, ListNode n2) {
				return Integer.valueOf(n1.val).compareTo(Integer.valueOf(n2.val));
			}
		};
		PriorityQueue<ListNode> queue = new PriorityQueue<>(c);
		for (ListNode node : lists) {
			while (node != null) {
				ListNode curr = node;
				node = node.next;
				curr.next = null;
				queue.add(curr);
			}
		}
		ListNode output = null;
		if (!queue.isEmpty()) {
			output = queue.poll();
		}
		ListNode iter = output;
		while (!queue.isEmpty()) {
			ListNode curr = queue.poll();
			iter.next = curr;
			iter = curr;
		}
		return output;
	}

	public static ListNode mergeKListsV2(ListNode[] lists) {
		Comparator<ListNode> c = new Comparator<ListNode>() {
			@Override
			public int compare(ListNode n1, ListNode n2) {
				return Integer.valueOf(n1.val).compareTo(Integer.valueOf(n2.val));
			}
		};
		PriorityQueue<ListNode> queue = new PriorityQueue<>(c);
		for (ListNode node : lists) {
			if (node != null) {
				queue.add(node);
			}
		}
		ListNode output = null;
		if (!queue.isEmpty()) {
			output = queue.poll();
			ListNode next = output.next;
			if (next != null) {
				queue.add(next);
			}
		}
		ListNode iter = output;
		while (!queue.isEmpty()) {
			ListNode curr = queue.poll();
			if (curr.next != null) {
				queue.add(curr.next);
			}
			iter.next = curr;
			iter = curr;
		}
		return output;
	}

	public static void main(String[] args) {
		ListNode[] lists = getInput();
		ListNode output = mergeKListsV2(lists);
		while (output != null) {
			System.out.print(output.val + "->");
			output = output.next;
		}
	}

	private static ListNode[] getInput() {
		ListNode n1 = new ListNode(5);
		ListNode n2 = new ListNode(4);
		n2.next = n1;
		ListNode n3 = new ListNode(1);
		n3.next = n2;
		
		ListNode n4 = new ListNode(4);
		ListNode n5 = new ListNode(3);
		n5.next = n4;
		ListNode n6 = new ListNode(1);
		n6.next = n5;
		
		ListNode n8 = new ListNode(6);
		ListNode n7 = new ListNode(2);
		n7.next = n8;
		return new ListNode[] { n3, n6, n7  };
	}
}
