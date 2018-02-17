/*
 * Copyright (c) 2016, nwillc@gmail.com
 *
 * Permission to use, copy, modify, and/or distribute this software for any purpose with or without fee is hereby granted,
 * provided that the above copyright notice and this permission notice appear in all copies.
 *
 * THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES WITH REGARD TO THIS SOFTWARE INCLUDING ALL
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY SPECIAL, DIRECT,
 * INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN
 * ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF OR IN CONNECTION WITH THE USE OR PERFORMANCE OF
 * THIS SOFTWARE.
 *
 */

package com.github.nwillc.contracts;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * This contract checks:
 * <ul>
 * <li>compareTo with null argument throws NullPointerException</li>
 * <li>compareTo with argument that can't be cast to correct type throws ClassCastException</li>
 * <li>returns 0 on equality</li>
 * <li>returns less then zero on less then</li>
 * <li>returns greater then zero on greater</li>
 * </ul>
 */
@SuppressWarnings("unchecked")
public abstract class ComparableContract<T extends Comparable> {
	private T value;
	private T equalValue;
	private T lesserValue;

	protected abstract T getValue();

	protected abstract T getEqualToValue();

	protected abstract T getLessThanValue();

	@BeforeEach
	@Before
	public void contractSetup() throws Exception {
		value = getValue();
		assertThat(value).isNotNull();
		equalValue = getEqualToValue();
		assertThat(equalValue).isNotNull();
		lesserValue = getLessThanValue();
		assertThat(lesserValue).isNotNull();
	}

	@org.junit.jupiter.api.Test
	@Test
	public void shouldThrowExceptionForNull() throws Exception {

		assertThatThrownBy(() -> value.compareTo(null)).isInstanceOf(NullPointerException.class);
	}

	@SuppressWarnings("unchecked")
	@org.junit.jupiter.api.Test
	@Test
	public void shouldThrowExceptionForBadCast() throws Exception {
		assertThatThrownBy(() -> value.compareTo(this)).isInstanceOf(ClassCastException.class);
	}

	@org.junit.jupiter.api.Test
	@Test
	public void shouldReturnZeroOnEquality() throws Exception {
		assertThat(value.compareTo(equalValue)).isEqualTo(0);
	}

	@org.junit.jupiter.api.Test
	@Test
	public void shouldReturnNegativeOnLessThan() throws Exception {
		assertThat(lesserValue.compareTo(value)).isLessThan(0);
	}

	@org.junit.jupiter.api.Test
	@Test
	public void shouldReturnPositiveOnGreaterThan() throws Exception {
		assertThat(value.compareTo(lesserValue)).isGreaterThan(0);
	}
}
