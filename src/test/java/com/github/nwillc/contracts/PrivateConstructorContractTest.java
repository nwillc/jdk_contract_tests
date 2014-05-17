package com.github.nwillc.contracts;

public class PrivateConstructorContractTest extends PrivateConstructorContract {
	@Override
	protected Class<?> getUtilityClass() {
		return UtilityClass.class;
	}

	private static final class UtilityClass {
		private UtilityClass() {}
	}
}