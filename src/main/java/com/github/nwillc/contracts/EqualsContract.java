package com.github.nwillc.contracts;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @since 1.6.8
 * @param <T>
 */
public abstract class EqualsContract<T> {
	protected abstract T getInstance();
	protected abstract T getEqualsInstance();
	protected abstract T getNotEqualInstance();

	@Test
	public void testSameObject() throws Exception {
		T instance = getInstance();
		assertThat(instance).isEqualTo(instance);
	}

	@Test
	public void testWrongClassType() throws Exception {
		assertThat(getInstance()).isNotEqualTo("foo");
	}

	@Test
	public void testEquals() throws Exception {
		assertThat(getInstance()).isEqualTo(getEqualsInstance());
		assertThat(getEqualsInstance()).isEqualTo(getInstance());
	}

	@Test
	public void testNotEquals() throws Exception {
		assertThat(getInstance()).isNotEqualTo(getNotEqualInstance());
		assertThat(getEqualsInstance()).isNotEqualTo(getNotEqualInstance());
	}

	@Test
	public void testHandlesNull() throws Exception {
		assertThat(getInstance()).isNotEqualTo(null);
	}

	@Test
	public void testHashEquals() throws Exception {
		assertThat(getInstance().hashCode()).isEqualTo(getEqualsInstance().hashCode());
	}

	@Test
	public void testHashNotEquals() throws Exception {
		assertThat(getInstance().hashCode()).isNotEqualTo(getNotEqualInstance().hashCode());
	}
}
