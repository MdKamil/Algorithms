package algorithm.eight;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class NestedIterator implements Iterator<Integer> {

	public interface NestedInteger {
		// @return true if this NestedInteger holds a single integer, rather
		// than a
		// nested list.
		public boolean isInteger();

		// @return the single integer that this NestedInteger holds, if it holds
		// a
		// single integer
		// Return null if this NestedInteger holds a nested list
		public Integer getInteger();

		// @return the nested list that this NestedInteger holds, if it holds a
		// nested list
		// Return null if this NestedInteger holds a single integer
		public List<NestedInteger> getList();
	}

	private List<Integer> internalList;

	int index = 0;

	public NestedIterator(List<NestedInteger> nestedList) {
		this.internalList = new ArrayList<>();
		convertNestedListToFlatList(nestedList);
	}

	private void convertNestedListToFlatList(List<NestedInteger> nestedList) {
		for (int idx = 0; idx <= nestedList.size() - 1; ++idx) {
			if (nestedList.get(idx).isInteger()) {
				this.internalList.add(nestedList.get(idx).getInteger());
			} else {
				convertNestedListToFlatList(nestedList.get(idx).getList());
			}
		}
	}

	@Override
	public Integer next() {
		return internalList.get(index++);
	}

	@Override
	public boolean hasNext() {
		if (index <= internalList.size() - 1) {
			return true;
		} else {
			return false;
		}
	}

}
