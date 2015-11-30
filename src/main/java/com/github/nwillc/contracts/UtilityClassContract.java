/*
 * Copyright (c) 2015, nwillc@gmail.com
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

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This contract checks for:
 * <ul>
 *     <li>Private constructors only</li>
 *     <li>Class should be final</li>
 *     <li>All methods declared should be static</li>
 * </ul>
 */
public abstract class UtilityClassContract extends PrivateConstructorContract {

    @Test
    public void shouldBeFinal() throws Exception {
        Class<?> actual = getClassToTest();
        assertThat(actual).isNotNull();
        final int modifiers = actual.getModifiers();
        assertThat(Modifier.isFinal(modifiers)).describedAs("Class should be final").isTrue();
    }

    @Test
    public void shouldHaveOnlyStaticMethods() throws Exception {
        Class<?> actual = getClassToTest();
        assertThat(actual).isNotNull();
        for (Method method : actual.getMethods()) {
            if (method.getDeclaringClass() == actual) {
                assertThat(Modifier.isStatic(method.getModifiers())).describedAs("Method is static: %s", method.getName()).isTrue();
            }
        }
    }
}
