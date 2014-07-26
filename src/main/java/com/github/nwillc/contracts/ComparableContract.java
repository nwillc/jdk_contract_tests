/*
 * Copyright (c) 2013-2014, nwillc@gmail.com
 *
 * Permission to use, copy, modify, and/or distribute this software for any purpose with or
 * without fee is hereby granted, provided that the above copyright notice and this permission
 * notice appear in all copies.
 *
 * THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES WITH REGARD TO
 * THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS. IN NO EVENT
 * SHALL THE AUTHOR BE LIABLE FOR ANY SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR
 * ANY DAMAGES WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN ACTION OF
 * CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF OR IN CONNECTION WITH THE USE
 * OR PERFORMANCE OF THIS SOFTWARE.
 */

package com.github.nwillc.contracts;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Fail.failBecauseExceptionWasNotThrown;

@SuppressWarnings("unchecked")
public abstract class ComparableContract {

	protected abstract Comparable getValue();
	protected abstract Comparable getEqualToValue();
	protected abstract Comparable getLessThanValue();
	protected abstract Comparable getGreaterThanValue();

	@Test
	public void shouldThrowExceptionForNull() throws Exception {
		final Comparable value = getValue();
		assertThat(value).isNotNull();
		try {
			value.compareTo(null);
			failBecauseExceptionWasNotThrown(NullPointerException.class);
		} catch (NullPointerException e) {}
	}

	@Test
	public void shouldThrowExceptionForBadCast() throws Exception {
		final Comparable value = getValue();
		assertThat(value).isNotNull();
		try {
			value.compareTo(new Object());
			failBecauseExceptionWasNotThrown(ClassCastException.class);
		} catch (ClassCastException e) {}
	}

	@Test
	public void shouldReturnZeroOnEquality() throws Exception {
		final Comparable value = getValue();
		final Comparable equal = getEqualToValue();

		assertThat(value).isNotNull();
		assertThat(equal).isNotNull();
		assertThat(value.compareTo(equal)).isEqualTo(0);
	}

	@Test
	public void shouldReturnNegativeOnLessThan() throws Exception {
		final Comparable value = getValue();
		final Comparable lessThan = getGreaterThanValue();

		assertThat(value).isNotNull();
		assertThat(lessThan).isNotNull();
		assertThat(value.compareTo(lessThan)).isLessThan(0);
	}

	@Test
	public void shouldReturnPositiveOnGreaterThan() throws Exception {
		final Comparable value = getValue();
		final Comparable greaterThan = getLessThanValue();

		assertThat(value).isNotNull();
		assertThat(greaterThan).isNotNull();
		assertThat(value.compareTo(greaterThan)).isGreaterThan(0);
	}
}
