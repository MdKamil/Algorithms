package algorithm.design;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;

public class NestedIterator2 implements Iterator<Integer> {

	public interface NestedInteger {
		// @return true if this NestedInteger holds a single integer, rather
		// than a nested list.
		public boolean isInteger();

		// @return the single integer that this NestedInteger holds, if it holds
		// a single integer Return null if this NestedInteger holds a nested
		// list
		public Integer getInteger();

		// @return the nested list that this NestedInteger holds, if it holds a
		// nested list
		// Return null if this NestedInteger holds a single integer
		public List<NestedInteger> getList();
	}

	private Deque<List<NestedInteger>> stack;
	private Deque<Integer> idxStack;
	private List<NestedInteger> currentList;
	private int index;

	public NestedIterator2(List<NestedInteger> nestedList) {
		index = 0;
		currentList = nestedList;
		stack = new ArrayDeque<>();
		idxStack = new ArrayDeque<>();
	}

	@Override
	public Integer next() {
		return currentList.get(index++).getInteger();
	}

	@Override
	public boolean hasNext() {
		boolean hasNext = false;
		while (true) {
			if (index <= currentList.size() - 1) {
				if (!currentList.get(index).isInteger()) {
					if (currentList.get(index).getList().size() == 0) {
						++index;
					} else {
						stack.addLast(currentList);
						idxStack.addLast(index + 1);
						currentList = currentList.get(index).getList();
						index = 0;
					}
				} else {
					hasNext = true;
					break;
				}
			} else {
				if (!stack.isEmpty() && !idxStack.isEmpty()) {
					currentList = stack.pollLast();
					index = idxStack.pollLast();
				} else {
					break;
				}
			}
		}
		return hasNext;
	}

}
