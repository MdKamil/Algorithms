package algorithm.tree;

public class BtreeLevelJoin {
	private static class Node {
		int data;
		Node left;
		Node right;
		Node next;

		Node(int data, Node left, Node right) {
			this.data = data;
			this.left = left;
			this.right = right;
			this.next = null;
		}
	}

	public Node joinNodes(Node rootSibling) {
		Node root = rootSibling;
		Node nextLevelRoot = rootSibling;
		while (nextLevelRoot != null) {
			Node joinChild = null;
			rootSibling = nextLevelRoot;
			nextLevelRoot = null;
			while (rootSibling != null) {
				// join root node's left and right child
				if (rootSibling.left != null && rootSibling.right != null) {
					rootSibling.left.next = rootSibling.right;
				}
				if (joinChild == null) {
					joinChild = (rootSibling.right != null ? rootSibling.right
							: (rootSibling.left != null ? rootSibling.left : null));
				} else {
					joinChild.next = (rootSibling.left != null ? rootSibling.left
							: (rootSibling.right != null ? rootSibling.right : null));
					joinChild = (rootSibling.right != null ? rootSibling.right
							: (rootSibling.left != null ? rootSibling.left : joinChild));
				}
				if (nextLevelRoot == null) {
					nextLevelRoot = rootSibling.left != null ? rootSibling.left
							: rootSibling.right != null ? rootSibling.right : null;
				}
				rootSibling = rootSibling.next;
			}
		}
		return root;
	}

	public static void main(String[] args) {
		Node root = getInput2();
		BtreeLevelJoin join = new BtreeLevelJoin();
		join.joinNodes(root);
		System.out.println(root);
	}

	private static Node getInput1() {
		Node n23 = new Node(23, null, null);
		Node n24 = new Node(24, null, null);
		Node n35 = new Node(35, null, null);
		Node n25 = new Node(25, null, null);
		Node n26 = new Node(26, null, null);

		Node n15 = new Node(15, n23, n24);
		Node n16 = new Node(16, null, n35);
		Node n17 = new Node(17, n25, n26);
		Node n18 = new Node(18, null, null);
		Node n19 = new Node(19, null, null);
		Node n20 = new Node(20, null, null);
		Node n21 = new Node(21, null, null);
		Node n22 = new Node(22, null, null);

		Node n11 = new Node(11, n15, n16);
		Node n12 = new Node(12, n17, n18);
		Node n13 = new Node(13, n19, n20);
		Node n14 = new Node(14, n21, n22);

		Node n5 = new Node(5, n11, n12);
		Node n2 = new Node(2, n13, n14);

		Node root = new Node(10, n5, n2);

		return root;
	}

	private static Node getInput2() {
		Node n12 = new Node(12, null, null);
		Node n11 = new Node(11, null, null);
		Node n10 = new Node(10, null, n12);
		Node n9 = new Node(9, null, n11);
		Node n8 = new Node(8, null, null);

		Node n7 = new Node(7, null, n10);
		Node n6 = new Node(6, null, null);
		Node n5 = new Node(5, n8, n9);
		Node n4 = new Node(4, null, null);

		Node n3 = new Node(3, n6, n7);
		Node n2 = new Node(2, n4, n5);

		Node root = new Node(1, n2, n3);

		return root;
	}
}
