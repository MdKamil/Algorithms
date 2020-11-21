package algorithm.tree;

public class CBTInserter {

	private static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	public static class Node {
		int val;
		Node left;
		Node right;
		int leftSubTreeSize;
		int rightSubTreeSize;

		Node(int x) {
			val = x;
		}
	}

	private int currentLevel;
	private Node backingTree;
	private TreeNode treeNode;

	public CBTInserter(TreeNode root) {
		currentLevel = 0;
		treeNode = root;
		int currTreeSize = cloneTree(root);
		currentLevel = (int) (Math.log(currTreeSize) / Math.log(2));
		if (backingTree.leftSubTreeSize == backingTree.rightSubTreeSize) {
			++currentLevel;
		}
	}

	private int cloneTree(TreeNode root) {
		backingTree = new Node(root.val);
		return performCloningByDFS(root, backingTree);
	}

	private int performCloningByDFS(TreeNode root, Node tree) {
		if (root == null) {
			return 0;
		}
		if (root.left != null) {
			tree.left = new Node(root.left.val);
			int leftSubTreeSize = performCloningByDFS(root.left, tree.left);
			tree.leftSubTreeSize = leftSubTreeSize;
		}
		if (root.right != null) {
			tree.right = new Node(root.right.val);
			int rightSubTreeSize = performCloningByDFS(root.right, tree.right);
			tree.rightSubTreeSize = rightSubTreeSize;
		}
		return tree.leftSubTreeSize + tree.rightSubTreeSize + 1;
	}

	public int insert(int v) {
		Node backingTreeRoot = backingTree;
		TreeNode root = treeNode;
		TreeNode parent = null;
		int level = currentLevel;
		while (true) {
			int expectedSubTreeSize = (int) Math.pow(2, level) - 1;
			if (backingTree.leftSubTreeSize < expectedSubTreeSize) {
				if (backingTree.left == null) {
					parent = treeNode;
					backingTree.left = new Node(v);
					++backingTree.leftSubTreeSize;
					treeNode.left = new TreeNode(v);
					break;
				} else {
					++backingTree.leftSubTreeSize;
					backingTree = backingTree.left;
					treeNode = treeNode.left;
					--level;
				}
			} else if (backingTree.rightSubTreeSize < expectedSubTreeSize) {
				if (backingTree.right == null) {
					parent = treeNode;
					backingTree.right = new Node(v);
					++backingTree.rightSubTreeSize;
					treeNode.right = new TreeNode(v);
					break;
				} else {
					++backingTree.rightSubTreeSize;
					backingTree = backingTree.right;
					treeNode = treeNode.right;
					--level;
				}
			}
		}
		treeNode = root;
		backingTree = backingTreeRoot;
		if (backingTree.leftSubTreeSize == backingTree.rightSubTreeSize) {
			++currentLevel;
		}
		return parent.val;
	}

	public TreeNode get_root() {
		return treeNode;
	}

	public static void main(String[] args) {
		TreeNode n4 = new TreeNode(4);
		TreeNode n5 = new TreeNode(5);
		TreeNode n6 = new TreeNode(6);
		TreeNode n2 = new TreeNode(2);
		n2.left = n4;
		n2.right = n5;
		TreeNode n3 = new TreeNode(3);
		n3.left = n6;

		TreeNode n1 = new TreeNode(1);
		n1.left = n2;
		n1.right = n3;

		CBTInserter cbtInserter = new CBTInserter(n1);
		System.out.println(cbtInserter.insert(7));
		System.out.println(cbtInserter.insert(8));
		System.out.println(cbtInserter.currentLevel);
	}

}
