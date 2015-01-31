/*
 * Copyright (c) 2015, nwillc@gmail.com
 *
 * Permission to use, copy, modify, and/or distribute this software for any
 * purpose with or without fee is hereby granted, provided that the above
 * copyright notice and this permission notice appear in all copies.
 *
 * THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES
 * WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR
 * ANY SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES
 * WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN
 * ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF
 * OR IN CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.
 */

package com.github.nwillc.contracts;

import org.junit.Before;
import org.junit.Test;

import java.util.Comparator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.failBecauseExceptionWasNotThrown;

/**
 * This contract checks:
 * <ul>
 *     <li>compare with argument that can't be cast to correct type throws ClassCastException</li>
 *     <li>returns 0 on equality</li>
 *     <li>returns less then zero on less then</li>
 *     <li>returns greater then zero on greater</li>
 *     <li>Null values throw a NullPointerException (see below)</li>
 * </ul>
 * The jdk contract does not specify how nulls are handled. The possibilities are nulls constitute an error,
 * they are ordered first, or ordered last. This contract defaults to the error case, but the behavior can
 * be controlled by calling setNulls() with the appropriate value.
 * @since 1.6.5
 */
public abstract class ComparatorContract<T> {
	protected enum Nulls {
		ERROR, NULLS_FIRST, NULLS_LAST;
	}

	private Comparator<T> comparator;
	private T value;
	private T lesserValue;
	private Nulls nulls = Nulls.ERROR;

	protected abstract Comparator<T> getComparator();
	protected abstract T getValue();
	protected abstract T getLesserValue();

	/**
	 * @since 1.6.6
	 * @param nulls How nulls should be treated.
	 */
	public void setNulls(Nulls nulls) {
		this.nulls = nulls;
	}

	@Before
	public void contractSetup() throws Exception {
		comparator = getComparator();
		assertThat(comparator).describedAs("getComparator must return non null value").isNotNull();
		value = getValue();
		lesserValue = getLesserValue();
	}

	@SuppressWarnings("unchecked")
	@Test
	public void shouldThrowExceptionForBadCast() throws Exception {
		try {
			comparator.compare(value, (T)this);
			failBecauseExceptionWasNotThrown(ClassCastException.class);
		} catch (ClassCastException e) {}
	}

	@Test
	public void shouldReturnZeroOnEquality() throws Exception {
		assertThat(comparator.compare(value,value)).isEqualTo(0);
	}

	@Test
	public void shouldReturnNegativeOnLessThan() throws Exception {
		assertThat(comparator.compare(lesserValue, value)).isLessThan(0);
	}

	@Test
	public void shouldReturnPositiveOnGreaterThan() throws Exception {
		assertThat(comparator.compare(value, lesserValue)).isGreaterThan(0);
	}

	/**
	 * @since 1.6.6
	 */
	@Test
	public void shouldThrowExceptionIfNullsError() throws Exception {
		if (nulls != Nulls.ERROR) {
			return;
		}

		try {
			comparator.compare(null, null);
			failBecauseExceptionWasNotThrown(NullPointerException.class);
		} catch (NullPointerException e) {}
	}
}
