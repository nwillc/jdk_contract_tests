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

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This contract checks for:
 * <ul>
 *     <li>All constructors are private</li>
 * </ul>
 */
public abstract class PrivateConstructorContract implements ClassProvider {

    @Test
    public void shouldHaveOnlyPrivateConstructors() throws Exception {
        Class<?> actual = getClassToTest();
        assertThat(actual).isNotNull();

        Constructor<?>[] cons = actual.getDeclaredConstructors();
        for (Constructor<?> constructor : cons) {
            assertThat(Modifier.isPrivate(constructor.getModifiers())).describedAs("Only private constructor(s)").isTrue();
        }
    }
}
