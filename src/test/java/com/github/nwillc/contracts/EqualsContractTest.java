package com.github.nwillc.contracts;

/**
 *
 */
public class EqualsContractTest extends EqualsContract<EqualsContractTest.Sample> {

	@Override
	protected Sample getEqualsInstance() {
		return new Sample(1, "foo");
	}

	@Override
	protected Sample getInstance() {
		return new Sample(1, "foo");
	}

	@Override
	protected Sample getNotEqualInstance() {
		return new Sample(1, "bar");
	}

	 static class Sample {
		private final String label;
		private final int count;

		public Sample(int count, String label) {
			this.count = count;
			this.label = label;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;

			Sample sample = (Sample) o;

			if (count != sample.count) return false;
			return label.equals(sample.label);

		}

		@Override
		public int hashCode() {
			int result = label.hashCode();
			result = 31 * result + count;
			return result;
		}
	}
}