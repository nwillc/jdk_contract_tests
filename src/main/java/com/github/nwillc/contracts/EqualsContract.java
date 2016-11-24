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

import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This contract checks:
 * <ul>
 * <li>That all the members of the Equals list are equal to one another</li>
 * <li>That all the members of the NotEquals list are not equal to the others</li>
 * <li>That hashCodes are also consistent as above.</li>
 * <li>That null is safely viewed as not equals</li>
 * </ul>
 * Depending on how your equals handles subclasses you should include appropriate examples in your lists.
 *
 * <P><b>NB:</b> While hashcode equivalence is supposed to parallel equals results, there is one exception. While equals
 * often considers the instance's classes, hashcode rarely does. So while a subclass with the same values may
 * fail to be "equal" it may still have the same hashcode. This contract therefore only compares hashcodes of the same class.</P>
 *
 * @param <T> type to test on
 * @since 1.6.8
 */
public abstract class EqualsContract<T> {

	/**
	 * List of type T which are all equals based on your implementation of equals. If your
	 * equals allows subclasses, include an example.
	 *
	 * @return List of instances
	 */
	protected abstract List<T> getEquals();

	/**
	 * List of type T which are not equal based on your implementation of equals. If your
	 * equals disallows subclasses, include an subclass.
	 *
	 * @return List of instances
	 */
	protected abstract List<T> getNotEquals();

	@org.junit.jupiter.api.Test
	@Test
	public void testEquals() throws Exception {
		List<T> instances = getEquals();
		assertThat(instances != null).isTrue();
        assertThat(instances.size()).isGreaterThan(0);

		for (T t1 : instances) {
			for (T t2 : instances) {
				assertThat(t1).isEqualTo(t2);
			}
		}
	}

	@org.junit.jupiter.api.Test
	@Test
	public void testNullValue() throws Exception {
		List<T> instances = getEquals();
        assertThat(instances != null).isTrue();
        assertThat(instances.size()).isGreaterThan(0);

		assertThat(instances.get(0).equals(null)).isFalse();
	}

	@org.junit.jupiter.api.Test
	@Test
	public void testNotEquals() throws Exception {
        List<T> instances = getNotEquals();
        assertThat(instances != null).isTrue();
        assertThat(instances.size()).isGreaterThan(0);

		for (T t1 : instances) {
			for (T t2 : instances) {
				if (t1 != t2) {
					assertThat(t1).isNotEqualTo(t2);
				}
			}
		}
	}

	@org.junit.jupiter.api.Test
	@Test
	public void testEqualHashes() throws Exception {
        List<T> instances = getEquals();
        assertThat(instances != null).isTrue();
        assertThat(instances.size()).isGreaterThan(0);

		for (T t1 : instances) {
			for (T t2 : instances) {
				assertThat(t1.hashCode()).isEqualTo(t2.hashCode());
			}
		}
	}


	@org.junit.jupiter.api.Test
	@Test
	public void testNotEqualHashes() throws Exception {
        List<T> instances = getNotEquals();
        assertThat(instances != null).isTrue();
        assertThat(instances.size()).isGreaterThan(0);

		for (T t1 : instances) {
			for (T t2 : instances) {
				if (t1 != t2 && t1.getClass() == t2.getClass()) {
					assertThat(t1.hashCode()).isNotEqualTo(t2.hashCode());
				}
			}
		}
	}
}
