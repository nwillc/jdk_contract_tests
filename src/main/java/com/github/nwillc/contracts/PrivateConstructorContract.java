/*
 * Copyright (c) 2014, nwillc@gmail.com
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

import org.fest.assertions.Assertions;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.fest.assertions.api.Fail.fail;

public abstract class PrivateConstructorContract {

    protected abstract Class<?> getUtilityClass();

	@Test
	public void shouldBeFinal() throws Exception {
		Class<?> actual = getUtilityClass();
		assertThat(actual).isNotNull();
		final int modifiers = actual.getModifiers();
		assertThat(Modifier.isFinal(modifiers)).isTrue();
	}

    @Test
    public void shouldHaveOnlyPrivateNoArgConstructor() throws Exception {
        Class<?> actual = getUtilityClass();
		assertThat(actual).isNotNull();

        Constructor<?>[] cons = actual.getDeclaredConstructors();
        Assertions.assertThat(cons.length).isEqualTo(1);
        Assertions.assertThat(cons[0].isAccessible()).isFalse();
        cons[0].setAccessible(true);
        try {
            cons[0].newInstance((Object[])null);
        } catch (Exception e) {
            fail("Could not execute no argument constructor", e);
        }
    }
}