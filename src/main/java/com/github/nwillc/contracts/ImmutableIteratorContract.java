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

import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * This contract checks for:
 * <ul>
 * <li>The iterator's remove throws UnsupportedOperationException</li>
 * </ul>
 */
public abstract class ImmutableIteratorContract extends IteratorContract {
	private static final Logger LOGGER = Logger.getLogger(ImmutableIteratorContract.class.getName());

	@Test
	public void shouldNotImplementRemove() throws Exception {
		Iterator iterator = getNonEmptyIterator();
		assertThat(iterator).isNotNull();
		assertThatThrownBy(() -> iterator.remove()).isInstanceOf(UnsupportedOperationException.class);
	}
}
