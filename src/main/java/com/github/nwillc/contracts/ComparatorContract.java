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

import org.junit.Test;
import org.junit.Before;

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
 * </ul>
 * No assumptions about null values are made, the jdk contract does not specify any.
 * @since 1.6.5
 */
public abstract class ComparatorContract<T> {
	private Comparator<T> comparator;
	private T value;
	private T lesserValue;

	protected abstract Comparator<T> getComparator();
	protected abstract T getValue();
	protected abstract T getLesserValue();

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
}
