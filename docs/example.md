# Example
To use these contracts is straight forward.

1. Include this project's jar in you project
1. Add tests subclassing the appropriate contracts
1. Implement the abstract methods the contract requires

This packages tests follow this pattern so lets look at `ComparableContractTest.java` as a simple example:

	package com.github.nwillc.contracts;
	
	public class ComparableContractTest extends ComparableContract<Integer> {
		@Override
		protected Integer getValue() {
	     	return 2;
		}

		@Override
		protected Integer getEqualToValue() {
			return 2;
		}
		
		@Override
		protected Integer getLessThanValue() {
			return 1;
		}
	}

The above extends `ComparableContract` and implements the abstract methods. Running `JUnit` will result in a series of `@Test`s in `ComparableContract` to run
that enforce the behavioral contract of `Comparable`.
