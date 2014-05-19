package com.github.nwillc.contracts;

public class ComparableContractTest extends ComparableContract {
	@Override
	protected Comparable getValue() {
		return new Integer(2);
	}

	@Override
	protected Comparable getEqualToValue() {
		return 2;
	}

	@Override
	protected Comparable getLessThanValue() {
		return 1;
	}

	@Override
	protected Comparable getGreaterThanValue() {
		return 3;
	}
}