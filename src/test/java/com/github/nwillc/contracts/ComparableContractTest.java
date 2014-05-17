package com.github.nwillc.contracts;

public class ComparableContractTest extends ComparableContract<Integer> {
	@Override
	protected Integer getValue() {
		return new Integer(2);
	}

	@Override
	protected Integer getEqualToValue() {
		return 2;
	}

	@Override
	protected Integer getLessThanValue() {
		return 1;
	}

	@Override
	protected Integer getGreaterThanValue() {
		return 3;
	}
}