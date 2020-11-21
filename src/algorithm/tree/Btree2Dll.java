package algorithm.tree;

public class Btree2Dll {

	private static class Node {
		int data;
		Node left;
		Node right;

		Node(int data, Node left, Node right) {
			this.data = data;
			this.left = left;
			this.right = right;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + data;
			result = prime * result + ((left == null) ? 0 : left.hashCode());
			result = prime * result + ((right == null) ? 0 : right.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Node other = (Node) obj;
			if (data != other.data)
				return false;
			if (left == null) {
				if (other.left != null)
					return false;
			} else if (!left.equals(other.left))
				return false;
			if (right == null) {
				if (other.right != null)
					return false;
			} else if (!right.equals(other.right))
				return false;
			return true;
		}
	}

	public Node getRMNOfLST(Node root) {
		if (root.right != null) {
			return getRMNOfLST(root.right);
		} else if (root.left != null) {
			return getRMNOfLST(root.left);
		} else {
			return root;
		}
	}

	public Node convertToDLL(Node root) {
		Node rnode = null;
		if (root.left != null) {
			if (root.left.right != null) {
				rnode = getRMNOfLST(root.left);
			} else {
				rnode = root.left;
			}
		}
		Node lhead = null;
		if (root.left != null) {
			lhead = convertToDLL(root.left);
			if (rnode.equals(lhead)) {
				rnode.right = root;
			} else {
				rnode.right = root;
				root.left = rnode;
			}
		}

		Node rhead = null;
		if (root.right != null) {
			rhead = convertToDLL(root.right);
			rhead.left = root;
			root.right = rhead;
		}

		if ((lhead == null && rhead == null) || (lhead == null)) {
			return root;
		}
		return lhead;
	}

	public Node convertToDLL(Node root, Node joinNode) {
		if (root == null) {
			return null;
		}
		Node r = convertToDLL(root.right, joinNode);
		// the following if case will be hit for non-leaf nodes of the tree.
		if (r != null) {
			root.right = r;
			r.left = root;
		} else {
			root.right = joinNode;
			// the following if case will be hit for leaf nodes of the tree.
			if (joinNode != null) {
				joinNode.left = root;
			}
		}
		Node l = convertToDLL(root.left, root);
		if (l == null) {
			return root;
		} else {
			return l;
		}
	}

	private static void solveNaive() {
		Node root = getInput3();
		Btree2Dll btree2Dll = new Btree2Dll();
		Node head = btree2Dll.convertToDLL(root);
		while (head != null) {
			System.out.print(head.data + " ");
			head = head.right;
		}
	}

	private static void optimizedV1() {
		Node root = getInput3();
		Node joinNode = null;
		Btree2Dll btree2Dll = new Btree2Dll();
		Node head = btree2Dll.convertToDLL(root, joinNode);
		Node tail = null;
		while (head != null) {
			System.out.print(head.data + " ");
			if (head.right == null) {
				tail = head;
			}
			head = head.right;
		}
		System.out.println();
		while (tail != null) {
			System.out.print(tail.data + " ");
			tail = tail.left;
		}
	}

	public static void main(String[] args) {
		// solveNaive();
		System.out.println();
		optimizedV1();
	}

	private static Node getInput2() {
		Node n5 = new Node(5, null, null);
		Node n4 = new Node(4, n5, null);
		Node n3 = new Node(3, n4, null);
		Node n2 = new Node(2, n3, null);

		Node n11 = new Node(11, null, null);
		Node root = new Node(1, n11, n2);
		return root;
	}

	private static Node getInput3() {
		Node n6 = new Node(6, null, null);
		Node n5 = new Node(5, null, n6);
		Node n4 = new Node(4, null, n5);
		Node n3 = new Node(3, null, null);
		Node n2 = new Node(2, null, n4);

		Node root = new Node(1, n2, n3);
		return root;
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
}
