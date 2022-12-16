package algorithm.design;

public class CustomStack {

	private static class Node {
		int val;
		int increment;

		public Node(int val) {
			this.val = val;
		}
	}

	private int idx = -1;
	private Node[] stack;

	public CustomStack(int maxSize) {
		stack = new Node[maxSize];
	}

	public void push(int x) {
		if (idx + 1 == stack.length) {
			return;
		}
		stack[++idx] = new Node(x);
	}

	public int pop() {
		if (idx == -1) {
			return idx;
		} else {
			if (idx != 0) {
				stack[idx - 1].increment += stack[idx].increment;
			}
			Node node = stack[idx--];
			return node.val + node.increment;
		}
	}

	public void increment(int k, int val) {
		if (idx != -1) {
			int range = Math.min(k, idx + 1);
			stack[range - 1].increment += val;
		}
	}
}
