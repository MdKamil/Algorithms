package algorithm.six;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class PeekingIterator implements Iterator<Integer> {

	private Integer peekVal;
	private Iterator<Integer> iterator;

	public PeekingIterator(Iterator<Integer> iterator) {
		// initialize any member here.
		this.iterator = iterator;
		peekVal = null;
	}

	// Returns the next element in the iteration without advancing the iterator.
	public Integer peek() {
		Integer val = null;
		if (peekVal != null) {
			val = peekVal;
		} else {
			if (hasNext()) {
				peekVal = next();
				val = peekVal;
			}
		}
		return val;
	}

	// hasNext() and next() should behave the same as in the Iterator interface.
	// Override them if needed.
	@Override
	public Integer next() {
		Integer val = null;
		if (peekVal != null) {
			val = peekVal;
			peekVal = null;
		} else {
			val = iterator.next();
		}
		return val;
	}

	@Override
	public boolean hasNext() {
		boolean hasNext = false;
		if (peekVal != null || iterator.hasNext()) {
			hasNext = true;
		}
		return hasNext;
	}

	public static void main(String[] args) {
		List<Integer> list = Arrays.asList(1);
		Iterator<Integer> iterator = list.iterator();
		PeekingIterator peekingIterator = new PeekingIterator(iterator);
		System.out.println(peekingIterator.peek());
		System.out.println(peekingIterator.peek());
		System.out.println(peekingIterator.next());
		System.out.println(peekingIterator.peek());
		System.out.println(peekingIterator.next());
		System.out.println(peekingIterator.peek());
		System.out.println(peekingIterator.next());
		System.out.println(peekingIterator.peek());
		System.out.println(peekingIterator.next());
		System.out.println(peekingIterator.peek());
		System.out.println(peekingIterator.next());
		System.out.println(peekingIterator.peek());
	}

}