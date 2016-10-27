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

import java.lang.reflect.Constructor;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This contract checks for:
 * <ul>
 * <li>Presence of a no argument constructor</li>
 * </ul>
 */
public abstract class NoArgConstructorContract implements ClassProvider {

	@org.junit.jupiter.api.Test
	@Test
	public void shouldHaveNoArgConstructor() throws Exception {
		Class<?> actual = getClassToTest();
		assertThat(actual).isNotNull();

		Constructor<?>[] cons = actual.getDeclaredConstructors();
		assertThat(cons.length).describedAs("Only single constructor").isEqualTo(1);
		boolean found = false;
		for (Constructor constructor : cons) {
			if (constructor.getParameterTypes().length == 0) {
				found = true;
				break;
			}
		}
		assertThat(found).describedAs("No arg constructor").isTrue();
	}
}
