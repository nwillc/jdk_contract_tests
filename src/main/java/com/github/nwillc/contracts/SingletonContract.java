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

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This contract checks for:
 * <ul>
 * <li>Private constructors only</li>
 * <li>Presence of method: public static Class getInstance()</li>
 * </ul>
 */
public abstract class SingletonContract extends PrivateConstructorContract {

	@Test
	public void shouldImplementGetInstance() throws Exception {
		Class<?> actual = getClassToTest();
		assertThat(actual).isNotNull();
		boolean found = false;
		for (Method method : actual.getMethods()) {
			int modifiers = method.getModifiers();
			if (Modifier.isStatic(modifiers)
					&& Modifier.isPublic(modifiers)
					&& method.getName().equals("getInstance")
					&& (method.getParameterTypes().length == 0)
					&& method.getReturnType() == actual) {
				found = true;
				break;
			}
		}
		assertThat(found).describedAs("Has method: public static %s getInstance()", actual.getSimpleName()).isTrue();
	}

}
