package algorithm.one;

public class ListIntersection {

	private static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
			next = null;
		}
	}

	public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
		int aLen = 0;
		int bLen = 0;

		ListNode tempA;
		ListNode tempB;
		tempA = headA;

		while (tempA != null) {
			tempA = tempA.next;
			++aLen;
		}
		tempB = headB;
		while (tempB != null) {
			tempB = tempB.next;
			++bLen;
		}

		if (aLen == 0 || bLen == 0) {
			return null;
		}
		tempA = headA;
		tempB = headB;
		if (aLen > bLen) {
			while (aLen > bLen) {
				tempA = tempA.next;
				--aLen;
			}
		} else if (bLen > aLen) {
			while (bLen > aLen) {
				tempB = tempB.next;
				--bLen;
			}
		}
		ListNode itxNode = null;
		while (tempA != null) {
			if (tempA.next == tempB.next) {
				itxNode = tempA.next;
				break;
			}
			tempA = tempA.next;
			tempB = tempB.next;
		}
		return (itxNode != null) ? itxNode : null;
	}
}
