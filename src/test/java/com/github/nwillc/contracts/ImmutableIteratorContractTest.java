package com.github.nwillc.contracts;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ImmutableIteratorContractTest extends ImmutableIteratorContract {
	@Override
	protected Iterator getNonEmptyIterator() {
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		return new ImmutableIterator(list.iterator());
	}

	private class ImmutableIterator<E> implements Iterator<E> {
		private Iterator<E> iterator;

		private ImmutableIterator(Iterator<E> iterator) {
			this.iterator = iterator;
		}

		public boolean hasNext() {
			return iterator.hasNext();
		}

		public E next() {
			return iterator.next();
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}
	}
}