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

	public static Node reversePlain(Node root) {
		Node revList = null;
		while (root != null) {
			Node curr = root;
			root = root.next;
			curr.next = revList;
			revList = curr;
		}
		return revList;
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

		Node root = n1;
		System.out.println("Before reverse");
		while (root != null) {
			System.out.print(root.data + "->");
			root = root.next;
		}
		System.out.print("null");
		System.out.println();
		root = n1;
		root = reverseSizeKv2(root, 11);
		System.out.println("After reverse");
		while (root != null) {
			System.out.print(root.data + "->");
			root = root.next;
		}
		System.out.print("null");
		root = n1;
	}
}
