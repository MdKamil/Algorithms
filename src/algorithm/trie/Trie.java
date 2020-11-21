package algorithm.trie;

public class Trie {

	private static class TrieNode {
		TrieNode[] children;
		int ALPHABET_COUNT = 26;
		boolean leaf;

		public TrieNode() {
			children = new TrieNode[ALPHABET_COUNT];
		}
	}

	public static void insert(TrieNode mainRoot, String word) {
		TrieNode innerRoot = mainRoot;
		for (char ch : word.toCharArray()) {
			TrieNode next = innerRoot.children[ch - 'a'];
			if (next == null) {
				TrieNode newNode = new TrieNode();
				innerRoot.children[ch - 'a'] = newNode;
				next = newNode;
			}
			innerRoot = next;
		}
		innerRoot.leaf = true;
	}

	public static boolean search(TrieNode mainRoot, String word) {
		TrieNode innerRoot = mainRoot;
		for (char ch : word.toCharArray()) {
			TrieNode next = innerRoot.children[ch - 'a'];
			if (next == null) {
				return false;
			} else {
				innerRoot = next;
			}
		}
		if (innerRoot != null && innerRoot.leaf) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean startsWith(TrieNode mainRoot, String word) {
		boolean isPresent = true;
		TrieNode root = mainRoot;
		for (char ch : word.toCharArray()) {
			TrieNode child = root.children[ch - 'a'];
			if (child == null) {
				isPresent = false;
				break;
			}
			root = child;
		}
		return isPresent;
	}

	public static void suggest(TrieNode mainRoot, String word) {
		TrieNode innerRoot = mainRoot;
		for (char ch : word.toCharArray()) {
			innerRoot = innerRoot.children[ch - 'a'];
		}
		StringBuilder builder = new StringBuilder();
		builder.append(word);
		printSuggestions(innerRoot, builder);
		System.out.println(builder.toString());
	}

	public static void printSuggestions(TrieNode startNode, StringBuilder builder) {
		for (int i = 0; i <= 25; ++i) {
			TrieNode child = startNode.children[i];
			if (child != null) {
				builder.append((char) (97 + i));
				if (!child.leaf) {
					printSuggestions(child, builder);
					builder = builder.deleteCharAt(builder.length() - 1);
				} else {
					System.out.println(builder);
					builder = builder.deleteCharAt(builder.length() - 1);
				}
			}
		}
	}

	public static void main(String[] args) {
		TrieNode root = new TrieNode();
		// insert(root, "apple");
		// System.out.println(search(root, "apple"));
		// System.out.println(search(root, "app"));
		// System.out.println(startsWith(root, "app"));
		// insert(root, "app");
		// System.out.println(search(root, "app"));

		insert(root, "ant");
		insert(root, "anty");
		insert(root, "antufgg");

		suggest(root, "ant");
	}

}
