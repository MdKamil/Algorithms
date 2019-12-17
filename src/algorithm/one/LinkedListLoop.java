package algorithm.one;

public class LinkedListLoop {

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
	
	private static boolean hasLoop(Node root) {
		if (root == null) {
			return false;
		}
		Node slow = root, fast = root;
		boolean hasLoop = false;
		while (true) {
			slow = slow.next;
			if (fast.next != null) {
				fast = fast.next.next;
			} else {
				break;
			}
			if (slow == null || fast == null) {
				break;
			}
			if (slow == fast) {
				hasLoop = true;
				break;
			}
		}

		if (hasLoop) {
			Node start = root;
			Node inLoop = fast;
			while (true) {
				if (start == inLoop) {
					System.out.println("Loop beginning. Val: " + start.toString());
					System.out.println("Loop beginning. Val: " + inLoop.toString());
					break;
				}
				start = start.next;
				if (inLoop.next == start) {
					System.out.println("To remove loop make next of " + inLoop + " to null");
				}
				inLoop = inLoop.next;
			}
		} else {
			System.out.println(hasLoop);
		}
		return hasLoop;
	}

	public static void main(String[] args) {
		Node n10 = new Node(10, null);
		Node n9 = new Node(9, n10);
		Node n8 = new Node(8, n9);
		Node n7 = new Node(7, n8);
		Node n6 = new Node(6, n7);
		Node n5 = new Node(5, n6);
		Node n4 = new Node(4, n5);
		Node n3 = new Node(3, n4);
		Node n2 = new Node(2, n3);
		Node n1 = new Node(1, n2);

		n10.next = n3;

		hasLoop(n1);
	}
}
