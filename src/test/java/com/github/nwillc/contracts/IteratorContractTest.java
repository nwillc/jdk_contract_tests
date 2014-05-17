package com.github.nwillc.contracts;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IteratorContractTest extends IteratorContract {
	@Override
	protected Iterator getNonEmptyIterator() {
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		return list.iterator();
	}
}