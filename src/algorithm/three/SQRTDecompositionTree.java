package algorithm.three;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class SQRTDecompositionTree {

	int N;
	int H;
	List<Integer>[] tree;
	int[] parent;
	int[] height;
	int[] prevLevelAncestor;

	public SQRTDecompositionTree(int N, List<Integer>[] tree) {
		this.N = N;
		this.tree = tree;
		parent = new int[N];
		height = new int[N];
		prevLevelAncestor = new int[N];
	}

	private void init() {
		height[0] = 0;
		parent[0] = -1;
		int maxHeight = height[0];
		maxHeight = getHeight(0, parent[0], height[0], maxHeight);
		H = (int) Math.sqrt(maxHeight);
		if (!isPerfectSquare(maxHeight)) {
			++H;
		}
		prevLevelAncestor[0] = 0;
		splitTree(0, parent[0], prevLevelAncestor[0]);
	}

	private int getHeight(int i, int p, int h, int maxHeight) {
		for (int j : tree[i]) {
			if (j != p) {
				parent[j] = i;
				height[j] = 1 + h;
				if (height[j] > maxHeight) {
					maxHeight = height[j];
				}
				maxHeight = getHeight(j, i, height[j], maxHeight);
			}
		}
		return maxHeight;
	}

	private static boolean isPerfectSquare(int input) {
		long closestRoot = (long) Math.sqrt(input);
		return input == closestRoot * closestRoot;
	}

	private void splitTree(int curr, int p, int prevAncestor) {
		for (int j : tree[curr]) {
			if (j != p) {
				if (height[j] % H == 0) {
					prevAncestor = curr;
				}
				prevLevelAncestor[j] = prevAncestor;
				splitTree(j, curr, prevAncestor);
			}
		}
	}

	private void printTree() {
		System.out.println(N + " " + H);
		System.out.println(Arrays.toString(height));
		System.out.println(Arrays.toString(prevLevelAncestor));
	}

	private void getLCA(int u, int v) {
		if (height[u] > height[v]) {
			int temp = u;
			u = v;
			v = temp;
		}
		while (prevLevelAncestor[u] != prevLevelAncestor[v]) {
			v = prevLevelAncestor[v];
		}
		while (height[v] != height[u]) {
			v = parent[v];
		}
		while (parent[u] != parent[v]) {
			u = parent[u];
			v = parent[v];
		}
		System.out.println("LCA is: " + parent[u]);
	}

	public static void main(String[] args) {
		Random random = new Random(1);
		int N = random.nextInt(50) + 1;
		List<Integer>[] input = getRandomTree(N, random);
		// int N = 13;
		// List<Integer>[] input = getInput();
		SQRTDecompositionTree tree = new SQRTDecompositionTree(N, input);
		tree.init();
		tree.printTree();
		// OPERATIONS ...
		tree.getLCA(16, 31);
	}

	private static List<Integer>[] getRandomTree(int n, Random rnd) {
		List<Integer>[] t = Stream.generate(ArrayList::new).limit(n).toArray(List[]::new);
		int[] p = new int[n];
		for (int i = 0, j; i < n; j = rnd.nextInt(i + 1), p[i] = p[j], p[j] = i, i++)
			; // random permutation
		for (int i = 1; i < n; i++) {
			int parent = p[rnd.nextInt(i)];
			t[parent].add(p[i]);
			t[p[i]].add(parent);
		}
		return t;
	}

	public static List<Integer>[] getInput() {
		List<Integer>[] list = new ArrayList[13];
		for (int i = 0; i <= list.length - 1; ++i) {
			list[i] = new ArrayList<>();
		}
		list[0].add(1);
		list[0].add(2);
		list[0].add(3);
		list[2].add(4);
		list[2].add(5);
		list[2].add(6);
		list[5].add(7);
		list[5].add(8);
		list[6].add(9);
		list[6].add(10);
		list[9].add(11);
		list[9].add(12);

		return list;
	}
}